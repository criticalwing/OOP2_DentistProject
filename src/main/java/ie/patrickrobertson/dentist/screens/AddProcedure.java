package ie.patrickrobertson.dentist.screens;

import ie.patrickrobertson.dentist.Procedure;
import ie.patrickrobertson.dentist.service.DataAccess;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;

public class AddProcedure extends LayoutTemplate {
	private JTextField procName;
	private JTextField procDesc;
	private JTextField procCost;
	private JTextField procRelated;
	private JTextField procTime;
	private DataAccess dataAccess;
	private JLabel lblProcedureAdded;

	/**
	 * Create the panel.
	 */
	public AddProcedure(DataAccess dataAccess) {
		this.dataAccess = dataAccess;
		JButton btnAddProcedure = new JButton("Add Procedure");
		btnAddProcedure.setBounds(10, 287, 139, 23);
		btnAddProcedure.addActionListener(new addProcedureListener());

		add(btnAddProcedure);

		JLabel lblProceedures = new JLabel("Brief Description");
		lblProceedures.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProceedures.setBounds(10, 60, 139, 14);
		add(lblProceedures);

		JLabel lblNewLabel_1 = new JLabel("Procedure Title");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 11, 197, 14);
		add(lblNewLabel_1);

		JLabel lblPatientContactNumber = new JLabel("Related Procedures");
		lblPatientContactNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPatientContactNumber.setBounds(312, 60, 139, 14);
		add(lblPatientContactNumber);

		JLabel lblPatientSurname = new JLabel("Procedure [Euros]");
		lblPatientSurname.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPatientSurname.setBounds(312, 11, 197, 14);
		add(lblPatientSurname);

		JLabel lblPatientAddress = new JLabel(
				"Estimates Time to Complete [Mins]");
		lblPatientAddress.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPatientAddress.setBounds(10, 234, 211, 14);
		add(lblPatientAddress);

		procName = new JTextField();
		procName.setBounds(10, 26, 292, 20);
		add(procName);
		procName.setColumns(10);

		procDesc = new JTextField();
		procDesc.setBounds(10, 79, 292, 144);
		add(procDesc);
		procDesc.setColumns(10);

		procRelated = new JTextField();
		procRelated.setBounds(312, 79, 292, 138);
		add(procRelated);
		procRelated.setColumns(10);

		procCost = new JTextField();
		procCost.setBounds(312, 26, 292, 20);
		add(procCost);
		procCost.setColumns(10);

		procTime = new JTextField();
		procTime.setBounds(10, 256, 292, 20);
		add(procTime);
		procTime.setColumns(10);

		lblProcedureAdded = new JLabel("Procedure : x, added to the System.");
		add(lblProcedureAdded);
		lblProcedureAdded.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProcedureAdded.setBounds(10, 317, 355, 23);
		lblProcedureAdded.setVisible(false);

	}

	protected void clearTextFields() {
		procName.setText("");
		procDesc.setText("");
		procRelated.setText("");
		procCost.setText("");
		procTime.setText("");
	}

	protected boolean validateInput() {
		if (procName.getText().equals("")) {
			JOptionPane.showMessageDialog(null,
					"You must enter a Procedure Name");
			return false;
		}
		if (procCost.getText().equals("") || !validInt(procCost.getText())) {
			JOptionPane.showMessageDialog(null,
					"You must enter a price in Euros");
			return false;
		}
		return true;
	}

	private boolean validInt(String input) {
		try {
			int x = (int) Double.parseDouble(input) * 100;
		} catch (NumberFormatException e) {
			return false;
		}
		return true;

	}

	public class addProcedureListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (validateInput()) {
				double d = Double.parseDouble(procCost.getText()) * 100;
				Procedure p = new Procedure(getNextProcID(),
						procName.getText(), (int) d);
				dataAccess.addProcedure(p);

				lblProcedureAdded.setText("Procedure ".concat(
						procName.getText()).concat(" added to the System."));
				clearTextFields();
				lblProcedureAdded.setVisible(true);
			}
		}
	}

	public int getNextProcID() {
		if (dataAccess.getProcedures().isEmpty()) {
			return 1;
		} else {
			ArrayList<Integer> iList = new ArrayList<Integer>();
			for (Procedure p : dataAccess.getProcedures()) {
				iList.add(p.getProc());
			}
			return Collections.max(iList) + 1;
		}
	}

}
