package shop;

import java.util.ArrayList;
import java.util.List;

import exceptions.EmptyCartExcpetion;
import exceptions.ExpiredItemException;
import exceptions.InsufficientBalanceException;
import exceptions.InvalidProcessException;
import exceptions.OutOfStockException;
import product.Expirable;
import product.Product;
import product.Shippable;
import product.products.Biscuits;
import product.products.Cheese;
import product.products.ScratchCard;
import product.products.Television;

public class Shop {
	private static void checkout(Customer customer, Cart cart) throws InvalidProcessException {
		System.out.println("Processing " + customer.getName() + "'s cart...");
		
		// Checking if the cart is empty
		if (cart.isEmpty()) {
			throw new EmptyCartExcpetion("Cart is empty! Please add items to the cart before checkout.");
		}
		
		List<CartItem> cartContents = cart.getProducts(); // products in the provided cart
		List<CartItem> checkedoutItems = new ArrayList<>(); // items that have been confirmed for checkout
		List<Shippable> shippableItems = new ArrayList<>(); // items that are able to be shipped
		List<Integer> shippableItemsAmounts = new ArrayList<>(); // the amounts of each items that are going to be shipped
		double subtotal = 0;
		
		// looping over each item in the provided cart
		for(CartItem item : cartContents) {
			
			Product product = item.getProduct();
			
			// Checking if the product is available or out of stock
			if (product.getQuantity() < item.getAmount()) {
				resetProductCounts(checkedoutItems);
				throw new OutOfStockException(product.getName() + " is out of stock!");
			}
			
			// Checking if the item is expired before checking out
			if (product instanceof Expirable && ((Expirable) product).getExpired()) {
				resetProductCounts(checkedoutItems);
				throw new ExpiredItemException(product.getName() + " is expired!");
			}
			
			// If the previous validation checks are passed we do the checkout process
			checkedoutItems.add(item);
			
			// removing the products from stock
			product.setQuantity(product.getQuantity() - item.getAmount());
			subtotal+=product.getPrice()*item.getAmount(); // updating the subtotal of the user
			if (product instanceof Shippable) {
				shippableItems.add((Shippable) product);
				shippableItemsAmounts.add(item.getAmount());
			}
			
		}
		
		// Passing the items that should be shipped to the shipping service where it returns the total weight 
		double weight = ShippingService(shippableItems, shippableItemsAmounts);
		final double shippingFees = (weight == 0)? 0 : (weight < 8500)? 50 : weight*0.015; // If the weight is greater than 8.5kg the shipping fees are calculated based on a factor
		double paidAmount = subtotal + shippingFees;
		
		// Checking if the user has enough balance for the transaction
		if (paidAmount > customer.getBalance()) {
			resetProductCounts(checkedoutItems);
			throw new InsufficientBalanceException("Insufficient funds in account!");
		}
		
		// Setting the user's new balance after taking out the paid amount
		customer.setBalance(customer.getBalance() - paidAmount);
		
		// Printing the checkout receipt for the user
		System.out.println("** Checkout receipt **");
		checkedoutItems.forEach(item -> System.out.println(item.getAmount() + "x " + item.getProduct().getName() + "\t\t" + item.getProduct().getPrice()*item.getAmount()));
		System.out.println("--------------------------------------");
		System.out.println("Subtotal: " + subtotal);
		System.out.println("Shipping: " + shippingFees);
		System.out.println("Amount: " + paidAmount);
		System.out.println(customer.getName() + "'s remaining balance: " + customer.getBalance() + "\n");
	}
	
	private static void resetProductCounts(List<CartItem> items) {
		
		// Resets the quantity of any checked out products to return them to normal in case of an error
		for (CartItem item : items) {
			item.getProduct().setQuantity(item.getProduct().getQuantity() + item.getAmount());
		}
	}
	
	private static double ShippingService(List<Shippable> products, List<Integer> amounts) {
		double totalWeight = 0;
		
		// If there are no shippable products then the shipping service is not needed
		if (!products.isEmpty()) {
			
			// Printing out the shipment details
			System.out.println("** Shipment notice **");
			
			for (int i = 0; i < products.size(); i++) {
				Shippable product = products.get(i);
				int amount = amounts.get(i);
				totalWeight+=amount*product.getWeight();
				System.out.println(amount + "x " + product.getName() + "\t\t" + product.getWeight()*amount + "g");
			}
			
			System.out.println("Total package weight: " + totalWeight/1000 + "kg\n");
		} else {
			System.out.println("No shipping needed.\n");
		}
		return totalWeight;
	}
	
	public static void main(String[] args) {
		
		// Available stock
		Cheese cheese = new Cheese("Cheese", 100, 3, false, 200);
		Television tv = new Television("Television", 20000, 5, 7000);
		ScratchCard scratchCard = new ScratchCard("Scratch card", 100, 10);
		Biscuits biscuit = new Biscuits("Biscuit", 20, 1, false, 700);
		
		Cart cart = new Cart();
		Customer customer = new Customer("Khalid", 100000);
		
		try {
			
			// Example 1: Basic example with shipping
			cart.add(cheese, 2);
			cart.add(tv, 2);
			cart.add(scratchCard, 1);

			checkout(customer, cart);
			
			
			// Example 2: Basic example without shipping
//			cart.add(scratchCard, 1);
//			
//			checkout(customer, cart);
			
			
			// Example 3: When the available quantity is exceeded
//			cart.add(cheese, 5);
//			
//			checkout(customer, cart);
			
			
			// Example 4: Empty cart
//			checkout(customer, cart);
			
			
			// Example 5: Expired item
//			Cheese cheese2 = new Cheese("Cheese", 100, 3, true, 200);
//			
//			cart.add(cheese2, 2);
//			
//			checkout(customer, cart);
			
			
			// Example 6: Insufficient balance
//			cart.add(tv, 5);
//			
//			checkout(customer, cart);
			
			
			// Example 7: Item out of stock
//			Customer customer2 = new Customer("Ahmed", 100000);
//			Cart cart2 = new Cart();
//			
//			cart.add(biscuit, 1);
//			cart2.add(biscuit, 1);
//			
//			checkout(customer, cart);
//			checkout(customer2, cart2);
			
		} catch (InvalidProcessException e) {
			System.out.println(e.getMessage());
		}
		
	}
}
