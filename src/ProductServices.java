import java.sql.*;
import java.util.*;
public class ProductServices {
	private DatabaseConnection db = new DatabaseConnection();

	public ProductServices() {
		// TODO Auto-generated constructor stub
	}

	public void addProduct(String name, double price){
		String sql = "INSERT INTO  [store].[dbo].[products] (ProductName, ProductPrice) VALUES (?, ?)" ;
		try(Connection conn = db.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)){

			stmt.setString(1, name); // fills the first ?
			stmt.setDouble(2, price); //fills the second ?

			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0){
				System.out.println("Product has been added succesfully");
			}

		}catch (SQLException e){
			System.out.println("Invalid data entered, try again" + e.getMessage());

		}
	}

	public void updateProduct (int id, String name, double price){
		String sql = "UPDATE [store].[dbo].[products] SET ProductName = ?, ProductPrice = ? WHERE ProductID = ?";
		try(Connection conn = db.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)){

			stmt.setString(1, name); // fills the first ?
			stmt.setDouble(2, price); //fills the second ?
			stmt.setInt(3, id); //fills the ? in WHERE id = ?

			int rowsAffected = stmt.executeUpdate();

			if(rowsAffected > 0){
				System.out.println("Product has been updated successfully");
			}else if (rowsAffected == 0){
				System.out.println("Product not found, try again");
			}	

		}catch (SQLException e){
			System.out.println("Invalid data entered, try again");

		}		

	}
	public void deleteProduct (int id){
		String sql = "Delete FROM [store].[dbo].[products] WHERE ProductID = ?";
		try(Connection conn = db.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)){

			stmt.setInt(1, id); //fills the ? in WHERE id = ?

			int rowsAffected = stmt.executeUpdate();

			if(rowsAffected > 0){
				System.out.println("Product has been deleted successfully");
			}else if (rowsAffected == 0){
				System.out.println("Product not found, try again");
			}	

		}catch (SQLException e){
			System.out.println("Invalid data entered, try again");

		}		
	}
	
	public ArrayList<Product> getAllProducts(){
		ArrayList<Product> prod = new ArrayList<>();   
		String sql = "SELECT ProductID, ProductName, ProductPrice FROM [store].[dbo].[products]";
		try(Connection conn = db.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()){
			while(rs.next()){
				String name = rs.getString("ProductName"); 
				double price = rs.getDouble("ProductPrice");
				int id = rs.getInt("ProductID"); 
				
				Product product = new Product(id, name, price);
				  
				prod.add(product);
			}

			if(prod.isEmpty()){
				System.out.println("Product not found, try again");
			}else{
				System.out.println("List of Products: ");
				for (int i= 0; i < prod.size(); i++){
					System.out.println(prod.get(i));
				}
			}	

		}catch (SQLException e){
			System.out.println("Invalid data entered, try again" + " " + e.getMessage());

		}	
		return prod;
	}





}
