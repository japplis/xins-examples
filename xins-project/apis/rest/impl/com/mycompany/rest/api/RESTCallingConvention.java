/*
 * $Id: RESTCallingConvention.java,v 1.6 2012/09/15 08:04:37 agoubard Exp $
 */
package com.mycompany.rest.api;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xins.common.MandatoryArgumentChecker;
import org.xins.common.Utils;
import org.xins.common.spec.EntityNotFoundException;
import org.xins.common.spec.InvalidSpecificationException;

import org.xins.server.API;
import org.xins.server.CallResultOutputter;
import org.xins.server.CustomCallingConvention;
import org.xins.server.FunctionNotSpecifiedException;
import org.xins.server.FunctionRequest;
import org.xins.server.FunctionResult;
import org.xins.server.InvalidRequestException;

/**
 * The REST calling convention as describe at http://en.wikipedia.org/wiki/REST.
 *
 * @version $Revision: 1.6 $ $Date: 2012/09/15 08:04:37 $
 * @author <a href="mailto:anthony.goubard@japplis.com">Anthony Goubard</a>
 */
public class RESTCallingConvention extends CustomCallingConvention {

   /**
    * The response encoding format.
    */
   static final String RESPONSE_ENCODING = "UTF-8";

   /**
    * The content type of the HTTP response.
    */
   static final String RESPONSE_CONTENT_TYPE = "text/xml; charset=" + RESPONSE_ENCODING;

   /**
    * The API using this calling convention.
    */
   private final API _api;

   /**
    * Constructs a new <code>addUserImpl</code> instance.
    *
    * @param api
    *    the API, using this calling convention, cannot be <code>null</code>.
    *
    * @throws IllegalArgumentException
    *    if <code>api == null</code>.
    */
   public RESTCallingConvention(API api) throws IllegalArgumentException {
      MandatoryArgumentChecker.check("api", api);
      _api = api;
   }

   @Override
   protected String[] getSupportedMethods() {
      return new String[] { "GET", "POST", "PUT", "DELETE" };
   }


   @Override
   protected boolean matches(HttpServletRequest httpRequest)
   throws Exception {

      return httpRequest.getQueryString() == null;
   }

   @Override
   protected FunctionRequest convertRequestImpl(HttpServletRequest httpRequest)
   throws InvalidRequestException, FunctionNotSpecifiedException {
      String restMethod = "";
      String httpMethod = httpRequest.getMethod().toLowerCase();
      if (httpMethod.equals("post")) {
         restMethod = "Update";
      } else if (httpMethod.equals("put")) {
         restMethod = "Add";
      } else if (httpMethod.equals("get")) {
         restMethod = "Get";
      } else if (httpMethod.equals("delete")) {
         restMethod = "Delete";
      } else {
         throw Utils.logProgrammingError("Unauthorized method: " + httpMethod);
      }
      String path = httpRequest.getContextPath();

      // Get the first path to know the resource wanted
      String resourceName = "";
      StringTokenizer stPaths = new StringTokenizer(path, "/");
      if (stPaths.hasMoreTokens()) {
         resourceName = stPaths.nextToken();
      }
      String functionName = restMethod + resourceName.substring(0, 1).toUpperCase() +
            resourceName.substring(1);
      if (functionName.endsWith("s")) {
         functionName = functionName.substring(0, functionName.length() - 1);
      }

      // Get the arguments of the functions
      Map<String, String> params = new HashMap<String, String>();
      try {
         Iterator itInputParameters = _api.getAPISpecification().getFunction(functionName).getInputParameters().keySet().iterator();
         while (itInputParameters.hasNext() && stPaths.hasMoreTokens()) {
            String nextParamName = (String) itInputParameters.next();
            String nextArg = stPaths.nextToken();
            params.put(nextParamName, nextArg);
         }
      } catch (InvalidSpecificationException isex) {
         Utils.logIgnoredException(isex);
      } catch (EntityNotFoundException enfex) {
         throw new FunctionNotSpecifiedException();
      }
      return new FunctionRequest(functionName, params, null);
   }

   @Override
   protected void convertResultImpl(FunctionResult      xinsResult,
                                    HttpServletResponse httpResponse,
                                    Map<String, Object> backack)
   throws IOException {

      // Set the status code and the content type
      httpResponse.setStatus(HttpServletResponse.SC_OK);
      httpResponse.setContentType(RESPONSE_CONTENT_TYPE);

      Writer out = httpResponse.getWriter();
      CallResultOutputter.output(out, xinsResult);
      out.close();
   }
}
