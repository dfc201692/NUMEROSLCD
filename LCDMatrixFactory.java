package LCD;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LCDMatrixFactory {

	//Constants for printing digits as a set of segments
	private static final String VERTICAL_CHAR = "|";
	private static final String HORIZONTAL_CHAR = "-";
	
	private static final String X_COORDINATE = "X";
	private static final String Y_COORDINATE = "Y";

	private int size;

	
	private final int[] pf1;
	private final int[] pf2;
	private final int[] pf3;
	private final int[] pf4;
	private final int[] pf5;

	private String[][] matrixPrint;

	private int totalColum;
	private int totalRows;
	private int rowsDig;
	private int columDig;

	/**
	 * Constructor of LCDMatrixFactory
	 */
	public LCDMatrixFactory() {

		this.pf1 = new int[2];
		this.pf2 = new int[2];
		this.pf3 = new int[2];
		this.pf4 = new int[2];
		this.pf5 = new int[2];
	}

	/**
	 *
	 * Procedure that prints a number
	 * 
	 * @param size
	 *            size of digits' segments
	 * @param numbers
	 *            numbert to print
	 * @param digitGap
	 *            gap between digits
	 */
	public String getLCDNumbers(int size, String numbers, int digitGap) {

		int pivotX = 0;

		// char array of digits
		char[] digits = numbers.toCharArray();

		digits = numbers.toCharArray();

		int stringLength = numbers.length();

		initMatConfig(stringLength, digitGap, size);

		for (char digit : digits) {

			if (!Character.isDigit(digit)) {
				throw new IllegalArgumentException("Character " + digit + " is not a digit");
			}

			int number = Integer.parseInt(String.valueOf(digit));

			// compute fixed points
			this.pf1[0] = 0;
			this.pf1[1] = 0 + pivotX;

			this.pf2[0] = (this.rowsDig / 2);
			this.pf2[1] = 0 + pivotX;

			this.pf3[0] = (this.rowsDig - 1);
			this.pf3[1] = 0 + pivotX;

			this.pf4[0] = (this.columDig - 1);
			this.pf4[1] = (this.rowsDig / 2) + pivotX;

			this.pf5[0] = 0;
			this.pf5[1] = (this.columDig - 1) + pivotX;

			pivotX = pivotX + this.columDig + digitGap;

			addDigit(number);
		}
		
		String result = "";
		// print matrix
		for (int i = 0; i < this.totalRows; i++) {
			for (int j = 0; j < this.totalColum; j++) {
				result+=this.matrixPrint[i][j];
			}
			result+="\n";
		}
		return result;
	}

	/**
	 *
	 * Procedure to add a segment to the matrix to be printed
	 * 
	 * @param segment
	 *            segment to be added
	 */
	private void addSegment(int segment) {

		switch (segment) {
		case 1:
			addLine(this.matrixPrint, this.pf1, Y_COORDINATE, this.size, VERTICAL_CHAR);
			break;
		case 2:
			addLine(this.matrixPrint, this.pf2, Y_COORDINATE, this.size, VERTICAL_CHAR);
			break;
		case 3:
			addLine(this.matrixPrint, this.pf5, Y_COORDINATE, this.size, VERTICAL_CHAR);
			break;
		case 4:
			addLine(this.matrixPrint, this.pf4, Y_COORDINATE, this.size, VERTICAL_CHAR);
			break;
		case 5:
			addLine(this.matrixPrint, this.pf1, X_COORDINATE, this.size, HORIZONTAL_CHAR);
			break;
		case 6:
			addLine(this.matrixPrint, this.pf2, X_COORDINATE, this.size, HORIZONTAL_CHAR);
			break;
		case 7:
			addLine(this.matrixPrint, this.pf3, X_COORDINATE, this.size, HORIZONTAL_CHAR);
			break;
		default:
			break;
		}
	}

	/**
	 *
	 * Procedure that adds a single line to the matrix to be printed
	 *
	 * @param matrix
	 *            Matrix to be printed
	 * @param point
	 *            pivot point
	 * @param fixedPosition
	 *            a fixed position
	 * @param size
	 *            segment size
	 * @param character
	 *            segment of character
	 */
	private void addLine(String[][] matrix, int[] point, String fixedPoint, int size, String character) {

		if (fixedPoint.equalsIgnoreCase(X_COORDINATE)) {
			for (int y = 1; y <= size; y++) {
				int value = point[1] + y;
				matrix[point[0]][value] = character;
			}
		} else {
			for (int i = 1; i <= size; i++) {
				int valor = point[0] + i;
				matrix[valor][point[1]] = character;
			}
		}
	}

	/**
	 *
	 * Procedure that defines how each digit is represented with segments, then
	 * adds the segments to the matrix for printing
	 * 
	 * @param number
	 *            digit
	 */
	private void addDigit(int number) {

		List<Integer> segList = new ArrayList<>();

		switch (number) {
		case 1:
			segList.add(3);
			segList.add(4);
			break;
		case 2:
			segList.add(5);
			segList.add(3);
			segList.add(6);
			segList.add(2);
			segList.add(7);
			break;
		case 3:
			segList.add(5);
			segList.add(3);
			segList.add(6);
			segList.add(4);
			segList.add(7);
			break;
		case 4:
			segList.add(1);
			segList.add(6);
			segList.add(3);
			segList.add(4);
			break;
		case 5:
			segList.add(5);
			segList.add(1);
			segList.add(6);
			segList.add(4);
			segList.add(7);
			break;
		case 6:
			segList.add(5);
			segList.add(1);
			segList.add(6);
			segList.add(2);
			segList.add(7);
			segList.add(4);
			break;
		case 7:
			segList.add(5);
			segList.add(3);
			segList.add(4);
			break;
		case 8:
			segList.add(1);
			segList.add(2);
			segList.add(3);
			segList.add(4);
			segList.add(5);
			segList.add(6);
			segList.add(7);
			break;
		case 9:
			segList.add(1);
			segList.add(3);
			segList.add(4);
			segList.add(5);
			segList.add(6);
			segList.add(7);
			break;
		case 0:
			segList.add(1);
			segList.add(2);
			segList.add(3);
			segList.add(4);
			segList.add(5);
			segList.add(7);
			break;
		default:
			break;
		}

		Iterator<Integer> iterator = segList.iterator();

		while (iterator.hasNext()) {
			addSegment(iterator.next());
		}
	}
	/**
	 * Procedure that initializes the matrix to be printed.
	 * @param stringLength length of the string of numbers to be printed
	 * @param digitGap gap between digits
	 * @param size size of digits when printing
	 */
	private void initMatConfig(int stringLength, int digitGap, int size) {

		this.size = size;

		// compute column and row numbers for each digit
		this.rowsDig = (2 * this.size) + 3;

		this.columDig = this.size + 2;

		// Compute column and row number for the matrix to be printed
		this.totalRows = this.rowsDig;

		this.totalColum = (this.columDig * stringLength) + (digitGap * stringLength);

		this.matrixPrint = new String[this.totalRows][this.totalColum];

		for (int i = 0; i < this.totalRows; i++) {
			for (int j = 0; j < this.totalColum; j++) {
				this.matrixPrint[i][j] = " ";
			}
		}

	}

}