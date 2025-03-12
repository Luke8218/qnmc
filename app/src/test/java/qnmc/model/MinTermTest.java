package qnmc.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class MinTermTest {

    @Test
    public void testToString() {
        MinTerm term = new MinTerm("1101");
        assertEquals("1101", term.toString());
    }
    
    @Test
    public void testIsSameWithMinTermsOfDifferingBitLengths() {
        MinTerm minTerm = new MinTerm("1101"); // 13
        MinTerm otherMinTerm = new MinTerm("10101"); // 21

        Exception exception = assertThrows(Exception.class, () -> {
            minTerm.isSame(otherMinTerm);
        });

        assertEquals("Minterms are of differing length", exception.getMessage());
    }

    @Test
    public void testIsSameWithDifferentMinTerms() {
        MinTerm minTerm = new MinTerm("1101"); // 13
        MinTerm otherMinTerm = new MinTerm("0110"); // 6

        assertDoesNotThrow(() -> {
            assertFalse(minTerm.isSame(otherMinTerm));
        });
    }

    @Test
    public void testIsSameWithIdenticalMinTerms() {
        MinTerm minTerm = new MinTerm("1101"); // 13
        MinTerm otherMinTerm = new MinTerm("1101"); // 13

        assertDoesNotThrow(() -> {
            assertTrue(minTerm.isSame(otherMinTerm));
        });
    }

    @Test
    public void testGetBitDifferencesCount() {
        MinTerm minTerm = new MinTerm("1101"); // 13
        MinTerm otherMinTerm = new MinTerm("0110"); // 6

        assertDoesNotThrow(() -> {
            assertEquals(3, minTerm.getBitDifferencesCount(otherMinTerm));
        });
    }

    @Test
    public void testGetBitDifferencesCountWithDifferentLengths() {
        MinTerm minTerm = new MinTerm("1101"); // 13
        MinTerm otherMinTerm = new MinTerm("10101"); // 21

        Exception exception = assertThrows(Exception.class, () -> {
            minTerm.getBitDifferencesCount(otherMinTerm);
        });

        assertEquals("Minterms are of differing length", exception.getMessage());
    }

    @Test
    public void testGetBitDifferencesCountWithIdenticalMinTerms() {
        MinTerm minTerm = new MinTerm("1101"); // 13
        MinTerm otherMinTerm = new MinTerm("1101"); // 13

        assertDoesNotThrow(() -> {
            assertEquals(0, minTerm.getBitDifferencesCount(otherMinTerm));
        });
    }

    @Test
    public void testResolutionPosWithDifferentMinTerms() {
        MinTerm minTerm = new MinTerm("1101"); // 13
        MinTerm otherMinTerm = new MinTerm("1110"); // 14

        assertDoesNotThrow(() -> {
            assertEquals(2, minTerm.resolutionPos(otherMinTerm));
        });
    }

    @Test
    public void testResolutionPosWithIdenticalMinTerms() {
        MinTerm minTerm = new MinTerm("1101"); // 13
        MinTerm otherMinTerm = new MinTerm("1101"); // 13

        assertDoesNotThrow(() -> {
            assertEquals(-1, minTerm.resolutionPos(otherMinTerm));
        });
    }

    @Test
    public void testResolutionPosWithDifferentLengths() {
        MinTerm minTerm = new MinTerm("1101"); // 13
        MinTerm otherMinTerm = new MinTerm("10101"); // 21

        Exception exception = assertThrows(Exception.class, () -> {
            minTerm.getBitDifferencesCount(otherMinTerm);
        });

        assertEquals("Minterms are of differing length", exception.getMessage());
    }

    @Test
    public void testCombineWithDifferentLengths() {
        MinTerm minTerm = new MinTerm("1101"); // 13
        MinTerm otherMinTerm = new MinTerm("10101"); // 21

        Exception exception = assertThrows(Exception.class, () -> {
            MinTerm.combine(minTerm, otherMinTerm);
        });

        assertEquals("Minterms are of differing length", exception.getMessage());
    }

    @Test
    public void testCombineWithIdenticalMinTerms() {
        MinTerm minTerm = new MinTerm("1101"); // 13
        MinTerm otherMinTerm = new MinTerm("1101"); // 13

        assertDoesNotThrow(() -> {
            assertEquals("1101", MinTerm.combine(minTerm, otherMinTerm).toString());
        });
    }

    @Test
    public void testCombineWithDifferentMinTerms() {
        MinTerm minTerm = new MinTerm("1101"); // 13
        MinTerm otherMinTerm = new MinTerm("1011"); // 11

        assertDoesNotThrow(() -> {
            assertEquals("1__1", MinTerm.combine(minTerm, otherMinTerm).toString());
        });
    }

}
