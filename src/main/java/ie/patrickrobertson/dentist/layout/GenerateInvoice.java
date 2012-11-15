package ie.patrickrobertson.dentist.layout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JButton;

public class GenerateInvoice extends LayoutTemplate {

	private JLabel systemTypeLabel;

	public GenerateInvoice() {
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 26, 292, 259);
		add(panel);
		
		JButton btnGenerateInvoice = new JButton("Generate Invoice");
		btnGenerateInvoice.setBounds(458, 337, 146, 23);
		add(btnGenerateInvoice);
		
		JButton btnAddProceedure = new JButton("Add Proceedure");
		btnAddProceedure.setBounds(312, 296, 146, 23);
		add(btnAddProceedure);
		
		JButton btnAddPatient = new JButton("Add Patient");
		btnAddPatient.setBounds(10, 296, 105, 23);
		add(btnAddPatient);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(312, 26, 292, 259);
		add(panel_1);
		
		JLabel lblProceedures = new JLabel("Proceedures");
		lblProceedures.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProceedures.setBounds(312, 11, 85, 14);
		add(lblProceedures);
		
		JLabel lblNewLabel_1 = new JLabel("Patients");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 11, 70, 14);
		add(lblNewLabel_1);
		
		JLabel lblSelectTheProceedures = new JLabel("Please select the patient and proceedures performed to generate invoice.");
		lblSelectTheProceedures.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblSelectTheProceedures.setBounds(10, 346, 438, 14);
		add(lblSelectTheProceedures);

	}

	public void setSystemLabelType(String title) {
		systemTypeLabel.setText(title);
	}
}
