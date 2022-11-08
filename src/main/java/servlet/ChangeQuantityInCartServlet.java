package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Cart;
import entities.Product;

/**
 * class handle request change quantity in cart
 * 
 * @author DuyTQFX11834
 *
 */
@WebServlet("/cart/change-quantity")
public class ChangeQuantityInCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));	
			int value = Integer.parseInt(request.getParameter("value"));	
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			Product productChange = cart.getItems().get(id - 1);
			productChange.setNumber(value);
			cart.getItems().set(id - 1, productChange);
			request.getSession().setAttribute("cart", cart);
			response.sendRedirect(request.getContextPath() + "/cart/show-cart");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
