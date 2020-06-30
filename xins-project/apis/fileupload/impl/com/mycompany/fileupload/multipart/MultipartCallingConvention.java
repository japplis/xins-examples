/*
 * $Id: MultipartCallingConvention.java,v 1.10 2012/09/15 08:04:44 agoubard Exp $
 */
package com.mycompany.fileupload.multipart;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.w3c.dom.Element;

import org.xins.common.types.standard.Hex;
import org.xins.common.xml.ElementFormatter;

import org.xins.server.CallResultOutputter;
import org.xins.server.CustomCallingConvention;
import org.xins.server.FunctionNotSpecifiedException;
import org.xins.server.FunctionRequest;
import org.xins.server.FunctionResult;
import org.xins.server.InvalidRequestException;
import org.xml.sax.SAXException;

/**
 * Calling convention that supports RFC 1867 multipart content. This content
 * type supports uploading of content items (typically: files.)
 *
 * <p>For an example of the HTML form, look at the upload.html file located in
 * the demo\xins-project\apis\fileupload directory.
 *
 * <p>For each defined input of type file, you must define two input parameters:
 * &lt;input field name&gt;Name of type _text for the name of the file and
 * &lt;input field name&gt;Content of type _hex for the content of the file.
 *
 * <p>Uploaded items will be retained in memory as long as they are reasonably
 * small. Larger items will be written to a temporary file on disk. Very large
 * upload requests are not permitted.
 *
 * @version $Revision: 1.10 $ $Date: 2012/09/15 08:04:44 $
 * @author <a href="mailto:ernst@ernstdehaan.com">Ernst de Haan</a>
 * @author <a href="mailto:anthony.goubard@japplis.com">Anthony Goubard</a>
 */
public class MultipartCallingConvention extends CustomCallingConvention {

   /**
    * Constructs a new <code>MultipartCallingConvention</code>.
    */
   public MultipartCallingConvention() {
      // empty
   }

    @Override
   protected boolean matches(HttpServletRequest httpRequest)
   throws Exception {

      // Requirement 1: The request must be multi-part
      if (!httpRequest.getContentType().startsWith(FileUpload.MULTIPART_FORM_DATA)) {
         return false;
      } else {
         return true;
      }
   }

    @Override
   protected FunctionRequest convertRequestImpl(HttpServletRequest httpRequest)
   throws InvalidRequestException,
          FunctionNotSpecifiedException {

      // The request must be multi-part
      if (!httpRequest.getContentType().startsWith(FileUpload.MULTIPART_FORM_DATA)) {
         throw new InvalidRequestException("Request is not multi-part.");
      }

      // Create a factory for disk-based file items
      FileItemFactory factory = new DiskFileItemFactory();

      // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);

      // Parse the request (list contains FileItem instances)
      List itemList;
      try {
         itemList = upload.parseRequest(httpRequest);
      } catch (FileUploadException exception) {
         throw new InvalidRequestException("Failed to parse request.", exception);
      }

      // Convert the list to a Map instance
      Map<String, String> params = new HashMap<String, String>();
      for (int i = 0; i < itemList.size(); i++) {
         FileItem item = (FileItem) itemList.get(i);
         String name = item.getFieldName();
         if (item.isFormField()) {
            String value = item.getString();
            params.put(name, value);
         } else {
            String fileName = item.getName();
            params.put(name + "Name", fileName);
            try {
               InputStream inputContent = item.getInputStream();
               ByteArrayOutputStream baos = new ByteArrayOutputStream();
               int availableBytes = inputContent.available();
               while (availableBytes > 0) {
                  byte[] buffer = new byte[availableBytes];
                  inputContent.read(buffer);
                  baos.write(buffer);
                  availableBytes = inputContent.available();
               }
               byte[] fileContent = baos.toByteArray();
               inputContent.close();
               baos.close();
               params.put(name + "Content", Hex.toString(fileContent));
            } catch (IOException ioe) {
               throw new InvalidRequestException("Failed to read the input file.", ioe);
            }
         }
      }

      // Determine the function name
      String function = params.get("_function");
      if (function == null) {
         throw new FunctionNotSpecifiedException();
      }

      // Get data section
      String dataSectionValue = params.get("_data");
      Element dataElement = null;
      if (dataSectionValue != null && dataSectionValue.length() > 0) {

         // Parse the data section
         StringReader reader = new StringReader(dataSectionValue);
         try {
            dataElement = ElementFormatter.parse(reader);

         // Parsing error
         } catch (SAXException exception) {
            String detail = "Cannot parse the data section.";
            throw new InvalidRequestException(detail, exception);
         }
      }

      // Construct and return the request object
      return new FunctionRequest(function, params, dataElement);
   }

    @Override
   protected void convertResultImpl(FunctionResult      xinsResult,
                                    HttpServletResponse httpResponse,
                                    Map<String, Object> backpack)
   throws IOException {

      // Send the XML output to the stream and flush
      httpResponse.setContentType("text/xml; charset=UTF-8");
      PrintWriter out = httpResponse.getWriter();
      httpResponse.setStatus(HttpServletResponse.SC_OK);
      CallResultOutputter.output(out, xinsResult);
      out.close();
   }
}
