package uet.jcia.shop.model;

import java.util.Date;
import java.util.List;

public class OrderDetails extends Item implements ItemManager {
	private int productId;
	private int orderId;
	private Date orderDate;
	private int quantity;
	
	public OrderDetails() {}
	
	public OrderDetails(int productId, int orderId, Date orderDate, int quantity) {
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
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public List<Item> getAllItems(ItemsTypes itemsType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item getItemById(int id) {
		// TODO Auto-generated method stub
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
	
}
