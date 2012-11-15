package ie.patrickrobertson.dentist.layout;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ListInvoices extends LayoutTemplate {

	/**
	 * Create the panel.
	 */
	public ListInvoices() {

		JPanel patientFirstName = new JPanel();
		patientFirstName.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		patientFirstName.setBackground(Color.WHITE);
		patientFirstName.setBounds(10, 26, 620, 220);
		add(patientFirstName);
		
		JButton btnAddPatient = new JButton("Search");
		btnAddPatient.setBounds(525, 333, 105, 23);
		add(btnAddPatient);
		
		JLabel lblNewLabel_1 = new JLabel("Invoice List");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 11, 197, 14);
		add(lblNewLabel_1);
		
		JPanel patientContactNo = new JPanel();
		patientContactNo.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		patientContactNo.setBackground(Color.WHITE);
		patientContactNo.setBounds(10, 299, 292, 23);
		add(patientContactNo);
		
		JLabel lblPatientContactNumber = new JLabel("Patient - Name");
		lblPatientContactNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPatientContactNumber.setBounds(10, 284, 139, 14);
		add(lblPatientContactNumber);
		
		JLabel lblRefineResults = new JLabel("Refine Results");
		lblRefineResults.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRefineResults.setBounds(10, 257, 139, 14);
		add(lblRefineResults);
		
		JLabel lblInvoiceDate = new JLabel("Invoice - Date");
		lblInvoiceDate.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInvoiceDate.setBounds(338, 284, 139, 14);
		add(lblInvoiceDate);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBackground(Color.WHITE);
		panel.setBounds(338, 299, 292, 23);
		add(panel);
		
		
		
		
		
	}

}
