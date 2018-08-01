package cs425_databases;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class estaconn{


		private final String url = "jdbc:postgresql://localhost:5432/";
	    private final String user = "postgres";
	    private final String password = "88378821";

	    public Connection connect() {
	        Connection conn = null;
	        try {
	            conn = DriverManager.getConnection(url, user, password);
	            System.out.println("Connected to the PostgreSQL server successfully.");
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	 
	        return conn;
	    }
	 
	    /**
	     * @param args the command line arguments
	     */
	 
	}


