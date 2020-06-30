/*
 * $Id: RegisterCustomerOkayImpl.java,v 1.8 2007/03/12 10:46:15 agoubard Exp $
 */
package com.mycompany.petstore.api;

import com.mycompany.petstore.types.Salutation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Implementation of the <code>RegisterCustomer</code> function.
 *
 * <p>Description: Register a new client.
 *
 * @version $Revision: 1.8 $ $Date: 2007/03/12 10:46:15 $
 * @author John Doe (<a href="mailto:john.doe@mycompany.com">john.doe@mycompany.com</a>)
 */
public final class RegisterCustomerOkayImpl extends RegisterCustomerOkay {

   /**
    * Constructs a new <code>RegisterCustomerOkayImpl</code> instance.
    *
    * @param api
    *    the API to which this function belongs, guaranteed to be not
    *    <code>null</code>.
    */
   public RegisterCustomerOkayImpl(APIImpl api) {
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
      try {
         Statement statement = _databaseConnection.getConnection().createStatement();
         ResultSet rsLogin = statement.executeQuery("SELECT password FROM CUSTOMERS WHERE email='" + request.getEmail() + "'");
         if (rsLogin.next()) {
            return new AlreadyRegisteredResult();
         }
      } catch (SQLException sqlEx) {
         return new DatabaseFailureResult();
      }
      try {
         Statement statement = _databaseConnection.getConnection().createStatement();
         statement.executeUpdate("INSERT INTO CUSTOMERS (email, password, gender, firstname, lastname, address, phonenumber) " +
               "VALUES ('" + request.getEmail() + "', '" +
               request.getPassword() + "', '" +
               (request.getSalutation() == Salutation.MALE ? "M" : "F") + "', '" +
               request.getFirstName() + "', '" +
               request.getLastName() + "', '" +
               request.getAddress() + "', '" +
               (request.isSetPhoneNumber() ? request.getPhoneNumber() : "") +
               "')");
      } catch (SQLException sqlEx) {
         return new DatabaseFailureResult();
      }
      SuccessfulResult result = new SuccessfulResult();
      return result;
   }
}
