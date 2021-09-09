package functors;

import common.Address;
import common.Customer;

import static java.util.Arrays.asList;

class FListExample {
	
	public static void main(String[] args) {
		
		Customer cust1 = new Customer(new Address("street1"), 500);
		Customer cust2 = new Customer(new Address("street2"), 1_500);
		
		FList<Customer> customers = new FList<>(asList(cust1, cust2));
		
		FList<String> streets = customers
				.map(Customer::getAddress)
				.map(Address::street);
		
		FList<byte[]> idBytesList = customers
				.map(Customer::getAddress)
				.map(Address::street)
				.map((String s) -> s.substring(0, 3))
				.map(String::toLowerCase)
				.map(String::getBytes);
	}
	
}
