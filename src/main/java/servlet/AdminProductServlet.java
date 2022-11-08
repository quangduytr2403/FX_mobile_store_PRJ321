package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDao;
import entities.Product;

/**
 * class handle request display product admin page
 * 
 * @author DuyTQFX11834
 *
 */
@WebServlet("/admin/product")
public class AdminProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ProductDao productDao = new ProductDao();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String keyword = request.getParameter("keyword");
			ArrayList<Product> listOfProducts = productDao.searchProduct(1, Integer.MAX_VALUE, keyword);
			request.setAttribute("listOfProducts", listOfProducts);
			request.getRequestDispatcher("/views/admin/product.jsp").forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
