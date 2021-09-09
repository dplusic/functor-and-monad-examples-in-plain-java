package monads;

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
		FOptional<String> num = FOptional.of("42");
		FOptional<Integer> answer = num.flatMap(this::tryParse);
	}
	
	public static void main(String[] args) {
		new FOptionalExample().run();
	}
	
}
