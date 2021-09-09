package functorsjoin;

import java.util.Date;

public class FOptionalExample {
	
	FOptional<Integer> tryParse(String s) {
		try {
			final int i = Integer.parseInt(s);
			return FOptional.of(i);
		} catch (NumberFormatException e) {
			return FOptional.empty();
		}
	}
	
	public void run() {
		FOptional<String> str = FOptional.of("42");
		FOptional<FOptional<Integer>> num = str.map(this::tryParse);
		
		FOptional<Integer> num1 = FOptional.of(42);
		FOptional<FOptional<Integer>> num2 = num;
		
		FOptional<Date> date1 = num1.map(t -> new Date(t));
		
		//doesn't compile!
//		FOptional<Date> date2 = num2.map(t -> new Date(t));
		FOptional<FOptional<Date>> date2 = num2.map(o -> o.map(t -> new Date(t)));
		
//		FOptional<Integer> num3 = num2.join();
		FOptional<Integer> num3 = FOptional.join(num2);
	}
	
	public static void main(String[] args) {
		new FOptionalExample().run();
	}
	
}
