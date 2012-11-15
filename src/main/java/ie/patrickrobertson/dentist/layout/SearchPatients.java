package ie.patrickrobertson.dentist.layout;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class SearchPatients extends LayoutTemplate {

	/**
	 * Create the panel.
	 */
	public SearchPatients() {

		JPanel patientFirstName = new JPanel();
		patientFirstName.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		patientFirstName.setBackground(Color.WHITE);
		patientFirstName.setBounds(10, 93, 620, 217);
		add(patientFirstName);
		
		JButton btnAddPatient = new JButton("Search");
		btnAddPatient.setBounds(525, 60, 105, 23);
		add(btnAddPatient);
		
		JLabel lblNewLabel_1 = new JLabel("Results");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 78, 197, 14);
		add(lblNewLabel_1);
		
		JPanel patientContactNo = new JPanel();
		patientContactNo.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		patientContactNo.setBackground(Color.WHITE);
		patientContactNo.setBounds(10, 26, 292, 23);
		add(patientContactNo);
		
		JLabel lblPatientContactNumber = new JLabel("Patient - Name");
		lblPatientContactNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPatientContactNumber.setBounds(10, 11, 139, 14);
		add(lblPatientContactNumber);
		
		JLabel lblInvoiceDate = new JLabel("Patient - ID");
		lblInvoiceDate.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInvoiceDate.setBounds(338, 11, 139, 14);
		add(lblInvoiceDate);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBackground(Color.WHITE);
		panel.setBounds(338, 26, 292, 23);
		add(panel);
		
		JButton btnGenerateInvoice = new JButton("Generate Invoice");
		btnGenerateInvoice.setBounds(491, 321, 139, 23);
		add(btnGenerateInvoice);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(393, 321, 89, 23);
		add(btnEdit);
		
		
	}

}
