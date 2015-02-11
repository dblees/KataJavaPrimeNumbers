/**
 * 
 */
package kata.math.prime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author dblees
 * @version 1.0
 * @since 2015 February 8
 * 
 * Generates Prime number range (inclusive)
 * Validates if a number is Prime
 */
public class PrimeNumber implements PrimeNumberGenerator {

	public PrimeNumber() {}
	
	/*
	 * (non-Javadoc)
	 * @see kata.math.prime.PrimeNumberGenerator#generate(int, int)
	 */
	@Override
	public List<Integer> generate(int startingValue, int endingValue)
	{
		List<Integer> result = new ArrayList<Integer>();
		List<Integer> range = toggleRangeAscending(startingValue, endingValue);
		for (int n : range)
			if(this.isPrime(n)) result.add(n);
		Collections.sort(result);
		return result;	
	}

	/*
	 * Returns List<Integer> toggled, where always start < end
	 */
	protected List<Integer> toggleRangeAscending(int start, int end)
	{
		if(start < end)
			return IntStream.rangeClosed(start, end).boxed()
				.collect(Collectors.toList());
		return IntStream.rangeClosed(end, start).boxed()
				.collect(Collectors.toList());
	}
	
	/*
	 * (non-Javadoc)
	 * @see kata.math.prime.PrimeNumberGenerator#isPrime(int)
	 */
	@Override
	public boolean isPrime(int value) {
		if (value % 2 == 0)
			return value == 2;
		if (value % 3 == 0)
			return value == 3;
		if (value < 2)
			return false;
		int step = 4, m = (int) Math.sqrt(value) + 1;
		for (int i = 5; i < m; step = 6 - step, i += step) {
			if (value % i == 0)
				return false;
		}
		return true;
	}

}
