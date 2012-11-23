package ie.patrickrobertson.dentist.main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;

import ie.patrickrobertson.dentist.Invoice;
import ie.patrickrobertson.dentist.Patient;
import ie.patrickrobertson.dentist.Procedure;
import ie.patrickrobertson.dentist.screens.AddPatient;
import ie.patrickrobertson.dentist.screens.AddProcedure;
import ie.patrickrobertson.dentist.screens.GenerateInvoice;
import ie.patrickrobertson.dentist.screens.InvoiceScreen;
import ie.patrickrobertson.dentist.screens.ListInvoices;
import ie.patrickrobertson.dentist.screens.ListProcedures;
import ie.patrickrobertson.dentist.screens.PatientDetails;
import ie.patrickrobertson.dentist.screens.SearchPatients;
import ie.patrickrobertson.dentist.screens.TitleBlock;
import ie.patrickrobertson.dentist.screens.Welcome;
import ie.patrickrobertson.dentist.service.DataAccess;
import ie.patrickrobertson.dentist.service.NonSerializedService;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Main {

	private JFrame frame;
	private String systemType;
	private final Welcome welcome = new Welcome();
	private GenerateInvoice generateInvoice;
	private TitleBlock titleBlock = new TitleBlock();
	private AddPatient addPatient;
	private AddProcedure addProcedure;
	private ListInvoices listInvoices;
	private ListProcedures listProcedures;
	private SearchPatients searchPatients;
	private InvoiceScreen invoiceScreen;
	private PatientDetails patientDetails;
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mnPatients = new JMenu("Patients");
	private final JMenu mnInvoicing = new JMenu("Invoicing");
	private final JMenu mnProcedures = new JMenu("Procedures");
	private DataAccess dataAccess;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {

		initialize();
		systemMenu();
		welcomeScreen();

	}

	// generate page methods
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("------- Biters : Dental Surgery -------");
		frame.setBounds(100, 100, 640, 180);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setJMenuBar(menuBar);
		frame.getContentPane().add(welcome);
		welcome.setVisible(true);
		titleBlock.setVisible(false);
		menuBar.setVisible(false);

	}

	private void addPanels() {
		generateInvoice = new GenerateInvoice(dataAccess);
		addPatient = new AddPatient(dataAccess);
		addProcedure = new AddProcedure(dataAccess);
		listInvoices = new ListInvoices(dataAccess);
		listProcedures = new ListProcedures(dataAccess);
		searchPatients = new SearchPatients(dataAccess, "All");
		invoiceScreen = new InvoiceScreen();
		patientDetails = new PatientDetails();

		frame.getContentPane().add(titleBlock);
		frame.getContentPane().add(generateInvoice);
		frame.getContentPane().add(addPatient);
		frame.getContentPane().add(addProcedure);
		frame.getContentPane().add(listProcedures);
		frame.getContentPane().add(listInvoices);
		frame.getContentPane().add(searchPatients);
		frame.getContentPane().add(invoiceScreen);
		frame.getContentPane().add(patientDetails);
	}

	private void systemMenu() {
		menuBar.add(mnPatients);
		JMenuItem mntmAddPatient = new JMenuItem("Add Patient");
		mnPatients.add(mntmAddPatient);
		mntmAddPatient.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				addPatientScreen();

			}
		});

		JMenuItem mntmListPatients = new JMenuItem("Patient Details");
		mnPatients.add(mntmListPatients);
		mntmListPatients.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				searchPatientsScreen("All");

			}
		});
		menuBar.add(mnProcedures);
		JMenuItem mntmListProcedures = new JMenuItem("List Procedures");
		mnProcedures.add(mntmListProcedures);
		mntmListProcedures.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listProceduresScreen();

			}

		});
		JMenuItem mntmAddProcedures = new JMenuItem("Add Procedures");
		mnProcedures.add(mntmAddProcedures);
		mntmAddProcedures.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				addProcedureScreen();

			}
		});

		menuBar.add(mnInvoicing);
		JMenuItem mntmGenerateInvoice = new JMenuItem("Generate Invoice");
		mnInvoicing.add(mntmGenerateInvoice);
		mntmGenerateInvoice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				generateInvoiceScreen();

			}
		});

	}

	private void setVisibilities() {

		menuBar.setVisible(true);
		generateInvoice.setVisible(false);
		welcome.setVisible(false);
		addPatient.setVisible(false);
		addProcedure.setVisible(false);
		listProcedures.setVisible(false);
		listInvoices.setVisible(false);
		searchPatients.setVisible(false);
		invoiceScreen.setVisible(false);
		patientDetails.setVisible(false);

	}

	// the welcome screen sets the type of data Access to be used
	private void welcomeScreen() {

		welcome.setBounds(10, 11, 614, 129);
		welcome.getBtnDatabase().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setBounds(100, 100, 640, 500);
				systemType = welcome.getBtnDatabase().getText();
				frame.setTitle("------- Biters : Dental Surgery [" + systemType
						+ "] -------");
				addPanels();
				setVisibilities();
				generateInvoiceScreen();
			}
		});
		welcome.getBtnNonserialized().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.setBounds(100, 100, 640, 500);
				systemType = welcome.getBtnNonserialized().getText();
				frame.setTitle("------- Biters : Dental Surgery [" + systemType
						+ "] -------");
				dataAccess = new NonSerializedService();
				addPanels();
				setVisibilities();
				generateInvoiceScreen();
			}
		});
		welcome.getBtnSerialized().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setBounds(100, 100, 640, 500);
				systemType = welcome.getBtnSerialized().getText();
				frame.setTitle("------- Biters : Dental Surgery [" + systemType
						+ "] -------");
				addPanels();
				setVisibilities();
				generateInvoiceScreen();

			}
		});
	}

	private void generateInvoiceScreen() {
		setVisibilities();
		frame.remove(generateInvoice);
		generateInvoice = new GenerateInvoice(dataAccess);
		frame.getContentPane().add(generateInvoice);
		titleBlock.setPageTitleLabelText("Generate Invoice");
		generateInvoice.setVisible(true);
		titleBlock.setVisible(true);
		generateInvoice.getBtnGenerateInvoice().addActionListener(
				new GenerateInvoiceListener());
	}

	private void addPatientScreen() {

		setVisibilities();

		titleBlock.setPageTitleLabelText("Add Patient");

		addPatient.setVisible(true);
		titleBlock.setVisible(true);
	}

	private void addProcedureScreen() {

		setVisibilities();

		titleBlock.setPageTitleLabelText("Add Procedure");

		addProcedure.setVisible(true);
		titleBlock.setVisible(true);

	}

	private void listProceduresScreen() {
		setVisibilities();
		frame.remove(listProcedures);
		listProcedures = new ListProcedures(dataAccess);
		frame.getContentPane().add(listProcedures);

		titleBlock.setPageTitleLabelText("List Procedures");

		listProcedures.setVisible(true);
		titleBlock.setVisible(true);

	}

	private void searchPatientsScreen(String searchParameter) {
		setVisibilities();
		frame.remove(searchPatients);
		searchPatients = new SearchPatients(dataAccess, searchParameter);
		frame.getContentPane().add(searchPatients);
		titleBlock.setPageTitleLabelText("Patient Details");
		searchPatients.setVisible(true);
		titleBlock.setVisible(true);
		searchPatients.getBtnEdit().addActionListener(
				new searchPatientsEditListener());
		searchPatients.getBtnDelete().addActionListener(
				new searchPatientDeleteListener());
		searchPatients.getBtnSearchPatient().addActionListener(
				new SearchPatientsSearchListener());

	}

	private void invoiceScreen(Patient p, ArrayList<Procedure> procedures, Date date) {
		setVisibilities();
		// reload to ensure data is current
		frame.remove(invoiceScreen);
		invoiceScreen = new InvoiceScreen(p, procedures, date);
		frame.getContentPane().add(invoiceScreen);
		titleBlock.setPageTitleLabelText("Patient Invoice");
		invoiceScreen.setVisible(true);
		titleBlock.setVisible(true);
		// get reset button to send back to generate invoice screen
		invoiceScreen.getBtnReset().addActionListener(
				new invoiceResetListener());
		invoiceScreen.getBtnSaveInvoice().addActionListener(
				new invoiceSaveListener(date));
	}

	private void patientDetailScreen(Patient p) {
		setVisibilities();
		// reload to ensure data is current
		frame.remove(patientDetails);
		patientDetails = new PatientDetails(p);
		frame.getContentPane().add(patientDetails);
		titleBlock.setPageTitleLabelText("Patient Details");
		patientDetails.setVisible(true);
		titleBlock.setVisible(true);
		patientDetails.getBtnReset().addActionListener(
				new PatientDetailResetListener());
		patientDetails.getBtnSave().addActionListener(
				new PatientDetailSaveListener());
	}

	public class searchPatientDeleteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int n = JOptionPane.showConfirmDialog(frame,
					"Are sure you want to delete patient:".concat(dataAccess
							.findPatientByID(searchPatients.getPatientID())
							.getPatientName()), "Delete Confirmation",
					JOptionPane.YES_NO_OPTION);
			if (n == 0) {
				dataAccess.deletePatient(dataAccess
						.findPatientByID(searchPatients.getPatientID()));
				JOptionPane.showMessageDialog(null, "Patient Deleted");
				searchPatientsScreen("All");
			}
		}
	}

	public class searchPatientsEditListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			patientDetailScreen(dataAccess.findPatientByID(searchPatients
					.getPatientID()));
		}

	}

	public class PatientDetailSaveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Patient updatePatient = new Patient(0, patientDetails
					.getTextFieldPatientName().getText(), patientDetails
					.getTextFieldPatientAddress().getText(), patientDetails
					.getTextFieldContact().getText());

			dataAccess.updatePatient(Integer.parseInt(patientDetails
					.getLblPatientID().getText()), updatePatient);

			JOptionPane.showMessageDialog(
					null,
					"Patient with ID: ".concat(
							patientDetails.getLblPatientID().getText()).concat(
							" updated"));

			searchPatientsScreen("All");
		}

	}

	public class PatientDetailResetListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			searchPatientsScreen("All");

		}

	}

	public class GenerateInvoiceListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Patient p = generateInvoice.getPatient();
			ArrayList<Procedure> pA = generateInvoice.getProcedures();
			Date today = new Date();
			DateFormat df = new SimpleDateFormat("dd/MMMMMMM/yyyy");
			String date = ((String) generateInvoice.getDp().getComboBoxDay()
					.getSelectedItem()).concat("/").concat(((String) generateInvoice
					.getDp().getComboBoxMonth().getSelectedItem()).concat("/")
					.concat((String) generateInvoice.getDp().getComboBoxYear()
							.getSelectedItem()));

				try {
					today = df.parse(date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			invoiceScreen(p, pA, today);
		}

	}

	public class invoiceResetListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			generateInvoiceScreen();

		}

	}

	public class invoiceSaveListener implements ActionListener {

		Date date;
		
		public invoiceSaveListener(Date date) {
			this.date = date;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Invoice i = new Invoice(getNextInvoiceNo(), date, false,
					invoiceScreen.getProcedures());
			invoiceScreen.getPatient().addPatientInvoice(i);
			JOptionPane.showMessageDialog(null, "Invoice added to Patient: "
					.concat(invoiceScreen.getPatient().getPatientName()));
			generateInvoiceScreen();

		}

		private int getNextInvoiceNo() {
			if (invoiceScreen.getPatient().getP_Invoice().isEmpty()) {
				return 1;
			} else {
				ArrayList<Integer> tempIntArray = new ArrayList<Integer>();
				for (Invoice i : invoiceScreen.getPatient().getP_Invoice()) {
					tempIntArray.add(i.getInvoice());
				}
				return Collections.max(tempIntArray);
			}
		}

	}

	public class SearchPatientsSearchListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (searchPatients.getSearchType().equals("ID")) {

				searchByID(searchPatients.getTextFieldPatientSearchDetail()
						.getText());

			} else {
				searchByName(searchPatients.getTextFieldPatientSearchDetail()
						.getText());
			}

		}

		private void searchByID(String ID) {

			if (validInt(ID)) {
				if (dataAccess.findPatientByID(Integer.valueOf(ID)) != null) {
					patientDetailScreen(dataAccess.findPatientByID(Integer
							.valueOf(ID)));
				} else
					JOptionPane.showMessageDialog(null,
							"There are no Patients with ID: ".concat(ID));
			} else {
				JOptionPane.showMessageDialog(null,
						"You must enter a valid number");
			}

		}

		private void searchByName(String Name) {
			if (!dataAccess.findPatientByName(Name).isEmpty()) {
				searchPatientsScreen(Name);
			} else {
				JOptionPane.showMessageDialog(null,
						"There are no Patients with name containing: "
								.concat(Name));
			}
		}

		private boolean validInt(String input) {

			try {
				Integer.valueOf(input);
			} catch (NumberFormatException e) {
				return false;
			}
			return true;
		}

	}
}
