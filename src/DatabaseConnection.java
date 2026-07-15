import java.sql.Connection;
import java.sql.*;
public class DatabaseConnection {
 
	//Update the database name, user, and password to match my setup
	private static final String URL = 
			"jdbc:sqlserver://localhost/SQLEXPRESS11:1433:" 
					+ "databaseName=store"
					+ "encrypt=true;trustServerCertificate=true";
	
	private static final String USER = "sa";
	private static final String PASSWORD = "your_password_here";
	
	
	public Connection getConnection(){
		try{
			Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connected to the store database successfully!");
			return connection;
		}catch(SQLException e){
			System.out.println("Connection failed: " + e.getMessage());
			return null;
		}
	}
}
