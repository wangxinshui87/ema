package dataoperate;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
public class OracleConnection 
{
		private String url = "jdbc:oracle:thin:@172.16.10.223:1521:orcl";
		private String user = "system";
		private String password = "oracle";
		private Connection conn = null;  //连接
		
		public void setUrl(String url)
		{
			this.url = url;
		}
		
		public void setUser(String user)
		{
			this.user = user;
		}
		
		public void setPassword(String password)
		{
			this.password = password;
		}
		
		public Connection getConnection()
		{
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(url, user, password);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("connect to db failed" +e.getMessage());
				return null;
			}
			System.out.println("connect to db succeed");
			return conn;
		}	
}
