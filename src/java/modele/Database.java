/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author fcd
 */
public class Database {
    
    /** Code erreur MySQL quand le serveur est inaccessible */
	  public static final int SERVER_OFF = 0;

	  /** Code erreur MySQL pour "duplicate entry" */
	  public static final int DOUBLON = 1062;

	  /** Code erreur MySQL pour "Cannot delete or update a parent row: a
	   * foreign key constraint fails " */
	  public static final int ROW_IS_REFERENCED = 1451;

	  /** Code erreur MySQL pour "Cannot add or update a child row: a
	   * foreign key constraint fails" */
	  public static final int FOREIGN_KEY_NOT_FOUND = 1452;

	//  protected static final String DRIVER_NAME = "org.postgresql.Driver";
	//  protected static final String URL = "jdbc:postgresql://localhost:5432";
	  protected static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	  protected static final String USER = "root";
	  protected static final String PASSWORD = "";
	  protected static final String DB_NAME = "quiz";
	  protected static final String URL = "jdbc:mysql://localhost/" + DB_NAME;

	  public enum SortOrder {

	    ASC, DESC;
	  }

	  static {
	    // Chargement du pilote
	    // Doit avoir lieu une seule fois, au dÃ©marrage de l'application
	    try {
	      Class.forName(DRIVER_NAME).newInstance();
	      System.out.println("*** Driver loaded.");
	    }
	    catch (ClassNotFoundException e) {
	      System.err.println("*** ERROR: Driver " + DRIVER_NAME + " not found");
	    }
	    catch (InstantiationException e) {
	      System.err.println("*** ERROR: Impossible to create an instance of " + DRIVER_NAME);
	      System.err.println(e.getMessage());
	    }
	    catch (IllegalAccessException e) {
	      System.err.println("*** ERROR: Impossible to create an instance of " + DRIVER_NAME);
	      System.err.println(e.getMessage());
	    }
	  }

	  /** Fournit une connexion Ã  la base de donnÃ©es. Ne fait pas appel Ã 
	   * un pool de connexion, mÃ¢me si cela est envisageable plus tard (ne
	   * changerait rien Ã  l'appel de la mÃ©thode)
	   *
	   * @throws java.sql.SQLException
	   */
	  public static Connection getConnection() throws SQLException {
	    return DriverManager.getConnection(URL, USER, PASSWORD);
	  }

	}




    


    

