package LCD;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class LCDTester2 {

	private static final String FINAL_STRING = "0,0";

	public static void main(String[] args) {

		List<String> comList = new ArrayList<>();
		String command;
		int digitGap;

		try {

			try (Scanner lector = new Scanner(System.in)) {

				System.out.print("Espacio entre Digitos (0 a 5): ");
				command = lector.next();

				if (LCDPrinter.isNumeric(command)) {
					digitGap = Integer.parseInt(command);

					// verify 0 < digitGap < 5
					if (digitGap < 0 || digitGap > 5) {
						throw new IllegalArgumentException(
								"Espacio [" + digitGap + "] Entre Digitos (0 a 5)");
					}

				} else {
					throw new IllegalArgumentException("String " + command + " No es un numero");
				}

				do {
					System.out.print("ENTRADA: ");
					command = lector.next();
					if (!command.equalsIgnoreCase(FINAL_STRING)) {
						comList.add(command);
					}
				} while (!command.equalsIgnoreCase(FINAL_STRING));
			}

			LCDPrinter lcdPrinter = new LCDPrinter();

			Iterator<String> iterator = comList.iterator();
			while (iterator.hasNext()) {
				try {
					lcdPrinter.procesar(iterator.next(), digitGap);
				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}

	}

}