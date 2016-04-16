package uet.jcia.shop.model;

import java.util.List;

public class Order extends Item {
	private int customerId;
	private List<OrderDetails> orderDetails;
	
	
	
	public Order() {}
	
	public Order(int customerId) {
		this.customerId = customerId;
		this.orderDetails = null;
	}
	
	public Order(int customerId, List<OrderDetails> orderDetails) {
		this.customerId = customerId;
		this.orderDetails = orderDetails;
	}
	
	public Order(int orderId, int customerId, List<OrderDetails> orderDetails) {
		this.id = orderId;
		this.customerId = customerId;
		this.orderDetails = orderDetails;
	}
	
	public int getOrderId() {
		return id;
	}
	
	public void setOrderId(int id) {
		this.id = id;
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
