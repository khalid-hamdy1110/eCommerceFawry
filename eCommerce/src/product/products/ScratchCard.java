package product.products;

import product.Product;

public class ScratchCard extends Product {
	
	public ScratchCard(String name, double price, int quantity) {
		super(name, price, quantity);
	}
	
	public String getName() {
		return this.name;
	}

}
