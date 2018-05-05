package pl.coderslab.model;

import java.sql.Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DbUtil {
    private static DataSource ds;
    public static Connection connect() throws SQLException {
        return getInstance().getConnection();
    }
    private static DataSource getInstance() {
        if(ds == null) {
            try {
                Context ctx = new InitialContext();
                ds = (DataSource)ctx.lookup("java:comp/env/jdbc/school");
            } catch (NamingException e) {
                e.printStackTrace();}}
        return ds;}
    
    
    
    /*
	Previous "Connect.java"
	Refactored to DbUtil.java
    
    
    package pl.coderslab.model;

    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.SQLException;

    public class Connect {
    	Connection connect = connect(); 
    	
    	public static Connection connect() {
    		Connection connect = null;
    		try {
    		connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/school?autoReconnect=true&useSSL=false", "root", "coderslab");
    		} catch (SQLException e) {
    		e.printStackTrace();
    		}
    		return connect;
    	}

    }
    */
}

