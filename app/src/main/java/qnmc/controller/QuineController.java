package qnmc.controller;

import java.util.Set;

import qnmc.model.Quine;
import qnmc.validators.Validator;

public class QuineController {
    private static QuineController instance;

    private final Quine quine;

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
            for (String term : set) {
                quine.addTerm(toBinary(Integer.parseInt(term), validator.getBits()));
            }

            quine.simplify();

            String result = quine.toString();
            return result;
        } catch (Exception e) {
            return "An error occured processing the values";
        }
    }

    private static String toBinary(int number, int bits) throws Exception {
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
}