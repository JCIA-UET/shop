package uet.jcia.shop.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import uet.jcia.shop.model.Item;
import uet.jcia.shop.model.OrderManager;
import uet.jcia.shop.model.Product;

/**
 * Servlet implementation class StatistService
 */
@WebServlet("/StatistService")
public class StatistService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderManager orderManager = new OrderManager();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StatistService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String destination = (String)session.getAttribute("request_from");
		Account account = (Account)session.getAttribute("session_account");
		
		if(account != null) {
			String accountType = account.getAccoutType();
			if(!accountType.equalsIgnoreCase("STAFF")) {
				request.setAttribute("message", "You have not privilege");
				forwardStream(request, response, destination);
				return;
			}
		}
		double[] arr = new double[3];
		
		String errorMes;
		
		String action = (String)request.getParameter("action");
		
		if(action.equals("get-day-revenue")) {
			// Check session
			if(account == null) {
				request.setAttribute("message", "You need to login first to use this feature.");
				forwardStream(request, response, "/login.jsp");
				return;
			}
			
			// If still in session, get param and execute query
			String szDay = (String)request.getParameter("day");
			String szMonth = (String)request.getParameter("month");
			String szYear = (String)request.getParameter("year");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String szDate = String.format(szYear + "-" + szMonth + "-" + szDay);
			Date date;
			try {
				date = sdf.parse(szDate);
				arr = orderManager.calDailyRevenue(date);
				request.setAttribute("action", action);
				request.setAttribute("gettopselling", false);
				request.setAttribute("result", arr);
				forwardStream(request, response, "/revenue.jsp");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				errorMes = "Date format error";
				request.setAttribute("message", errorMes);
			}
		}
		else if(action.equals("get-month-revenue")) {
			// Check session
			if(account == null) {
				request.setAttribute("message", "You need to login first to use this feature.");
				forwardStream(request, response, "/login.jsp");
				return;
			}
			
			// If still in session, get param and execute query
			String szMonth = request.getParameter("month");
			String szYear = request.getParameter("year");
			
			// Convert String to Int
			int month = Integer.parseInt(szMonth);
			
			arr = orderManager.calMonthRevenue(month);
			request.setAttribute("action", action);
			request.setAttribute("result", arr);
			forwardStream(request, response, "/revenue.jsp");
		}
		else if(action.equals("get-total-revenue")) {
			// Check session
			if(account == null) {
				request.setAttribute("message", "You need to login first to use this feature.");
				forwardStream(request, response, "/login.jsp");
				return;
			}
			
			// If still in session, get param and execute query
			arr = orderManager.calTotalRevenue();
			request.setAttribute("action", action);
			request.setAttribute("result", arr);
			forwardStream(request, response, "/revenue.jsp");
		}
		else if(action.equals("get-top-selling")) {
			//Check seesion
			if(account == null) {
				request.setAttribute("message", "You need to login first to use this feature.");
				forwardStream(request, response, "/login.jsp");
				return;
			}
			
			// If still in session, get param and execute query
			List<Item> list = orderManager.getTopSellingProduct(10);
			request.setAttribute("action", action);
			request.setAttribute("list", list);
			forwardStream(request, response, "/revenue.jsp");
		}
	}

	private void forwardStream(HttpServletRequest request, HttpServletResponse response, String destination) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher(destination);
		dispatcher.forward(request, response);
	}

}
