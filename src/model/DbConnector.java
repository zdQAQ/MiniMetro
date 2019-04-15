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
	// private static String url = "jdbc:mysql://localhost:3306/minimetro?autoReconnect=true&useSSL=false";
    // private static String user = "root";
    // private static String password = "root";
    private static Connection connection;
	public static String username;
	public static Long id;

    public DbConnector(String name) throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        connection = DriverManager.getConnection(url, user, password);
        username = name;
		System.out.println("Connected to database");
		setName(name);
    }
    
    public static void setName(String name) {
    	java.sql.Statement stmt = null;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String sql = "INSERT INTO data (name) values ('"+name+"')";
		 
        try {
			stmt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
			
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {  
            	id = rs.getLong(1);   
                System.out.println("数据主键：" + id);
            }  
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void update(String key,String value) {
    	java.sql.Statement stmt = null;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String sql = "update data set "+key+" = '"+value+"' where id = "+id+"";
        System.out.println(sql);
        try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void update(String key,Integer value) {
    	java.sql.Statement stmt = null;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String sql = "update data set "+key+" = "+value+" where id = "+id+"";
        System.out.println(sql);
        try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public static void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

}