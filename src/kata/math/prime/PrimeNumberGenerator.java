package kata.math.prime;

import java.util.List;

/**
 * @author dblees
 * @version 1.0
 * 
 * SPECIFICATION REFERENCE: This interface represents a core specification for method signature requirements.
 */
public interface PrimeNumberGenerator {
	
	/*
	 * Take a Range of integers and produces a List<Integer> (sorted).
	 * 
	 * @param  startingValue -  an absolute URL giving the base location of the image
	 * @param  endingValue - the location of the image, relative to the URL argument
	 * @return List<Integer> - sorted that contain Prime Numbers ONLY.
	 */
	List<Integer> generate(int startingValue, int endingValue);
	
	/*
	 * Formula function that determines if a number is Prime or not.
	 * 
	 * http://stackoverflow.com/questions/9625663/calculating-and-printing-the-nth-prime-number
	 * http://www.factmonster.com/math/numbers/prime.html
	 *  
	 * @param  value the
	 * @return Boolean - is input a prime?
	 */
	boolean isPrime(int value);
}
