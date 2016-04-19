package uet.jcia.shop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderManager implements ItemManager {
	private Connection conn = null;
	
	private final String GET_ORDER_BY_ID_QUERY  		= "SELECT * FROM shop.order WHERE orderID=?";
	private final String GET_ALL_ORDERS_QUERY   		= "SELECT * FROM shop.order";
	private final String GET_DETAILS_BY_ORDER_ID_QUERY 	= "SELECT * FROM shop.orderdetail WHERE orderID=?";
	private final String DELETE_ORDER_BY_ID_QUERY    	= "DELETE FROM shop.order WHERE orderID=?";
	private final String ADD_NEW_ORDER_QUERY	 		= "INSERT INTO shop.order (customerID) VALUES(?)";
	private final String ADD_NEW_ORDERDETAIL_QUERY		= "INSERT INTO shop.orderdetail (orderID, productID, quantity, orderDate) VALUES (?,?,?,now())";
	private final String GET_TOP_SELLING_PRODUCT_QUERY 	= "SELECT product.productID, SUM(orderdetail.Quantity) AS Quantity FROM orderdetail INNER JOIN product ON product.productID = orderdetail.productID GROUP BY orderdetail.productID ORDER BY Quantity DESC LIMIT ?";
	private final String GET_REVENUE_IN_DAY_QUERY 		= "SELECT DATE(orderDate) AS Date, orderdetail.quantity, SUM(orderdetail.quantity * product.price) AS Revenue FROM shop.orderdetail LEFT JOIN product ON product.productID = orderdetail.productID WHERE DATE(orderDate)=? GROUP BY orderId, orderdetail.productID";
	private final String GET_REVENUE_IN_MONTH_QUERY		= "SELECT MONTH(DATE(orderDate)) as Month, orderdetail.quantity, SUM(orderdetail.quantity * product.price) AS Revenue FROM shop.orderdetail LEFT JOIN product ON product.productID = orderdetail.productID WHERE DATE(orderDate) BETWEEN ? AND ? GROUP BY orderId, orderdetail.productID";
	private final String GET_TOTAL_REVENUE_QUERY		= "SELECT orderdetail.quantity, SUM(orderdetail.quantity * product.price) as Revenue FROM shop.orderdetail LEFT JOIN product ON product.productID = orderdetail.productID GROUP BY orderId, orderdetail.productID";
	
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
			PreparedStatement stt = conn.prepareStatement(DELETE_ORDER_BY_ID_QUERY);
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
	 * get all details in an order
	 * 
	 * @param id                    id of order want to see details
	 * @return List<OrderDetails>   list of orderdetail in order
	 */
	public List<OrderDetails> GetDetailsById(int id) {
		conn = dbConnector.createConnection();
		List<OrderDetails> list = new ArrayList<>();
		
		try {
			PreparedStatement stt = conn.prepareStatement(GET_DETAILS_BY_ORDER_ID_QUERY);
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
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public boolean addDetailToOrder(OrderDetails detail) {
		conn = dbConnector.createConnection();
		
		try {
			PreparedStatement stt = conn.prepareStatement(ADD_NEW_ORDERDETAIL_QUERY);
			stt.setInt(1, detail.getOrderId());
			stt.setInt(2, detail.getProductId());
			stt.setInt(3, detail.getQuantity());
			
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
	 * add a new order into database
	 * @param newItem			order want to add
	 * @return					true if the addition is successful . Otherwise return false.
	 */
	@Override
	public boolean addItem(Item newItem) {
		if(!(newItem instanceof Order)) return false;
		conn = dbConnector.createConnection();
		
		try {
			PreparedStatement stt = conn.prepareStatement(ADD_NEW_ORDER_QUERY);
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
	 * get the list of best-selling products
	 * @param n                    	the number of products want to list
	 * @return List<OrderDetails>   list of products
	 */
	public List<Item> getTopSellingProduct(int n) {
		conn = dbConnector.createConnection();
		List<Item> list = new ArrayList<>();
		ProductManager pm = new ProductManager();
		
		try {
			PreparedStatement stt = conn.prepareStatement(GET_TOP_SELLING_PRODUCT_QUERY);
			stt.setInt(1, n);
			ResultSet rs = stt.executeQuery();
			while(rs.next()) {
				int productID = rs.getInt("productID");
				int quantity = rs.getInt("Quantity");
				Product product = (Product)pm.getItemById(productID);
				product.setQuantity(quantity);
				list.add(product);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * compute revenue in a day
	 * @param date				the day we want to see revenue
	 * @return revenue			revenue of this day
	 */
	public double[] calDailyRevenue(Date date) {
		conn = dbConnector.createConnection();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String szDate = sdf.format(date);
		double[] arr = new double[3];
		
		try {
			PreparedStatement stt = conn.prepareStatement(GET_REVENUE_IN_DAY_QUERY);
			stt.setString(1, szDate);
			
			ResultSet rs = stt.executeQuery();
			while(rs.next()) {
				arr[0]++;
				arr[1] += (double)rs.getInt("quantity");
				arr[2] += rs.getInt("Revenue");
			}
			return arr;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			arr[0] = arr[1] = arr[2] = 0.0;
			return arr;
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
	 * compute revenue in a month
	 * @param month				the month we want to see revenue
	 * @return revenue			revenue of this month
	 */
	
	public double[] calMonthRevenue(int month) {
		int lastDay;
		String szDate;
		double[] arr = new double[3];
		conn = dbConnector.createConnection();
	
		if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) lastDay = 31;
		else if(month == 2) lastDay = 28;
		else lastDay = 30;
		
		try {
			PreparedStatement stt = conn.prepareStatement(GET_REVENUE_IN_MONTH_QUERY);
			szDate = String.format("2016-" + month + "-" + 1);
			stt.setString(1, szDate);
			szDate = String.format("2016-" + month + "-" + lastDay);
			stt.setString(2, szDate);
			
			ResultSet rs = stt.executeQuery();
			while(rs.next()) {
				arr[0]++;
				arr[1] += (double)rs.getInt("quantity");
				arr[2] += rs.getInt("Revenue");
			}
			return arr;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			arr[0] = arr[1] = arr[2] = 0.0;
			return arr;
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
	 * compute total revenue
	 * @param month
	 * @return
	 */
	public double[] calTotalRevenue() {
		conn = dbConnector.createConnection();
		double[] arr = new double[3];
		try {
			PreparedStatement stt = conn.prepareStatement(GET_TOTAL_REVENUE_QUERY);
			ResultSet rs = stt.executeQuery();
			
			while(rs.next()) {
				arr[0]++;
				arr[1] += (double)rs.getInt("quantity");
				arr[2] += rs.getInt("Revenue");
			}
			
			return arr;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			arr[0] = arr[1] = arr[2] = 0.0;
			return arr;
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
	public boolean setItem(int id, Item newItem) {
		// TODO Auto-generated method stub
		return false;
	}

}

