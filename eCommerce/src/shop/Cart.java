package shop;

import java.util.ArrayList;
import java.util.List;

import exceptions.AvailableQuantityExceededException;
import product.Product;

public class Cart {
	public final List<CartItem> products;
	
	public Cart() {
		products = new ArrayList<>();
	}
	
	public void add(Product product, int amount) throws AvailableQuantityExceededException {
		int availableQuantity = product.getQuantity();
		
		// If the amount requested is more than what is available in stock throw an error
		if (amount > availableQuantity) {
			throw new AvailableQuantityExceededException("Amount requested is greater than the amount available for item: " + product.getName());
		}
		
		// Add the product to the products list with the amount of times that is requested
		products.add(new CartItem(product, amount));
	}
	
	public boolean isEmpty() {
		return products.isEmpty();
	}

	public List<CartItem> getProducts() {
		return products;
	}
}
