/*
 * $Id: DataSectionImpl.java,v 1.9 2007/09/18 11:27:12 agoubard Exp $
 */
package com.mycompany.allinone.api;

/**
 * Implementation of the <code>DataSection</code> function.
 *
 * @version $Revision: 1.9 $ $Date: 2007/09/18 11:27:12 $
 * @author <a href="mailto:anthony.goubard@japplis.com">Anthony Goubard</a>
 */
public class DataSectionImpl extends DataSection  {

   /**
    * Constructs a new <code>DataSectionImpl</code> instance.
    *
    * @param api
    *    the API to which this function belongs, guaranteed to be not
    *    <code>null</code>.
    */
   public DataSectionImpl(APIImpl api) {
      super(api);
   }

   public final Result call(Request request) throws Throwable {
      SuccessfulResult result = new SuccessfulResult();

      // Always add the superuser
      User su = new User();
      su.setName("superuser");
      su.setAddress("12 Madison Avenue");
      su.pcdata("This user has the root authorisation.");
      result.addUser(su);

      if (request.isSetInputText()) {
         User user = new User();
         user.setName(request.getInputText());
         user.setAddress("Unknown");
         result.addUser(user);
      }
      return result;
   }
}
