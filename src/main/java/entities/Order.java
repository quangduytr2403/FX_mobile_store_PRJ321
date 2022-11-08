package entities;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Entity Order
 * 
 * @author DuyTQFX11834
 */
public class Order {
	private int orderId;
	private String userMail;
	private String orderName;
	private int status;
	private LocalDate orderDate;
	private String discount;
	private String orderAddress;
	private ArrayList<ProductOrder> productOrders;

	public Order() {
	}

	public Order(String userMail, String orderName, int status, String discount, String orderAddress) {
		this.userMail = userMail;
		this.orderName = orderName;
		this.status = status;
		this.discount = discount;
		this.orderAddress = orderAddress;
	}

	public Order(int orderId, String orderName, int status, LocalDate orderDate, 
			String discount, String orderAddress) {
		this.orderId = orderId;
		this.orderName = orderName;
		this.status = status;
		this.orderDate = orderDate;
		this.discount = discount;
		this.orderAddress = orderAddress;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	
	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	public ArrayList<ProductOrder> getProductOrders() {
		return productOrders;
	}

	public void setProductOrders(ArrayList<ProductOrder> productOrders) {
		this.productOrders = productOrders;
	}
	
	public double getPrice() {
		double price = 0;
		for(ProductOrder p : productOrders) {
			price += p.getAmount() * p.getProductPrice();
		}
		return Math.round(price * 100.0) / 100.0;
	}
}
