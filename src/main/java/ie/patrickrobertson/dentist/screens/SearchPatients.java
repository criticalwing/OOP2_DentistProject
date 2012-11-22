package ie.patrickrobertson.dentist.screens;

import ie.patrickrobertson.dentist.Patient;
import ie.patrickrobertson.dentist.service.DataAccess;
import ie.patrickrobertson.dentist.service.PatientTableModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
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
import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class SearchPatients extends LayoutTemplate {

	String patientName = "All";
	private int patientID;
	private DataAccess dataAccess;
	private ArrayList<Patient> patients;
	private TableModel tableModel;
	private JButton btnEdit;
	private JTable patientListPanel;
	private JButton btnDelete;
	private JTextField textFieldPatientSearchDetail;
	private JButton btnSearchPatient;
	private JLabel lblPatientName;
	private JLabel lblPatientID;
	private JRadioButton rdbtnSearchByID;
	private JRadioButton rdbtnSearchByName;
	private String searchType;
	private String searchParameter;

	public SearchPatients(DataAccess dataAccess, String searchParameter) {
		searchType = "ID";
		this.searchParameter = searchParameter;
		this.dataAccess = dataAccess;
		patients = setDataForTable(searchParameter);
		loadTableData();

		JPanel listOfPatients = new JPanel();
		listOfPatients.setBackground(Color.WHITE);
		listOfPatients.setBounds(10, 93, 620, 217);
		listOfPatients.setLayout(null);

		patientListPanel = new JTable(tableModel);
		patientListPanel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		patientListPanel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		patientListPanel.getColumnModel().getColumn(0).setPreferredWidth(25);
		patientListPanel.getColumnModel().getColumn(1).setPreferredWidth(150);
		patientListPanel.getColumnModel().getColumn(2).setPreferredWidth(310);
		patientListPanel.getColumnModel().getColumn(3).setPreferredWidth(120);
		patientListPanel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				patientID = (int) patientListPanel.getModel().getValueAt(
						patientListPanel.getSelectedRow(), 0);
				btnEdit.setVisible(true);
				btnDelete.setVisible(true);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

			}
		});

		JScrollPane scroller = new JScrollPane(patientListPanel);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setBounds(0, 0, 620, 217);

		listOfPatients.add(scroller);
		add(listOfPatients);

		btnSearchPatient = new JButton("Search");
		btnSearchPatient.setBounds(338, 53, 105, 23);
		add(btnSearchPatient);

		JLabel lblNewLabel_1 = new JLabel("Results");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 78, 197, 14);
		add(lblNewLabel_1);

		lblPatientName = new JLabel("Patient - Name");
		lblPatientName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPatientName.setBounds(10, 36, 139, 14);
		add(lblPatientName);
		lblPatientName.setVisible(false);

		lblPatientID = new JLabel("Patient - ID");
		lblPatientID.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPatientID.setBounds(10, 36, 139, 14);
		add(lblPatientID);

		btnDelete = new JButton("Delete");
		btnDelete.setBounds(491, 321, 139, 23);
		add(btnDelete);
		btnDelete.setVisible(false);

		btnEdit = new JButton("Edit");
		btnEdit.setBounds(393, 321, 89, 23);
		add(btnEdit);
		btnEdit.setVisible(false);

		textFieldPatientSearchDetail = new JTextField();
		textFieldPatientSearchDetail.setBounds(10, 54, 318, 20);
		add(textFieldPatientSearchDetail);
		textFieldPatientSearchDetail.setColumns(10);

		ButtonGroup searchType = new ButtonGroup();
		rdbtnSearchByID = new JRadioButton("SearchByID");
		rdbtnSearchByID.setBounds(10, 7, 118, 23);
		add(rdbtnSearchByID);
		rdbtnSearchByID.setSelected(true);
		searchType.add(rdbtnSearchByID);

		rdbtnSearchByName = new JRadioButton("Search By Name");
		rdbtnSearchByName.setBounds(130, 7, 139, 23);
		add(rdbtnSearchByName);
		searchType.add(rdbtnSearchByName);

		rdbtnSearchByID.addActionListener(new rdbtnByIDListener());
		rdbtnSearchByName.addActionListener(new rdbtnByNameListener());

	}

	private ArrayList<Patient> setDataForTable(String searchParameter) {
		if(searchParameter.equals("All")){
		return dataAccess.getPatients();
		}else{
			return dataAccess.findPatientByName(searchParameter);
		}
		
	}

	private void loadTableData() {
		tableModel = new PatientTableModel(patients, 4);
	}

	public ArrayList<Patient> getPatients() {
		return patients;
	}

	public void setPatients(ArrayList<Patient> patients) {
		this.patients = patients;
	}

	public int getPatientID() {
		return patientID;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	public JButton getBtnEdit() {
		return btnEdit;
	}

	public void setBtnEdit(JButton btnEdit) {
		this.btnEdit = btnEdit;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public JButton getBtnSearchPatient() {
		return btnSearchPatient;
	}

	public void setBtnSearchPatient(JButton btnSearchPatient) {
		this.btnSearchPatient = btnSearchPatient;
	}

	public String getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(String searchParameter) {
		this.searchParameter = searchParameter;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public JTextField getTextFieldPatientSearchDetail() {
		return textFieldPatientSearchDetail;
	}

	public void setTextFieldPatientSearchDetail(
			JTextField textFieldPatientSearchDetail) {
		this.textFieldPatientSearchDetail = textFieldPatientSearchDetail;
	}

	public class rdbtnByIDListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			lblPatientName.setVisible(false);
			lblPatientID.setVisible(true);
			searchType = "ID";

		}

	}

	public class rdbtnByNameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			lblPatientName.setVisible(true);
			lblPatientID.setVisible(false);
			searchType = "Name";

		}

	}

}
