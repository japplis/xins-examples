/*
 * $Id: GetUserImpl.java,v 1.3 2007/09/18 11:27:16 agoubard Exp $
 */
package com.mycompany.rest.api;


/**
 * Implementation of the <code>GetUser</code> function.
 *
 * <p>Description: Get the information related to a new user.
 *
 * @version $Revision: 1.3 $ $Date: 2007/09/18 11:27:16 $
 * @author <a href="mailto:anthony.goubard@japplis.com">Anthony Goubard</a>
 */
public final class GetUserImpl extends GetUser {

   /**
    * Constructs a new <code>GetUserImpl</code> instance.
    *
    * @param api
    *    the API to which this function belongs, guaranteed to be not
    *    <code>null</code>.
    */
   public GetUserImpl(APIImpl api) {
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
      SuccessfulResult result = new SuccessfulResult();
      String[] name = _users.getUser(request.getAccountNumber());
      if (name == null) {
         return new AccountNotFoundResult();
      }
      result.setFirstName(name[0]);
      result.setLastName(name[1]);
      return result;
   }
}
