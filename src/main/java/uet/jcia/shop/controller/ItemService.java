package uet.jcia.shop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Constants;

import uet.jcia.shop.model.Account;
import uet.jcia.shop.model.Item;
import uet.jcia.shop.model.ItemType;
import uet.jcia.shop.model.Transaction;

/**
 * Servlet implementation class ItemService
 */
@WebServlet("/ItemService")
public class ItemService extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Transaction transaction = new Transaction();
    
    public ItemService() {
        super();
    }

    private List<Item> getAllItemsOperation(String itemType) {
    	List<Item> items = null;
    	
    	if (itemType.equalsIgnoreCase(ItemType.CATEGORY.name())) {
			items =	transaction.getAllItems(ItemType.CATEGORY);
			
		} else if (itemType.equalsIgnoreCase(ItemType.PRODUCT.name())) {
			items = transaction.getAllItems(ItemType.PRODUCT);
			
		} else if (itemType.equalsIgnoreCase(ItemType.ORDER.name())) {
			items = transaction.getAllItems(ItemType.ORDER);
			
		}
    	return items;
    }
    
    private Item getItemOperation(String itemType, int id) {
    	Item item = null;
    	if (itemType.equalsIgnoreCase(ItemType.CATEGORY.name())) {
			item = transaction.getItemById(ItemType.CATEGORY, id);
			
		} else if (itemType.equalsIgnoreCase(ItemType.PRODUCT.name())) {
			item = transaction.getItemById(ItemType.PRODUCT, id);
			
		} else if (itemType.equalsIgnoreCase(ItemType.ORDER.name())) {
			item = transaction.getItemById(ItemType.ORDER, id);
			
		}
    	return item;
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String itemType = request.getParameter("itemtype");
		String itemId = request.getParameter("itemid");
		
		if (action == null) return;
		
		if (action.equals("getallitems") && itemType != null) {
			List<Item> items = getAllItemsOperation(itemType);					
			request.setAttribute("items", items);
		
		} else if (action.equals("getitem") && itemType != null && itemId != null) {
			Item item = null;
			int id = Integer.parseInt(itemId);
			getItemOperation(itemType, id);
			request.setAttribute("item", item); 
			
		} else {
			request.setAttribute("message", "cannot do this operation");
		}
		
		forwardStream(request, response, "/items-list.jsp");
	}
	
	private boolean removeItemOperation(String itemType, int id) {
		boolean result = false;
		if (itemType.equalsIgnoreCase(ItemType.CATEGORY.name())) {
			 result = transaction.removeItem(ItemType.CATEGORY, id);
			
		} else if (itemType.equalsIgnoreCase(ItemType.PRODUCT.name())) {
			result = transaction.removeItem(ItemType.PRODUCT, id);
			
		} else if (itemType.equalsIgnoreCase(ItemType.ORDER.name())) {
			result = transaction.removeItem(ItemType.ORDER, id);
			
		}
		
		return result;
	}
	
	private boolean addItemOpreation(String itemType,
			HttpServletRequest request, HttpServletResponse response) {
		boolean result = false;
		
		if (itemType.equalsIgnoreCase(ItemType.CATEGORY.name())) {
			String categoryName = request.getParameter("categoryname");
			result = transaction.addCategory(categoryName);
		
		} else if (itemType.equalsIgnoreCase(ItemType.PRODUCT.name())) {
			String productName = request.getParameter("productname");
			String priceStr = request.getParameter("productprice");
			double price = Double.parseDouble(priceStr);
			String quantityStr = request.getParameter("quantity");
			int quantity = Integer.parseInt(quantityStr);
			String categoryIdStr = request.getParameter("categoryid");
			int categoryId = Integer.parseInt(categoryIdStr);
			String description = request.getParameter("description");
			
			result = transaction.addProduct(
					productName, price, quantity, categoryId, description);
		}
		
		return result;
	}
	
	private boolean updateItemOperation(String itemType, int id,
			HttpServletRequest request, HttpServletResponse response) {
		boolean result = false;
		
		if (itemType.equalsIgnoreCase(ItemType.CATEGORY.name())) {
			String newcategoryIdStr = request.getParameter("newcategoryid");
			int newId = Integer.parseInt(newcategoryIdStr);
			String categoryName = request.getParameter("categoryname");
			result = transaction.updateCategory(id, newId, categoryName);
		
		} else if (itemType.equalsIgnoreCase(ItemType.PRODUCT.name())) {
			String newcategoryIdStr = request.getParameter("newproductid");
			int newId = Integer.parseInt(newcategoryIdStr);
			String productName = request.getParameter("productname");
			String priceStr = request.getParameter("productprice");
			double price = Double.parseDouble(priceStr);
			String quantityStr = request.getParameter("quantity");
			int quantity = Integer.parseInt(quantityStr);
			String categoryIdStr = request.getParameter("categoryname");
			int categoryId = Integer.parseInt(categoryIdStr);
			String description = request.getParameter("description");
			
			result = transaction.updateProduct(
							id, newId, productName, price,
							quantity, categoryId, description);
		}
		
		return result;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String destination = (String) session.getAttribute("request_from");
		Account account = (Account) session.getAttribute("session_account");
		
		if (account == null) {
			request.setAttribute("message", "You have not logged in");
			forwardStream(request, response, destination);
		}
		
		String accountType = account.getAccoutType();
		if (!accountType.equalsIgnoreCase("STAFF")) {
			request.setAttribute("message", "You have not privilege");
			forwardStream(request, response, destination);
		}
		
		String action = request.getParameter("action");
		String itemType = request.getParameter("itemtype");
		String itemId = request.getParameter("itemid");
		
		if (action == null || itemType == null)
			return ;
				
		boolean result = false;
		String message = "cannot do this operation";
		
		if (action.equals("remove")) {
			int id = Integer.parseInt(itemId);
			result = removeItemOperation(itemType, id);
			
			if (result) {
				message = "remove successfully!";
				destination = "/home.jsp";
			}
			
		} else if (action.equals("add")) {
			result = addItemOpreation(itemType, request, response);
			
			if (result) {
				message = "add successully";
			}
		
		} else if (action.equals("update")) {
			int id = Integer.parseInt(itemId);
			result = updateItemOperation(itemType, id, request, response);
					
			if (result) {
				message = "add successully";
			}
		}
		
		request.setAttribute("message", message);
		forwardStream(request, response, destination);
	}

	private void forwardStream(HttpServletRequest req, HttpServletResponse rsp, String destination)
			throws ServletException, IOException {
		
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher(destination);
		dispatcher.forward(req, rsp);
	}
}
