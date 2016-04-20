package uet.jcia.shop.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import uet.jcia.shop.model.Account;
import uet.jcia.shop.model.ItemType;
import uet.jcia.shop.model.Product;
import uet.jcia.shop.model.Transaction;

@WebServlet("/TransactionService")
public class TransactionService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Transaction transaction = new Transaction();
	
    public TransactionService() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = "cannot do this operation";
		
		HttpSession session = request.getSession();
		String destination = (String) session.getAttribute("request_from");
		Account account = (Account) session.getAttribute("session_account");
		if (account == null) {
			request.setAttribute("message", "You have not logged in");
			forwardStream(request, response, destination);
			return ;
		}
		
		String accountType = account.getAccoutType();
		String action = request.getParameter("action");
		
		if (!accountType.equalsIgnoreCase("CUSTOMER")) {
			System.out.println(accountType +"----"+destination);
			message = "you have not previlege";
			
		} else if (action == null) {
			
		} else if (action.equals("buy")) {
			int customerId = (int) session.getAttribute("customer_id");
			List<Product> shoppingCart = 
					(List<Product>)session.getAttribute("shopping_cart");
			boolean test = transaction.doBuy(customerId, shoppingCart);
			
			if(test){
				message = "Buy Successfull";
			} else {
				message = "Buy fails";
			}
			
			destination = "/buyResult.jsp";
		
		} else if (action.equals("cancelorder")) {
			String orderIdStr = request.getParameter("orderid");
			int orderId = Integer.parseInt(orderIdStr);
			
			boolean test =  transaction.doCancel(orderId);
			message = "cancel successfully";
			
		} else if (action.equals("addToCart")){
			List<Product> shoppingCart = 
					(List<Product>) session.getAttribute("shopping_cart");
			
			String quantityStr = request.getParameter("quantity");
			String productIdStr = request.getParameter("productid");
			String productName = request.getParameter("productname");
					
					
			int quantity = Integer.parseInt(quantityStr);
			int productId = Integer.parseInt(productIdStr);
			
			Product product = new Product();
			product.setId(productId);
			product.setName(productName);
			product.setQuantity(quantity);
			
			shoppingCart.add(product);
			
			message = "add to cart successfully!";
			destination =  "/addResult.jsp";
		}
		
		request.setAttribute("message", message);
		forwardStream(request, response, destination);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null) return;

		String message = null;
		HttpSession session = request.getSession();
		String destination = (String) session.getAttribute("request_from");
			
		if (action.equals("requestaddtocart")){
			String idProduct = request.getParameter("idProduct");
			int idProduct1 = Integer.parseInt(idProduct);
			Product product =(Product) transaction.getItemById(ItemType.PRODUCT, idProduct1);
			session.setAttribute("product", product);
			destination = "/addToCart.jsp";
		
		} else {
			message = "cannot do this operation";
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
