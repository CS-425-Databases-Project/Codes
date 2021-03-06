package cs425_databases;

import java.sql.*;

public class SQLInterface {
	
	private final String url = "jdbc:postgresql://localhost/grocery";
    private final String user = "postgres";
    private final String password = "RS2381998";
    
    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
        }

        return conn;
    }
    
    public ResultSet sqlCode(String code) {
        ResultSet rs = null;
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();) {
        	rs = stmt.executeQuery(code);
        } 
        catch (SQLException ex) {
        }
        return rs;
    }
    
    public ResultSet sqlCode(String code, String arg1) {
        ResultSet rs = null;
        try (Connection conn = connect();
        		PreparedStatement stmt = conn.prepareStatement(code);) {   
        	 stmt.setString(1, arg1);
        	 rs=stmt.executeQuery();
        } 
        catch (SQLException ex) {
        }
        return rs;
    }
    
    public ResultSet sqlCode(String code, String arg1, String arg2) {
        ResultSet rs = null;
        try (Connection conn = connect();
        		PreparedStatement stmt = conn.prepareStatement(code);) {   
        	 stmt.setString(1, arg1);
        	 stmt.setString(2, arg2);
        	 rs=stmt.executeQuery();
        } 
        catch (SQLException ex) {
        }
        return rs;
    }
    
    public ResultSet sqlCode(String code, String arg1, String arg2, String arg3) {
        ResultSet rs = null;
        try (Connection conn = connect();
        		PreparedStatement stmt = conn.prepareStatement(code);) {   
        	 stmt.setString(1, arg1);
        	 stmt.setString(2, arg2);
        	 stmt.setString(3, arg3);
        	 rs=stmt.executeQuery();
        } 
        catch (SQLException ex) {
        }
        return rs;
    }
    
    public ResultSet sqlCode(String code, String arg1, String arg2, String arg3, String arg4) {
        ResultSet rs = null;
        try (Connection conn = connect();
        		PreparedStatement stmt = conn.prepareStatement(code);) {   
        	 stmt.setString(1, arg1);
        	 stmt.setString(2, arg2);
        	 stmt.setString(3, arg3);
        	 stmt.setString(4, arg4);
        	 rs=stmt.executeQuery();
        } 
        catch (SQLException ex) {
        }
        return rs;
    }
    
}
