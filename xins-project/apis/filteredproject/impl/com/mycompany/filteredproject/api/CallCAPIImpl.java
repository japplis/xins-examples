/*
 * $Id: CallCAPIImpl.java,v 1.8 2012/06/09 12:28:32 agoubard Exp $
 */
package com.mycompany.filteredproject.api;

import com.mycompany.myproject.capi.CAPI;
import com.mycompany.myproject.types.Gender;
import java.util.Map;

import org.xins.client.UnsuccessfulXINSCallException;

import org.xins.common.collections.MissingRequiredPropertyException;
import org.xins.common.collections.InvalidPropertyValueException;
import org.xins.common.manageable.InitializationException;
import org.xins.common.service.Descriptor;
import org.xins.common.service.DescriptorBuilder;

/**
 * Implementation of the <code>CallCAPI</code> function.
 *
 * @version $Revision: 1.8 $ $Date: 2012/06/09 12:28:32 $
 * @author <a href="mailto:anthony.goubard@japplis.com">Anthony Goubard</a>
 */
public class CallCAPIImpl extends CallCAPI  {

   /**
    * The client-side interface to call the my-project API.
    */
   private CAPI _myProject;

   /**
    * Constructs a new <code>CallCAPIImpl</code> instance.
    *
    * @param api
    *    the API to which this function belongs, guaranteed to be not
    *    <code>null</code>.
    */
   public CallCAPIImpl(APIImpl api) {
      super(api);
   }

   protected void initImpl(Map<String, String> properties)
   throws MissingRequiredPropertyException,
          InvalidPropertyValueException,
          InitializationException {

      // This method is also invoked when the configuration file has changed
      // Get the reference to the my-project API instance(s)
      Descriptor descriptor = DescriptorBuilder.build(properties, "capis.myproject");
      _myProject = new CAPI(descriptor);
   }

   protected void deinitImpl() {

      // This method is invoked only when the application stops
      _myProject = null;
   }

   public final Result call(Request request) throws Throwable {

      // Call the my-project API
      try {
         String sentence = _myProject.callMyFunction(Gender.MALE, "Doe").getMessage();

         SuccessfulResult result = new SuccessfulResult();
         result.setSentence(sentence);
         return result;

      // Catch unsuccessful result
      } catch (UnsuccessfulXINSCallException exception) {
         String code = exception.getErrorCode();

         // Create a new exception
         throw new Exception(code + ": " + exception.getMessage());
      }

      // All other CallException thrown which are not UnsuccessfulCallException
      // are forwarded
   }
}
