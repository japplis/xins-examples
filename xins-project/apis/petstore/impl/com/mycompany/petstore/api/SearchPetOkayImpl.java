/*
 * $Id: SearchPetOkayImpl.java,v 1.5 2007/03/12 10:46:15 agoubard Exp $
 */
package com.mycompany.petstore.api;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Implementation of the <code>SearchPetOkay</code> function.
 *
 * <p>Description: Get the list of pets starting with the typed letters.
 *
 * @version $Revision: 1.5 $ $Date: 2007/03/12 10:46:15 $
 * @author John Doe (<a href="mailto:john.doe@mycompany.com">john.doe@mycompany.com</a>)
 */
public final class SearchPetOkayImpl extends SearchPetOkay {

   /**
    * Constructs a new <code>SearchPetOkayImpl</code> instance.
    *
    *
    * @param api
    *    the API to which this function belongs, guaranteed to be not
    *    <code>null</code>.
    */
   public SearchPetOkayImpl(APIImpl api) {
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

      /* This function can be accessed with the _xins-std calling convention.
      if (!_sessionManager.getBoolProperty(_sessionManager.getSessionId())) {
         return new NotLoggedInResult();
      }*/

      SuccessfulResult result = new SuccessfulResult();
      try {
         Statement statement = _databaseConnection.getConnection().createStatement();
         String query = "SELECT id, petname, price, age FROM PETS";
         if (request.isSetPetName()) {
            query += " WHERE petname LIKE '" + request.getPetName() + "%'";
         }
         ResultSet rsPets = statement.executeQuery(query);
         while (rsPets.next()) {
            Pet pet = new Pet();
            pet.setPetID(Integer.parseInt(rsPets.getString(1)));
            pet.setPetName(rsPets.getString(2).trim());
            pet.setPrice(rsPets.getFloat(3));
            pet.setAge(Byte.parseByte(rsPets.getString(4)));
            result.addPet(pet);
         }
         rsPets.close();
      } catch (SQLException sqlEx) {
         sqlEx.printStackTrace();
         return new DatabaseFailureResult();
      }
      return result;
   }
}
