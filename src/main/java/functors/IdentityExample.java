package functors;

import common.Address;
import common.Customer;

class IdentityExample {
	
	public static void main(String[] args) {
		
		Identity<String> idString = new Identity<>("abc");
		Identity<Integer> idInt = idString.map(String::length);
		
		Customer customer = new Customer(new Address("street"), 500);
		
		Identity<byte[]> idBytes = new Identity<>(customer)
				.map(Customer::getAddress)
				.map(Address::street)
				.map((String s) -> s.substring(0, 3))
				.map(String::toLowerCase)
				.map(String::getBytes);
		
		byte[] bytes = customer
				.getAddress()
				.street()
				.substring(0, 3)
				.toLowerCase()
				.getBytes();
	}
	
}
