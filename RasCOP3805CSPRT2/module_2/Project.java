package module_2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Project {
	
	
	void run() {
		
		char input = ' ';
		Scanner scanner = new Scanner(System.in);
		do {
		
		//Main menu for the user
		
		//System.out.println("Welcome to the Inventory Management System");
		System.out.println("Please select one of the following options:");
		System.out.println("------------------------------");
		System.out.println("1: Add an item");
		System.out.println("2: Update item quantity");
		System.out.println("3: Remove an item");
		//System.out.println("4: Update inventory quantity");
		System.out.println("4: Search for an item by name");
		System.out.println("5: View all items");
		System.out.println("q: Quit the program");
		System.out.println("------------------------------");

		
				
		
			

			input = scanner.next().charAt(0); //get input from user and give the first char
			
			if (input == '1') {
				//call the add item method
				System.out.println("---------------------------------");
				System.out.println("You have selected to add an item.");
				System.out.println("---------------------------------");
				System.out.println("Enter the product name: ");
				String pName = scanner.next();
				System.out.println("Enter the product quantity: ");
				int qty = scanner.nextInt();
				
				addItem(pName, qty);
				
			} 
			else if (input == '2') {
				//call update item method
				System.out.println("------------------------------------------");
				System.out.println("You have selected to update item quantity.");
				System.out.println("------------------------------------------");

				System.out.println("Enter the product ID number: ");
				String pID = scanner.next();
				System.out.println("Enter the new product quantity: ");
				int qty = scanner.nextInt();
				
				updateQty(pID, qty);
			}
			else if (input == '3') {
				//call delete item method
				System.out.println("------------------------------------");
				System.out.println("You have selected to remove an item.");
				System.out.println("------------------------------------");
				
				System.out.println("Enter the product ID number: ");
				String pID = scanner.next();
				
				removeItem(pID);
			}
			//else if (input == '4') {
				//call update quantity method
			//	System.out.println("Okay. Lets update a quantity");
			//}
			else if (input == '4') {
				//call search method
				System.out.println("Okay. Lets search for an item");
				System.out.println("Enter the product name: ");
				String pName = scanner.next();
				SearchProduct(pName);
			}
			else if (input == '5') {
				//call search method
				System.out.println("Okay. Lets view all items");
				GetProducts();
			}
			else if (input !='q'){
				//Let's the user know if they have entered something that isn't an option
				System.out.println("Invalid entry");
			}

			//scanner.close();
		}while (input != 'q');
		System.out.println("Thank you for using the program.");
		System.out.println("Restart to enter the database.");
		scanner.close();

		
	}
	public void GetProducts() {
		try {
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/InventorySystem","root","Database123!");
			
			PreparedStatement ps = connect.prepareStatement("Select * from Products");
			
			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next()) {
				System.out.println(rs.getString("ProductID") + " - " + rs.getString("productName") + " - " + rs.getString("qty"));
			}
			connect.close();
			rs.close();
			ps.close();
			//get one database interaction working
		} catch (SQLException e) {
		
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addItem(String productName, int qty) {
		try {
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/InventorySystem","root","Database123!");
			
			
			
			PreparedStatement ps = connect.prepareStatement("insert into products(productName, qty)" + " values('" + productName + "'," + qty + ")");
			
			ps.execute();
			
			System.out.println("Item: "+ productName + " has been added with a quantity of " + qty );
			connect.close();
			
			ps.close();
			
		} catch (SQLException e) {
		
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public void updateQty(String pID, int qty) {
		try {
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/InventorySystem","root","Database123!");
			
			PreparedStatement ps = connect.prepareStatement("UPDATE products SET qty = '" + qty + "' WHERE productID = '" + pID +"';");
			
			ps.execute();
			
			System.out.println("Item # "+ pID + " has been updated to a quantity of: " + qty );
			System.out.println();
			connect.close();
			ps.close();
			
		} catch (SQLException e) {
		
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/InventorySystem","root","Database123!");
			
			PreparedStatement ps = connect.prepareStatement("Select * from Products WHERE productID = '" + pID +"';");
			
			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next()) {
				System.out.println("Current Product Details:");
				System.out.println(rs.getString("ProductID") + " - " + rs.getString("productName") + " - " + rs.getString("qty"));
			}
			connect.close();
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
		
			 //TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public void removeItem(String pID) {
		try {
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/InventorySystem","root","Database123!");
			
			
			
			PreparedStatement ps = connect.prepareStatement("DELETE from products where productID = '" + pID + "';");
			
			ps.execute();
			connect.close();
			System.out.println("Item # " + pID + " has been removed from table Products");
			
		} catch (SQLException e) {
		
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public void SearchProduct(String productName) {
		try {
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/InventorySystem","root","Database123!");
			
			PreparedStatement ps = connect.prepareStatement("Select * from Products where productname = '"+ productName +"'");
			
			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next()) {
				System.out.println("Search returns the following:");
				System.out.println(rs.getString("ProductID") + " - " + rs.getString("productName")+ " - " + rs.getString("qty"));
			}
			
			connect.close();
			rs.close();
			ps.close();

			
		} catch (SQLException e) {
		
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	public void PrintHeader() {
		System.out.println("---------------------");
		System.out.println("Your query returns: ");
	}
	

}
