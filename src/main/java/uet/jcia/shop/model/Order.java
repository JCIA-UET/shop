package uet.jcia.shop.model;

public class Order extends Item {
	private int customerId;
	private OrderDetails orderDetails;
	
	public Order() {}
	
	public Order(int customerId, OrderDetails orderDetails) {
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
	
	public OrderDetails getOrderDetails() {
		return orderDetails;
	}
	
	public void setOrderDetails(OrderDetails orderDetails) {
		this.orderDetails = orderDetails;
	}

	
}
