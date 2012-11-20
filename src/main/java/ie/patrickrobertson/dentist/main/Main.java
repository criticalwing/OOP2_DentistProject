package ie.patrickrobertson.dentist.main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import ie.patrickrobertson.dentist.screens.AddPatient;
import ie.patrickrobertson.dentist.screens.AddProcedure;
import ie.patrickrobertson.dentist.screens.GenerateInvoice;
import ie.patrickrobertson.dentist.screens.ListInvoices;
import ie.patrickrobertson.dentist.screens.ListProcedures;
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
		searchPatients = new SearchPatients(dataAccess);
		
		
		frame.getContentPane().add(titleBlock);
		frame.getContentPane().add(generateInvoice);
		frame.getContentPane().add(addPatient);
		frame.getContentPane().add(addProcedure);
		frame.getContentPane().add(listProcedures);
		frame.getContentPane().add(listInvoices);
		frame.getContentPane().add(searchPatients);
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

		JMenuItem mntmListPatients = new JMenuItem("Search Patients");
		mnPatients.add(mntmListPatients);
		mntmListPatients.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				searchPatientsScreen();

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
		JMenuItem mntmListInvoices = new JMenuItem("List Invoices");
		mnInvoicing.add(mntmListInvoices);
		mntmListInvoices.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listInvoicesScreen();

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

	}

	//the welcome screen sets the type of data Access to be used
	private void welcomeScreen() {

		welcome.setBounds(10, 11, 614, 129);
		welcome.getBtnDatabase().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setBounds(100, 100, 640, 500);
				systemType = welcome.getBtnDatabase().getText();
				frame.setTitle("------- Biters : Dental Surgery ["+systemType+"] -------");
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
				frame.setTitle("------- Biters : Dental Surgery ["+systemType+"] -------");
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
				frame.setTitle("------- Biters : Dental Surgery ["+systemType+"] -------");
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

	private void listInvoicesScreen() {
		setVisibilities();
		//reload to ensure data is current
		frame.remove(listInvoices);
		listInvoices = new ListInvoices(dataAccess);		
		frame.getContentPane().add(listInvoices);
		titleBlock.setPageTitleLabelText("List Invoices");
		listInvoices.revalidate();
		listInvoices.setVisible(true);
		titleBlock.setVisible(true);

	}

	protected void searchPatientsScreen() {
		setVisibilities();
		frame.remove(searchPatients);
		searchPatients = new SearchPatients(dataAccess);		
		frame.getContentPane().add(searchPatients);
		titleBlock.setPageTitleLabelText("Search Patients");
		searchPatients.setVisible(true);
		titleBlock.setVisible(true);

	}
}
