package backendCore;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import backendOperations.ListOperations;

public class DatabaseConnection {
	
	Connection connection;
	Statement statement;
	ResultSet resultset;
	DatabaseMetaData  databasetMetaData;
	ResultSetMetaData resultsetMetaData;
	ListOperations listoperation =new ListOperations();


	
	//Creating the database connection to a Mysql database
	public  void connectDBConnection() {
		
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			connection=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/LidDatabase?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=CST","root","Infosys123");  
			//here LidDatabase is database name, root is username and password is stated 
			databasetMetaData = connection.getMetaData();
			}
		catch(Exception e)
		{ System.out.println(e);}  
	}

	//Closing the Database connection
	public void closeDBConnection() {
		
		try {
		if(!connection.isClosed()) {
		resultset.close(); 	
		statement.close(); 
		connection.close();
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// Selecting a value from the table
	public  void selectFromTable() {
		
		try{  
			
			statement=connection.createStatement();  
			resultset=statement.executeQuery("select * from IMS_manager");  
			while(resultset.next())  
			System.out.println(resultset.getInt(1)+"  "+resultset.getString(2)+"  "+resultset.getString(3));
			
			}
		catch(Exception e)
		{ System.out.println(e);}  
	}

	// Inserting a value into the table
		public  void insertIntoTable( 
									String ProductName, String ProductType, double SalePrice,
									String Location, String VendorID,
									int QuantityOnHand,
									boolean Discount) {
			
			try{  
				
				statement=connection.createStatement();  
				statement.executeUpdate("insert into IMS_manager values (null,'"
						+ProductName+"','"
						+ProductType+"',"
						+SalePrice+",'"
						+Location+"','"
						+VendorID+"',"
						+QuantityOnHand+","
						+Discount+")"
				);  
				resultset=statement.executeQuery("select max(ProductID) from IMS_manager");  
				while(resultset.next())  {
				listoperation.push( resultset.getInt(1), ProductName, ProductType, SalePrice, Location, VendorID, QuantityOnHand, Discount);
				}
				
			}
			catch(Exception e)
			{ System.out.println(e);}  
		
		}
	
	//Updating a record 
		
		public  void updateTable( 
							String ProductName, String ProductType, double SalePrice,
							String Location, String VendorID,
							int QuantityOnHand,
							boolean Discount) {
		
			try{  
			
			statement=connection.createStatement();  
			statement.executeUpdate("insert into IMS_manager values (null,'"
			+ProductName+"','"
			+ProductType+"',"
			+SalePrice+",'"
			+Location+"','"
			+VendorID+"',"
			+QuantityOnHand+","
			+Discount+")"
			);  
			
			}
			catch(Exception e)
			{ System.out.println(e);}  
			
				}
		}
