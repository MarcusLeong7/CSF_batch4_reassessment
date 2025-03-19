package vttp2023.assessment.csf.orderbackend.services;

import java.util.List;
import java.util.Random;
import java.util.UUID;

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

	// TODO: Task 6 Use this method to persist the Order
	// DO NOT MODIFY THE SIGNATURE OF THIS METHOD. You may only add thrown exceptions
	public String createOrder(Order order) {

		Random random = new Random();
		// +1 to start from 1-99999999
		Integer intOrderId = random.nextInt(99999999) + 1;
		String orderId = intOrderId.toString();

		String pizzaId = UUID.randomUUID().toString().substring(0, 8);

		orderRepo.createOrder(order,intOrderId,pizzaId);
		pizzaRepo.createPizza(order,pizzaId);

		// Return the order_id 
		return orderId;
	}

	// TODO: Task 7 Use this method to get a list of all the orders
	// DO NOT MODIFY THE SIGNATURE OF THIS METHOD. You may only add thrown exceptions
	public List<OrderSummary> getOrdersByEmail(String email) {
		return List.of();
	}
}
