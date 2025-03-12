package qnmc.controller;

import java.util.Set;

import qnmc.model.Quine;
import qnmc.validators.Validator;

public class QuineController {
    private static QuineController instance;

    private Quine quine;

    private QuineController() {
        quine = new Quine();
    }
    
    // Public method to get singleton instance
    public static QuineController getInstance() {
        if (instance == null) {
            instance = new QuineController();
        }
        return instance;
    }

    public String process(Set<String> set, Validator validator) {
        try {
            if (set.isEmpty()) {
                return "No values have been entered";
            }

            for (String term : set) {
                quine.addTerm(toBinary(Integer.parseInt(term), validator.getBits()));
            }

            quine.simplify();

            String result = quine.toString();
            resetQuine();
            return result;
        } catch (Exception e) {
            // Rather than throw an exception, sending a string allows it to be
            // easily displayed to the user in the output box
            return "An error occured processing the values";
        }
    }

    public static String toBinary(int number, int bits) throws Exception {
		int maxValue = (1 << bits) - 1;

		if (number < 0) {
			throw new Exception("Number must be non-negative");
		}

		if (number > maxValue) {
			throw new Exception("Number cannot be represented with " + bits + " bits.");
		}

		String binary = Integer.toBinaryString(number);
		return String.format("%" + bits + "s", binary).replace(' ', '0');
	}

    private void resetQuine() {
        quine = new Quine();
    }
}