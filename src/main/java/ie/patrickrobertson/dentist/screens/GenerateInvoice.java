package ie.patrickrobertson.dentist.screens;

import ie.patrickrobertson.dentist.Patient;
import ie.patrickrobertson.dentist.Procedure;
import ie.patrickrobertson.dentist.service.DataAccess;
import ie.patrickrobertson.dentist.service.JDateChooser;
import ie.patrickrobertson.dentist.service.PatientTableModel;
import ie.patrickrobertson.dentist.service.ProcedureTableModel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.border.LineBorder;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.UIManager;
import javax.swing.JComboBox;


public class GenerateInvoice extends LayoutTemplate {

	private JLabel lblPatientToAdd = new JLabel();
	private JTextArea lblProceduresToAdd = new JTextArea();
	private JTable procedureListPanel = new JTable();
	private DataAccess dataAccess;
	private Patient patient;
	private ArrayList<Procedure> procedures;
	private JTable patientListPanel;
	private JButton btnResetProc;
	private JLabel lblTotalCost;
	private JButton btnAddPatientReset;
	private JButton btnGenerateInvoice;
	private JButton btnAddPatient;
	private JButton btnAddProceedure;
	private JDateChooser dateChooser;
	private JButton btnSelectDifferentDate;
	private JLabel lblDate;
	private SimpleDateFormat df;
	private DatePicker dp;

	public GenerateInvoice(DataAccess dataAccess) {
		df = new SimpleDateFormat("dd/MM/yyyy");
		this.dataAccess = dataAccess;
		procedures = new ArrayList<Procedure>();
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 26, 292, 180);

