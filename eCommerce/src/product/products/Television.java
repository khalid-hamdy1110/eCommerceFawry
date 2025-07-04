package product.products;

import product.Product;
import product.Shippable;

public class Television extends Product implements Shippable {
	private final double weight;
	
	public Television(String name, double price, int quantity, double weight) {
		super(name, price, quantity);
		this.weight = weight;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getWeight() {
		return this.weight;
	}
	
}
