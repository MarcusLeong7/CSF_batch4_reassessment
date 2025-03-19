package vttp2023.assessment.csf.orderbackend.models;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

// IMPORTANT: Do not modify this file. If you modify this file any task using 
// this class will be invalidated
public class Order {

	private Integer orderId;
	private String name;
	private String email;
	private Integer size;
	private String sauce;
	private Boolean thickCrust;
	private List<String> toppings = new LinkedList<>();
	private String comments;

	public void setOrderId(Integer orderId) { this.orderId = orderId; }
	public Integer getOrderId() { return this.orderId; }

	public void setName(String name) { this.name = name; }
	public String getName() { return this.name; }

	public void setEmail(String email) { this.email = email; }
	public String getEmail() { return this.email; }

	public void setSize(Integer size) { this.size = size; }
	public Integer getSize() { return this.size; }

	public void setSauce(String sauce) { this.sauce = sauce; }
	public String getSauce() { return this.sauce; }

	public void setThickCrust(Boolean thickCrust) { this.thickCrust = thickCrust; }
	public Boolean isThickCrust() { return this.thickCrust; }

	public void setToppings(List<String> toppings) { this.toppings = toppings; }
	public List<String> getToppings() { return this.toppings; }
	public void addTopping(String topping) { this.toppings.add(topping); }
	public String getToppingsAsCSV() {
		return this.toppings.stream().collect(Collectors.joining(","));
	}

	public void setComments(String comments) { this.comments = comments; }
	public String getComments() { return this.comments; }

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", name=" + name + ", email=" + email + ", size=" + size + ", sauce=" + sauce
				+ ", thickCrust=" + thickCrust + ", toppings=" + toppings + ", comments=" + comments + "]";
	}


}
