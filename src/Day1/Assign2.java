/*2) create table "Employee"
	empid,empname,desig

	add 5 records manually.

   Now write a java program to accept "designation" from user and retrieve employees with the given designation.
   */
package Day1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;


public class Assign2 {
	public static void main(String[] args) {
		String string = "jdbc:mysql://localhost:3306/bit";
		Scanner scanner = new Scanner(System.in);
		try(Connection connection = DriverManager.getConnection(string,"root", "root")) {
			
//			System.out.println("how many records you want to add");
//			int rec = scanner.nextInt();
//			for (int x = 0; x < rec; x++) {
//				
//				PreparedStatement preparedStatement = connection.prepareStatement("insert into employee (empName, desig) values(?, ?)");
//				
//				System.out.println("Enter employee name");
//				String empString = scanner.next();
//				preparedStatement.setString(1, empString);
//				
//				System.out.println("enter designation");
//				String desig = scanner.next();
//				preparedStatement.setString(2, desig);
//				
//				preparedStatement.executeUpdate();
//			}
//			
			 System.out.println("Enter designation to search for employees:");
	            int Id = scanner.nextInt();
	            PreparedStatement preparedStatement = connection.prepareStatement("SELECT empId, empName, desig FROM employee WHERE empId = ?");
	            preparedStatement.setInt(1, Id);
	            ResultSet resultSet = preparedStatement.executeQuery();
			
	            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
	            int col = resultSetMetaData.getColumnCount();
	            
			for (int i = 1; i <=col; i++) {
				System.out.print(resultSetMetaData.getColumnName(i)+"\t");
			}
			
			while (resultSet.next()) {
				System.out.println();
				for (int i =1 ; i <=col; i++) {
					System.out.print(resultSet.getObject(i)+"\t");
				}
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
