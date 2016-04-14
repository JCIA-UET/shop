	package uet.jcia.shop.model;

import java.util.List;

public interface ItemManager {
	
	/**
	 * connector for processing with database 
	 */
	DBConnector dbConnector = DBConnector.getInstance();
	
	/**
	 * get all items by item type
	 * @return
	 */
	List<Item> getAllItems();
	
	/**
	 * get an item by id
	 * @param id
	 * @return
	 */
	Item getItemById(int id);
	
	/**
	 * remove an item by id
	 * @param id
	 * @return
	 */
	boolean removeItemById(int id);
	
	/**
	 * set an item by id, use for create new or update
	 * @param id
	 * @param newItem
	 * @return
	 */
	boolean setItem(int id, Item newItem);
}
