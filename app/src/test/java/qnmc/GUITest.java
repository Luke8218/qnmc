package qnmc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class GUITest {

    //////////////////////////////////////////////////////////////////////////////////////////
    
    @Test
    public void testDataThreeWithValidInputs() {
        GUI gui = new GUI();
        
        assertEquals("000", gui.dataThree("0"));
        assertEquals("011", gui.dataThree("3"));
        assertEquals("111", gui.dataThree("7"));
    }

    @Test
    public void testDataThreeWithInvalidInputs() {
        GUI gui = new GUI();

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            gui.dataThree("8");
        });

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            gui.dataThree("-1");
        });

        assertThrows(NumberFormatException.class, () -> {
            gui.dataThree("not a number");
        });
    }

    //////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testDataFourWithValidInputs() {
        GUI gui = new GUI();
        
        assertEquals("0000", gui.dataFour("0"));
        assertEquals("0110", gui.dataFour("6"));
        assertEquals("1111", gui.dataFour("15"));
    }

    @Test
    public void testDataFourWithInvalidInputs() {
        GUI gui = new GUI();

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            gui.dataFour("16");
        });

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            gui.dataFour("-1");
        });

        assertThrows(NumberFormatException.class, () -> {
            gui.dataFour("not a number");
        });
    }

    //////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testDataFiveWithValidInputs() {
        GUI gui = new GUI();
        
        assertEquals("00000", gui.dataFive("0"));
        assertEquals("01101", gui.dataFive("13"));
        assertEquals("11111", gui.dataFive("31"));
    }

    @Test
    public void testDataFiveWithInvalidInputs() {
        GUI gui = new GUI();

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            gui.dataFive("32");
        });

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            gui.dataFive("-1");
        });

        assertThrows(NumberFormatException.class, () -> {
            gui.dataFive("not a number");
        });
    }

    //////////////////////////////////////////////////////////////////////////////////////////
}
