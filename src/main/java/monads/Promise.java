package monads;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

//public class Promise<T> implements Monad<T, Promise<?>> {
public class Promise<T> implements Functor<T, Promise<?>> {
	private final CompletableFuture<T> completableFuture;
	
	private Promise(T value) {
		this.completableFuture = CompletableFuture.completedFuture(value);
	}
	
	private Promise(CompletableFuture<T> completableFuture) {
		this.completableFuture = completableFuture;
	}
	
	@Override
	public <R> Promise<R> map(Function<T, R> f) {
		Function<T, Promise<R>> f2 = t -> Promise.of(f.apply(t));
		return flatMap(f2);
	}
	
	public <R> Promise<R> flatMap(Function<T, Promise<R>> f) {
		return new Promise<>(completableFuture.thenCompose(
				t -> f.apply(t).completableFuture
		));
	}
	
	public static <T> Promise<T> of(T a) {
		return new Promise<T>(a);
	}
	
}
