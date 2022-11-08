package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDao;
import entities.Cart;
import entities.Product;

/**
 * class handle request add product to cart
 * 
 * @author DuyTQFX11834
 *
 */
@WebServlet("/cart/add-to-cart")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static ProductDao productDao = new ProductDao();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			int productId = Integer.parseInt(request.getParameter("id"));
			Product product = productDao.getDetailProduct(productId);
			product.setNumber(1);
			
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			cart.addProduct(product);
			response.sendRedirect(request.getContextPath() + "/detail?id=" + productId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
