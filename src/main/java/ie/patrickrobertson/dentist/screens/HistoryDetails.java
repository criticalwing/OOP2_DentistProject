package ie.patrickrobertson.dentist.screens;

import ie.patrickrobertson.dentist.Patient;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.Font;
import javax.swing.JTextField;

public class HistoryDetails extends LayoutTemplate {

	Patient patient;
	DatePicker dp;
	private JTextField textFieldCondition;
	private JTextField textFieldMedication;
	
	public HistoryDetails(){
		
	}
	
	public HistoryDetails(Patient patient) {
		
		this.patient = patient;
		
		JLabel lblCondition = new JLabel("Condition:");
		lblCondition.setBounds(10, 119, 98, 14);
		add(lblCondition);
		
		JLabel lblMedication = new JLabel("Medication:");
		lblMedication.setBounds(10, 171, 98, 14);
		add(lblMedication);
		
		JLabel lblPatient = new JLabel("Patient:");
		lblPatient.setBounds(10, 11, 46, 14);
		add(lblPatient);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setBounds(10, 223, 89, 23);
		add(btnNewButton);

		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(109, 223, 89, 23);
		add(btnReset);
		
		JLabel lblPatientName = new JLabel(patient.getPatientName());
		lblPatientName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPatientName.setBounds(10, 24, 253, 26);
		add(lblPatientName);
		
		dp = new DatePicker();
		dp.setBounds(10, 64, 253, 44);
		add(dp);
		
		textFieldCondition = new JTextField();
		textFieldCondition.setBounds(10, 134, 253, 26);
		add(textFieldCondition);
		textFieldCondition.setColumns(10);
		
		textFieldMedication = new JTextField();
		textFieldMedication.setColumns(10);
		textFieldMedication.setBounds(10, 186, 253, 26);
		add(textFieldMedication);

	}
}
