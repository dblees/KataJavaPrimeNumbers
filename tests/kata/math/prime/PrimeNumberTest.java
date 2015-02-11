package kata.math.prime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author dblees
 * @version 1.0
 * @since 2015 February 7
 * 
 * Unit Tests for PrimeNumber.java
 * 
 * KATA NOTES: This represents the primary tests for "PrimeNumbers"
 * 
 * SPECS NOTE: Requirement comments are commented at the END of this class for transparency.
 */
public class PrimeNumberTest {

	static final Integer[] PRIMES_101 = new Integer[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29,
			31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101 };
	
	public static List<Integer> WHITELIST_PRIMES = new ArrayList<Integer>(
			Arrays.asList(PRIMES_101));

	List<Integer> BLACKLIST_NOT_PRIMES = IntStream.rangeClosed(0, 101).boxed()
			.collect(Collectors.toList());

	static final Integer[] PRIMES_7900_7920 = new Integer[] {7901, 7907, 7919};
	
	public static List<Integer> WHITELIST_PRIMES_7900_7920 = new ArrayList<Integer>(
			Arrays.asList(PRIMES_7900_7920));
	
	PrimeNumberGenerator pnGenerator = null;
	List<Integer> primeRangeGenerated101 = null;
	
	@Before
	public void setUp() throws Exception {
		BLACKLIST_NOT_PRIMES.removeAll(WHITELIST_PRIMES);
		pnGenerator = new PrimeNumber();
		primeRangeGenerated101 = pnGenerator.generate(0, 101);
	}

	//@After
	// ... if there was anything heavy or needing a dispose ..
	
	@Test
	public void ConfirmSetup_BlacklistDoesNotContainWhitelistValues()
			throws Exception {
		Assert.assertTrue("Setup Error: blacklist contains whitelist values",
				Collections.disjoint(BLACKLIST_NOT_PRIMES, WHITELIST_PRIMES));
	}

	@Test
	public void ConfirmSetup_PrimeNumberGeneratorIsNotNull() throws Exception {
		Assert.assertTrue(
				"Setup Error: shared class reference to generator is not initialized.",
				pnGenerator != null);
	}

	@Test
	public void ConfirmSetup_PrimeRangeGenerated101IsNotEmptyList()
			throws Exception {
		Assert.assertTrue(
				"Setup Error: shared List reference is null or not initialized.",
				primeRangeGenerated101 != null
						&& primeRangeGenerated101.size() > 0);
	}

	@Test
	public void ClassPrimeNumberImplementsInterfacePrimeNumberGenerator() {
		Class<?> clazz = PrimeNumber.class;
		Assert.assertTrue(
				"The class \"PrimeNumber\" does NOT implement specification interface \"PrimeNumberGenerator\"",
				PrimeNumberGenerator.class.isAssignableFrom(clazz));
	}

	@Test
	public void First101PrimesRangeInWhiteList_isPrime() throws Exception {
		Boolean overlap = true;
		for (Integer n : WHITELIST_PRIMES) {
			if (!pnGenerator.isPrime(n)) {
				overlap = false;
				break;
			}
		}
		Assert.assertTrue(
				"First 101 range of primes not resulting in known whitelist of primes.",
				overlap);
	}

	@Test
	public void First101RangeInBlackList_isNotPrime() throws Exception {
		Boolean inBlackList = false;
		for (Integer n : BLACKLIST_NOT_PRIMES) {
			if (pnGenerator.isPrime(n)) {
				inBlackList = true;
				break;
			}
		}
		Assert.assertFalse(
				"First 101 range non-primes is resulting in overlap of blacklist primes.",
				inBlackList);
	}

	@Test
	public void GeneratePrimesRangeTo101MatchesWhitelist() throws Exception {
		Assert.assertTrue(
				"First 101 range of primes does not match Whitelist of Primes.",
				!Collections.disjoint(WHITELIST_PRIMES, primeRangeGenerated101));
	}

	@Test
	public void GeneratePrimesRangeTo101WhenRangeToggledRangeReturnsSortedList() throws Exception {
		List<Integer> primeRangeGenerated101_toggled = pnGenerator.generate(101, 0);
		Assert.assertTrue(
				"Toggled 101 primes range input does not produce same set as whitelist primes.",
				!Collections.disjoint(WHITELIST_PRIMES, primeRangeGenerated101_toggled));
	}
	
	@Test
	public void GeneratePrimesRange7900to7920MatchesWhitelist7900to7920() throws Exception {
		List<Integer> primeRangeGenerated7900to7920 = pnGenerator.generate(7900,7920);
		Assert.assertTrue(
				"First 101 range of primes does not match Whitelist of Primes.",
				!Collections.disjoint(WHITELIST_PRIMES_7900_7920, primeRangeGenerated7900to7920));
	}
	
	/*
	 * performance time - better to use a profiler as this is a subjective test
	@Test
	public void GeneratePrimesRange100000PerformsUnderFiveSeconds() throws Exception {
		long startTime = System.currentTimeMillis();
		List<Integer> primeRangeGenerated100000 = pnGenerator.generate(0,100000);
		long endTime = System.currentTimeMillis();
		primeRangeGenerated100000 = null;
		Assert.assertTrue(endTime-startTime <= 5000);
	}
	*/
}

/*
 * SPECS:
 * 
 * Your task is to use test driven development to implement a prime number
 * generator that returns an ordered list of all prime numbers in a given range
 * (inclusive of the end points).
 * 
 * You must implement the interface specified below.
 * 
 * You may also create any other methods, interfaces and/or classes that you
 * deem necessary to complete the project.
 * 
 * You should also develop a small main program to drive your generator and to
 * allow the user to specify the prime number range via the command line.
 * 
 * To successfully complete the exercise, all unit tests must pass as well as
 * provide 100% code coverage.
 * 
 * Notes:
 * 
 * The code should handle inverse ranges such that 1-10 and 10-1 are
 * equivalent.
 * 
 * Ensure that you run a test against the range 7900 and 7920
 * (valid primes are 7901, 7907, 7919).
 * 
 * Interface: Interface PrimeNumberGenerator {
 * 
 * List<Integer> generate(int startingValue, int endingValue); boolean
 * isPrime(int value); }
 * 
 * Definition (from wikipedia): In mathematics, a prime number (or a prime) is a
 * natural number which has exactly two distinct natural number divisors: 1 and
 * itself. The first twenty-six prime numbers are: 2, 3, 5, 7, 11, 13, 17, 19,
 * 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101
 */