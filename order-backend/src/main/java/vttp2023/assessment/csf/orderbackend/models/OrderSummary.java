package vttp2023.assessment.csf.orderbackend.models;

// IMPORTANT: Do not modify this file. If you modify this file any task using 
// this class will be invalidated
public class OrderSummary {
	private Integer orderId;
	private String name;
	private String email;
	private Float amount;

	public void setOrderId(Integer orderId) { this.orderId = orderId; }
	public Integer getOrderId() { return this.orderId; }

	public void setName(String name) { this.name = name; }
	public String getName() { return this.name; }

	public void setEmail(String email) { this.email = email; }
	public String getEmail() { return this.email; }

	public void setAmount(Float amount) { this.amount = amount; }
	public Float getAmount() { return this.amount; }

	@Override
	public String toString() {
		return "OrderSummary [orderId=" + orderId + ", name=" + name + ", email=" + email + ", amount=" + amount + "]";
	}

}
