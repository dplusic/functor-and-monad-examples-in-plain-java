package common;

public class Customer {
	private final Address address;
	public final int totalOrders;
	
	public Customer(Address address, int totalOrders) {
		this.address = address;
		this.totalOrders = totalOrders;
	}
	
	public Address getAddress() {
		return address;
	}
	
}
