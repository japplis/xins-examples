/*
 * $Id: UploadFileImpl.java,v 1.3 2007/03/12 10:46:14 agoubard Exp $
 */
package com.mycompany.fileupload.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Random;

/**
 * Implementation of the <code>UploadFile</code> function.
 *
 * <p>Description: Upload a file.
 *
 * @version $Revision: 1.3 $ $Date: 2007/03/12 10:46:14 $
 * @author TODO
 */
public final class UploadFileImpl extends UploadFile {

   /**
    * Constructs a new <code>UploadFileImpl</code> instance.
    *
    * @param api
    *    the API to which this function belongs, guaranteed to be not
    *    <code>null</code>.
    */
   public UploadFileImpl(APIImpl api) {
      super(api);
   }

   /**
    * Calls this function. If the function fails, it may throw any kind of
    * exception. All exceptions will be handled by the caller.
    *
    * @param request
    *    the request, never <code>null</code>.
    *
    * @return
    *    the result of the function call, should never be <code>null</code>.
    *
    * @throws Throwable
    *    if anything went wrong.
    */
   public Result call(Request request) throws Throwable {

      String inputFileName = request.getDocumentName();
      byte[] fileContent = request.getDocumentContent();

      String destinationDir = ((RuntimeProperties) getAPI().getProperties()).getFileuploadDirectory();
      String outputFileName = getDestinationFile(request.getDocumentName());
      File outputFile = new File(destinationDir, outputFileName);

      FileOutputStream fos = new FileOutputStream(outputFile);
      fos.write(fileContent);
      fos.close();

      File descriptionFile = new File(destinationDir, outputFileName + ".desc");
      FileWriter descriptionWriter = new FileWriter(descriptionFile);
      descriptionWriter.write("Owner: " + request.getOwner() + "\n");
      descriptionWriter.write("Length: " + fileContent.length + "\n");
      descriptionWriter.write("Description: " + request.getDescription() + "\n");
      if (request.isSetKeywords()) {
         descriptionWriter.write("Keywords: " + request.getKeywords() + "\n");
      }
      descriptionWriter.close();

      SuccessfulResult result = new SuccessfulResult();
      return result;
   }

   /**
    * Gets the destination for the uploaded file.
    *
    * @param inputFileName
    *    the original location from the client.
    *
    * @return
    *    the name of the file to store the uploaded file.
    */
   protected String getDestinationFile(String inputFileName) {

      String outputFileName = null;
      // The client may have sent a file from another OS
      if (inputFileName.lastIndexOf('/') != -1) {
         outputFileName = inputFileName.substring(inputFileName.lastIndexOf('/') + 1);
      } else if (inputFileName.lastIndexOf('\\') != -1) {
         outputFileName = inputFileName.substring(inputFileName.lastIndexOf('\\') + 1);
      } else {
         outputFileName = inputFileName;
      }

      File outputFile = new File(outputFileName);
      String newOutputFileName = outputFileName;
      int counter = 0;
      while (outputFile.exists()) {
         counter++;
         newOutputFileName = outputFileName + "." + counter;
         outputFile = new File(newOutputFileName);
      }
      return newOutputFileName;
   }
}
