package cs425_databases;

import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.concurrent.Callable;

public class SQLInterface {
	
	private static final String url = "jdbc:postgresql://localhost/gst";
    private static final String user = "postgres";
    private static final String password = "RS2381998";
    
    
    private static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
        }

        return conn;
    }
    
    public static ArrayList<ArrayList<Object>> sqlExecute(String code) {
    	
    	int numCol = 0;
    	
    	ArrayList<Object> columnNames = new ArrayList<Object>();
    	ArrayList<String> columnTypes = new ArrayList<String>();
    	ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
    	ArrayList<Object> rowData = new ArrayList<Object>();
    	
        ResultSet rs = null;
        
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();) {
        	rs = stmt.executeQuery(code);
        	numCol = rs.getMetaData().getColumnCount();
        	
        	for(int i = 1; i < numCol+1; i++) {
        		columnNames.add(rs.getMetaData().getColumnName(i));
        		columnTypes.add(rs.getMetaData().getColumnTypeName(i));
        	}
        	data.add(columnNames);
   
        	while(rs.next()) {
        		rowData = new ArrayList<Object>();
        		for (int i = 1; i < numCol+1; i++) {
        			rowData.add(rs.getString(i));
        		}
        		data.add(rowData);
        	}
        	
        	
        } 
        catch (SQLException ex) {
        }
        return data;
    }
    
    public static int sqlUpdate(String code) {
    	
		int update = 0;
    	
        ResultSet rs = null;
        
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();) {
        	update = stmt.executeUpdate(code);
        } 
        catch (SQLException ex) {
        }
        
        return update;
    }
    
    public static ArrayList<ArrayList<Object>> sqlCode(String code, String arg1) {
    	
    	int numCol = 0;
    	
    	ArrayList<Object> columnNames = new ArrayList<Object>();
    	ArrayList<String> columnTypes = new ArrayList<String>();
    	ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
    	ArrayList<Object> rowData = new ArrayList<Object>();
    	
        ResultSet rs = null;
        
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(code)) {

            pstmt.setString(1, "customer");
            rs = pstmt.executeQuery();
        }
        
        catch (SQLException ex) {
        }
        
        return data;
    }

    
    
    public static ArrayList<ArrayList<Object>> sqlCode(String code, String arg1, String arg2) {
        ResultSet rs = null;
        int numCol = 0;
    	
    	ArrayList<Object> columnNames = new ArrayList<Object>();
    	ArrayList<String> columnTypes = new ArrayList<String>();
    	ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
    	ArrayList<Object> rowData = new ArrayList<Object>();
    	
        try (Connection conn = connect();
        		Statement stmt = conn.prepareStatement("select " + arg1 +" from " + arg2 + ";");) {
        	rs = stmt.executeQuery(code);
        	numCol = rs.getMetaData().getColumnCount();
        	
        	for(int i = 1; i < numCol+1; i++) {
        		columnNames.add(rs.getMetaData().getColumnName(i));
        		columnTypes.add(rs.getMetaData().getColumnTypeName(i));
        	}
        	data.add(columnNames);
   
        	while(rs.next()) {
        		rowData = new ArrayList<Object>();
        		for (int i = 1; i < numCol+1; i++) {
        			rowData.add(rs.getString(i));
        		}
        		data.add(rowData);
        	}
        } 
        catch (SQLException ex) {
        }
        return data;
    }
    
    public static ResultSet sqlCode(String code, String arg1, String arg2, String arg3) {
        ResultSet rs = null;
        try (Connection conn = connect();
        		PreparedStatement stmt = conn.prepareStatement(code);) {   
        	 stmt.setString(1, arg1);
        	 stmt.setString(2, arg2);
        	 stmt.setString(3, arg3);
        	 rs=stmt.executeQuery();
        	 System.out.println(rs.next());
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
        	 while(rs.next()){
         	    int cols = rs.getMetaData().getColumnCount();
         	    String[] arr = new String[cols];
         	    for(int i=0; i<cols; i++){
         	      arr[i] = rs.getString(i+1);
         	    }
         	}
        } 
        catch (SQLException ex) {
        }
        return rs;
    }
    
	public static void main(String[] args) {
		
	}
	

    
}
