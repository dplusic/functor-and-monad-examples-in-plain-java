package monadsmore;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

class FList<T> {
	private final ImmutableList<T> list;
	
	FList() {
		this.list = ImmutableList.of();
	}
	
	FList(T value) {
		this.list = ImmutableList.of(value);
	}
	
	FList(Iterable<T> value) {
		this.list = ImmutableList.copyOf(value);
	}
	
	public static <T> FList<T> of(T... args) {
		return new FList<>(Arrays.asList(args));
	}
	
	public <R> FList<R> flatMap(Function<T, FList<R>> f) {
		ArrayList<R> result = new ArrayList<R>(list.size());
		for (T t : list) {
			result.addAll(f.apply(t).list);
		}
		return new FList<>(result);
	}
	
	public <R> FList<R> map(Function<T, R> f) {
		Function<T, FList<R>> f2 = t -> new FList<>(f.apply(t));
		return flatMap(f2);
	}
	
	public static <T1, T2, R> FList<R> liftM2(FList<T1> t1, FList<T2> t2, BiFunction<T1, T2, R> fun) {
		return t1.flatMap((T1 tv1) ->
				t2.map((T2 tv2) -> fun.apply(tv1, tv2))
		);
	}
	
	public FList<T> filter(Predicate<T> predicate) {
		return flatMap(x -> predicate.test(x) ? new FList<>(x) : new FList<>());
	}
	
	public <R> R reduce(BiFunction<T, R, R> fun, R initial) {
		R result = initial;
		for (T t : list) {
			result = fun.apply(t, result);
		}
		return result;
	}
	
	public FList<T> add(T t) {
		return new FList<>(new ImmutableList.Builder<T>()
				.addAll(list)
				.add(t)
				.build()
		);
	}
	
	public static <T> FList<FList<T>> sequence(FList<FList<T>> lists) {
		return lists.reduce(
				(list, r) -> FList.liftM2(r, list, FList::add),
				FList.of(new FList<>())
		);
	}
	
}
