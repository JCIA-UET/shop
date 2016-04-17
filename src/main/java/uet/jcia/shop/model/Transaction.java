package uet.jcia.shop.model;

import java.util.List;

public class Transaction {
	
	public boolean doBuy(int customerId, List<Product> products) {		
		return true;
	}
	
	public boolean doCancel(int order) {
		return false;
	}
	
	public List<Item> getAllItems(ItemType type) {
		return null;
	}
	
	public Item getItemById(ItemType type, int itemId) {
		return null;
	}
	
	public boolean removeItem(ItemType type, int itemId) {
		return false;
	}
	
	public boolean addCategory(String categoryName) {
		return false;
	}
	
	public boolean addProduct(String pName, double price, int quantity,
			int cateId, String des) {
		return false;
	}
	
	public boolean updateCategory(int id, int newId, String name) {
		return false;
	}
	
	public boolean updateProduct(int id, int newId, String name,
			double price, int quantity, int categoryId, String des) {
		return false;
	}
	
}
