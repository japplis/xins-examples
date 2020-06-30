/*
 * $Id: DatabaseConnection.java,v 1.16 2012/06/09 12:28:32 agoubard Exp $
 *
 * Copyright 2003-2008 Online Breedband B.V.
 * See the COPYRIGHT file for redistribution and use restrictions.
 */
package com.mycompany.petstore.api;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import org.xins.common.collections.InvalidPropertyValueException;
import org.xins.common.collections.MissingRequiredPropertyException;
import org.xins.common.manageable.InitializationException;
import org.xins.common.manageable.Manageable;

/**
 * Manageable used to share the connection with the database and execute common
 * queries.
 *
 * @version $Revision: 1.16 $
 * @author <a href="mailto:anthony.goubard@japplis.com">Anthony Goubard</a>
 */
public class DatabaseConnection extends Manageable {

   /**
    * The API.
    */
   private APIImpl _api;

   /**
    * Connection to the database.
    */
   private Connection _connection;

   /**
    * Creates a new instance of DatabaseConnection
    */
   public DatabaseConnection(APIImpl api) {
      _api = api;
   }

   protected void initImpl(Map<String, String> properties)
   throws MissingRequiredPropertyException,
          InvalidPropertyValueException,
          InitializationException {

      try {
         if (_connection != null) {
            _connection.close();
         }
      } catch (SQLException sqlEx) {
         // Ignore as we're building a connection
      }

      RuntimeProperties runtimeProps = (RuntimeProperties) _api.getProperties();
      String driverName = runtimeProps.getPetstoreDatabaseDriver();
      String databaseURL = runtimeProps.getPetstoreDatabaseUrl().replace('\\', File.separatorChar);
      String userName = runtimeProps.getPetstoreDatabaseUsername();
      String password = runtimeProps.getPetstoreDatabasePassword();

      try {
         Class.forName(driverName);
         _connection = DriverManager.getConnection(databaseURL, userName, password);
      } catch (ClassNotFoundException cnfex) {
         // Could not find the database driver
         throw new InitializationException(cnfex);
      } catch (SQLException sqlEx) {
         // Could not connect to the database
         sqlEx.printStackTrace();
         throw new InitializationException(sqlEx);
      }

      // Creates the tables if needed.
      try {
         _connection.createStatement().executeQuery("SELECT email FROM CUSTOMERS");
      } catch (SQLException sqlEx) {
         try {
            _connection.createStatement().executeUpdate("CREATE TABLE PETS (id int, petname char(30), price float, age int, quantity int)");
//            _connection.createStatement().execute("CREATE TABLE USERS (email varchar(70), password varchar(20), gender varchar(6), " +
//                  "firstname varchar(20), lastname varchar(20), address varchar(60), phonenumber varchar(12)");
            _connection.createStatement().executeUpdate("CREATE TABLE CUSTOMERS (email char(60), password char(20), gender char, " +
                  "firstname char(20), lastname char(20), address char(60), phonenumber char(30))");
            _connection.createStatement().executeUpdate("CREATE TABLE ORDERS (petid int, email char(60), quantity int, status char(10))");
            Properties petsProps = new Properties();
            String databaseLocation = runtimeProps.getPetstoreDatabaseLocation().replace('/', File.separatorChar).replace('\\', File.separatorChar);
            FileInputStream databaseBootstrap = new FileInputStream(databaseLocation + File.separator + "pets.properties");
            petsProps.load(databaseBootstrap);
            databaseBootstrap.close();
            Iterator itPets = petsProps.keySet().iterator();
            while (itPets.hasNext()) {
               String nextPetID = (String) itPets.next();
               String petData = petsProps.getProperty(nextPetID);
               StringTokenizer stPetData = new StringTokenizer(petData, ",");
               if (stPetData.hasMoreTokens()) {
                  String petName = stPetData.nextToken().trim();
                  String petPrice = stPetData.nextToken().trim();
                  String age = stPetData.nextToken().trim();
                  String quantity = stPetData.nextToken().trim();
                  _connection.createStatement().executeUpdate("INSERT INTO PETS (id, petname, price, age, quantity) " +
                        " VALUES (" + nextPetID + ", '" + petName + "', " + petPrice + ", " + age + ", " + quantity + ")");
               }
            }
            _connection.createStatement().executeUpdate("INSERT INTO CUSTOMERS (email, password, gender, firstname, lastname, address, phonenumber) " +
               "VALUES ('test@test.com', 'tester', 'Mister', 'John', 'Doe', 'Baker Street 1, 1011PZ London, UK', '')");
         } catch (Exception ex) {
            ex.printStackTrace();
            throw new InitializationException(ex);
         }
      }
   }

   protected void deinitImpl() throws Throwable {
      _connection.close();
   }

   /**
    * Gets the connection to the database.
    *
    * @return
    *    the connection to the database, never <code>null</code>.
    */
   Connection getConnection() {
      return _connection;
   }
}
