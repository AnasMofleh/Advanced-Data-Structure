package game;

import java.util.ArrayList;

/**
 * @author A&B
 * @version 2.0
 *
 */
final public class Sudoku {
	private int[][] sudoku;
	private ArrayList<Tuple> values;

	
	/**
	 * Constructs an empty soduku board represented by a 9x9 matrix of integers, and an ArrayList used for keeping track off already set values 
	 * and thier positions on the soduku board.
	 */
	public Sudoku() {
		this.sudoku = new int[9][9];
		values = new ArrayList<>();
	}

	/**
	 * Inserts an object of Tuple in an ArrayList that keeps track of already set values and their position in the matrix representing the soduku board.
	 * 
	 * @param value a class with atributes that keep track of a value and its position on the soduku board. 
	 */
	public void addValues(Tuple value) {
		values.add(value);
	}

	/**
	 * Inserts an integer value in the matrix repesenting the soduku board at position specified by row and colomn.
	 * @param rows integer value representing the y-component of a position in the soduku board.
	 * @param cols integer value representing the x-component of a position in the soduku board.
	 * @param value integer value at a position in the soduku board.
	 */
	public void setValue(int rows, int cols, int value) {
		sudoku[rows][cols] = value;
	}

	/**
	 * Returns the integer value in the matrix repesenting the soduku board at position specified by row and colomn.
	 * @param rows integer value representing the y-component of a position in the soduku board.
	 * @param cols integer value representing the x-component of a position in the soduku board.
	 * @return returns integer value att specified position in the soduku board.
	 */
	public int getValue(int rows, int cols) {
		return sudoku[rows][cols];
	}

	/**
	 * Sets integer value in matrix to 0 at position specified by row and colomn.
	 * @param rows integer value representing the y-component of a position in the soduku board.
	 * @param cols integer value representing the x-component of a position in the soduku board.
	 */
	private void remove(int rows, int cols) {
		sudoku[rows][cols] = 0;
	}

	/**
	 * Clears matrix and ArrayList of values.
	 */
	public void clear() {
		this.sudoku = new int[9][9];
		this.values = new ArrayList<>();
	}

	private boolean findRegion(int row, int col, int value) {
		int regionRow = row / 3;
		int regionCol = col / 3;
		for (int r = 3 * regionRow; r < 3 * regionRow + 3; r++) {
			for (int c = 3 * regionCol; c < 3 * regionCol + 3; c++) {
				if (sudoku[r][c] == value && r != row && c != col) {
					return true;
				}

			}
		}
		return false;
	}

	/**
	 * Checks if there is a duplicate of the passed value in either the same subsection, row, or colomn of the sodukuboard 
	 * in wich the value is to be placed. Returns True if a duplicate is found, and False if not.
	 * @param row integer value representing the y-component of a position in the soduku board.
	 * @param col integer value representing the x-component of a position in the soduku board.
	 * @param value integer value at a position in the soduku board.
	 * @return returns True if a duplicate is found, and False if not.
	 */
	public boolean FindDuplicate(int row, int col, int value) {
		for (int i = 0; i < 9; i++) {
			if (findRegion(row, col, value) || getValue(i, col) == value && i != row
					|| getValue(row, i) == value && i != col) {
				return true;
			}
		}
		return false;
	}

	
	private boolean checktuples() {
		boolean b = true;
		if (!values.isEmpty()) {
			for (Tuple t : values) {
				if (FindDuplicate(t.getX(), t.getY(), t.getZ()))
					b = false;
			}
		}
		return b;
	}

	/**
	 * Solves the given soduku puzzle by recursive backtracking,
	 * returns True if the puzzle can be solved, and False if not.
	 * @return returns True if the puzzle can be solved, and False if not.
	 */
	public boolean solve() {
		return solve(0, 0);
	}

	private boolean solve(int row, int col) {
		if (row == 9) {
			return true;
		}

		else if (checktuples()) {
			if (getValue(row, col) == 0) {
				for (int value = 1; value < 10; value++) {
					if (!FindDuplicate(row, col, value)) {
						setValue(row, col, value);
						if (col == 8) {
							if (solve(row + 1, 0))
								return true;
						} else {
							if (solve(row, col + 1))
								return true;
						}
					}
				}
				remove(row, col);
			} else {
				if (!FindDuplicate(row, col, getValue(row, col))) {
					if (col == 8) {
						return solve(row + 1, 0);
					} else {
						return solve(row, col + 1);
					}
				}
			}
		}
		return false;
	}
}