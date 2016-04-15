package uet.jcia.shop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class OrderManager implements ItemManager {
	private Connection conn = null;
	
	private final String GET_ORDER_BY_ID_QUERY  = "SELECT * FROM order WHERE orderID=?";
	private final String GET_ALL_ORDERS_QUERY    = "FIND * INTO order";
	private final String GET_DETAILS_BY_ORDER_ID = "SELECT * FROM orderdetail WHERE orderID=?";
	private final String DELETE_ORDER_BY_ID      = "DELETE FROM order WHERE orderID=?";
	private final String ADD_ORDER 				 = "INSERT INTO order (customerID) VALUES(?)";
	
	/**
	 * get order 
	 * 
	 * @param id           id of order
	 * @return Item		   item
	 */
	@Override
	public Item getItemById(int id) {
		// TODO Auto-generated method stub
		conn = dbConnector.createConnection();
		Order item = null;
		
		try {
			PreparedStatement stt = conn.prepareStatement(GET_ORDER_BY_ID_QUERY);
			stt.setInt(1, id);
			
			ResultSet rs = stt.executeQuery();
			rs.next();
			
			int customerID = rs.getInt("customerID");
			List<OrderDetails> list = this.GetDetailsById(id);
			
			item = new Order(id, customerID, list);
			
			System.out.println("OrderID: " + item.getOrderId() + " CustomerID: " + item.getCustomerId());
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Fail!");
			return null;
		}
		finally { 
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return (Order)item;
	}
	
	/**
	 * get all products in an order
	 * 
	 * @param id                    id of order want to get details
	 * @return List<OrderDetails>   list of item in order
	 */
	@Override
	public List<Item> getAllItems() {
		// TODO Auto-generated method stub
		conn = dbConnector.createConnection();
		List<Item> resultList = null;
		
		try {
			PreparedStatement stt = conn.prepareStatement(GET_ALL_ORDERS_QUERY);
			ResultSet rs = stt.executeQuery();
			while(rs.next()) {
				int orderID = rs.getInt("orderID");
				int customerID = rs.getInt("customerID");
				List<OrderDetails> list = this.GetDetailsById(orderID);
				
				Order element = new Order(orderID, customerID, list);
				resultList.add(element);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally { 
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultList;
	}

	/**
	 * get all products in an order
	 * 
	 * @param id                    id of order want to get details
	 * @return List<OrderDetails>   list of item in order
	 */
	@Override
	public boolean removeItemById(int id) {
		// TODO Auto-generated method stub
		conn = dbConnector.createConnection();
		
		try {
			PreparedStatement stt = conn.prepareStatement(DELETE_ORDER_BY_ID);
			stt.setInt(1, id);
			
			stt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	
	
	/**
	 * get all products in an order
	 * 
	 * @param id                    id of order want to get details
	 * @return List<OrderDetails>   list of item in order
	 */
	public List<OrderDetails> GetDetailsById(int id) {
		conn = dbConnector.createConnection();
		List<OrderDetails> list = null;
		
		try {
			PreparedStatement stt = conn.prepareStatement(GET_DETAILS_BY_ORDER_ID);
			stt.setInt(1,id);
			ResultSet rs = stt.executeQuery();
			while(rs.next()) {
				int orderId = rs.getInt("orderID");
				int productId = rs.getInt("produtID");
				int quantity = rs.getInt("quantity");
				Date orderDate = rs.getDate("orderDate");
				
				OrderDetails temp = new OrderDetails(productId, orderId, quantity, orderDate);
				list.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	public boolean addItem(Item newItem) {
		if(!(newItem instanceof Order)) return false;
		
		conn = dbConnector.createConnection();
		
		try {
			PreparedStatement stt = conn.prepareStatement(ADD_ORDER);
			Order currentItem = (Order)newItem;
			int customerID = currentItem.getCustomerId();
			int orderID = currentItem.getId();
			List<OrderDetails> list = this.GetDetailsById(orderID);
			stt.setInt(1, customerID);
			
			stt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * get all products in an order
	 * 
	 * @param id                    id of order want to get details
	 * @return List<OrderDetails>   list of item in order
	 */
	public List<Item> getTopSellingProduct(int n) {
		conn = dbConnector.createConnection();
		return null;
	}
	
	public double calDailyRevenue(Date date) {
		return 0;
	}
	
	public double calTotalRevenue() {
		return 0;
	}
	
	// Please don't care about these code below. Do not modify!
	@Override
	public boolean setItem(int id, Item newItem) {
		// TODO Auto-generated method stub
		return false;
	}
}

