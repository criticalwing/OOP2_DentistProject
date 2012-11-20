package ie.patrickrobertson.dentist.screens;

import ie.patrickrobertson.dentist.Patient;
import ie.patrickrobertson.dentist.service.DataAccess;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.UIManager;

public class AddPatient extends LayoutTemplate {

	private static final long serialVersionUID = 1L;
	private JTextField patientFirstName;
	private JTextField patientAddress1;
	private JTextField patientAddress2;
	private JTextField patientContactNo;
	private JTextField patientNotes;
	private JTextField patientSurname;
	private JLabel lblPatientPatient;
	private JButton btnAddPatient;
	private DataAccess dataAccess;

	public AddPatient(final DataAccess dataAccess) {
		this.dataAccess = dataAccess;

		btnAddPatient = new JButton("Add Patient");
		btnAddPatient.setBorder(UIManager.getBorder("Button.border"));
		btnAddPatient.setBounds(10, 223, 105, 23);
		add(btnAddPatient);
		btnAddPatient.addActionListener(new AddPatientListener());

		JLabel lblProceedures = new JLabel("Patient Address 1");
		lblProceedures.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProceedures.setBounds(10, 60, 139, 14);
		add(lblProceedures);

		JLabel lblPatentFirstName = new JLabel("Patient First Name:");
		lblPatentFirstName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPatentFirstName.setBounds(10, 11, 197, 14);
		add(lblPatentFirstName);

		JLabel lblPatientContactNumber = new JLabel("Patient Contact Number:");
		lblPatientContactNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPatientContactNumber.setBounds(10, 170, 139, 14);
		add(lblPatientContactNumber);

		JLabel lblPatientSurname = new JLabel("Patient Surname:");
		lblPatientSurname.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPatientSurname.setBounds(312, 11, 197, 14);
		add(lblPatientSurname);

		JLabel lblPatientAddress = new JLabel("Patient Address 2");
		lblPatientAddress.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPatientAddress.setBounds(10, 113, 139, 14);
		add(lblPatientAddress);

		JLabel lblPatientNotes = new JLabel("Patient Notes:");
		lblPatientNotes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPatientNotes.setBounds(312, 60, 197, 14);
		add(lblPatientNotes);

		patientFirstName = new JTextField();
		patientFirstName.setBounds(10, 29, 292, 20);
		add(patientFirstName);
		patientFirstName.setColumns(10);

		patientAddress1 = new JTextField();
		patientAddress1.setColumns(10);
		patientAddress1.setBounds(10, 75, 292, 20);
		add(patientAddress1);

		patientAddress2 = new JTextField();
		patientAddress2.setColumns(10);
		patientAddress2.setBounds(10, 127, 292, 20);
		add(patientAddress2);

		patientContactNo = new JTextField();
		patientContactNo.setColumns(10);
		patientContactNo.setBounds(10, 184, 292, 20);
		add(patientContactNo);

		patientNotes = new JTextField();
		patientNotes.setBounds(312, 75, 292, 129);
		add(patientNotes);
		patientNotes.setColumns(10);

		patientSurname = new JTextField();
		patientSurname.setBounds(312, 29, 292, 20);
		add(patientSurname);
		patientSurname.setColumns(10);
		
		lblPatientPatient = new JLabel("Patient : Patient, added to the System.");
		add(lblPatientPatient);
		lblPatientPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPatientPatient.setBounds(10, 269, 355, 23);
		lblPatientPatient.setVisible(false);

	}

	protected boolean validateInput() {
		if (patientFirstName.getText().equals("")
				|| patientSurname.getText().equals("")) {
			JOptionPane.showMessageDialog(null,
					"You must enter a First Name and a Surname");
			return false;
		}
		if (patientAddress1.getText().equals("")
				|| patientAddress2.getText().equals("")) {
			JOptionPane.showMessageDialog(null,
					"You must enter a Address details in both address boxes");
			return false;
		}
		if (patientContactNo.getText().equals("")) {
			JOptionPane.showMessageDialog(null,
					"You must enter a contact Number");
			return false;
		}
		return true;
	}

	public JButton getBtnAddPatient() {
		return btnAddPatient;
	}

	public void setBtnAddPatient(JButton btnAddPatient) {
		this.btnAddPatient = btnAddPatient;
	}

	public class AddPatientListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Patient p = new Patient();
			if (validateInput()) {
				p.setPatientName(patientFirstName.getText().concat(" ")
						.concat(patientSurname.getText()));
				p.setPatientAdd(patientAddress1.getText().concat(", ")
						.concat(patientAddress2.getText()));
				p.setPatientPhone(patientContactNo.getText());
				if (patientNotes.getText() != null) {
					p.setNotes(patientNotes.getText());
					dataAccess.addPatient(p);
					lblPatientPatient.setText("Patient : ".concat(patientFirstName.getText())
							.concat(" ").concat(patientSurname.getText()).concat(" added to the System"));
					lblPatientPatient.setVisible(true);
					setTextToBlank();
				}
			}
		}
	}

	public void setTextToBlank() {
		patientFirstName.setText("");
		patientAddress1.setText("");
		patientAddress2.setText("");
		patientContactNo.setText("");
		patientNotes.setText("");
		patientSurname.setText("");
	}

}