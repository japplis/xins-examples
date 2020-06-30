/*
 * $Id: MyFunctionImpl.java,v 1.7 2007/09/18 11:27:13 agoubard Exp $
 */
package com.mycompany.myproject.api;

/**
 * Implementation of the <code>MyFunction</code> function.
 *
 * @version $Revision: 1.7 $ $Date: 2007/09/18 11:27:13 $
 * @author <a href="mailto:anthony.goubard@japplis.com">Anthony Goubard</a>
 */
public class MyFunctionImpl extends MyFunction  {

   /**
    * Constructs a new <code>MyFunctionImpl</code> instance.
    *
    * @param api
    *    the API to which this function belongs, guaranteed to be not
    *    <code>null</code>.
    */
   public MyFunctionImpl(APIImpl api) {
      super(api);
   }

   public final Result call(Request request) throws Throwable {
      String lastNameLower = request.getPersonLastName().toLowerCase();
      if (lastNameLower.indexOf('a') == -1 && lastNameLower.indexOf('e') == -1 &&
          lastNameLower.indexOf('i') == -1 && lastNameLower.indexOf('o') == -1 &&
          lastNameLower.indexOf('u') == -1 && lastNameLower.indexOf('y') == -1) {
         return new NoVowelResult();
      }
      String salutation = null;
      if (request.getGender().equals(com.mycompany.myproject.types.Gender.MALE)) {
         salutation = "Mister";
      } else {
         salutation = "Miss";
      }
      SuccessfulResult result = new SuccessfulResult();
      result.setMessage("Hello " + salutation + " " +request.getPersonLastName());
      return result;
   }
}
