/*
 * $Id: OrderPetImpl.java,v 1.9 2007/03/12 10:46:15 agoubard Exp $
 */
package com.mycompany.petstore.api;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Implementation of the <code>OrderPet</code> function.
 *
 * <p>Description: Order a pet.
 *
 * @version $Revision: 1.9 $ $Date: 2007/03/12 10:46:15 $
 * @author John Doe (<a href="mailto:john.doe@mycompany.com">john.doe@mycompany.com</a>)
 */
public final class OrderPetImpl extends OrderPet {

   /**
    * Constructs a new <code>OrderPetImpl</code> instance.
    *
    * @param api
    *    the API to which this function belongs, guaranteed to be not
    *    <code>null</code>.
    */
   public OrderPetImpl(APIImpl api) {
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
      if (!_sessionManager.getBoolProperty(_sessionManager.getSessionId())) {
         return new NotLoggedInResult();
      }
      int requestedQuantity = 1;
      if (request.isSetQuantity()) {
         requestedQuantity = request.getQuantity().intValue();
      }

      try {
         Statement statement = _databaseConnection.getConnection().createStatement();
         ResultSet quantityQuery = statement.executeQuery("SELECT quantity FROM PETS WHERE id=" + request.getPetID());
         int stock = 0;
         if (quantityQuery.next()) {
            stock = quantityQuery.getInt(1);
         }
         if (stock <= 0) {
            return new ProductNotAvailableResult();
         }

         // Note that the quantity is not passed as input parameter.
         // The user can only order one pet.
         statement.executeUpdate("INSERT INTO ORDERS (petid, email, quantity, status) VALUES (" +
               request.getPetID() + ", '" +
               _sessionManager.getProperty("email") + "', " + requestedQuantity + ", 'ordered')");

         statement.executeUpdate("UPDATE PETS SET quantity=" + (stock - requestedQuantity) +
               " WHERE id=" +
               request.getPetID());
      } catch (SQLException sqlEx) {
         return new DatabaseFailureResult();
      }

      SuccessfulResult result = new SuccessfulResult();
      return result;
   }
}
