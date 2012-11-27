package ie.patrickrobertson.dentist.screens;

import ie.patrickrobertson.dentist.Invoice;
import ie.patrickrobertson.dentist.Patient;
import ie.patrickrobertson.dentist.service.DataAccess;
import ie.patrickrobertson.dentist.service.PatientInvoiceTableModel;
import ie.patrickrobertson.dentist.service.InvoiceTableModel;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PatientInvoiceListScreen extends LayoutTemplate {

	ArrayList<Patient> patientDebtors;
	private JPanel listOfPatientInvoices;
	private JTable patientListPanel;
	private JScrollPane scroller;
	private DataAccess dataAccess;
	private JButton btnViewInvoice;
	private String type;
	private JLabel lblNewLabel;
	private Patient selectedPatient;
	private Invoice selectedInvoice;
	private JButton btnMarkPaid;
	private JLabel lblTotal;
	

	public PatientInvoiceListScreen() {

	}

	public PatientInvoiceListScreen(DataAccess dataAccess, String type) {
		this.dataAccess = dataAccess;
		this.type = type;
		setButtons();

		if (dataAccess.findPatientInvoice(type).isEmpty()) {
			if (type.equals("debtors")) {
				lblNewLabel = new JLabel(
						"Currently there are no outstanding invoices.");
			} else {
				lblNewLabel = new JLabel("All invoices are outstanding.");
			}
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel.setBounds(10, 11, 620, 25);
			add(lblNewLabel);
		} else {
			lblNewLabel = new JLabel("List of Invoices.");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblNewLabel.setBounds(10, 11, 620, 25);
			add(lblNewLabel);
			
			lblTotal = new JLabel();
			lblTotal.setFont(new Font("Tahoma", Font.BOLD, 12);
			
			listOfPatientInvoices = new JPanel();
			listOfPatientInvoices.setBorder(new LineBorder(new Color(0, 0, 0),
					1, true));
			listOfPatientInvoices.setBackground(Color.WHITE);
			listOfPatientInvoices.setBounds(10, 47, 620, 220);

			patientListPanel = new JTable(listInvoices());
			patientListPanel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			patientListPanel
					.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			patientListPanel.getColumnModel().getColumn(0)
					.setPreferredWidth(120);
			patientListPanel.getColumnModel().getColumn(1)
					.setPreferredWidth(45);
			patientListPanel.getColumnModel().getColumn(2)
					.setPreferredWidth(80);
			patientListPanel.getColumnModel().getColumn(3)
					.setPreferredWidth(80);
			patientListPanel.getColumnModel().getColumn(4)
					.setPreferredWidth(292);
			patientListPanel.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					// make selected Patient equal the one selected on the table
					selectedPatient = PatientInvoiceListScreen.this.dataAccess
							.findPatientByName(
									(String) patientListPanel.getModel()
											.getValueAt(
													patientListPanel
															.getSelectedRow(),
													0)).get(0);
					// check through the patients invoice to find the selected
					// one and assign it
					for (Invoice i : selectedPatient.getP_Invoice()) {
						if (i.getInvoice() == (int) patientListPanel.getModel()
								.getValueAt(patientListPanel.getSelectedRow(),
										1)) {
							selectedInvoice = i;
						}
					}
					btnViewInvoice.setVisible(true);
					if (PatientInvoiceListScreen.this.type.equals("debtors")) {
						btnMarkPaid.setVisible(true);
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

			listOfPatientInvoices.setLayout(null);

			scroller = new JScrollPane(patientListPanel);
			scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			scroller.setBounds(0, 0, 620, 220);

			listOfPatientInvoices.add(scroller);
			add(listOfPatientInvoices);

		}

	}

	private void setButtons() {
		btnViewInvoice = new JButton("View Invoice");
		btnViewInvoice.setBounds(10, 277, 118, 23);
		add(btnViewInvoice);

		btnMarkPaid = new JButton("Mark Paid");
		btnMarkPaid.setBounds(138, 277, 110, 23);
		add(btnMarkPaid);

		btnMarkPaid.setVisible(false);
		btnViewInvoice.setVisible(false);
	}

	public JButton getBtnMarkPaid() {
		return btnMarkPaid;
	}

	public void setBtnMarkPaid(JButton btnMarkPaid) {
		this.btnMarkPaid = btnMarkPaid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public JButton getBtnViewInvoice() {
		return btnViewInvoice;
	}

	public void setBtnViewInvoice(JButton btnViewInvoice) {
		this.btnViewInvoice = btnViewInvoice;
	}

	public Patient getSelectedPatient() {
		return selectedPatient;
	}

	public Invoice getSelectedInvoice() {
		return selectedInvoice;
	}

	private TableModel listInvoices() {
		return new PatientInvoiceTableModel(
				dataAccess.findPatientInvoice(type), 5, type);
	}
}
