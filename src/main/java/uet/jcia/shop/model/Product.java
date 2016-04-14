package uet.jcia.shop.model;

public class Product extends Item {
	private int quantity;
	private double price;
	private int categoryId;
	private String description;
	
	public Product() {
		
	}

	public Product(
			int id, String name,
			int quantity, double price, int categoryId, String description) {
		
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.categoryId = categoryId;
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
	
}
