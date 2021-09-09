package monadsmore;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Promise<T> {
	
	private final CompletableFuture<T> completableFuture;
	
	private Promise(T value) {
		this.completableFuture = CompletableFuture.completedFuture(value);
	}
	
	private Promise(CompletableFuture<T> completableFuture) {
		this.completableFuture = completableFuture;
	}
	
	public static <T> Promise<T> of(T a) {
		return new Promise<T>(a);
	}
	
	public <R> Promise<R> flatMap(Function<T, Promise<R>> f) {
		return new Promise<>(completableFuture.thenCompose(
				t -> f.apply(t).completableFuture
		));
	}
	
	public <R> Promise<R> map(Function<T, R> f) {
		Function<T, Promise<R>> f2 = t -> Promise.of(f.apply(t));
		return flatMap(f2);
	}
	
	public static <T1, T2, R> Promise<R> liftM2(Promise<T1> t1, Promise<T2> t2, BiFunction<T1, T2, R> fun) {
		return t1.flatMap((T1 tv1) ->
				t2.map((T2 tv2) -> fun.apply(tv1, tv2))
		);
	}
	
	public static <T> Promise<FList<T>> sequence(FList<Promise<T>> promises) {
		return promises.reduce(
				(promise, r) -> Promise.liftM2(r, promise, FList::add),
				Promise.of(new FList<>())
		);
	}
	
}
