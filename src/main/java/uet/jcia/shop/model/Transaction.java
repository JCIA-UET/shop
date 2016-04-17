package uet.jcia.shop.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Transaction {
	
	private ProductManager productManager = new ProductManager();
	private OrderManager orderManager = new OrderManager();
	private CategoryManager categoryManager = new CategoryManager();
	
	public boolean doBuy(int customerId, List<Product> products) {
		
		List<OrderDetails> listOrderDetail = new ArrayList<OrderDetails>();
		for(int i = 0 ; i < products.size();i++){
			Product check =(Product) productManager.getItemById(products.get(i).getId());
			if(check.getQuantity() < products.get(i).getQuantity())
				return false; // vi so luong trong kho nho hon so luong yeu cau
			listOrderDetail.add(new OrderDetails(customerId,products.get(i).getId(),
					products.get(i).getQuantity(), null));
		}
		Order order = new Order(customerId, listOrderDetail);
		boolean result = orderManager.addItem(order);
		return result;
	}
	
	public boolean doCancel( int orderId){
		boolean result = orderManager.removeItemById(orderId);
		return result;
	}
	public boolean addCategory(String nameCategory){
		Category category = new Category(nameCategory);
     	boolean result = categoryManager.addItem(category);
		return result;
	}
	public boolean addProduct(String productName , double price , int quantity,int categoryId, String description){
		Product product = new Product(productName,quantity,price,categoryId, description);
		boolean result = productManager.addItem(product);
		return result;
	}
	public List<Item> getAllItems(ItemType type){
		List<Item> result = null;
		if(type == ItemType.PRODUCT){
			result = productManager.getAllItems();
			
		}
		else if(type == ItemType.CATEGORY){
			result = categoryManager.getAllItems();
		}
		else {
			result = orderManager.getAllItems();
		}
		return result;
	}
	public boolean removeItem(ItemType type,int itemId){
		boolean result;
		if(type == ItemType.CATEGORY){
			result =  categoryManager.removeItemById(itemId);
		}
		else if(type == ItemType.ORDER){
			result = orderManager.removeItemById(itemId);
		}
		else {
			result = productManager.removeItemById(itemId);
		}
		return result;
		
	}
	
	public Item getItemById(ItemType type, int itemId) {
		Item item = null;
		
		if(type == ItemType.CATEGORY){
			item =  categoryManager.getItemById(itemId);
		}
		else if(type == ItemType.ORDER){
			item = orderManager.getItemById(itemId);
		}
		else if (type == ItemType.PRODUCT) {
			item = productManager.getItemById(itemId);
		}
		
		return item;
	}
	
	public boolean updateCategory(int id , int newId , String description){
		Category newItem = new Category(newId,description);
		boolean result = categoryManager.setItem(id, newItem);
		return result;
	}
	public boolean updateProduct(int id , int newId ,String name , double price, int quantity ,	int categoryId, String description )
	{
		Product newProduct = new Product(newId,name,quantity,price,categoryId,description);
		boolean result = productManager.setItem(id, newProduct);
		return result;
	}
	public List<Product> getTopSelling(int n){
		List<Item> items = orderManager.getTopSellingProduct(n);
		List<Product> products = (List<Product>)(List<?>) items;
		return products;
	}
	public double getDailyRevenue(Date date){
		double result = orderManager.calDailyRevenue(date);
		return result;
	}
	public double getTotalRevenue(){
		double result = orderManager.calTotalRevenue();
		return result;
	}
}
