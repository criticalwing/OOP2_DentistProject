package ie.patrickrobertson.dentist.layout;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// setup basic parameters of the window
	public MainWindow() {
		super("Biters : Dentist Surgery");

		setSize(600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		

		JPanel calendarPanel = new JPanel();

		//run seperate sections to develop output window
		setupTitlePanel();
		setupSearchPanel();
		add(calendarPanel, BorderLayout.SOUTH);

	}

	private void setupTitlePanel(){
		
		JPanel titlePanel = new JPanel(new GridBagLayout());
		add(titlePanel, BorderLayout.NORTH);
		
		
		JLabel mainTitle = new JLabel("Biters : Dentist Surgery");
		mainTitle.setFont(new Font("Arial", 1, 22));
		mainTitle.setAlignmentX(LEFT_ALIGNMENT);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridwidth = 1;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		titlePanel.add(mainTitle, gbc);
		
		
	}
	
	
 	private void setupSearchPanel() {

		JPanel searchPanel = new JPanel(new GridBagLayout());
		add(searchPanel, BorderLayout.CENTER);
		searchPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Search"), BorderFactory.createEmptyBorder(5,5,5,5)));

		JTextField searchbyPatientNameField = new JTextField();
		searchbyPatientNameField.setColumns(20);

		JButton searchByPatientNameButton = new JButton();
		searchByPatientNameButton.setText("Search by Name");
		setupSearchActionListener(searchByPatientNameButton, "Name");

		JTextField searchbyPatientIDField = new JTextField();
		searchbyPatientIDField.setColumns(20);

		JButton searchByPatientIDButton = new JButton();
		searchByPatientIDButton.setText("Search by ID");
		setupSearchActionListener(searchByPatientIDButton, "ID");

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = GridBagConstraints.WEST;

		gbc.gridx = 0;
		gbc.gridy = 0;
		searchPanel.add(searchbyPatientNameField, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		searchPanel.add(searchByPatientNameButton, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		searchPanel.add(searchbyPatientIDField, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		searchPanel.add(searchByPatientIDButton, gbc);

	}

	private void setupSearchActionListener(JButton jb, String type) {

		if (type.equals("Name")) {
			jb.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JOptionPane.showMessageDialog(null, "Name Button is Working");
				}
			});
		}else if(type.equals("ID")){
			jb.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JOptionPane.showMessageDialog(null, "ID Button is Working");
				}
			});
		}

	}

}
