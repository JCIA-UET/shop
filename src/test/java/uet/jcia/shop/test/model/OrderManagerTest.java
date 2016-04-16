package uet.jcia.shop.test.model;

import uet.jcia.shop.model.*;

/**
 * @author dinht_000
 *
 */
public class OrderManagerTest {

	public static void main(String[] args) {
		OrderManager orderManager = new OrderManager();
		Item newOrder = null;
		Item newOrderDetails = null;
		
		//System.out.println("Get order by id:");
		//orderManager.getItemById(1);
		System.out.println("Get All Order:");
		orderManager.getAllItems();
		//System.out.println("Get details:");
		//System.out.println(orderManager.GetDetailsById(1));
		System.out.println("Remove Order");
		orderManager.removeItemById(1);
		System.out.println("Get All Order:");
		orderManager.getAllItems();
		
		newOrder = new Order(1);
		orderManager.addItem(newOrder);
		
		orderManager.getTopSellingProduct(10);
	}
}
