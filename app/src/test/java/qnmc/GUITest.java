package qnmc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import qnmc.ui.GUI;

public class GUITest {

    @Test
    public void testToBinaryWithValidInputs() {

        try {
            assertEquals("0000", GUI.toBinary(0, 4));
            assertEquals("01011", GUI.toBinary(11, 5));
            assertEquals("11111", GUI.toBinary(31, 5));
            assertEquals("101", GUI.toBinary(5, 3));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testToBinaryWithInvalidInputs() {

        try {
            assertThrows(Exception.class, () -> {
                GUI.toBinary(-1, 4);
            });

            assertThrows(Exception.class, () -> {
                GUI.toBinary(32, 4);
            });

            assertThrows(Exception.class, () -> {
                GUI.toBinary(-10, -4);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
