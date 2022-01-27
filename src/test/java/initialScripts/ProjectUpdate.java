package initialScripts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;

public class ProjectUpdate {
	@Test
	
	public void update() throws SQLException
	{
		Connection conn = null;
		int result = 0;
		try {
			Driver drRef = new Driver();
			
			DriverManager.registerDriver(drRef);
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
			System.out.println("Connection is complete");
			
			Statement stm = conn.createStatement();
			String query = "insert into project values('TY_PROJ_3009', 'mishra','13/01/2022','BankOfIndia','On Going',10)";
			
			result = stm.executeUpdate(query);
			
		}
		catch(Exception e) {
		}
		finally {
			
			if(result==1)
				System.out.println("Project inserted successfully");
			else
				System.out.println("Project is not inserted");
			
			conn.close();
			System.out.println("close the database connection");			
		}
	}

}
