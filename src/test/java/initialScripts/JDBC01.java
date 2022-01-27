package initialScripts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class JDBC01
{

	public static void main(String[] args) throws SQLException
	{
		Connection conn = null;
		
		try
		{
			Driver drRef = new Driver();
			
			 /* load/register MySQL Database */
			DriverManager.registerDriver(drRef);
			
			 /* connect to database */
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
			
			/* create query statement */
			Statement stm = conn.createStatement();
			String query = "select * from project";
			
			/* execute the query */
			ResultSet res_set = stm.executeQuery(query);
			while(res_set.next())
			{
				System.out.println(res_set.getString(1) +"\t"+ res_set.getString(2) +"\t"+
						res_set.getString(3) +"\t"+ res_set.getString(4));	
			}
		}
		catch(Exception e) {
		}
		finally
		{
			/* close the connection */
			conn.close();
			System.out.println("close the conncection");
		}		
	}
}
