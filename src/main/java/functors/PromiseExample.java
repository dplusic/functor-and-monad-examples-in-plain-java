package functors;

import common.Address;
import common.Customer;

public class PromiseExample {
	
	public static void main(String[] args) {
		Promise<Customer> customer = Promise.of(new Customer(new Address("street"), 500));
		Promise<byte[]> bytes = customer
				.map(Customer::getAddress)
				.map(Address::street)
				.map((String s) -> s.substring(0, 3))
				.map(String::toLowerCase)
				.map(String::getBytes);
	}
	
}
