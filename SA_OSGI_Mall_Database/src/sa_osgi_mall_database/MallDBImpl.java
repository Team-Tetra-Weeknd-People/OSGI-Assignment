package sa_osgi_mall_database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MallDBImpl implements MallDB{

	@SuppressWarnings("unused")
	private Connection connection;
	private static String driverName;
	private static String databaseConnectionLink;
	private static String databaseUser;
	private static String databasePassword;
	
	@SuppressWarnings("static-access")
	public MallDBImpl() {
		this.driverName = "com.mysql.jdbc.Driver";
		this.databaseConnectionLink = "jdbc:mysql://localhost:3306/malldb?characterEncoding=latin1&useConfigs=maxPerformance";
		this.databaseUser = "root";
		this.databasePassword = "";
	}
	
	@Override
	public Connection connection() {
		// TODO Auto-generated method stub
		Connection con = null; 
	    try {
	      Class.forName(driverName);
	      con = DriverManager.getConnection(databaseConnectionLink, databaseUser, databasePassword); // connecting to our database
	      System.out.println("Database Connected!");
	    } catch (ClassNotFoundException | SQLException e ) {
	      
	      System.out.println(e+"");
	    }
	    
	    return con; 
	}

}
