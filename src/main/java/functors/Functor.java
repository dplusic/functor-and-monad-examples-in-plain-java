package functors;

import java.util.function.Function;

interface Functor<T,F extends Functor<?,?>> {
	<R> F map(Function<T,R> f);
//	<R> F<R> map(Function<T,R> f);
}
