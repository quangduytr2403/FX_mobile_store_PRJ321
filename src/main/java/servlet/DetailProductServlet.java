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
 * class handle request display detail product
 * 
 * @author DuyTQFX11834
 *
 */
@WebServlet("/detail")
public class DetailProductServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	ProductDao productDao = new ProductDao();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
        
		try {
			Product product = productDao.getDetailProduct(id);
			ArrayList<Product> listOfPopularProducts = productDao.getPopularProduct();
			
			request.setAttribute("product", product);
			request.setAttribute("listOfPopularProducts", listOfPopularProducts);
			request.getRequestDispatcher("/views/detail-product.jsp").forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
