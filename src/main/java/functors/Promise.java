package functors;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

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
		return new Promise<>(
				completableFuture.thenApply(f)
		);
	}
	
	public static <T> Promise<T> of(T a) {
		return new Promise<T>(a);
	}
	
}
