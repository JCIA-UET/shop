package uet.jcia.shop.test.model;

import uet.jcia.shop.model.*;

public class ItemManagerTest {

	public static void main(String[] args) {
//		ItemManager categoryManager = new CategoryManager();
//		Item newCategory = new Category("Tablet");
//		
//		System.out.println(categoryManager.addItem(newCategory));
//		System.out.println(categoryManager.setItem(4, newCategory));
//		System.out.println(categoryManager.getAllItems());
//		System.out.println(categoryManager.getItemById(4));
//		System.out.println(categoryManager.removeItemById(5));
//		System.out.println(categoryManager.getAllItems());
//
		ItemManager productManager = new ProductManager();
		Item newProduct = null;
//		
//		newProduct = new Product(
//				1, "Iphone 6", 100, 16000000.0, 1,
//				"Dien thoai Iphone 6");
//		
//		newProduct = new Product(
//				2, "Samsung Galaxy S6", 100, 14000000, 1,
//				"Dien thoai Samsung Galaxy S6");
//		
//		newProduct = new Product(
//				3, "Xperia Z5", 100, 18000000, 1,
//				"Dien thoai Sony Xperia Z5");
//		
		newProduct = new Product(
				4, "Xperia Z5", 200, 20000000, 1,
				"Dien thoai Sony Xperia Z5");
//		
//		System.out.println(productManager.addItem(newProduct));
		System.out.println(productManager.setItem(4, newProduct));
//		System.out.println(productManager.removeItemById(3));
		System.out.println(productManager.getAllItems());		
	}

}
