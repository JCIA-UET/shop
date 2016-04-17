package uet.jcia.shop.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uet.jcia.shop.model.Account;
import uet.jcia.shop.model.AccountManager;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginservlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			
			
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		AccountManager accountManager = new AccountManager();
		Account account = accountManager.authenticate(username, password);
		
	
		if( account != null && account.getAccoutType().equals("CUSTOMER")){
			RequestDispatcher rs = request.getRequestDispatcher("/home.jsp");
			rs.forward(request, response);
		}
		else if(account != null && account.getAccoutType().equals("STAFF")){
			RequestDispatcher rs = request.getRequestDispatcher("/homeAd.jsp");
			rs.forward(request, response);
		}
		else {
			RequestDispatcher rs = request.getRequestDispatcher("/login.jsp");
			rs.forward(request, response);
		}
	}
	

}
