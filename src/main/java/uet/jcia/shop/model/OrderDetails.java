package uet.jcia.shop.model;

import java.sql.Timestamp;


public class OrderDetails {
	private int orderId;
	private int productId;
	private int quantity;
	private Timestamp orderDate;
	
	public OrderDetails() {}
	
	public OrderDetails(int orderId, int  productId, int quantity, Timestamp orderDate) {
		this.productId = productId;
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.quantity = quantity;
	}
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Timestamp getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return String.format("ProductID:" + productId + " OrderID:" + orderId + " Quantity:" + quantity + " Order Date:" + orderDate);
	}
}
