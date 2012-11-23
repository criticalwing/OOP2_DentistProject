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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



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
	private JTable historyListTable;
	private JScrollPane scroller2;
	private JLabel lblHistory;
	private JButton btnHistoryEdit;
	private JButton btnHistoryDelete;
	private JButton buttonAddHistory;

	/**
	 * Create the panel.
	 */
	public PatientDetails(){
		
	}
	
	public PatientDetails(final Patient patient) {
		this.patient = patient;
		
		JLabel lblPatientNameTitle = new JLabel("Patient Name:");
		lblPatientNameTitle.setBounds(109, 11, 117, 14);
		add(lblPatientNameTitle);
		
		JLabel lblPatientAddressTitle = new JLabel("Patient Address:");
		lblPatientAddressTitle.setBounds(109, 57, 117, 14);
		add(lblPatientAddressTitle);
		
		JLabel lblContacttitle = new JLabel("Contact:");
		lblContacttitle.setBounds(109, 101, 117, 14);
		add(lblContacttitle);
		
		JLabel lblInvoicestitle = new JLabel("Invoices:");
		lblInvoicestitle.setBounds(10, 151, 304, 14);
		add(lblInvoicestitle);
		
		textFieldPatientName = new JTextField();
		textFieldPatientName.setBounds(109, 32, 304, 20);
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
		textFieldPatientAddress.setBounds(109, 78, 304, 20);
		add(textFieldPatientAddress);
		textFieldPatientAddress.setText(patient.getPatientAdd());
		
		textFieldContact = new JTextField();
		textFieldContact.setColumns(10);
		textFieldContact.setBounds(109, 122, 304, 20);
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
		
		btnSave = new JButton("Save Changes");
		btnSave.setBounds(496, 11, 135, 23);
		add(btnSave);
		
		btnReset = new JButton("Cancel");
		btnReset.setBounds(496, 48, 135, 23);
		add(btnReset);
		
		JLabel lblPatientId = new JLabel("Patient ID:");
		lblPatientId.setBounds(10, 11, 89, 14);
		add(lblPatientId);
		
		lblPatientID = new JLabel("");
		lblPatientID.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblPatientID.setBounds(10, 24, 200, 38);
		add(lblPatientID);
		lblPatientID.setText(String.valueOf(patient.getPatient()));
		
		panelPatientHistory = new JPanel();
		panelPatientHistory.setBounds(344, 172, 286, 175);
		add(panelPatientHistory);
		panelPatientHistory.setLayout(null);
		
		historyListTable = new JTable(listHistory());
		historyListTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		historyListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		historyListTable.getColumnModel().getColumn(0).setPreferredWidth(25);
		historyListTable.getColumnModel().getColumn(1).setPreferredWidth(85);
		historyListTable.getColumnModel().getColumn(2).setPreferredWidth(95);
		historyListTable.getColumnModel().getColumn(3).setPreferredWidth(80);
		historyListTable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				btnHistoryEdit.setVisible(true);
				btnHistoryDelete.setVisible(true);
				
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
		scroller2 = new JScrollPane(historyListTable);
		scroller2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroller2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroller2.setBounds(0, 0, 286, 175);
		panelPatientHistory.add(scroller2);
		
		lblHistory = new JLabel("History:");
		lblHistory.setBounds(344, 151, 46, 14);
		add(lblHistory);
		
		buttonAddHistory = new JButton("Add");
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

	
	
	public Patient getPatient() {
		return patient;
	}


	public void setPatient(Patient patient) {
		this.patient = patient;
	}


	public JButton getButtonAddHistory() {
		return buttonAddHistory;
	}


	public void setButtonAddHistory(JButton buttonAddHistory) {
		this.buttonAddHistory = buttonAddHistory;
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
