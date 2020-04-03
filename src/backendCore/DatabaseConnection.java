package backendCore;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import backendOperations.ListOperations;

public class DatabaseConnection {
	
	public Connection connection;
	Statement statement;
	ResultSet resultset;
	DatabaseMetaData  databasetMetaData;
	ResultSetMetaData resultsetMetaData;
	ListOperations listoperation;
	
	//Creating the database connection to a Mysql database
	public  DatabaseConnection() {
		 listoperation =new ListOperations();
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
	
	// Updating the array list
	public void updateList() {
		try{  
			
			statement=connection.createStatement();  
			resultset=statement.executeQuery("select * from IMS_manager");  
			while(resultset.next())  {
				listoperation.push( resultset.getInt(1),
						resultset.getString(2),
						resultset.getString(3),
						resultset.getDouble(4),
						resultset.getString(5),
						resultset.getString(6),
						resultset.getInt(7),
						resultset.getBoolean(8));
				}
			
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
				int ProductID,
				String ProductName, String ProductType, double SalePrice,
				String Location, String VendorID,
				int QuantityOnHand,
				boolean Discount) {

			try{  

				statement=connection.createStatement();  
				statement.executeUpdate("update IMS_manager set ProductName='"
						+ProductName+"', ProductType='"
						+ProductType+"', SalePrice="
						+SalePrice+", Location='"
						+Location+"', VendorID='"
						+VendorID+"', QuantityOnHand="
						+QuantityOnHand+", Discount="
						+Discount+" where ProductID="
						+ProductID
						);  	
				listoperation.update( ProductID, ProductName, ProductType, SalePrice, Location, VendorID, QuantityOnHand, Discount);

			}
			catch(Exception e)
			{ 
				System.out.println(e);
			}  

		}
		
		// Deleting a record
		public  int deleteTable(int ProductID) {
			int flag=0;
			try {
				statement = connection.createStatement();  
				flag = statement.executeUpdate("delete from IMS_manager where ProductID="+ProductID);  
				listoperation.remove(ProductID);
			}
			catch(Exception e)
			{ System.out.println(e);
			} 
			return flag;

		}
		
		// Searching the PO table
		public  void searchPoTable() {
			
			try{  
				
				statement=connection.createStatement();  
				resultset=statement.executeQuery("select * from PO_table");  
				while(resultset.next())  {
				System.out.println(resultset.getInt(1)+"  "+resultset.getInt(2)+""+resultset.getString(2)+"  "+resultset.getString(3));
				}
				}
			catch(Exception e)
			{ System.out.println(e);}  
		}

		
		// Inserting a value into the table
				public  void insertIntoPoTable( 
											int ProductID,
											String ProductName, String ProductType,
											String Location, String VendorID,
											int Quantity
											) {
					
					try{  
						DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
						Date date = new Date();
						System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
						statement=connection.createStatement();  
						statement.executeUpdate("insert into PO_table values (null,"
								+ProductID+"'"
								+ProductName+"','"
								+ProductType+"',"
								+Location+"','"
								+VendorID+"',"
								+Quantity+","
								+dateFormat.format(date)+")"
								
						);  
						resultset=statement.executeQuery("select * from from IMS_manager where ProductID="+ProductID);  
						while(resultset.next())  {
							
						listoperation.update( resultset.getInt(1), ProductName, ProductType, resultset.getDouble(4), Location, VendorID, Quantity, resultset.getBoolean(8));
						
						}
						
					}
					catch(Exception e)
					{ System.out.println(e);}  
				
				}
			
				//Updating a record 
				public  void updatePoTable( 
						int PoID, int ProductID,
						String ProductName, String ProductType,
						String Location, String VendorID,
						int Quantity,
						boolean Discount) {

					try{  

						statement=connection.createStatement();  
						statement.executeUpdate("update PO_table set ProductID="
								+ProductID+ ", ProductName='"
								+ProductName+"', ProductType='"
								+ProductType+"', SalePrice="
								+Location+"', VendorID='"
								+VendorID+"', Quantity="
								+Quantity+"where PoID="
								+PoID
								);  	
						
						resultset=statement.executeQuery("select * from from IMS_manager where ProductID="+ProductID);  
						while(resultset.next())  {
							
						listoperation.update( resultset.getInt(1), ProductName, ProductType, resultset.getDouble(4), Location, VendorID, Quantity, resultset.getBoolean(8));
						}

					}
					catch(Exception e)
					{ 
						System.out.println(e);
					}  

				}
				
				// Deleting a record
				public  int deletePoTable(int PoID) {
					int flag=0;
					try {
						statement = connection.createStatement();  
						flag = statement.executeUpdate("delete from PO_table where PoID="+PoID);  
						
					}
					catch(Exception e)
					{ System.out.println(e);
					} 
					return flag;

				}
				
}
