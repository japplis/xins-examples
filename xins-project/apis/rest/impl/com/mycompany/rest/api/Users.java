/*
 * $Id: Users.java,v 1.5 2007/09/18 11:27:16 agoubard Exp $
 */
package com.mycompany.rest.api;

import java.util.Map;
import java.util.TreeMap;
import org.xins.server.API;

import org.xins.common.manageable.Manageable;

/**
 * Common object used to manage the users.
 *
 * @version $Revision: 1.5 $ $Date: 2007/09/18 11:27:16 $
 * @author <a href="mailto:anthony.goubard@japplis.com">Anthony Goubard</a>
 */
public class Users extends Manageable {

   /**
    * The users are stored in a map where the key is the account number and the
    * value a String[] with the first name and the surname.
    */
   private Map _usersMap = new TreeMap();

   /**
    * The last account number created.
    */
   private int _lastAccountNumber = 1000;

   /**
    * Constructs a new <code>Users</code> instance.
    *
    * @param api
    *    the API to which this function belongs, guaranteed to be not
    *    <code>null</code>.
    */
   public Users(API api) {
   }

   /**
    * Creates a new user.
    *
    * @param firstName
    *    the first name of the user.
    * @param surname
    *    the surname of the user.
    *
    * @return
    *    the account number associated with this user.
    */
   public final synchronized int addUser(String firstName, String surname) {
      String[] name = { firstName, surname };
      _lastAccountNumber++;
      _usersMap.put(new Integer(_lastAccountNumber), name);
      return _lastAccountNumber;
   }

   /**
    * Updates the information of a user.
    *
    * @param accountNumber
    *    the account number of the user.
    * @param firstName
    *    the first name of the user.
    * @param surname
    *    the surname of the user.
    *
    * @return
    *    true if the user has been found and has been updated, false otherwise.
    */
   public final synchronized boolean updateUser(int accountNumber, String firstName, String surname) {
      if (!_usersMap.containsKey(new Integer(accountNumber))) {
         return false;
      }
      String[] name = { firstName, surname };
      _usersMap.put(new Integer(accountNumber), name);
      return true;
   }

   /**
    * Deletes the information of a user.
    *
    * @param accountNumber
    *    the account number of the user.
    *
    * @return
    *    true if the user has been found and has been deleted, false otherwise.
    */
   public final synchronized boolean deleteUser(int accountNumber) {
      if (!_usersMap.containsKey(new Integer(accountNumber))) {
         return false;
      }
      _usersMap.remove(new Integer(accountNumber));
      return true;
   }

   /**
    * Update the information of a user.
    *
    * @param accountNumber
    *    the account number of the user.
    *
    * @return
    *    a String[] containing the first name and the surname of the user or
    *    <code>null</code> if the user cannot be found.
    */
   public final String[] getUser(int accountNumber) {
      return (String[]) _usersMap.get(new Integer(accountNumber));
   }
}
