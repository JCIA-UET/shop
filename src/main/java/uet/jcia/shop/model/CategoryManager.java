package uet.jcia.shop.model;

import java.sql.Connection;
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
			Statement statement = con.createStatement();
			String query = String.format(
					"select * from category " +
					"where categoryID=%d",
					id);
			
			ResultSet rs = statement.executeQuery(query);
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
			Statement statement = con.createStatement();
			String query = String.format(
					"delete from category " +
					"where categoryID=%d",
					id);
			
			statement.executeUpdate(query);
			con.close();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean setItem(int id, Item newItem) {
		try {
			con = dbConnector.createConnection();
			Statement statement = con.createStatement();
			
			String query = String.format(
					"update category " + 
					"set categoryID=%d, categoryName=\"%s\" " +
					"where categoryID=%d",
					newItem.getId(), newItem.getName(),
					id);
			
			if (getItemById(id) == null) {
				query = String.format(
						"insert into category "	+
						"(categoryID, categoryName) " +
						"values " +
						"(%d, \"%s\")",
						newItem.getId(), newItem.getName());
				
			}
						
			statement.executeUpdate(query);
			con.close();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
			
		}
	}

}
