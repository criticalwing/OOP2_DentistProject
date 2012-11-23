package ie.patrickrobertson.dentist.screens;

import ie.patrickrobertson.dentist.Patient;
import ie.patrickrobertson.dentist.Procedure;
import ie.patrickrobertson.dentist.service.DataAccess;
import ie.patrickrobertson.dentist.service.ProcedureTableModel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import java.awt.FlowLayout;
import java.awt.Component;

public class InvoiceScreen extends LayoutTemplate {

	private Patient patient;
	private ArrayList<Procedure> procedures;
	private DataAccess dataAccess;
	JLabel lblPatientName, lblCostFigure, lblPatientAddress, lblPatientContact;
	private JButton btnReset;
	private JButton btnSaveInvoice;
	private Date today;


	public InvoiceScreen(){
		
	}
	
	public InvoiceScreen(Patient p, ArrayList<Procedure> procedures, Date today) {
		this.patient = p;
		this.procedures = procedures;
		this.today = today;
		SimpleDateFormat month = new SimpleDateFormat("MMMMMMMM");
		SimpleDateFormat day = new SimpleDateFormat("dd");
		SimpleDateFormat year = new SimpleDateFormat("yyyy");

		JLabel lblPatientDetailsTitle = new JLabel("Patient Details:");
		lblPatientDetailsTitle.setBounds(10, 11, 138, 14);
		add(lblPatientDetailsTitle);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 28, 251, 69);
		add(panel);
		panel.setLayout(null);

		lblPatientName = new JLabel(patient.getPatientName());
		lblPatientName.setBounds(5, 6, 189, 17);
		panel.add(lblPatientName);
		lblPatientName.setFont(new Font("Tahoma", Font.BOLD, 14));

		lblPatientAddress = new JLabel(patient.getPatientAdd());
		lblPatientAddress.setBounds(5, 29, 236, 14);
		panel.add(lblPatientAddress);

		lblPatientContact = new JLabel(patient.getPatientPhone());
		lblPatientContact.setBounds(5, 49, 101, 14);
		panel.add(lblPatientContact);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 123, 251, 134);
		add(panel_1);
		panel_1.setLayout(null);

		JTable procedureListPanel = new JTable(listProcedures(procedures));
		procedureListPanel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		procedureListPanel
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		procedureListPanel.getColumnModel().getColumn(0).setPreferredWidth(35);
		procedureListPanel.getColumnModel().getColumn(1).setPreferredWidth(125);
		procedureListPanel.getColumnModel().getColumn(2).setPreferredWidth(90);

		JScrollPane scrollPane = new JScrollPane(procedureListPanel);
		scrollPane.setBackground(Color.WHITE);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 0, 251, 134);
		panel_1.add(scrollPane);

		JLabel lblProcedures = new JLabel("Procedures:");
		lblProcedures.setBounds(10, 108, 90, 14);
		add(lblProcedures);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(10, 336, 251, 38);
		add(panel_2);
		panel_2.setLayout(null);

		lblCostFigure = new JLabel(getTotalCost());
		lblCostFigure.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCostFigure.setBounds(170, 9, 56, 22);
		panel_2.add(lblCostFigure);

		JLabel label = new JLabel("\u20AC");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(160, 13, 15, 14);
		panel_2.add(label);

		JLabel lblTotalCost = new JLabel("Total Invoice:");
		lblTotalCost.setBounds(10, 320, 90, 14);
		add(lblTotalCost);

		btnSaveInvoice = new JButton("Save Invoice");
		btnSaveInvoice.setBounds(283, 28, 117, 23);
		add(btnSaveInvoice);

		btnReset = new JButton("Reset");
		btnReset.setBounds(410, 28, 89, 23);
		add(btnReset);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(10, 268, 46, 14);
		add(lblDate);
		
		JPanel panel_3 = new JPanel();
		panel_3.setAlignmentY(Component.TOP_ALIGNMENT);
		panel_3.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		panel_3.setBounds(10, 284, 251, 31);
		add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblDay = new JLabel(day.format(today));
		lblDay.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_3.add(lblDay);
		
		JLabel lblMonth = new JLabel(month.format(today));
		lblMonth.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_3.add(lblMonth);
		
		JLabel lblYear = new JLabel(year.format(today));
		lblYear.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_3.add(lblYear);

	}


	public JButton getBtnReset() {
		return btnReset;
	}

	public void setBtnReset(JButton btnReset) {
		this.btnReset = btnReset;
	}

	public JButton getBtnSaveInvoice() {
		return btnSaveInvoice;
	}

	public void setBtnSaveInvoice(JButton btnSaveInvoice) {
		this.btnSaveInvoice = btnSaveInvoice;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public ArrayList<Procedure> getProcedures() {
		return procedures;
	}

	public void setProcedures(ArrayList<Procedure> procedures) {
		this.procedures = procedures;
	}

	private String getTotalCost() {
		
		double cost = 0;
		for(Procedure p : procedures){
			cost += p.getProcCost();
		}
		return String.valueOf(cost);
		
	}

	private TableModel listProcedures(ArrayList<Procedure> procedures) {
		return new ProcedureTableModel(procedures, 3);
	}
}
