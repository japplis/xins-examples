/*
 * $Id: LoginOkayImpl.java,v 1.8 2007/03/12 10:46:15 agoubard Exp $
 */
package com.mycompany.petstore.api;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

/**
 * Implementation of the <code>LoginOkay</code> function.
 *
 * @version $Revision: 1.8 $ $Date: 2007/03/12 10:46:15 $
 * @author TODO
 */
public final class LoginOkayImpl extends LoginOkay {

   /**
    * Constructs a new <code>LoginOkayImpl</code> instance.
    *
    * @param api
    *    the API to which this function belongs, guaranteed to be not
    *    <code>null</code>.
    */
   public LoginOkayImpl(APIImpl api) {
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
         ResultSet rsLogin = statement.executeQuery("SELECT password FROM CUSTOMERS WHERE email='" + request.getEmail()+"'");
         if (rsLogin.next()) {
            String databasePassword = rsLogin.getString(1).trim();
            if (!request.getPassword().equals(databasePassword)) {
               return new IncorrectPasswordResult();
            }
         } else {
            return new UnknownLoginResult();
         }
      } catch (SQLException sqlEx) {
          DatabaseFailureResult databaseFailure = new DatabaseFailureResult();
          databaseFailure.setDetails(sqlEx.getMessage());
          return databaseFailure;
      }

      // The customer successfully logged in.
      _sessionManager.removeProperty("password");
      _sessionManager.setProperty(_sessionManager.getSessionId(), Boolean.TRUE);
      SuccessfulResult result = new SuccessfulResult();
      return result;
   }
}
