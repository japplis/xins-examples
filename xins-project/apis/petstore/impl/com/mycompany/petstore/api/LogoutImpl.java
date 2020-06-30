/*
 * $Id: LogoutImpl.java,v 1.5 2007/03/12 10:46:15 agoubard Exp $
 */
package com.mycompany.petstore.api;

/**
 * Implementation of the <code>Logout</code> function.
 *
 * @version $Revision: 1.5 $ $Date: 2007/03/12 10:46:15 $
 * @author TODO
 */
public final class LogoutImpl extends Logout {

   /**
    * Constructs a new <code>LogoutImpl</code> instance.
    *
    * @param api
    *    the API to which this function belongs, guaranteed to be not
    *    <code>null</code>.
    */
   public LogoutImpl(APIImpl api) {
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
      String customerLogin = (String) _sessionManager.getProperty("email.login");
      _sessionManager.removeProperties();
      _sessionManager.setProperty(_sessionManager.getSessionId(), Boolean.FALSE);
      SuccessfulResult result = new SuccessfulResult();
      return result;
   }
}
