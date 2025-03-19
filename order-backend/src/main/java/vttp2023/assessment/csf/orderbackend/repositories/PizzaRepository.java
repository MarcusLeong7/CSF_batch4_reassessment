package vttp2023.assessment.csf.orderbackend.repositories;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import vttp2023.assessment.csf.orderbackend.models.Order;

@Repository
public class PizzaRepository {

	@Autowired
	private MongoTemplate template;

	// TODO Task 6 - you are free to add parameters and change the return type
	// DO NOT CHANGE THE METHOD NAME
	// Write the native MongoDB statement in the commend below. Marks will be 
	// give for the native MongoDB statement
	/*
	 *  db.pizza.insert({
        _id: uuid string,
        size: number,
        thickCrust: boolean,
        sauce: string,
        toppings: [<string>],
        comments: <string>
	 * 
	 */
	public void createPizza(Order order, String pizzaId) {
		Document doc = new Document();
		doc.put("_id", pizzaId);
		doc.put("size", order.getSize());
		doc.put("thickCrust", order.isThickCrust());
		doc.put("sauce", order.getSauce());
		doc.put("toppings", order.getToppings());
		doc.put("comments", order.getComments());

		template.save(doc,"pizza");
	}

	// TODO Task 7
	// Write the native MongoDB statement in the commend below. Marks will be 
	// give for the native MongoDB statement
	/*
	 * Native MongoDB statement here
	 * 
	 */
}
