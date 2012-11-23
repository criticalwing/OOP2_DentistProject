package ie.patrickrobertson.dentist.screens;

import ie.patrickrobertson.dentist.Patient;
import ie.patrickrobertson.dentist.service.HistoryTableModel;
import ie.patrickrobertson.dentist.service.InvoiceTableModel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



public class PatientDetails extends LayoutTemplate {
	private JTextField textFieldPatientName;
	private JTextField textFieldPatientAddress;
	private JTextField textFieldContact;
	private JPanel panelInvoices;
	private JTable InvoiceListTable;
	private JScrollPane scroller;
	private Patient patient;
	private JButton btnReset;
	private JButton btnSave;
	private JLabel lblPatientID;
	private JPanel panelPatientHistory;
	private JTable HistoryListTable;
	private JScrollPane scroller2;
	private JLabel lblHistory;
	private JButton btnHistoryEdit;
	private JButton btnHistoryDelete;

	/**
	 * Create the panel.
	 */
	public PatientDetails(){
		
	}
	
	public PatientDetails(final Patient patient) {
		this.patient = patient;
		
		JLabel lblPatientNameTitle = new JLabel("Patient Name:");
		lblPatientNameTitle.setBounds(10, 7, 117, 14);
		add(lblPatientNameTitle);
		
		JLabel lblPatientAddressTitle = new JLabel("Patient Address:");
		lblPatientAddressTitle.setBounds(10, 55, 117, 14);
		add(lblPatientAddressTitle);
		
		JLabel lblContacttitle = new JLabel("Contact:");
		lblContacttitle.setBounds(10, 103, 117, 14);
		add(lblContacttitle);
		
		JLabel lblInvoicestitle = new JLabel("Invoices:");
		lblInvoicestitle.setBounds(10, 151, 304, 14);
		add(lblInvoicestitle);
		
		textFieldPatientName = new JTextField();
		textFieldPatientName.setBounds(10, 28, 304, 20);
		add(textFieldPatientName);
		textFieldPatientName.setColumns(10);
		textFieldPatientName.setText(patient.getPatientName());
		textFieldPatientName.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				patient.setPatientName(textFieldPatientName.getText());
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		textFieldPatientAddress = new JTextField();
		textFieldPatientAddress.setColumns(10);
		textFieldPatientAddress.setBounds(10, 76, 304, 20);
		add(textFieldPatientAddress);
		textFieldPatientAddress.setText(patient.getPatientAdd());
		
		textFieldContact = new JTextField();
		textFieldContact.setColumns(10);
		textFieldContact.setBounds(10, 124, 304, 20);
		add(textFieldContact);
		textFieldContact.setText(patient.getPatientPhone());
		
		panelInvoices = new JPanel();
		panelInvoices.setBackground(Color.WHITE);
		panelInvoices.setBounds(10, 172, 304, 175);
		add(panelInvoices);
		panelInvoices.setLayout(null);
		
		InvoiceListTable = new JTable(listInvoices());
		InvoiceListTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		InvoiceListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		InvoiceListTable.getColumnModel().getColumn(0).setPreferredWidth(35);
		InvoiceListTable.getColumnModel().getColumn(1).setPreferredWidth(105);
		InvoiceListTable.getColumnModel().getColumn(2).setPreferredWidth(90);
		InvoiceListTable.getColumnModel().getColumn(3).setPreferredWidth(73);

		
		scroller = new JScrollPane(InvoiceListTable);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroller.setBounds(0, 0, 620, 220);
		
		panelInvoices.add(scroller);
		add(panelInvoices);
		
		btnSave = new JButton("Save");
		btnSave.setBounds(10, 354, 89, 23);
		add(btnSave);
		
		btnReset = new JButton("Reset");
		btnReset.setBounds(109, 354, 89, 23);
		add(btnReset);
		
		JLabel lblPatientId = new JLabel("Patient ID:");
		lblPatientId.setBounds(344, 7, 89, 14);
		add(lblPatientId);
		
		lblPatientID = new JLabel("");
		lblPatientID.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblPatientID.setBounds(344, 20, 200, 38);
		add(lblPatientID);
		lblPatientID.setText(String.valueOf(patient.getPatient()));
		
		panelPatientHistory = new JPanel();
		panelPatientHistory.setBounds(344, 172, 286, 175);
		add(panelPatientHistory);
		panelPatientHistory.setLayout(null);
		
		HistoryListTable = new JTable(listHistory());
		HistoryListTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		HistoryListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		HistoryListTable.getColumnModel().getColumn(0).setPreferredWidth(25);
		HistoryListTable.getColumnModel().getColumn(1).setPreferredWidth(115);
		HistoryListTable.getColumnModel().getColumn(2).setPreferredWidth(95);
		HistoryListTable.getColumnModel().getColumn(3).setPreferredWidth(50);

		
		scroller2 = new JScrollPane(HistoryListTable);
		scroller2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroller2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroller2.setBounds(0, 0, 286, 175);
		panelPatientHistory.add(scroller2);
		
		lblHistory = new JLabel("History:");
		lblHistory.setBounds(344, 151, 46, 14);
		add(lblHistory);
		
		JButton buttonAddHistory = new JButton("Add");
		buttonAddHistory.setBounds(344, 354, 89, 23);
		add(buttonAddHistory);
		
		btnHistoryEdit = new JButton("Edit");
		btnHistoryEdit.setBounds(443, 354, 89, 23);
		add(btnHistoryEdit);
		btnHistoryEdit.setVisible(false);
		
		btnHistoryDelete = new JButton("Delete");
		btnHistoryDelete.setBounds(542, 354, 89, 23);
		add(btnHistoryDelete);
		btnHistoryDelete.setVisible(false);
		

	}

	
	public JButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(JButton btnSave) {
		this.btnSave = btnSave;
	}

	public JButton getBtnReset() {
		return btnReset;
	}

	public void setBtnReset(JButton btnReset) {
		this.btnReset = btnReset;
	}
	
	public JLabel getLblPatientID() {
		return lblPatientID;
	}

	public void setLblPatientID(JLabel lblPatientID) {
		this.lblPatientID = lblPatientID;
	}

	
	public JTextField getTextFieldPatientName() {
		return textFieldPatientName;
	}

	public void setTextFieldPatientName(JTextField textFieldPatientName) {
		this.textFieldPatientName = textFieldPatientName;
	}

	public JTextField getTextFieldPatientAddress() {
		return textFieldPatientAddress;
	}

	public void setTextFieldPatientAddress(JTextField textFieldPatientAddress) {
		this.textFieldPatientAddress = textFieldPatientAddress;
	}

	public JTextField getTextFieldContact() {
		return textFieldContact;
	}

	public void setTextFieldContact(JTextField textFieldContact) {
		this.textFieldContact = textFieldContact;
	}

	private TableModel listInvoices() {
		return new InvoiceTableModel(patient.getP_Invoice(),4);
	}
	
	private TableModel listHistory() {
		return new HistoryTableModel(patient.getP_History(),4);
	}
}
