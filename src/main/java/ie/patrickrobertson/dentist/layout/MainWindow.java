package ie.patrickrobertson.dentist.layout;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// setup basic parameters of the window
	public MainWindow() {
		super("Biters : Dentist Surgery");

		setSize(600, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		

		// run separate sections to develop output window
		setupTitlePanel();
		setupSearchPanel();
		setupCalendarPanel();

	}

	private void setupTitlePanel() {

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
		searchPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Search"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		JTextField searchbyPatientNameField = new JTextField();
		searchbyPatientNameField.setColumns(20);

		JButton searchByPatientNameButton = new JButton();
		searchByPatientNameButton.setText("Search by Name");
		searchByPatientNameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "Name Button is Working");
			}
		});

		JTextField searchbyPatientIDField = new JTextField();
		searchbyPatientIDField.setColumns(20);

		JButton searchByPatientIDButton = new JButton();
		searchByPatientIDButton.setText("Search by ID");
		searchByPatientIDButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "ID Button is Working");
			}
		});

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

	private void setupCalendarPanel() {
		
		JPanel calendarWrapper = new JPanel(new GridBagLayout());
		add(calendarWrapper, BorderLayout.SOUTH);
		calendarWrapper.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Todays Appointments"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		
		GridBagConstraints cWC = new GridBagConstraints();

		cWC.anchor = GridBagConstraints.WEST;
		cWC.gridx = 0;
		cWC.gridy = 0;
		JLabel name = new JLabel();
		name.setText("Name: ");
		calendarWrapper.add(name, cWC);
		
		cWC.gridx = 1;
		cWC.gridy = 0;
		JTextField patientName = new JTextField();
		patientName.setText("Patrick Robertson");
		patientName.setMargin(new Insets(2,10,2,10));
		calendarWrapper.add(patientName, cWC);
		
		cWC.gridx = 2;
		cWC.gridy = 0;	
		JLabel proceedures = new JLabel();
		proceedures.setText("Proceedures: ");
		calendarWrapper.add(proceedures, cWC);
		
		cWC.gridx = 3;
		cWC.gridy = 0;
		JTextField proceeduresField = new JTextField();
		proceeduresField.setText("Tooth Extraction\nFilling");
		proceeduresField.setMargin(new Insets(2,10,2,10));
		calendarWrapper.add(proceeduresField, cWC);
		
 


	}
}
