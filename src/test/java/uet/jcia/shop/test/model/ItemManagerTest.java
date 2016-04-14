package uet.jcia.shop.test.model;

import uet.jcia.shop.model.*;

public class ItemManagerTest {

	public static void main(String[] args) {
		ItemManager categoryManager = new CategoryManager();
//		Item newCategory = new Category(3, "Thiet Bi Van Phong");
//		System.out.println(categoryManager.setItem(3, newCategory));

//		System.out.println(categoryManager.getItemById(4));
//		System.out.println(categoryManager.removeItemById(4));
		System.out.println(categoryManager.getAllItems());

	}

}