		patientListPanel = new JTable(listPatients());
		patientListPanel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		patientListPanel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		patientListPanel.getColumnModel().getColumn(0).setPreferredWidth(35);
		patientListPanel.getColumnModel().getColumn(1).setPreferredWidth(255);
		patientListPanel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				btnAddPatient.setVisible(true);
				
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
				btnAddPatient.setVisible(true);
				
			}
		});
		panel.setLayout(null);

		JScrollPane patScroller = new JScrollPane(patientListPanel);
		patScroller
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		patScroller.setBounds(0, 0, 292, 180);

		panel.add(patScroller);
		add(panel);

		btnGenerateInvoice = new JButton("Generate Invoice");
		btnGenerateInvoice.setBounds(458, 337, 146, 23);
		add(btnGenerateInvoice);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(312, 26, 292, 180);

		procedureListPanel = new JTable(listProcedures());
		procedureListPanel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		procedureListPanel
				.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		procedureListPanel.getColumnModel().getColumn(0).setPreferredWidth(35);
		procedureListPanel.getColumnModel().getColumn(1).setPreferredWidth(180);
		procedureListPanel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				btnAddProceedure.setVisible(true);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				btnAddProceedure.setVisible(true);
				
			}
		});
		panel.setLayout(null);

		JScrollPane procScroller = new JScrollPane(procedureListPanel);
		procScroller
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		procScroller.setBounds(0, 0, 292, 180);
		panel_1.setLayout(null);

		panel_1.add(procScroller);
		add(panel_1);

		JLabel lblProceedures = new JLabel("Proceedures");
		lblProceedures.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProceedures.setBounds(312, 11, 85, 14);
		add(lblProceedures);

		JLabel lblNewLabel_1 = new JLabel("Patients");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 11, 70, 14);
		add(lblNewLabel_1);

		JLabel lblPatient = new JLabel("Patient:");
		lblPatient.setBounds(10, 251, 46, 14);
		add(lblPatient);

		lblPatientToAdd = new JLabel("Patient");
		lblPatientToAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPatientToAdd.setBounds(10, 264, 292, 23);
		add(lblPatientToAdd);
		lblPatientToAdd.setVisible(false);

		JLabel lblProcedures = new JLabel("Procedures:");
		lblProcedures.setBounds(312, 251, 146, 14);
		add(lblProcedures);

		lblProceduresToAdd = new JTextArea("");
		lblProceduresToAdd.setAlignmentY(Component.TOP_ALIGNMENT);
		lblProceduresToAdd.setAlignmentX(Component.LEFT_ALIGNMENT);
		lblProceduresToAdd.setLineWrap(true);
		lblProceduresToAdd.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblProceduresToAdd.setEditable(false);
		lblProceduresToAdd.setBackground(UIManager
				.getColor("EditorPane.disabledBackground"));
		lblProceduresToAdd.setBorder(null);
		lblProceduresToAdd.setBounds(312, 265, 292, 61);
		add(lblProceduresToAdd);
		lblProceduresToAdd.setVisible(false);

		btnAddProceedure = new JButton("Add Proceedure");
		btnAddProceedure.setBounds(312, 217, 136, 23);
		btnAddProceedure.addActionListener(new addProcedureListener());
		add(btnAddProceedure);
		btnAddProceedure.setVisible(false);

		btnAddPatient = new JButton("Add Patient");
		btnAddPatient.setBounds(10, 217, 105, 23);
		btnAddPatient.addActionListener(new addPatientListener());
		add(btnAddPatient);
		btnAddPatient.setVisible(false);

		btnResetProc = new JButton("Reset");
		btnResetProc.setBounds(458, 217, 89, 23);
		add(btnResetProc);
		btnResetProc.addActionListener(new addProcedureResetListener());
		btnResetProc.setVisible(false);

		JLabel lblTotalCosttitle = new JLabel("Total Cost:");
		lblTotalCosttitle.setBounds(10, 335, 70, 14);
		add(lblTotalCosttitle);

		lblTotalCost = new JLabel("0.00");
		lblTotalCost.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotalCost.setBounds(22, 349, 93, 14);
		add(lblTotalCost);

		JLabel label = new JLabel("\u20AC");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(10, 349, 46, 14);
		add(label);

		btnAddPatientReset = new JButton("Reset");
		btnAddPatientReset.setBounds(125, 217, 89, 23);
		add(btnAddPatientReset);
		
		dp = new DatePicker();
		add(dp);
		
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

	public JButton getBtnGenerateInvoice() {
		return btnGenerateInvoice;
	}

	public void setBtnGenerateInvoice(JButton btnGenerateInvoice) {
		this.btnGenerateInvoice = btnGenerateInvoice;
	}

	public DatePicker getDp() {
		return dp;
	}

	public void setDp(DatePicker dp) {
		this.dp = dp;
	}

	private TableModel listPatients() {
		return new PatientTableModel(dataAccess.getPatients(), 2);

	}

	private TableModel listProcedures() {
		return new ProcedureTableModel(dataAccess.getProcedures(), 3);
	}

	public class addPatientListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String patientTitle = (String) patientListPanel.getModel()
					.getValueAt(patientListPanel.getSelectedRow(), 1);
			patient = dataAccess.findPatientByID((int) patientListPanel
					.getModel()
					.getValueAt(patientListPanel.getSelectedRow(), 0));
			btnAddPatientReset.setVisible(true);
			lblPatientToAdd.setText(patientTitle);
			lblPatientToAdd.setVisible(true);
		}

	}

	public class addProcedureListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			double totalCost = Double.parseDouble(lblTotalCost.getText());
			String procList = lblProceduresToAdd.getText();
			int[] selectedProcedures = procedureListPanel.getSelectedRows();
			for (int i : selectedProcedures) {
				procList = procList.concat(
						((String) procedureListPanel.getModel()
								.getValueAt(i, 1))).concat("\n");
				totalCost += (Double) procedureListPanel.getModel().getValueAt(
						i, 2);
				procedures.add(dataAccess
						.findProcedureByID((int) procedureListPanel.getModel()
								.getValueAt(i, 0)));
			}
			lblTotalCost.setText(String.valueOf(totalCost));
			lblProceduresToAdd.setText(procList);
			lblProceduresToAdd.setVisible(true);
			btnResetProc.setVisible(true);
		}

	}

	public class addProcedureResetListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			procedures.clear();
			lblProceduresToAdd.setText("");
			lblTotalCost.setText("0.00");
			lblProceduresToAdd.setVisible(false);
			btnResetProc.setVisible(false);
		}

	}

	public class addPatientResetListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			patient = null;
			lblPatientToAdd.setText("");
			lblPatientToAdd.setVisible(false);
			btnAddPatientReset.setVisible(false);

		}

	}
}
