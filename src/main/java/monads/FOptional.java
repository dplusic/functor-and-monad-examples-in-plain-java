package monads;

import java.util.function.Function;

//class FOptional<T> implements Monad<T, FOptional<?>> {
class FOptional<T> implements Functor<T, FOptional<?>> {
	private final T valueOrNull;
	
	private FOptional(T valueOrNull) {
		this.valueOrNull = valueOrNull;
	}
	
	public <R> FOptional<R> map(Function<T, R> f) {
		Function<T, FOptional<R>> f2 = t -> FOptional.of(f.apply(t));
		return flatMap(f2);
	}
	
	public <R> FOptional<R> flatMap(Function<T, FOptional<R>> f) {
		if (valueOrNull == null)
			return empty();
		else
			return f.apply(valueOrNull);
	}
	
	public static <T> FOptional<T> of(T a) {
		return new FOptional<T>(a);
	}
	
	public static <T> FOptional<T> empty() {
		return new FOptional<T>(null);
	}
	
}
