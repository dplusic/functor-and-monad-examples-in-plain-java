package monadsmore;

import java.time.LocalDate;
import java.time.Month;

public class MonadExample {
	
	public static void main(String[] args) {
		
		Monad<Month> month = Monad.of(Month.of(1));
		Monad<Integer> dayOfMonth = Monad.of(2);
		
		Monad<LocalDate> date = month.flatMap((Month m) ->
				dayOfMonth
//						.map((int d) -> LocalDate.of(2016, m, d)));
						.map((Integer d) -> LocalDate.of(2016, m, d)));
		
		Monad<Integer> year = Monad.of(2016);
		Monad<LocalDate> date2 =
				year.flatMap(y ->
						month.flatMap(m ->
								dayOfMonth.map(d ->
										LocalDate.of(y, m, d))));
		
		Monad<LocalDate> date3 = Monad.liftM2(month, dayOfMonth, (m, d) -> LocalDate.of(2016, m, d));
	}
	
}
