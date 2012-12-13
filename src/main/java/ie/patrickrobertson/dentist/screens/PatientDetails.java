package ie.patrickrobertson.dentist.screens;

import ie.patrickrobertson.dentist.Patient;
import ie.patrickrobertson.dentist.service.DataAccess;
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
	private JTable invoiceListTable;
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
	private int selectedHistory;
	private int selectedInvoice;
	private JButton btnInvoiceDelete;
	private JButton btnInvoicePaid;
	private DataAccess dataAccess;

	/**
	 * Create the panel.
	 */
	public PatientDetails() {

	}

	public PatientDetails(Patient patient, DataAccess dataAccess) {
		this.patient = patient;
		this.dataAccess = dataAccess;

		JLabel lblPatientNameTitle = new JLabel("Patient Name:");
		lblPatientNameTitle.setBounds(109, 11, 117, 14);
		add(lblPatientNameTitle);

		JLabel lblPatientAddressTitle = new JLabel("Patient Address:");
		lblPatientAddressTitle.setBounds(109, 48, 117, 14);
		add(lblPatientAddressTitle);

		JLabel lblContacttitle = new JLabel("Contact:");
		lblContacttitle.setBounds(109, 86, 117, 14);
		add(lblContacttitle);

		JLabel lblInvoicestitle = new JLabel("Invoices:");
		lblInvoicestitle.setBounds(10, 123, 304, 14);
		add(lblInvoicestitle);

		textFieldPatientName = new JTextField();
		textFieldPatientName.setBounds(109, 27, 304, 20);
		add(textFieldPatientName);
		textFieldPatientName.setColumns(10);
		textFieldPatientName.setText(patient.getPatientName());
		textFieldPatientName.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				PatientDetails.this.patient.setPatientName(textFieldPatientName
						.getText());

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
		textFieldPatientAddress.setBounds(109, 63, 304, 20);
		add(textFieldPatientAddress);
		textFieldPatientAddress.setText(patient.getPatientAdd());

		textFieldContact = new JTextField();
		textFieldContact.setColumns(10);
		textFieldContact.setBounds(109, 101, 304, 20);
		add(textFieldContact);
		textFieldContact.setText(patient.getPatientPhone());

		panelInvoices = new JPanel();
		panelInvoices.setBackground(Color.WHITE);
		panelInvoices.setBounds(10, 144, 522, 105);
		add(panelInvoices);
		panelInvoices.setLayout(null);

		invoiceListTable = new JTable(listInvoices());
		invoiceListTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		invoiceListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		invoiceListTable.getColumnModel().getColumn(0).setPreferredWidth(25);
		invoiceListTable.getColumnModel().getColumn(1).setPreferredWidth(75);
		invoiceListTable.getColumnModel().getColumn(2).setPreferredWidth(90);
		invoiceListTable.getColumnModel().getColumn(3).setPreferredWidth(53);
		invoiceListTable.getColumnModel().getColumn(4).setPreferredWidth(276);
		invoiceListTable.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				btnInvoiceDelete.setVisible(true);
				PatientDetails.this.selectedInvoice = (int) invoiceListTable
						.getModel().getValueAt(
								invoiceListTable.getSelectedRow(), 0);
				if(invoiceListTable
						.getModel().getValueAt(
								invoiceListTable.getSelectedRow(), 2).equals("No")){
					btnInvoicePaid.setVisible(true);
					btnInvoiceDelete.setBounds(542, 178, 89, 23);
				}else{
					btnInvoicePaid.setVisible(false);
					btnInvoiceDelete.setBounds(542, 144, 89, 23);
				}

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
				// TODO Auto-generated method stub

			}
		});

		scroller = new JScrollPane(invoiceListTable);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroller.setBounds(0, 0, 522, 105);

		panelInvoices.add(scroller);
		add(panelInvoices);

		btnSave = new JButton("Save Changes to Details");
		btnSave.setBounds(426, 11, 205, 23);
		add(btnSave);

		btnReset = new JButton("Back");
		btnReset.setBounds(426, 48, 205, 23);
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
		panelPatientHistory.setBounds(10, 269, 522, 105);
		add(panelPatientHistory);
		panelPatientHistory.setLayout(null);

		historyListTable = new JTable(listHistory());
		historyListTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		historyListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		historyListTable.getColumnModel().getColumn(0).setPreferredWidth(25);
		historyListTable.getColumnModel().getColumn(1).setPreferredWidth(185);
		historyListTable.getColumnModel().getColumn(2).setPreferredWidth(200);
		historyListTable.getColumnModel().getColumn(3).setPreferredWidth(110);
		historyListTable.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				btnHistoryEdit.setVisible(true);
				btnHistoryDelete.setVisible(true);
				selectedHistory = (int) historyListTable.getModel().getValueAt(
						historyListTable.getSelectedRow(), 0);
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
		scroller2
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroller2
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroller2.setBounds(0, 0, 522, 105);
		panelPatientHistory.add(scroller2);

		lblHistory = new JLabel("History:");
		lblHistory.setBounds(10, 252, 46, 14);
		add(lblHistory);

		buttonAddHistory = new JButton("Add");
		buttonAddHistory.setBounds(542, 269, 89, 23);
		add(buttonAddHistory);

		btnHistoryEdit = new JButton("Edit");
		btnHistoryEdit.setBounds(542, 303, 89, 23);
		add(btnHistoryEdit);
		btnHistoryEdit.setVisible(false);

		btnHistoryDelete = new JButton("Delete");
		btnHistoryDelete.setBounds(542, 337, 89, 23);
		add(btnHistoryDelete);

		btnInvoiceDelete = new JButton("Delete");
		btnInvoiceDelete.setBounds(542, 178, 89, 23);
		add(btnInvoiceDelete);

		btnInvoicePaid = new JButton("Pay");
		btnInvoicePaid.setBounds(542, 144, 89, 23);
		add(btnInvoicePaid);

		btnHistoryDelete.setVisible(false);
		btnInvoiceDelete.setVisible(false);
		btnInvoicePaid.setVisible(false);

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

	public JButton getBtnHistoryEdit() {
		return btnHistoryEdit;
	}

	public void setBtnHistoryEdit(JButton btnHistoryEdit) {
		this.btnHistoryEdit = btnHistoryEdit;
	}

	public JButton getBtnHistoryDelete() {
		return btnHistoryDelete;
	}

	public void setBtnHistoryDelete(JButton btnHistoryDelete) {
		this.btnHistoryDelete = btnHistoryDelete;
	}

	public int getSelectedHistory() {
		return selectedHistory;
	}

	public void setSelectedHistory(int selectedHistory) {
		this.selectedHistory = selectedHistory;
	}

	public int getSelectedInvoice() {
		return selectedInvoice;
	}

	public void setSelectedInvoice(int selectedInvoice) {
		this.selectedInvoice = selectedInvoice;
	}

	public JButton getBtnInvoicePaid() {
		return btnInvoicePaid;
	}

	public void setBtnInvoicePaid(JButton btnInvoicePaid) {
		this.btnInvoicePaid = btnInvoicePaid;
	}

	public JButton getBtnInvoiceDelete() {
		return btnInvoiceDelete;
	}

	public void setBtnInvoiceDelete(JButton btnInvoiceDelete) {
		this.btnInvoiceDelete = btnInvoiceDelete;
	}

	private TableModel listInvoices() {
		return new InvoiceTableModel(dataAccess.findPatientByID(
				patient.getPatient()).getP_Invoice(), 5);
	}

	private TableModel listHistory() {
		return new HistoryTableModel(patient.getP_History(), 4);
	}
}
