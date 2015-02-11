/**
 * 
 */
package kata.math.prime;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.Assertion;
//http://stefanbirkner.github.io/system-rules/download.html
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

/**
 * @author dblees
 * @version 1.0
 * @since 2015 February 8
 *
 * Unit Tests for PrimeNumberRunner.java
 * 
 * KATA NOTE: EXTERNAL LIB DEPENDENCY: system-rules-1.8.0.jar
 *
 * CODE COVERAGE NOTE: System.out, System.err, and System.exit is NOT showing as tested in eclemma ...
 * 	https://github.com/jacoco/jacoco/issues/117
 * 
 * WHAT THIS MEANS: code coverage is not represented accurately, showing false positive of non-coverage
 * 	for the class PrimeNumberRunner.java
 * 
 * SETUP NOTE: in order to overrider the System.out and System.err streams you must set the java.policy setting ... 
		permission java.lang.RuntimePermission "setIO";
		
 * SETUP NOTE: in order to override the Security Manager you must set java.policy settings ...
  		permission java.lang.RuntimePermission "setSecurityManager";
		permission java.lang.RuntimePermission "createSecurityManager";
		permission java.lang.RuntimePermission "usePolicy";		
 */
public class PrimeNumberRunnerTest {

	@Rule
	public final ExpectedSystemExit exit = ExpectedSystemExit.none();

	/*
	@Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog();
	*/

	private final ByteArrayOutputStream sysOut = new ByteArrayOutputStream();
	private final ByteArrayOutputStream sysErr = new ByteArrayOutputStream();

	Method main = null;
	
	@Before
	public void setUpStreams() {
		// Override the std out && std err
	    System.setOut(new PrintStream(sysOut));
	    System.setErr(new PrintStream(sysErr));
	}

	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}

	/**
	 * KATA NOTE: Normally I would not put comments here because the naming should be so clear, that no comments should be necessary.
	 * 
	 * Test method for
	 * {@link kata.math.prime.PrimeNumberRunner#main(java.lang.String[])}.
	 */
	@Test
	public void PrimeNumberRunnerShowsUSAGEWhenNoInputForArgsInput() throws Exception {
		exit.expectSystemExit();
		exit.checkAssertionAfterwards(new Assertion() {
		      public void checkAssertion() {
				assertTrue(SysOutMinusCRLF().equals(PrimeNumberRunner.USAGE));
		      }
		    });
		try{
			PrimeNumberRunner.main(new String[] {});
		}catch(SecurityException se) {}
	}

	@Test
	public void PrimeNumberRunnerShowsUsageWhenNullForArgsInput() throws Exception {
		exit.expectSystemExit();
		exit.checkAssertionAfterwards(new Assertion() {
		      public void checkAssertion() {
				assertTrue(SysOutMinusCRLF().equals(PrimeNumberRunner.USAGE));
		      }
		    });
		try{
			PrimeNumberRunner.main(null);
		}catch(SecurityException se) {}		
	}
	
	@Test
	public void PrimeNumberRunnerThrowsErrorWhenNotTwoIntegersForArgsInput() throws Exception {
		exit.expectSystemExitWithStatus(1);
		String[] inputs = new String[] {"A", "B"};
		String expectedSysErr = "Argument " + inputs[0] + " and " + inputs[1] + " must be an integer.";
		exit.checkAssertionAfterwards(new Assertion() {
		      public void checkAssertion() {
		        assertTrue(ErrOutMinusCRLF().equals(expectedSysErr));
		      }
		    });
		try{
			PrimeNumberRunner.main(inputs);
		}catch(SecurityException se) {}
	}
	
	@Test
	public void PrimeNumberRunnerWithRangeZeroTo101ProducesCSVofPrimesAsOutput() throws Exception {
		exit.expectSystemExit();
		exit.checkAssertionAfterwards(new Assertion() {
		      public void checkAssertion() {
				assertTrue(SysOutMinusCRLF().equals(PrimesZeroTo101Compare()));
		      }
		    });
		try{
			PrimeNumberRunner.main(new String[] {"0", "101"});
		}catch(SecurityException se) {}
	}
	
	@Test
	public void PrimeNumberRunnerWithRange101downtoZeroProducesCSVofPrimesAsOutput() throws Exception {
		exit.expectSystemExit();
		exit.checkAssertionAfterwards(new Assertion() {
		      public void checkAssertion() {
				assertTrue(SysOutMinusCRLF().equals(PrimesZeroTo101Compare()));
		      }
		    });
		try{
			PrimeNumberRunner.main(new String[] {"101", "0"});
		}catch(SecurityException se) {}
	}
	
	@Test
	public void PrimeNumberRunnerWithRange7900To7920ProducesCSVofPrimesAsOutput() throws Exception {
		exit.expectSystemExit();
		exit.checkAssertionAfterwards(new Assertion() {
		      public void checkAssertion() {
				assertTrue(SysOutMinusCRLF().equals(Primes7900to7920Compare()));
		      }
		    });
		try{
			PrimeNumberRunner.main(new String[] {"7900", "7920"});
		}catch(SecurityException se) {}
	}
	
	@Test
	public void PrimeNumberRunnerWithRangeMinus101TOZeroAreABSValueAndReturnPositivePrimeNumberRange() throws Exception {
		exit.expectSystemExit();
		exit.checkAssertionAfterwards(new Assertion() {
		      public void checkAssertion() {
				assertTrue(SysOutMinusCRLF().equals(PrimesZeroTo101Compare()));
		      }
		    });
		try{
			PrimeNumberRunner.main(new String[] {"-101", "0"});
		}catch(SecurityException se) {}		
	}
	
	@Test
	public void PrimeNumberRunnerWithNoRangeAs71To71Produces71() throws Exception {
		exit.expectSystemExit();
		exit.checkAssertionAfterwards(new Assertion() {
		      public void checkAssertion() {
				assertTrue(SysOutMinusCRLF().equals("71"));
		      }
		    });
		try{
			PrimeNumberRunner.main(new String[] {"71", "71"});
		}catch(SecurityException se) {}		
	}
	
	@Test
	public void PrimeNumberRunnerRangeZeroToZeroProducesNothing() throws Exception {
		exit.expectSystemExit();
		exit.checkAssertionAfterwards(new Assertion() {
		      public void checkAssertion() {
				assertTrue(SysOutMinusCRLF().equals(""));
		      }
		    });
		try{
			PrimeNumberRunner.main(new String[] {"0", "0"});
		}catch(SecurityException se) {}			
	}
	
	// HELPERS BELOW THIS POINT
	
	protected String ErrOutMinusCRLF()
	{
		String errput = sysErr.toString();
		return errput.substring(0,errput.length()-2);
	}
	
	
	protected String SysOutMinusCRLF()
	{
		String output = sysOut.toString();
		if(output.length() < 1)
			return "";
		return output.substring(0,output.length()-2);
	}
	
	protected String PrimesZeroTo101Compare()
	{
		StringBuilder sb = new StringBuilder();
		for(Integer prime : PrimeNumberTest.WHITELIST_PRIMES)
			sb.append(prime.intValue()+", ");
		return sb.delete(sb.length()-2, sb.length()).toString();		
	}
	
	protected String Primes7900to7920Compare()
	{
		StringBuilder sb = new StringBuilder();
		for(Integer prime : PrimeNumberTest.WHITELIST_PRIMES_7900_7920)
			sb.append(prime.intValue()+", ");
		return sb.delete(sb.length()-2, sb.length()).toString();		
	}
}
