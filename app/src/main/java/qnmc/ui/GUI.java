package qnmc.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import qnmc.controller.QuineController;
import qnmc.validators.Validator;

public class GUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Set<String> set = new TreeSet<>();

	public GUI(Validator validator) {
		super("Quine McCluskey Prime Implicant Generator");

		// Main application window styles
		setLayout(null); 
		setSize(550, 500); 
		setResizable(false);
		setJMenuBar(new MenuBar());
		
		// Panel containing all of the UI elements
		JPanel panel = new JPanel(); 
		panel.setBounds(0, 0, 500, 500); 
		panel.setLayout(null);

		// Label prompting the user to enter a minterm
		JLabel mintermLabel = new JLabel("Enter Minterm list: ");
		mintermLabel.setBounds(50, 100, 200, 30);
		mintermLabel.setFont(new Font("Verdana", Font.BOLD, 14));
		panel.add(mintermLabel);

		// Textfield for user to enter a minterm into
		JTextField mintermTextField = new JTextField();
		mintermTextField.setBounds(50, 140, 70, 30);
		panel.add(mintermTextField);

		// Next button to add a minterm to the item's list
		JButton nextButton = new JButton("Next");
		nextButton.setBounds(140, 140, 70, 30);
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					if (validator.validate(Integer.parseInt(mintermTextField.getText()))) {
						set.add(mintermTextField.getText());
						mintermTextField.setText("");
					} else {
						JOptionPane.showMessageDialog(null, validator.getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE, null);
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, validator.getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE, null);
				}
			}
		});
		panel.add(nextButton);

		JTextArea resultsTextArea = new JTextArea();
		resultsTextArea.setBounds(50, 200, 300, 200);
		resultsTextArea.setEditable(false);
		panel.add(resultsTextArea);

		JButton calculateButton = new JButton("Calculate");
		calculateButton.setBounds(400, 250, 100, 50);
		calculateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				resultsTextArea.setText(QuineController.getInstance().process(set, validator));

			}
		});
		panel.add(calculateButton);

		setVisible(true); 
		add(panel);
	}

	public static void main(String[] args) {
		setUILookAndFeel();
		start();
	}

	private static void setUILookAndFeel() {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			System.out.println("Unable to set the UI look and feel to 'NimbusLookAndFeel'");
		}
	}

	public static void start() {
		String numOfBitsString = JOptionPane.showInputDialog("Enter the boolean bits(3 to 5): ");
		int numOfBits = -1;

		try {
			numOfBits = Integer.parseInt(numOfBitsString);
		} catch (NumberFormatException e) {
			System.out.println("Error converting '" + numOfBitsString + "' into an integer");
		}

		if (numOfBits < 3 || numOfBits > 5) {
			JOptionPane.showMessageDialog(null, "Wrong input. Press File and then NEW", "Error", JOptionPane.ERROR_MESSAGE, null);
		}

		GUI gui = new GUI(Validator.of(numOfBits));
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
