package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Cart;

/**
 * class handle request remove item in cart
 * 
 * @author DuyTQFX11834
 *
 */
@WebServlet("/cart/remove-item")
public class RemoveItemInCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));			
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			cart.getItems().remove(id - 1);
			request.getSession().setAttribute("cart", cart);
			response.sendRedirect(request.getContextPath() + "/cart/show-cart");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
