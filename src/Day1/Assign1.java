/*1) create a table "Student"

	rollno,name,age

write a java program to accept rollno,name,age from user and insert a row.

after inserting that row try to view using a query.
*/
package Day1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Scanner;

import javax.xml.crypto.Data;

public class Assign1 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String ssString = "jdbc:mysql://localhost:3306/bit";
		
		try(Connection connection = DriverManager.getConnection(ssString,"root","root")) {
			String namString = scanner.next();
			int age = scanner.nextInt();
			
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO student (name, age) VALUES (?, ?)");
			
			preparedStatement.setString(1,namString);
			preparedStatement.setInt(2,age);
			
			int row_affected = preparedStatement.executeUpdate();
			if(row_affected>0) {
				System.out.println("Data is inserted" );
			}
			else {
				System.out.println("data is not inserted");
			}
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from student");
			
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			
			int col = resultSetMetaData.getColumnCount();
			
			for (int i = 1; i <=col; i++) {
				System.out.print(resultSetMetaData.getColumnName(i)+"\t");
			}
			
			while(resultSet.next()) {
				System.out.println();
				for (int i = 1; i <=col; i++) {
					System.out.print(resultSet.getObject(i)+"\t");
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
}
