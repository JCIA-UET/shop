package uet.jcia.shop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryManager implements ItemManager {
	
	private Connection con = null;
	
	@Override
	public List<Item> getAllItems() {
		List<Item> categories = new ArrayList<>();
		Item category = null;
		
		try {
			con = dbConnector.createConnection();
			Statement statement = con.createStatement();
			String query = "select * from category";
			ResultSet rs = statement.executeQuery(query);
		
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				
				category = new Category(id, name);
				categories.add(category);
			}
			
			con.close();
			return categories;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Item getItemById(int id) {
		try {
			con = dbConnector.createConnection();
			String query = "select * from category " +
							"where categoryID= ?";
			
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, id);
			
			ResultSet rs = statement.executeQuery();
			Item category = null;
			
			if (rs.next()) {
				String categoryName = rs.getString(2);
				category = new Category(id, categoryName);
			}
			
			con.close();
			return category;
			
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
			String query = "delete from category " +
							"where categoryID= ?";
			
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
		
		if (!(newItem instanceof Category)) {
			return false;
		}
		
		try {
			boolean doesExist = true;
			
			String query =
					"update category " + 
					"set categoryID= ?, categoryName= ? " +
					"where categoryID= ?";
						
			if (getItemById(id) == null) {
				doesExist = false;
				query = "insert into category "	+
						"(categoryID, categoryName) " +
						"values " +
						"(?, ?)";
				
			}
			
			con = dbConnector.createConnection();
			PreparedStatement statement = con.prepareStatement(query);
			
			Category newCategory = (Category) newItem;
			statement.setInt(1, newCategory.getId());
			statement.setString(2, newCategory.getName());
			
			if (doesExist) {
				statement.setInt(3, id);	
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
