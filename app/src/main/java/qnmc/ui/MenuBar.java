package qnmc.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;

	public static int bits;

	private static final String OBJECTIVE = 
	"""
		The Quine McCluskey algorithm (or the method of prime implicants) 
		is a method used for minimization of boolean functions which was developed by W.V. 
		Quine and Edward J. McCluskey. It is functionally identical to Karnaugh mapping, 
		but the tabular form makes it more efficient for use in computer algorithms, and
		it also gives a deterministic way to check that the minimal form of a Boolean 
		function has been reached. It is sometimes referred to as the tabulation method.
	""";

	public MenuBar() {
		
		// Create 'File' option and add to the menu bar
		JMenu fileMenuBarOption = new JMenu("File");
		fileMenuBarOption.setMnemonic(KeyEvent.VK_F);
		add(fileMenuBarOption);

		// Create 'New' item and add as child to 'File'
		JMenuItem newMenuItem = new JMenuItem("New  Ctrl+N", KeyEvent.VK_N);
		fileMenuBarOption.add(newMenuItem);
		newMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUI.start();
			}
		});

		// Create 'Exit' item and add as child to 'File'
		JMenuItem exitMenuItem = new JMenuItem("Exit  Alt+F4", KeyEvent.VK_N);
		fileMenuBarOption.add(exitMenuItem);
		exitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		// Create 'Help' option and add to the menu bar
		JMenu helpMenuBarOption = new JMenu("Help");
		helpMenuBarOption.setMnemonic(KeyEvent.VK_F);
		add(helpMenuBarOption);

		// Create 'About Quine McCluskey Algorithm' item and add as child to 'Help'
		JMenuItem aboutMenuItem = new JMenuItem("About Quine McCluskey Algorithm", KeyEvent.VK_N);
		helpMenuBarOption.add(aboutMenuItem);
		aboutMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, OBJECTIVE, "Quine McCluskey Prime Implicant Generator", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		// Create 'Help' option and add to the menu bar
		JMenu developers = new JMenu("About...");
		developers.setMnemonic(KeyEvent.VK_F);
		add(developers);

		// Add 'Jane Smith', 'John Doe', 'Ashok Kumar' to the 'About' section
		for (JMenuItem item : getDeveloperMenuBarItems()) {
			developers.add(item);
		}
	}

	public static ArrayList<JMenuItem> getDeveloperMenuBarItems() {
		ArrayList<JMenuItem> developerMenuItems = new ArrayList<>();
		String[] developers = {"Jane Smith", "John Doe", "Ashok Kumar"};

		for (int i = 0; i < developers.length; i++) {
			final int developerIndex = i;

			JMenuItem developerMenuItem = new JMenuItem("Developer " + (i + 1));
			developerMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(null, developers[developerIndex],
							"Quine McCluskey Prime Implicant Generator", JOptionPane.INFORMATION_MESSAGE);
				}
			});

			developerMenuItems.add(developerMenuItem);
		}

		return developerMenuItems;
	}

}
