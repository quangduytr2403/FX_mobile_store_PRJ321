package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDao;
import entities.Account;
import entities.Cart;
import entities.Order;
import utils.Constants;

/**
 * class handle request submit order
 * 
 * @author DuyTQFX11834
 *
 */
@WebServlet("/cart/submit-order")
public class SubmitOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static OrderDao orderDao = new OrderDao();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean check = true;
    	
		String userMail = ((Account)request.getSession().getAttribute("userLogin")).getUserMail();
		Cart cart = (Cart)request.getSession().getAttribute("cart");
        String customerName = request.getParameter("customerName");
        String customerAddress = request.getParameter("customerAddress");
        String discount = request.getParameter("discount");
        
        //Validate form
        if("".equals(customerName)) {
        	check = false;
        	request.setAttribute("customerNameFeedback", Constants.EMPTY_FEEDBACK_MESSAGE);
        } 
        if("".equals(customerAddress)) {
        	check = false;
        	request.setAttribute("customerAddressFeedback", Constants.EMPTY_FEEDBACK_MESSAGE);
        } 
        if (!discount.matches(Constants.DISCOUNT_REGEX)) {
        	check = false;
        	request.setAttribute("discountFeedback", Constants.DISCOUNT_FEEDBACK_MESSAGE);
        }
        
        Order order = new Order(userMail, customerName, 1, discount, customerAddress);
        
        //If input is not correct
        if(!check) {
            request.setAttribute("order", order);
            request.getRequestDispatcher("../views/cart.jsp").forward(request, response);
            return;
        }
		
		try {
			orderDao.submitOrder(order, cart);
			//reset cart
			request.getSession().setAttribute("cart", new Cart());
			response.sendRedirect(request.getContextPath() + "/show-history");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
