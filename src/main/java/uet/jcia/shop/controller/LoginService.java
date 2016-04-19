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
import uet.jcia.shop.model.AccountManager;
import uet.jcia.shop.model.Product;

/**
 * Servlet implementation class LoginService
 */
@WebServlet("/LoginService")
public class LoginService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Account account = new Account("bqc", "12345", 10, "cuong@vnu", "STAFF");
//		HttpSession session = request.getSession();
//		session.setAttribute("session_account", account);
//		response.getWriter().append("DONE");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		AccountManager accountManager = new AccountManager();
		Account account = accountManager.authenticate(username, password);
		
		String message = "login successfully!";
	
		if (account != null) {
			// set Account Object to the session
			HttpSession session = request.getSession();
			session.setAttribute("session_account", account);
			
			// if user is Customer, add shopping cart to the session
			if(account.getAccoutType().equals("CUSTOMER")){
				List<Product> shoppingCart = new ArrayList<>();
				session.setAttribute("shopping_cart", shoppingCart);				
			}
			
//			else if(account.getAccoutType().equals("STAFF")){
//				
//			}
		
		} else {
			message = "login fail!";
		}
		
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/login_result.jsp");
		request.setAttribute("message", message);
		dispatcher.forward(request, response);
	}
	

}
