package uet.jcia.shop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderManager implements ItemManager {
	private Connection conn = null;
	
	private final String GET_ORDER_BY_ID_QUERY  = "SELECT * FROM shop.order WHERE orderID=?";
	private final String GET_ALL_ORDERS_QUERY    = "SELECT * FROM shop.order";
	private final String GET_DETAILS_BY_ORDER_ID = "SELECT * FROM shop.orderdetail WHERE orderID=?";
	private final String DELETE_ORDER_BY_ID      = "DELETE FROM shop.order WHERE orderID=?";
	private final String ADD_NEW_ORDER 				 = "INSERT INTO shop.order (customerID) VALUES(?)";
	private final String GET_TOP_SELLING_PRODUCT = "SELECT product.productID, SUM(orderdetail.Quantity) AS Quantity FROM orderdetail INNER JOIN product ON product.productID = orderdetail.productID GROUP BY orderdetail.productID ORDER BY Quantity DESC LIMIT ?";
	
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
			
			//System.out.println("OrderID: " + item.getOrderId() + " CustomerID: " + item.getCustomerId());
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
		List<Item> resultList = new ArrayList<>();
		
		try {
			PreparedStatement stt = conn.prepareStatement(GET_ALL_ORDERS_QUERY);
			ResultSet rs = stt.executeQuery();
			while(rs.next()) {
				int orderID = rs.getInt("orderID");
				int customerID = rs.getInt("customerID");
				List<OrderDetails> list = this.GetDetailsById(orderID);
				
				Order element = new Order(orderID, customerID, list);
				resultList.add(element);
				System.out.println("OrderID: " + element.getOrderId() + " CustomerID: " + element.getCustomerId() + " Details: " + element.getOrderDetails());
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
		List<OrderDetails> list = new ArrayList<>();
		
		try {
			PreparedStatement stt = conn.prepareStatement(GET_DETAILS_BY_ORDER_ID);
			stt.setInt(1,id);
			ResultSet rs = stt.executeQuery();
			while(rs.next()) {
				int orderId = rs.getInt("orderID");
				int productId = rs.getInt("productID");
				int quantity = rs.getInt("quantity");
				Timestamp orderDate = rs.getTimestamp("orderDate");
				
				OrderDetails temp = new OrderDetails(productId, orderId, quantity, orderDate);
				list.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public boolean addItem(Item newItem) {
		if(!(newItem instanceof Order)) return false;
		conn = dbConnector.createConnection();
		
		try {
			PreparedStatement stt = conn.prepareStatement(ADD_NEW_ORDER);
			Order currentItem = (Order)newItem;
			int customerID = currentItem.getCustomerId();
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
		List<Item> list = new ArrayList<>();
		ProductManager pm = new ProductManager();
		
		try {
			PreparedStatement stt = conn.prepareStatement(GET_TOP_SELLING_PRODUCT);
			stt.setInt(1, n);
			ResultSet rs = stt.executeQuery();
			while(rs.next()) {
				int productID = rs.getInt("productID");
				Product product = (Product)pm.getItemById(productID);
				list.add(product);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public double calDailyRevenue(Date date) {
		return 0;
	}
	
	public double calTotalRevenue() {
		return 0;
	}

	@Override
	public boolean setItem(int id, Item newItem) {
		// TODO Auto-generated method stub
		return false;
	}
}

