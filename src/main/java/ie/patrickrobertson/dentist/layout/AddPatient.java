package ie.patrickrobertson.dentist.layout;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class AddPatient extends LayoutTemplate {

	private static final long serialVersionUID = 1L;

	public AddPatient() {

		JPanel patientFirstName = new JPanel();
		patientFirstName.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		patientFirstName.setBackground(Color.WHITE);
		patientFirstName.setBounds(10, 26, 292, 23);
		add(patientFirstName);
		
		JButton btnAddPatient = new JButton("Add Patient");
		btnAddPatient.setBounds(10, 223, 105, 23);
		add(btnAddPatient);
		
		JLabel lblProceedures = new JLabel("Patient Address 1");
		lblProceedures.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProceedures.setBounds(10, 60, 139, 14);
		add(lblProceedures);
		
		JLabel lblNewLabel_1 = new JLabel("Patient First Name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 11, 197, 14);
		add(lblNewLabel_1);
		
		JPanel patientAddress1 = new JPanel();
		patientAddress1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		patientAddress1.setBackground(Color.WHITE);
		patientAddress1.setBounds(10, 79, 292, 23);
		add(patientAddress1);
		
		JPanel patientContactNo = new JPanel();
		patientContactNo.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		patientContactNo.setBackground(Color.WHITE);
		patientContactNo.setBounds(10, 189, 292, 23);
		add(patientContactNo);
		
		JLabel lblPatientContactNumber = new JLabel("Patient Contact Number:");
		lblPatientContactNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPatientContactNumber.setBounds(10, 170, 139, 14);
		add(lblPatientContactNumber);
		
		JLabel lblPatientSurname = new JLabel("Patient Surname:");
		lblPatientSurname.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPatientSurname.setBounds(312, 11, 197, 14);
		add(lblPatientSurname);
		
		JPanel patientSurname = new JPanel();
		patientSurname.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		patientSurname.setBackground(Color.WHITE);
		patientSurname.setBounds(312, 26, 292, 23);
		add(patientSurname);
		
		JPanel patientAddress2 = new JPanel();
		patientAddress2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		patientAddress2.setBackground(Color.WHITE);
		patientAddress2.setBounds(10, 132, 292, 23);
		add(patientAddress2);
		
		JLabel lblPatientAddress = new JLabel("Patient Address 2");
		lblPatientAddress.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPatientAddress.setBounds(10, 113, 139, 14);
		add(lblPatientAddress);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBackground(Color.WHITE);
		panel.setBounds(312, 75, 292, 137);
		add(panel);
		
		JLabel lblPatientNotes = new JLabel("Patient Notes:");
		lblPatientNotes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPatientNotes.setBounds(312, 60, 197, 14);
		add(lblPatientNotes);
		
		
		
		
		
	}
}
