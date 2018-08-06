package LCD;

public class LCDPrinter {

	/**
	 * singleton class to manipulate matrix
	 */
	private static LCDMatrixFactory matFactory = new LCDMatrixFactory();

	public LCDPrinter() {

	}

	/**
	 * Procedure that processes input containing digits' sizes and the digits
	 * that are to be printed
	 * 
	 * @param command
	 *            input containing digits' sizes and the digits that are to be
	 *            printed
	 * @param digitGap
	 *            Space between digits
	 */
	public String procesar(String command, int digitGap) {

		String[] parameters;

		int size;

		if (!command.contains(",")) {
			throw new IllegalArgumentException("String " + command + " does not contain character ,");
		}

		parameters = command.split(",");

		// validate number of parameters
		if (parameters.length != 2) {
			throw new IllegalArgumentException("String " + command + "must contain only one ,");
		}

		// validate size is a number
		if (isNumeric(parameters[0])) {
			size = Integer.parseInt(parameters[0]);

			// validate 1 < size < 10
			if (size < 1 || size > 10) {
				throw new IllegalArgumentException("Size [" + size + "] must be between 1 and 10");
			}
		} else {
			throw new IllegalArgumentException("Size [" + parameters[0] + "] is not a number");
		}

		// process and prints the numbers
		String result = matFactory.getLCDNumbers(size, parameters[1], digitGap);
		System.out.println(result);
		return result;

	}

	/**
	 *
	 * Verifies if a string contains numbers only
	 *
	 * @param string
	 *            String
	 */
	protected static boolean isNumeric(String string) {
		try {
			Integer.parseInt(string);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

}