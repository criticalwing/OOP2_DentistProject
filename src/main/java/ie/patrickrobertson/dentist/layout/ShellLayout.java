package ie.patrickrobertson.dentist.layout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class ShellLayout extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField txtPnamesearch;
	private JTextField searchbyPatientIDField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShellLayout window = new ShellLayout();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ShellLayout() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.add(setSearchSpace(),BorderLayout.CENTER);
		


	}
	
	private JPanel setTitleBlock(){
		
		JPanel titleBlock = new JPanel();
		titleBlock.setLayout(new BoxLayout(titleBlock, BoxLayout.X_AXIS));
		
		JLabel lblDentistSurgerySystem = new JLabel("Dentist Surgery System");
		lblDentistSurgerySystem.setHorizontalAlignment(SwingConstants.LEFT);
		titleBlock.add(lblDentistSurgerySystem);
		
		return titleBlock;
	}
	
	private JPanel setSearchSpace(){

		
		JPanel search_Space = new JPanel(new GridBagLayout());

		JTextField searchbyPatientNameField = new JTextField();
		searchbyPatientNameField.setColumns(20);
		
		JButton searchByPatientNameButton = new JButton();
		searchByPatientNameButton.setText("Search");

		JTextField searchbyPatientIDField = new JTextField();
		searchbyPatientIDField.setColumns(20);
		
		JButton searchByPatientIDButton = new JButton();
		searchByPatientIDButton.setText("Search");
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10,10,10,10);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		search_Space.add(searchbyPatientNameField, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		search_Space.add(searchByPatientNameButton, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		search_Space.add(searchbyPatientIDField, gbc);
		gbc.gridx = 2;
		gbc.gridy = 1;
		search_Space.add(searchByPatientIDButton, gbc);
		
		
		
		
		return search_Space;
	}
	
	

}
