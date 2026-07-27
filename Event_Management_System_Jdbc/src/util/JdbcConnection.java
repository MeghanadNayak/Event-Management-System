package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {

   private static Connection connection;
   public static Connection getConnection() throws SQLException {
	   if(connection == null) {
		   return DriverManager.getConnection("jdbc:mysql://localhost:3306/event_jdbc?useSSL=false","root","root");
	   }else {
		   return connection;
	   }
   }
}
