package uet.jcia.shop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class OrderManager implements ItemManager {
	private final String FIND_ITEM_BY_ID_QUERY = "SELECT * FROM orderDetails WHERE productID=?";
	private final String CREATE_ORDER_QUERY = "INSERT INTO order (customerID, orderName) VALUES(?, ?)";
	private final String GET_ALL_ITEMS_QUERY = "FIND * INTO product WHERE categoryID=?";
	
	public boolean createNewOrder(int customerId, String orderName) {
		DBConnector dbconn = DBConnector.getInstance();
		Connection conn = dbconn.getConnection();
		
		try {
			PreparedStatement stt = conn.prepareStatement(CREATE_ORDER_QUERY);
			stt.setInt(1, customerId);
			stt.setString(2, orderName);
			
			stt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			// In case of getting fail query, how to close connection ???
			return false;
			
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
	
	@Override
	public Item getItemById(int id) {
		// TODO Auto-generated method stub
		DBConnector dbconn = DBConnector.getInstance();
		Connection conn = dbconn.getConnection();
		
		Product product = null;
		try {
			PreparedStatement stt = conn.prepareStatement(FIND_ITEM_BY_ID_QUERY);
			stt.setInt(1, id);
			stt.executeQuery();
			ResultSet rs = stt.executeQuery();
			rs.next();
			
			int productId = rs.getInt("productID");
			String name = rs.getString("name");
			int quantity = rs.getInt("quantity");
			int price = rs.getInt("price");
			int categoryId = rs.getInt("categoryID");
			String des = rs.getString("description");
			
			product = new Product(productId, name, quantity, price, categoryId, des);
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}
	
	@Override
	public List<Item> getAllItems() {
		// TODO Auto-generated method stub
		DBConnector dbconn = DBConnector.getInstance();
		Connection conn = dbconn.getConnection();
		
		try {
			if(itemsType == ItemsTypes.CATEGORY) {
				PreparedStatement stt = conn.prepareStatement(GET_ALL_ITEMS_QUERY);
				stt.setInt(1, x);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean removeItemById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setItem(int id, Item newItem) {
		// TODO Auto-generated method stub
		return false;
	}
 
	public List<Item> getTopSellingProduct(int n) {
		return null;
	}
	
	public double calDailyRevenue(Date date) {
		return 0;
	}
	
	public double calTotalRevenue() {
		return 0;
	}
}

