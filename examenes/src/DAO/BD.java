package DAO;
import java.sql.*; 
import java.util.logging.Level;
import java.util.logging.Logger;


public class BD {
static String bd = "examenBD"; 
static String login = "root"; 
static String password = "root"; 
static String url  ="jdbc:mysql://localhost/examenBD?user=" + login + "&password=" + password; 
static String Driver ="com.mysql.jdbc.Driver";
Connection conn = null; 
Connection conex;



public Connection conexion() {
 
        try {
            Class.forName(Driver);
	        }
	        catch (ClassNotFoundException ex) {
	            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
	
	        try {
	            conn = DriverManager.getConnection(url);
	        }
	        catch (SQLException ex) {
	            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
	        }
	       return conn;
	    }
	    
	  

}
