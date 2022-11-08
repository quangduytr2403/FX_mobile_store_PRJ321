package dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Product;
import utils.DBUtil;

public class ProductDao {
	public ArrayList<Product> getAllProduct(int currentPage, int size)
			throws SQLException, ClassNotFoundException, IOException {
		Connection connection = DBUtil.getConnection();
		CallableStatement callableStatement = connection.prepareCall("{call GetProducts(?,?)}");
		callableStatement.setInt(1, (currentPage - 1) * size + 1);
		callableStatement.setInt(2, currentPage * size);
		ResultSet resultSet = callableStatement.executeQuery();
		ArrayList<Product> listOfProducts = new ArrayList<>();
		while (resultSet.next()) {
			int id = resultSet.getInt("product_id");
			String name = resultSet.getString("product_name");
			String description = resultSet.getString("product_des");
			float price = resultSet.getFloat("product_price");
			String src = resultSet.getString("product_img_source");
			String type = resultSet.getString("product_type");
			String brand = resultSet.getString("product_brand");

			Product product = new Product(id, name, description, price, src, type, brand);
			listOfProducts.add(product);
		}
		DBUtil.closeConnection(connection);
		return listOfProducts;
	}
	
	public int getNumberProductRecord()
			throws SQLException, ClassNotFoundException, IOException {
		Connection connection = DBUtil.getConnection();
		CallableStatement callableStatement = connection.prepareCall("{call GetNumberProductRecord()}");
		ResultSet resultSet = callableStatement.executeQuery();
		resultSet.next();
		int numberProductRecord = resultSet.getInt(1);
		DBUtil.closeConnection(connection);
		return numberProductRecord;
	}
	
	public ArrayList<Product> searchProduct(int currentPage, int size, String keyword)
			throws SQLException, ClassNotFoundException, IOException {
		Connection connection = DBUtil.getConnection();
		CallableStatement callableStatement = connection.prepareCall("{call SearchProducts(?,?,?)}");
		callableStatement.setInt(1, (currentPage - 1) * size + 1);
		callableStatement.setInt(2, currentPage * size);
		callableStatement.setString(3, keyword);
		ResultSet resultSet = callableStatement.executeQuery();
		ArrayList<Product> listOfProducts = new ArrayList<>();
		while (resultSet.next()) {
			int id = resultSet.getInt("product_id");
			String name = resultSet.getString("product_name");
			String description = resultSet.getString("product_des");
			float price = resultSet.getFloat("product_price");
			String src = resultSet.getString("product_img_source");
			String type = resultSet.getString("product_type");
			String brand = resultSet.getString("product_brand");

			Product product = new Product(id, name, description, price, src, type, brand);
			listOfProducts.add(product);
		}
		DBUtil.closeConnection(connection);
		return listOfProducts;
	}
	
	public int getNumberProductSearchRecord(String keyword)
			throws SQLException, ClassNotFoundException, IOException {
		Connection connection = DBUtil.getConnection();
		CallableStatement callableStatement = connection.prepareCall("{call GetNumberProductSearchRecord(?)}");
		callableStatement.setString(1, keyword);
		ResultSet resultSet = callableStatement.executeQuery();
		resultSet.next();
		int numberProductSearchRecord = resultSet.getInt(1);
		DBUtil.closeConnection(connection);
		return numberProductSearchRecord;
	}
	
	public Product getDetailProduct(int id)
			throws SQLException, ClassNotFoundException, IOException {
		Connection connection = DBUtil.getConnection();
		CallableStatement callableStatement = connection.prepareCall("{call GetDetailProduct(?)}");
		callableStatement.setInt(1, id);
		ResultSet resultSet = callableStatement.executeQuery();
		resultSet.next();
			
		String name = resultSet.getString("product_name");
		String description = resultSet.getString("product_des");
		float price = resultSet.getFloat("product_price");
		String src = resultSet.getString("product_img_source");
		String type = resultSet.getString("product_type");
		String brand = resultSet.getString("product_brand");

		Product product = new Product(id, name, description, price, src, type, brand);
		DBUtil.closeConnection(connection);
		return product;
	}
	
	public ArrayList<Product> getPopularProduct()
			throws SQLException, ClassNotFoundException, IOException {
		Connection connection = DBUtil.getConnection();
		CallableStatement callableStatement = connection.prepareCall("{call GetPopularProduct()}");
		ResultSet resultSet = callableStatement.executeQuery();
		ArrayList<Product> listOfProducts = new ArrayList<>();
		while (resultSet.next()) {
			Product product = new Product();
			product.setId(resultSet.getInt("product_id"));
			product.setName(resultSet.getString("product_name"));
			product.setSrc(resultSet.getString("product_img_source"));
			
			listOfProducts.add(product);
		}
		DBUtil.closeConnection(connection);
		return listOfProducts;
	}
}