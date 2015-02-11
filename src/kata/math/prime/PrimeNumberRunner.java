package kata.math.prime;

import java.util.List;

/**
 * @author dblees
 * @version 1.0
 * @since 2015 February 8
 * 
 *        Wraps the PrimeNumber Generator functionality for command line usage.
 *
 */
public class PrimeNumberRunner {

	public static final String USAGE = "Usage:\r\n  Inputs: TWO non-negative integers representing a range, where first input < second input.\r\n  Output: A Prime number sequence range (inclusive), ascending.";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args != null && args.length == 2) {
			int start = -1, end = -1;
			try {
				start = Integer.parseInt(args[0]);
				end = Integer.parseInt(args[1]);
			} catch (NumberFormatException e) {
				System.err.println("Argument " + args[0] + " and " + args[1]
						+ " must be an integer.");
				System.exit(1);
			}
			List<Integer> range = (new PrimeNumber()).generate(Math.abs(start),
					Math.abs(end));
			StringBuilder sb = new StringBuilder();
			if (range.size() > 0) {
				for (Integer n : range)
					sb.append(n.toString() + ", ");
				System.out.println(sb.delete(sb.length() - 2, sb.length())
						.toString());
			}
			System.exit(0);
		} else {
			System.out.println(USAGE);
			System.exit(0);
		}
	}
}
