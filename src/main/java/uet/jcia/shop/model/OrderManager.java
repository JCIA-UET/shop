package uet.jcia.shop.model;

import java.util.Date;
import java.util.List;

public class OrderManager implements ItemManager {

	@Override
	public List<Item> getAllItems() {
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
 
	public List<Item> getTopSellingProduct(int n) {
		return null;
	}
	
	public double calDailyRevenue(Date date) {
		return 0;
	}
	
	public double calTotalRevenue() {
		return 0;
	}
}

