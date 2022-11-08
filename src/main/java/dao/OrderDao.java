package dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import entities.Cart;
import entities.Order;
import entities.Product;
import entities.ProductOrder;
import utils.DBUtil;

public class OrderDao {
	public ArrayList<Order> listOrderByMail(String userMail)
			throws SQLException, ClassNotFoundException, IOException {
		Connection connection = DBUtil.getConnection();
		CallableStatement callableStatement = connection.prepareCall("{call ListOrderByMail(?)}");
		callableStatement.setString(1, userMail);
		ResultSet resultSet = callableStatement.executeQuery();
		
		ArrayList<Order> listOfOrders = new ArrayList<>();
		int curOrderId = -1;
		Order order = null;
		ArrayList<ProductOrder> productOrders = null;
		
		while (resultSet.next()) {
			if(resultSet.getInt("order_id") != curOrderId) {
				//Save order
				if(order != null) {
					order.setProductOrders(productOrders);
					listOfOrders.add(order);
				}
				
				order = new Order(
					resultSet.getInt("order_id"),
					resultSet.getString("order_name"),
					resultSet.getInt("order_status"),
					LocalDate.parse(resultSet.getString("order_date")),
					resultSet.getString("order_discount_code"),
					resultSet.getString("order_address"));
				
				productOrders = new ArrayList<>();
				
				ProductOrder productOrder = new ProductOrder(
					resultSet.getInt("order_id"),
					resultSet.getInt("product_id"),
					resultSet.getInt("amount_product"),
					resultSet.getString("product_name"),
					resultSet.getDouble("product_price"));
				
				productOrders.add(productOrder);
				
				curOrderId = resultSet.getInt("order_id");
			} else {
				ProductOrder productOrder = new ProductOrder(
						resultSet.getInt("order_id"),
						resultSet.getInt("product_id"),
						resultSet.getInt("amount_product"),
						resultSet.getString("product_name"),
						resultSet.getDouble("product_price"));
					
				productOrders.add(productOrder);
			}
		}
		//Save last order
		if(order != null) {
			order.setProductOrders(productOrders);
			listOfOrders.add(order);
		}
		
		
		DBUtil.closeConnection(connection);
		return listOfOrders;
	}
	
	public void submitOrder(Order order, Cart cart) throws 
			ClassNotFoundException, IOException, SQLException {
		Connection connection = DBUtil.getConnection();
		
		//Insert to order
		PreparedStatement preparedStatement = connection.prepareStatement(
				"INSERT INTO Orders(user_mail, order_name, order_status, order_discount_code, order_address) " + 
				"VALUES (?,?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS);
		int param = 0;
		preparedStatement.setString(++param, order.getUserMail());
		preparedStatement.setString(++param, order.getOrderName());
		preparedStatement.setInt(++param, order.getStatus());
		preparedStatement.setString(++param, order.getDiscount());
		preparedStatement.setString(++param, order.getOrderAddress());
		int affectRowOrder = preparedStatement.executeUpdate();
		if (affectRowOrder == 0) {
            throw new SQLException();
        }
		
		ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
		generatedKeys.next();
		int orderId = generatedKeys.getInt(1);
		
		//Insert to order detail
		preparedStatement = connection.prepareCall("{call SubmitOrderDetail(?,?,?)}");
		for(Product p : cart.getItems()) {
			param = 0;
			preparedStatement.setInt(++param, orderId);
			preparedStatement.setInt(++param, p.getId());
			preparedStatement.setInt(++param, p.getNumber());
			int affectRowOrderDetail = preparedStatement.executeUpdate();
			if (affectRowOrderDetail == 0) {
	            throw new SQLException();
	        }
		}
		DBUtil.closeConnection(connection);
	}
}