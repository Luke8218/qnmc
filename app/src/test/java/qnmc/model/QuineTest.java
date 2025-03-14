package qnmc.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class QuineTest {

    @Test
    public void testAddTerm() {
        Quine quine = new Quine();

        assertDoesNotThrow(() -> {
            quine.addTerm("0101");
            quine.addTerm("1011");
            quine.addTerm("0001");
        });
  
        assertEquals("0101\n1011\n0001\n", quine.toString());
    }

    @Test
    public void testAddTermOverMaxLimit() {
        Quine quine = new Quine();

        Exception exception = assertThrows(Exception.class, () -> {
            for (int i = 0; i < Quine.MAX_TERMS + 1; i++) {
                quine.addTerm("0101");
            }
        });
  
        assertEquals("Maximum number of terms exceeded", exception.getMessage());
    }

    @Test
    public void testHasTerm() {
        Quine quine = new Quine();
        
        assertDoesNotThrow(() -> {
            quine.addTerm("0101");
            quine.addTerm("1011");
            quine.addTerm("0001");
        });

        assertDoesNotThrow(() -> {
            assertTrue(quine.hasTerm(new MinTerm("0101")));
            assertTrue(quine.hasTerm(new MinTerm("1011")));
            assertTrue(quine.hasTerm(new MinTerm("0001")));

            assertFalse(quine.hasTerm(new MinTerm("1111")));
        });

        Exception exception = assertThrows(Exception.class, () -> {
            quine.hasTerm(new MinTerm("010"));
        });

        assertEquals("Minterms are of differing length", exception.getMessage());
    }

    @Test
    public void testSimplify() {
        Quine quine = new Quine();

        assertDoesNotThrow(() -> {
            quine.addTerm("0101");
            quine.addTerm("1011");
            quine.addTerm("0001");
        });

        assertDoesNotThrow(() -> {
            quine.simplify();
        });

        assertEquals("0_01\n1011\n", quine.toString());
    }
    
}
