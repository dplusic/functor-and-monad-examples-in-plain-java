package monads;

import java.util.function.Function;

interface Monad<T,M extends Monad<?,?>> extends Functor<T,M> {
	M flatMap(Function<T,M> f);
//	M<R> flatMap(Function<T,M<R>> f);
}
