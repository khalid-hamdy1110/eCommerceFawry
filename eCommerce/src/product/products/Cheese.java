package product.products;

import product.Expirable;
import product.Product;
import product.Shippable;

public class Cheese extends Product implements Expirable, Shippable {
	private final boolean expired;
	private final double weight;
	
	public Cheese(String name, double price, int quantity, boolean expired, double weight) {
		super(name, price, quantity);
		this.expired = expired;
		this.weight = weight;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getWeight() {
		return this.weight;
	}

	public boolean getExpired() {
		return expired;
	}
	
}
