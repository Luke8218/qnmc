package qnmc.model;

public class Quine {

	protected static final int MAX_TERMS = 255;
	public MinTerm[] minterms = new MinTerm[MAX_TERMS];
	public int mintermCount = 0;

	/**
	 * Adds a new minterm to the Quine-McCluskey solver.
	 * @param binaryString The string representation of the minterm to add
	 * @throws Exception if the maximum number of terms is exceeded
	 */
	public void addTerm(String binaryString) throws Exception {
		if (mintermCount == MAX_TERMS)
			throw new Exception("Quine::addTerm()");
		minterms[mintermCount++] = new MinTerm(binaryString);
	}

	/**
	 * Converts all minterms to a string representation.
	 * @return A string containing all minterms, one per line
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < mintermCount; i++) {
			result.append(minterms[i]).append("\n");
		}
		return result.toString();
	}

	/**
	 * Checks if a given minterm already exists in the solver.
	 * @param term The MinTerm to check for
	 * @return true if the minterm exists, otherwise false
	 * @throws Exception if comparison fails
	 */
	public boolean hasTerm(MinTerm term) throws Exception {
		for (int i = 0; i < mintermCount; i++) {
			if (term.isSame(minterms[i]))
				return true;
		}
		return false;
	}

	/**
	 * Simplifies the boolean function by repeatedly reducing minterms
	 * until no further reduction is possible.
	 * @throws Exception if reduction process fails
	 */
	public void simplify() throws Exception {
		while (reduce() > 0);
	}

	/**
	 * Performs one iteration of the reduction algorithm.
	 * @return The number of new terms created by reduction
	 * @throws Exception if reduction process fails
	 */
	private int reduce() throws Exception {

		int newTermCount = 0;
		MinTerm[] newReducedTerms = new MinTerm[MAX_TERMS];
		boolean[] termsUsedInReduction = new boolean[MAX_TERMS];
		// working with all minterms
		for (int i = 0; i < mintermCount; i++) {
			for (int j = i + 1; j < mintermCount; j++) {
				// finding the terms which differs in one place
				if (minterms[i].getBitDifferencesCount(minterms[j]) == 1) {
					newReducedTerms[newTermCount++] = MinTerm.combine(minterms[i], minterms[j]);
					termsUsedInReduction[i] = true;
					termsUsedInReduction[j] = true;
				}
			}
		}
		// Copy the unchanged minterms into a new list

		int totalReduced = newTermCount;
		for (int i = 0; i < mintermCount; i++) {
			if (termsUsedInReduction[i] == false) {
				newReducedTerms[totalReduced++] = minterms[i];
			}
		}

		mintermCount = 0;

		for (int i = 0; i < totalReduced; i++) {
			if (!hasTerm(newReducedTerms[i]))
				minterms[mintermCount++] = newReducedTerms[i];
		}
		
		return newTermCount;
	}
}