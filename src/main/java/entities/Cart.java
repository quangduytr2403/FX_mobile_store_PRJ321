package entities;

import java.util.ArrayList;

/**
 * Entity Cart
 * 
 * @author DuyTQFX11834
 */
public class Cart {
	private ArrayList<Product> items;

	public Cart() {
		items = new ArrayList<>();
	}
	
	//Add a new product to cart
	public void addProduct(Product product) {
		for(Product p : items) {
			if (product.getId() == p.getId()) {
				p.setNumber(p.getNumber() + 1);
				return;
			}
		}
		items.add(product);
	}
	
	//Remove a product from cart
	public void remove (int productId) {
		for(Product p : items) {
			if(p.getId() == productId) {
				items.remove(p);
				return;
			}
		}
	}
	
	//Return total amount of cart
	public double getAmount() {
		double amount = 0;
		for(Product p : items) {
			amount += p.getPrice() * p.getNumber();
		}
		return Math.round(amount * 100.0) / 100.0;
	}
	
	//Return list of products in cart
	public ArrayList<Product> getItems() {
		return items;
	}
	
	//return number of product type in cart
	public int getNumberProduct() {
		int number = 0;
		for(Product p : items) {
			number += p.getNumber();
		}
		return number;
	}
}
