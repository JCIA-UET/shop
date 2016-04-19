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
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("session_account");
		String accountType = account.getAccoutType();
		if (!accountType.equals("STAFF")) return ;
		
		String action = request.getParameter("action");
		if (action == null) return ;
		
		String message = "cannot do this operation";
		
		if (action.equals("buy")) {
			int customerId = (int) session.getAttribute("customer_id");
			List<Product> shoppingCart = 
					(List<Product>)session.getAttribute("shopping_cart");
			transaction.doBuy(customerId, shoppingCart);
			message = "buy successfully";
		
		} else if (action.equals("cancelorder")) {
			String orderIdStr = request.getParameter("orderid");
			int orderId = Integer.parseInt(orderIdStr);
			
			transaction.doCancel(orderId);
			message = "cancel successfully";
		}
		else if (action.equals("addToCart")){
			List<Product> shoppingCart = 
					(List<Product>) session.getAttribute("shopping_cart");
			session.removeAttribute("shopping_cart");
			String quantity = request.getParameter("quantity");
			int quantity1 = Integer.parseInt(quantity);
			Product product =(Product) session.getAttribute("product");
			product.setQuantity(quantity1);
			shoppingCart.set(0, product);
			session.setAttribute("shopping_cart", shoppingCart);
			forwardStream(request, response, "/addResult.jsp");
		}
		else if(action.equals("add")){
			String idProduct = request.getParameter("idProduct");
			int idProduct1 = Integer.parseInt(idProduct);
			Product product =(Product) transaction.getItemById(ItemType.PRODUCT, idProduct1);
			session.setAttribute("product", product);
			forwardStream(request, response, "/addToCart.jsp");
		}
		
		request.setAttribute("message", message);

	}
	
	private void forwardStream(HttpServletRequest req, HttpServletResponse rsp, String destination)
			throws ServletException, IOException {
		
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher(destination);
		dispatcher.forward(req, rsp);
	}

}
