package qnmc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import qnmc.ui.MenuBar;

public class MenuBarTest {
    
    @Test
    public void testGetDeveloperMenuBarItems() {

        JMenuItem developer1MenuItemMock = new JMenuItem("Developer 1");
        developer1MenuItemMock.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                JOptionPane.showMessageDialog(null, "Jane Smith",
                        "Quine McCluskey Prime Implicant Generator", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JMenuItem developer2MenuItemMock = new JMenuItem("Developer 2");
        developer2MenuItemMock.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                JOptionPane.showMessageDialog(null, "John Doe",
                        "Quine McCluskey Prime Implicant Generator", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JMenuItem developer3MenuItemMock = new JMenuItem("Developer 3");
        developer3MenuItemMock.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                JOptionPane.showMessageDialog(null, "Ashok Kumar",
                        "Quine McCluskey Prime Implicant Generator", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Create mock items array
        ArrayList<JMenuItem> mockItems = new ArrayList<>(Arrays.asList(developer1MenuItemMock, developer2MenuItemMock, developer3MenuItemMock));

        MenuBar menuBar = new MenuBar();
        ArrayList<JMenuItem> actualItems = menuBar.getDeveloperMenuBarItems();

        assertEquals(mockItems.size(), actualItems.size());

        for (int i = 0; i < actualItems.size(); i++) {
            JMenuItem expectedItem = mockItems.get(i);
            JMenuItem actualItem = actualItems.get(i);

            assertEquals(expectedItem.getText(), actualItem.getText());
            assertEquals(expectedItem.getActionListeners().length, actualItem.getActionListeners().length);        
        }
    }
}
