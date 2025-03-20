package vttp2023.assessment.csf.orderbackend.services;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2023.assessment.csf.orderbackend.models.Order;
import vttp2023.assessment.csf.orderbackend.models.OrderSummary;
import vttp2023.assessment.csf.orderbackend.repositories.OrderRepository;
import vttp2023.assessment.csf.orderbackend.repositories.PizzaRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private PizzaRepository pizzaRepo;

	@Autowired
	private PricingService pricingService;

	// TODO: Task 6 Use this method to persist the Order
	// DO NOT MODIFY THE SIGNATURE OF THIS METHOD. You may only add thrown exceptions
	public String createOrder(Order order) {

		Random random = new Random();
		// +1 to start from 1-99999999
		Integer intOrderId = random.nextInt(99999999) + 1;
		order.setOrderId(intOrderId);

		String orderId = intOrderId.toString(); // To return String type

		String pizzaId = UUID.randomUUID().toString().substring(0, 8);

		orderRepo.createOrder(order,intOrderId,pizzaId);
		pizzaRepo.createPizza(order,pizzaId);

		// Return the order_id 
		return orderId;
	}

	/*
	private Integer orderId;  - SQL
	private String name;	- SQL
	private String email; - SQL
	private Float amount;*/

	// TODO: Task 7 Use this method to get a list of all the orders
	// DO NOT MODIFY THE SIGNATURE OF THIS METHOD. You may only add thrown exceptions
	public List<OrderSummary> getOrdersByEmail(String email) {
		// Get basic order details from SQL database
		List<OrderSummary> orderSummaries = orderRepo.getOrders(email);

		// For each order, get the pizza_id and calculate the amount
		for (OrderSummary summary : orderSummaries) {
			// Get the pizza_id for this order
			String pizzaId = orderRepo.getPizzaIdForOrder(summary.getOrderId());
			// Calculate the amount based on pizza details
			Float amount = calculateAmount(pizzaId);

			// Set the calculated amount
			summary.setAmount(amount);
		}

		return orderSummaries;
	}


	/* Helper Method*/
	private Float calculateAmount(String pizzaId) {
		// Retrieve the pizza document from MongoDB
		Document pizza = pizzaRepo.getPizza(pizzaId);
		if (pizza == null) {
			// Log an error and return a default value if pizza not found
			System.err.println("Pizza not found with ID: " + pizzaId);
			return 0.0f;
		}
		// Initialize the total amount
		Float totalAmount = 0.0f;
		// Calculate base price by size
		Integer size = pizza.getInteger("size");
		totalAmount += pricingService.size(size);
		// Add price for crust type
		Boolean thickCrust = pizza.getBoolean("thickCrust");
		if (thickCrust) {
			totalAmount += pricingService.thickCrust();
		} else {
			totalAmount += pricingService.thinCrust();
		}
		// Add price for sauce
		String sauce = pizza.getString("sauce");
		Float saucePrice = pricingService.sauce(sauce);
		if (saucePrice > 0) {  // Check if sauce price is valid
			totalAmount += saucePrice;
		}
		// Add price for each topping
		List<String> toppings = pizza.getList("toppings", String.class);
		if (toppings != null) {
			for (String topping : toppings) {
				Float toppingPrice = pricingService.topping(topping);
				if (toppingPrice > 0) {  // Check if topping price is valid
					totalAmount += toppingPrice;
				}
			}
		}
		// Round to 2 decimal places
		totalAmount = Math.round(totalAmount * 100) / 100.0f;
		System.out.println("Calculated amount for pizza " + pizzaId + ": $" + totalAmount);

		return totalAmount;
	}
}


