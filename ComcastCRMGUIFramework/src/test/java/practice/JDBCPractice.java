package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;

/**
 * @author ngorai JDBC Practice class
 */
public class JDBCPractice {
	@Test
	/**
	 * To execute select query
	 * 
	 * @throws SQLException
	 */
	public void executeSelectQuery() throws SQLException {
		Connection conn = null;

		/* Load/register database driver */
		try {
			Driver driverSql = new Driver();
			DriverManager.registerDriver(driverSql);

			/*
			 * connect the database
			 */
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3333/projects", "root", "root");
			/*
			 * create sql statement
			 */
			Statement stat = conn.createStatement();
			/*
			 * execute select query
			 */
			ResultSet result = stat.executeQuery("select * from project");
			while (result.next()) {
				System.out.println(result.getString(1) + "\t" + result.getString(2));
			}
		} catch (Exception e) {
			System.out.println("not connected");
		}
		/*
		 * close db connection
		 */
		finally {
			conn.close();
		}
	}

}
