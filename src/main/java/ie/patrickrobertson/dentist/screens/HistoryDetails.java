package ie.patrickrobertson.dentist.screens;

import ie.patrickrobertson.dentist.History;
import ie.patrickrobertson.dentist.Patient;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class HistoryDetails extends LayoutTemplate {

	Patient patient;
	DatePicker dp;
	private JTextField textFieldCondition;
	private JTextField textFieldMedication;
	private JButton btnAdd;
	private JButton btnCancel;
	History history;
	String type;

	public HistoryDetails() {

	}

	public HistoryDetails(Patient patient, History history, String type) {

		this.patient = patient;
		this.history = history;
		this.type = type;
		standardSetup();
		if(type.equals("Edit")){
			editModeSetup(history);
		}else{
			addModeSetup();
		}

	}

	private void addModeSetup() {
		// TODO Auto-generated method stub
		
	}

	private void editModeSetup(History history2) {
		textFieldCondition.setText(history.getConditionName());
		textFieldMedication.setText(history.getMedication());
		btnAdd.setVisible(true);
	}

	private void standardSetup() {
		JLabel lblCondition = new JLabel("Condition:");
		lblCondition.setBounds(10, 119, 98, 14);
		add(lblCondition);

		JLabel lblMedication = new JLabel("Medication:");
		lblMedication.setBounds(10, 171, 98, 14);
		add(lblMedication);

		JLabel lblPatient = new JLabel("Patient:");
		lblPatient.setBounds(10, 11, 46, 14);
		add(lblPatient);

		btnAdd = new JButton("Save");
		btnAdd.setBounds(10, 223, 89, 23);
		add(btnAdd);
		btnAdd.setVisible(false);

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(10, 257, 89, 23);
		add(btnCancel);

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
		textFieldCondition.addKeyListener(new textFieldListener());

		textFieldMedication = new JTextField();
		textFieldMedication.setColumns(10);
		textFieldMedication.setBounds(10, 186, 253, 26);
		add(textFieldMedication);
		textFieldMedication.addKeyListener(new textFieldListener());
		
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public void setBtnAdd(JButton btnAdd) {
		this.btnAdd = btnAdd;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public JTextField getTextFieldCondition() {
		return textFieldCondition;
	}

	public void setTextFieldCondition(JTextField textFieldCondition) {
		this.textFieldCondition = textFieldCondition;
	}

	public JTextField getTextFieldMedication() {
		return textFieldMedication;
	}

	public void setTextFieldMedication(JTextField textFieldMedication) {
		this.textFieldMedication = textFieldMedication;
	}

	public DatePicker getDp() {
		return dp;
	}

	public void setDp(DatePicker dp) {
		this.dp = dp;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}

	public History getHistory() {
		return history;
	}

	public void setHistory(History history) {
		this.history = history;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public class textFieldListener implements KeyListener{

		@Override
		public void keyPressed(KeyEvent arg0) {
			if(!textFieldCondition.getText().equals("")&&!textFieldMedication.getText().equals("")){
				btnAdd.setVisible(true);
			}else{
				btnAdd.setVisible(false);
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
