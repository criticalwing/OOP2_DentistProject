package ie.patrickrobertson.dentist.screens;

import ie.patrickrobertson.dentist.Patient;
import ie.patrickrobertson.dentist.service.DataAccess;
import ie.patrickrobertson.dentist.service.PatientTableModel;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;

public class SearchPatients extends LayoutTemplate {

	String patientName = "All";
	int patientID = 0;
	DataAccess dataAccess;
	ArrayList<Patient> patients;
	TableModel tableModel;
	
	public SearchPatients(DataAccess dataAccess) {

		this.dataAccess = dataAccess;
		patients = dataAccess.getPatients();
		loadTableData();
		
		JPanel listOfPatients = new JPanel();
		listOfPatients.setBackground(Color.WHITE);
		listOfPatients.setBounds(10, 93, 620, 217);
		listOfPatients.setLayout(null);

		JTable patientListPanel = new JTable(tableModel);
		patientListPanel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		patientListPanel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		patientListPanel.getColumnModel().getColumn(0).setPreferredWidth(25);
		patientListPanel.getColumnModel().getColumn(1).setPreferredWidth(150);
		patientListPanel.getColumnModel().getColumn(2).setPreferredWidth(310);
		patientListPanel.getColumnModel().getColumn(3).setPreferredWidth(120);
		
		JScrollPane scroller = new JScrollPane(patientListPanel);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setBounds(0, 0, 620, 217);
		
		listOfPatients.add(scroller);
		add(listOfPatients);

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
	
	public void loadTableData(){
		tableModel = new PatientTableModel(patients,4);
	}

	public ArrayList<Patient> getPatients() {
		return patients;
	}

	public void setPatients(ArrayList<Patient> patients) {
		this.patients = patients;
	}
	
	
	

}
