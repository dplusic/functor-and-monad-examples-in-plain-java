package monadsmore;

import java.util.function.BiFunction;
import java.util.function.Function;

class Monad<T> {
	
	private final T value;
	
	private Monad(T value) {
		this.value = value;
	}
	
	public static <T> Monad<T> of(T value) {
		return new Monad(value);
	}
	
	public <R> Monad<R> flatMap(Function<T, Monad<R>> f) {
		return f.apply(value);
	}
	
	public <R> Monad<R> map(Function<T, R> f) {
		Function<T, Monad<R>> f2 = t -> new Monad<>(f.apply(t));
		return flatMap(f2);
	}
	
	static <T1, T2, R>
	Monad<R> liftM2(Monad<T1> t1, Monad<T2> t2, BiFunction<T1, T2, R> fun) {
		return t1.flatMap((T1 tv1) ->
				t2.map((T2 tv2) -> fun.apply(tv1, tv2))
		);
	}
	
//	Monad<Iterable<T>> sequence(Iterable<Monad<T>> monads)
	public static <T> Monad<FList<T>> sequence(FList<Monad<T>> monads) {
		return monads.reduce(
				(monad, r) -> Monad.liftM2(r, monad, FList::add),
				Monad.of(new FList<>())
		);
	}
	
}
