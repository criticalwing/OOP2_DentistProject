package ie.patrickrobertson.dentist.screens;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class AddProcedure extends LayoutTemplate {

	/**
	 * Create the panel.
	 */
	public AddProcedure() {

		JPanel patientFirstName = new JPanel();
		patientFirstName.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		patientFirstName.setBackground(Color.WHITE);
		patientFirstName.setBounds(10, 26, 292, 23);
		add(patientFirstName);
		
		JButton btnAddProcedure = new JButton("Add Procedure");
		btnAddProcedure.setBounds(10, 287, 139, 23);
		add(btnAddProcedure);
		
		JLabel lblProceedures = new JLabel("Brief Description");
		lblProceedures.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProceedures.setBounds(10, 60, 139, 14);
		add(lblProceedures);
		
		JLabel lblNewLabel_1 = new JLabel("Procedure Title");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 11, 197, 14);
		add(lblNewLabel_1);
		
		JPanel patientAddress1 = new JPanel();
		patientAddress1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		patientAddress1.setBackground(Color.WHITE);
		patientAddress1.setBounds(10, 79, 292, 144);
		add(patientAddress1);
		
		JPanel patientContactNo = new JPanel();
		patientContactNo.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		patientContactNo.setBackground(Color.WHITE);
		patientContactNo.setBounds(312, 79, 292, 144);
		add(patientContactNo);
		
		JLabel lblPatientContactNumber = new JLabel("Related Procedures");
		lblPatientContactNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPatientContactNumber.setBounds(312, 60, 139, 14);
		add(lblPatientContactNumber);
		
		JLabel lblPatientSurname = new JLabel("Procedure [Euros]");
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
		patientAddress2.setBounds(10, 253, 292, 23);
		add(patientAddress2);
		
		JLabel lblPatientAddress = new JLabel("Estimates Time to Complete [Mins]");
		lblPatientAddress.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPatientAddress.setBounds(10, 234, 211, 14);
		add(lblPatientAddress);
		
		
		
		
		
	}

}
