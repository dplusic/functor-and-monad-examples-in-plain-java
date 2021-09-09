package monadsmore;

import com.google.common.collect.Maps;
import common.Address;
import common.Customer;

import java.util.Map;

public class FListExample {
	
	public static void main(String[] args) {
		
		Customer cust1 = new Customer(new Address("street1"), 500);
		Customer cust2 = new Customer(new Address("street2"), 1_500);
		
		FList<Customer> customers = FList.of(cust1, cust2);
		
		FList<Customer> vips =
				customers.filter(c -> c.totalOrders > 1_000);
		
		FList<Integer> list1 = FList.of(1, 2, 3);
		FList<String> list2 = FList.of("one", "two", "three");
		
		FList<Map.Entry<Integer, String>> entries = FList.liftM2(list1, list2, Maps::immutableEntry);
		
		FList<Integer> filtered = list1.filter(x -> x % 2 == 0);
		
		FList<FList<Integer>> r = FList.sequence(FList.of(FList.of(1,2), FList.of(3,4)));
	}
	
}
