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
 * class handle request search products
 * 
 * @author DuyTQFX11834
 *
 */
@WebServlet("/search/*")
public class SearchProductServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	ProductDao productDao = new ProductDao();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] pathInfo = request.getPathInfo().split("/");
        int currentPage = Integer.parseInt(pathInfo[1]);
        int recordsPerPage = 6;
        String keyword = request.getParameter("keyword");
        
		try {
			ArrayList<Product> listOfProducts = productDao.searchProduct(currentPage, recordsPerPage, keyword);
			int totalItems = productDao.getNumberProductSearchRecord(keyword);
			int totalPages = (int)Math.ceil(totalItems * 1.0 / recordsPerPage);
			
			ArrayList<Product> listOfPopularProducts = productDao.getPopularProduct();
			
			request.getSession().setAttribute("searchKey", keyword);
			
			request.setAttribute("currentPage", currentPage);
		    request.setAttribute("totalItems", totalItems);
		    request.setAttribute("totalPages", totalPages);
			request.setAttribute("listOfProducts", listOfProducts);
			request.setAttribute("listOfPopularProducts", listOfPopularProducts);
			request.getRequestDispatcher("/views/search.jsp").forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
