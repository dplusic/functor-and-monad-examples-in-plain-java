package functors;

import common.Address;
import common.Customer;

class FOptionalExample {
		
	public static void main(String[] args) {
		Customer customer = new Customer(new Address("street"), 500);
		
		FOptional<byte[]> idBytesOptional = FOptional.of(customer)
				.map(Customer::getAddress)
				.map(Address::street)
				.map((String s) -> s.substring(0, 3))
				.map(String::toLowerCase)
				.map(String::getBytes);
	}
	
}
