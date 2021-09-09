package monadsmore;

import common.Address;
import common.Customer;

public class PromiseExample {
	
	private final Database database = new Database();
	
	public void run() {
		FList<Promise<Customer>> custPromises = FList
				.of(1, 2, 3)
				.map(database::loadCustomer);

//		Promise<FList<Customer>> customers = custPromises.sequence();
		Promise<FList<Customer>> customers = Promise.sequence(custPromises);
		
		customers.map((FList<Customer> c) -> c.filter(x -> x.getAddress().street().equals("street 1")));
	}
	
	static class Database {
		
		Promise<Customer> loadCustomer(int id) {
			return Promise.of(new Customer(new Address("street " + id), 500));
		}
	}
	
	public static void main(String[] args) {
		new PromiseExample().run();
	}
	
}
