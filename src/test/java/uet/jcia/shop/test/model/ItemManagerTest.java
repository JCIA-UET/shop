package uet.jcia.shop.test.model;

import uet.jcia.shop.model.*;

public class ItemManagerTest {

	public static void main(String[] args) {
//		ItemManager categoryManager = new CategoryManager();
//		Item newCategory = new Category(4, "Phu Kien");
//		System.out.println(categoryManager.setItem(4, newCategory));

//		System.out.println(categoryManager.getItemById(4));
//		System.out.println(categoryManager.removeItemById(4));
//		System.out.println(categoryManager.getAllItems());

		ItemManager productManager = new ProductManager();
		Item newProduct = null;
		
		newProduct = new Product(
				"Iphone 6", 100, 16000000.0, 1,
				"Dien thoai Iphone 6");
		
		productManager.addItem(newProduct);
//		System.out.println(productManager.setItem(3, newProduct));
//		System.out.println(productManager.removeItemById(3));
//		System.out.println(productManager.getAllItems());		
	}

}
