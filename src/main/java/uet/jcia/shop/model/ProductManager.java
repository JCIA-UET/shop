package uet.jcia.shop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductManager implements ItemManager {

	private Connection con = null;
	
	@Override
	public List<Item> getAllItems() {
		List<Item> products = new ArrayList<>();
		Item product = null;
		
		try {
			con = dbConnector.createConnection();
			Statement statement = con.createStatement();
			String query = "select * from product";
			ResultSet rs = statement.executeQuery(query);
		
			while (rs.next()) {
				int productId = rs.getInt(1);
				String name = rs.getString(2);
				double price = rs.getDouble(3);
				int quantity = rs.getInt(4);
				int categoryId = rs.getInt(5);
				String description = rs.getString(6);
				
				product = new Product(
						productId, name, quantity, price,
						categoryId, description);
				products.add(product);
			}
			
			con.close();
			return products;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Item getItemById(int id) {
		try {
			con = dbConnector.createConnection();
			String query = "select * from product " +
							"where productID= ?";
			
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, id);
			
			ResultSet rs = statement.executeQuery();
			Item product = null;
			
			if (rs.next()) {
				int productId = rs.getInt(1);
				String name = rs.getString(2);
				double price = rs.getDouble(3);
				int quantity = rs.getInt(4);
				int categoryId = rs.getInt(5);
				String description = rs.getString(6);
				
				product = new Product(
						productId, name, quantity, price,
						categoryId, description);
			}
			
			con.close();
			return product;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean removeItemById(int id) {
		if (getItemById(id) == null) {
			return false;
		}
		
		try {
			con = dbConnector.createConnection();
			String query = "delete from product " +
							"where productID= ?";
			
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, id);
			statement.execute();

			con.close();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean setItem(int id, Item newItem) {
		
		if (!(newItem instanceof Product)) {
			return false;
		}
		
		try {
			boolean doesExist = true;
			
			String query =
					"update product " + 
					"set " +
					"productID= ?, productName= ?, price= ?, " + 
					"quantity= ?, categoryId= ?, description= ?" +
					"where productID= ?";
						
			if (getItemById(id) == null) {
				doesExist = false;
				query = "insert into product "	+
						"(" +
						"productID, productName, price, " +
						"quantity, categoryId, description" +
						") " +
						"values " +
						"(?, ?, ?, ?, ?, ?)";
				
			}
			
			con = dbConnector.createConnection();
			PreparedStatement statement = con.prepareStatement(query);
			
			Product newProduct = (Product) newItem;
			statement.setInt(1, newProduct.getId());
			statement.setString(2, newProduct.getName());
			statement.setDouble(3, newProduct.getPrice());
			statement.setInt(4, newProduct.getQuantity());
			statement.setInt(5, newProduct.getCategoryId());
			statement.setString(6, newProduct.getDescription());
			
			if (doesExist) {
				statement.setInt(7, newItem.getId());
			}
			
			statement.execute();
			con.close();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
			
		}
	}
}
