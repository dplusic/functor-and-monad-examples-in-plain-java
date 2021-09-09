package functorsjoin;

import java.util.function.Function;

class FOptional<T> implements Functor<T, FOptional<?>> {
	private final T valueOrNull;
	
	private FOptional(T valueOrNull) {
		this.valueOrNull = valueOrNull;
	}
	
	@Override
	public <R> FOptional<R> map(Function<T, R> f) {
		if (valueOrNull == null)
			return empty();
		else
			return of(f.apply(valueOrNull));
	}
	
	public static <T> FOptional<T> of(T a) {
		return new FOptional<T>(a);
	}
	
	public static <T> FOptional<T> empty() {
		return new FOptional<T>(null);
	}
	
	public static <T> FOptional<T> join(FOptional<FOptional<T>> optional) {
		if (optional.valueOrNull == null)
			return empty();
		else
			return optional.valueOrNull;
	}
	
}
