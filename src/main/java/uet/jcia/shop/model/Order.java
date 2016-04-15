package uet.jcia.shop.model;

import java.sql.*;
import java.util.List;

public class Order extends Item implements ItemManager{
	private int customerId;
	private List<OrderDetails> orderDetails;
	
	
	
	public Order() {}
	
	public Order(int customerId, List<OrderDetails> orderDetails) {
		super();
		this.customerId = customerId;
		this.orderDetails = orderDetails;
	}
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}
	
	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}
	


	
}
