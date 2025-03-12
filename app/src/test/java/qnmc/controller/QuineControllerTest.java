package qnmc.controller;


import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import qnmc.validators.FourBitsValidator;

public class QuineControllerTest {

    @Test
    public void testToBinaryWithNegativeNumber() {
        Exception exception = assertThrows(Exception.class, () -> {
            QuineController.toBinary(-1, 4);
        });
        assertEquals("Number must be non-negative", exception.getMessage());
    }

    @Test
    public void testToBinaryWithNumberGreaterThanMaxValueByBits() {
        Exception exception = assertThrows(Exception.class, () -> {
            QuineController.toBinary(100, 4);
        });
        assertEquals("Number cannot be represented with 4 bits.", exception.getMessage());
    }

    @Test
    public void testToBinaryWithValidInputs() throws Exception {   
        assertEquals("0", QuineController.toBinary(0, 1));
        assertEquals("1", QuineController.toBinary(1, 1));
        assertEquals("11", QuineController.toBinary(3, 2));
        assertEquals("101", QuineController.toBinary(5, 3));
        assertEquals("1100", QuineController.toBinary(12, 4));
    }

    @Test
    public void testProcessWithValidInputs() {
        QuineController quineController = QuineController.getInstance();
        FourBitsValidator validator = new FourBitsValidator();
        Set<String> set;
        
        set = new HashSet<>() {{ add("0"); add("1"); add("2"); add("4"); add("6"); add("8"); add("9"); add("11"); add("13"); add("15"); }};
        assertEquals("_00_\n0__0\n1__1\n", quineController.process(set, validator));
        
        set = new HashSet<>() {{ add("0"); add("2"); add("5"); add("6"); add("7"); add("8"); add("10"); add("12"); add("13"); add("14"); add("15");}};
        assertEquals("_0_0\n11__\n1__0\n__10\n_1_1\n_11_\n", quineController.process(set, validator));
    }

    @Test
    public void testProcessWithValueOutsideBitValidatorRange() {
        QuineController quineController = QuineController.getInstance();
        FourBitsValidator validator = new FourBitsValidator();
        Set<String> set;
        
        set = new HashSet<>() {{ add("0"); add("2"); add("99"); }};
        assertEquals("An error occured processing the values", quineController.process(set, validator));
    }

    @Test
    public void testProcessWithNoValuesProvided() {
        QuineController quineController = QuineController.getInstance();
        FourBitsValidator validator = new FourBitsValidator();
        Set<String> set;

        set = new HashSet<>() {{ }};
        assertEquals("No values have been entered", quineController.process(set, validator));
    }

}
