package uet.jcia.shop.test.model;

import uet.jcia.shop.model.*;
import java.util.*;

public class OrderManagerTest {

	public static void main(String[] args) {
		OrderManager orderManager = new OrderManager();
		Item newOrder = null;
		Item newOrderDetails = null;
		
		newOrder = new Order(1, null);
		orderManager.addItem(newOrder);
		
		newOrder = new Order(2, null);
		orderManager.addItem(newOrder);
		
		newOrder = new Order(3, null);
		orderManager.addItem(newOrder);
		
		System.out.println(orderManager.getItemById(1));
	}
}
