package qnmc.model;

public class MinTerm {
	// External data representation
	public static final char NOT_CH = '0';
	public static final char SET_CH = '1';
	public static final char ANY_CH = '_';
	
	// Internal data representation
	protected static final int NOT = 0;
	protected static final int SET = 1;
	protected static final int ANY = -1;

	protected int variableCount;
	protected int[] termValues;

	/**
	 * Constructs a MinTerm from a string representation.
	 * The string should contain only '0' (NOT), '1' (SET), or '_' (ANY) characters.
	 * @param binaryString The string representation of the minterm
	 */
	public MinTerm(String binaryString) {
		termValues = new int[binaryString.length()];
		for (int i = 0; i < binaryString.length(); i++) {
			switch (binaryString.charAt(i)) {
			case NOT_CH:
				termValues[variableCount++] = NOT;
				break;
			case SET_CH:
				termValues[variableCount++] = SET;
				break;
			case ANY_CH:
				termValues[variableCount++] = ANY;
				break;
			}
		}
	}

	/**
	 * Converts the MinTerm to its string representation.
	 * @return A string containing '0', '1', or '_' characters representing the minterm
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(variableCount);
		for (int i = 0; i < variableCount; i++) {
			switch (termValues[i]) {
				case NOT -> result.append(NOT_CH);
				case SET -> result.append(SET_CH);
				case ANY -> result.append(ANY_CH);
			}
		}
		return result.toString();
	}

	/**
	 * Validates that two MinTerms have the same variable count
	 * @param other The MinTerm to compare with
	 * @throws Exception if the minterms have different lengths
	 */
	private void validateVariableCount(MinTerm other) throws Exception {
		if (variableCount != other.variableCount)
			throw new Exception("Minterms are of differing length");
	}

	/**
	 * Checks if this MinTerm is identical to another MinTerm.
	 * @param otherTerm The MinTerm to compare with
	 * @return true if the minterms are identical, false otherwise
	 * @throws Exception if the minterms have different lengths
	 */
	public boolean isSame(MinTerm otherTerm) throws Exception {
		validateVariableCount(otherTerm);
		for (int i = 0; i < variableCount; i++) {
			if (termValues[i] != otherTerm.termValues[i])
				return false;

		}
		return true;
	}

	/**
	 * Counts the number of positions where two minterms differ.
	 * @param other The MinTerm to compare with
	 * @return The number of positions where the minterms differ
	 * @throws Exception if the minterms have different lengths
	 */
	public int getBitDifferencesCount(MinTerm other) throws Exception {
		validateVariableCount(other);
		int differenceCount = 0;
		for (int i = 0; i < variableCount; i++) {
			if (termValues[i] != other.termValues[i])
				differenceCount++;
		}
		return differenceCount;
	}

	/**
	 * Finds the first position where two minterms differ.
	 * @param other The MinTerm to compare with
	 * @return The index of the first difference, or -1 if the minterms are identical
	 * @throws Exception if the minterms have different lengths
	 */
	public int resolutionPos(MinTerm other) throws Exception {
		validateVariableCount(other);
		for (int i = 0; i < variableCount; i++) {
			if (termValues[i] != other.termValues[i])
				return i;
		}

		return -1;
	}

	/**
	 * Combines two minterms into a new minterm.
	 * @param first The first MinTerm to combine
	 * @param second The second MinTerm to combine
	 * @return A new MinTerm representing the combination of the two input minterms
	 * @throws Exception if the minterms have different lengths
	 */
	public static MinTerm combine(MinTerm firstTerm, MinTerm secondTerm) throws Exception {
		firstTerm.validateVariableCount(secondTerm);
		StringBuilder result = new StringBuilder(firstTerm.variableCount);
		for (int i = 0; i < firstTerm.variableCount; i++) {
			if (firstTerm.termValues[i] != secondTerm.termValues[i])
				result.append(ANY_CH);
			else
				result.append(firstTerm.toString().charAt(i));
		}
		return new MinTerm(result.toString());
	}
}
