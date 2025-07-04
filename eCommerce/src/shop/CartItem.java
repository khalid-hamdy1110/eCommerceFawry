package shop;

import product.Product;

public class CartItem {
	private final Product product;
	private final int amount;
	
	public CartItem(Product product, int amount) {
		this.product = product;
		this.amount = amount;
	}

	public Product getProduct() {
		return product;
	}

	public int getAmount() {
		return amount;
	}
	
}
