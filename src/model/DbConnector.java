package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DbConnector {
    // in real life, use a connection pool....
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://rm-2zeo26w82637usg12qo.mysql.rds.aliyuncs.com:3306/minimetro?autoReconnect=true&useSSL=false";
    private static String user = "zhangdai";
    private static String password = "zhangdai1!";
    private static Connection connection;
    private static String username;

    public DbConnector(String name) throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        connection = DriverManager.getConnection(url, user, password);
        username = name;
        System.out.println("Connected to database");
    }
    
//    public static Connection getConnection() throws SQLException {
//    	Connection conn = null;
//    	conn = DriverManager.getConnection(url, user, password);
//    	return conn;
//    }
//    
//    public static void execute(String sql) throws SQLException {
//    	Connection conn = getConnection();
//    	java.sql.Statement stmt = conn.createStatement();
//        
//        stmt.executeUpdate(sql);
//    }
    
    public static void setName(String name) {
    	java.sql.Statement stmt = null;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String sql = "INSERT INTO z (name) values ('"+name+"')";
        String sql1 = "INSERT INTO j (name) values ('"+name+"')";
		String sql2 = "INSERT INTO g (name) values ('"+name+"')";
		 
        try {
			stmt.executeUpdate(sql);
			stmt.executeUpdate(sql1);
			stmt.executeUpdate(sql2,Statement.RETURN_GENERATED_KEYS);
			
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {  
                Long id = rs.getLong(1);   
                System.out.println("数据主键：" + id);
            }  
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void updateZ(String key,String value) {
    	java.sql.Statement stmt = null;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String sql = "update z set "+key+" = '"+value+"' where name = '"+username+"'";
        System.out.println(sql);
        try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void updateJ(String key,String value) {
    	java.sql.Statement stmt = null;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "update j set "+key+" = '"+value+"' where name = '"+username+"'";
        System.out.println(sql);
        try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void updateG(String key,String value) {
    	java.sql.Statement stmt = null;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "update g set "+key+" = '"+value+"' where name = '"+username+"'";
        System.out.println(sql);
        try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

}