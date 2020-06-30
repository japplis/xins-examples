package com.mycompany.example1;

import org.xins.common.service.TargetDescriptor;
import org.xins.common.http.HTTPMethod;

import com.mycompany.myproject.capi.CAPI;
import com.mycompany.myproject.types.Gender;

/**
 * Simple program that invoke MyFunction
 */
public class TestMyFunction {

   /**
    * Invokes MyFunction
    *
    * @param args
    *    optional parameters: args[0] is the URL, args[1] is the gender(m/f) and
    *    args[2] is the last name.
    */
   public final static void main(String[] args) throws Exception {

      String targetURL = "http://127.0.0.1:8080/myproject/";
      if (args.length > 0) {
         targetURL = args[0];
      }

      Gender.Item gender = Gender.MALE;
      if (args.length > 1) {
         gender = Gender.getItemByName(args[1]);
      }

      String lastName = "Lee";
      if (args.length > 2) {
         lastName = args[2];
      }

      // Create the descriptor for the service with a time-out of 20 seconds
      TargetDescriptor descriptor =
         new TargetDescriptor(targetURL, 20000);

      // Create the CAPI instance
      CAPI project = new CAPI(descriptor);

      // Invoke the function
      String message = project.callMyFunction(gender, lastName).getMessage();

      // No exceptions thown
      System.out.println("Call successful. Message: " + message);
   }
}