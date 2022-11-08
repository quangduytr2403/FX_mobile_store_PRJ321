package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDao;
import entities.Account;
import entities.Order;

/**
 * class handle request show history order
 * 
 * @author DuyTQFX11834
 *
 */
@WebServlet("/show-history")
public class ShowHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static OrderDao orderDao = new OrderDao();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Account userLogin = (Account)request.getSession().getAttribute("userLogin");
		
		try {
			ArrayList<Order> listOfOrders = orderDao.listOrderByMail(userLogin.getUserMail());
			request.setAttribute("listOfOrders", listOfOrders);
			request.getRequestDispatcher("/views/history.jsp").forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
