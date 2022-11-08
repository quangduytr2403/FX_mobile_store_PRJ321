package entities;

/**
 * Entity Product Order
 * 
 * @author DuyTQFX11834
 */
public class ProductOrder {
	private int orderId;
	private int productId;
	private int amount ;
	private String productName;
	private double productPrice;
	
	public ProductOrder() {
	}

	public ProductOrder(int orderId, int productId, int amount, String productName, double productPrice) {
		this.orderId = orderId;
		this.productId = productId;
		this.amount = amount;
		this.productName = productName;
		this.productPrice = productPrice;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
}
