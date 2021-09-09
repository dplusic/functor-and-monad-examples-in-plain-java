package monads;

import common.Address;
import common.Basket;
import common.Customer;

import java.math.BigDecimal;
import java.time.DayOfWeek;

class PromiseExample {
	
	Promise<Customer> loadCustomer(int id) {
		return Promise.of(new Customer(new Address("street"), 500));
	}
	
	Promise<Basket> readBasket(Customer customer) {
		return Promise.of(new Basket());
	}
	
	Promise<BigDecimal> calculateDiscount(Basket basket, DayOfWeek dow) {
		return Promise.of(BigDecimal.ZERO);
	}
	
	public void run() {
		Promise<BigDecimal> discount =
				loadCustomer(42)
						.flatMap(this::readBasket)
						.flatMap(b -> calculateDiscount(b, DayOfWeek.FRIDAY));
	}
	
	public static void main(String[] args) {
		new PromiseExample().run();
	}
	
}
