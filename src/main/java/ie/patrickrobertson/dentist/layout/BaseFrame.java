package ie.patrickrobertson.dentist.layout;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ie.patrickrobertson.dentist.layout.Welcome;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class BaseFrame {

	private JFrame frame;
	private JPanel basePanel;
	private String systemType;
	private final Welcome welcome = new Welcome();
	private final GenerateInvoice generateInvoice = new GenerateInvoice();
	private TitleBlock titleBlock = new TitleBlock();
	private final AddPatient addPatient = new AddPatient();
	private final AddProcedure addProcedure = new AddProcedure();
	private final ListInvoices listInvoices = new ListInvoices();
	private final ListProcedures listProcedures = new ListProcedures();
	private final SearchPatients searchPatients = new SearchPatients();
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mnPatients = new JMenu("Patients");
	private final JMenu mnInvoicing = new JMenu("Invoicing");
	private final JMenu mnProcedures = new JMenu("Procedures");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BaseFrame window = new BaseFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BaseFrame() {
		
		initialize();
		systemMenu();
		welcomeScreen();

	}

	// generate page methods
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 180);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.getContentPane().add(titleBlock);
		frame.setJMenuBar(menuBar);
		titleBlock.setVisible(false);
		menuBar.setVisible(false);

	}

	private void systemMenu() {
		menuBar.add(mnPatients);
		JMenuItem mntmAddPatient = new JMenuItem("Add Patient");
		mnPatients.add(mntmAddPatient);
		mntmAddPatient.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addInvoiceScreen();
				
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

	private void welcomeScreen() {

		welcome.setBounds(10, 11, 614, 129);
		frame.getContentPane().add(welcome);
		welcome.getBtnDatabase().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setBounds(100, 100, 640, 500);
				titleBlock.setSystemTypeLabelText(welcome.getBtnDatabase()
						.getText());
				generateInvoiceScreen();
			}
		});
		welcome.getBtnNonserialized().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.setBounds(100, 100, 640, 500);
				titleBlock.setSystemTypeLabelText(welcome.getBtnNonserialized()
						.getText());
				generateInvoiceScreen();
			}
		});
		welcome.getBtnSerialized().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setBounds(100, 100, 640, 500);
				titleBlock.setSystemTypeLabelText(welcome.getBtnSerialized()
						.getText());
				generateInvoiceScreen();

			}
		});
	}

	private void generateInvoiceScreen() {
		setVisibilities();

		frame.getContentPane().add(generateInvoice);
		titleBlock.setPageTitleLabelText("Generate Invoice");

		generateInvoice.setVisible(true);
		titleBlock.setVisible(true);
	}

	private void addInvoiceScreen(){
		
		setVisibilities();

		frame.getContentPane().add(addPatient);
		titleBlock.setPageTitleLabelText("Add Patient");

		addPatient.setVisible(true);
		titleBlock.setVisible(true);
		
		
		
	}

	private void addProcedureScreen(){
		
		setVisibilities();

		frame.getContentPane().add(addProcedure);
		titleBlock.setPageTitleLabelText("Add Procedure");

		addProcedure.setVisible(true);
		titleBlock.setVisible(true);
		
		
		
	}

	private void listProceduresScreen() {
		setVisibilities();

		frame.getContentPane().add(listProcedures);
		titleBlock.setPageTitleLabelText("List Procedures");

		listProcedures.setVisible(true);
		titleBlock.setVisible(true);
		
	}
	
	private void listInvoicesScreen() {
		setVisibilities();

		frame.getContentPane().add(listInvoices);
		titleBlock.setPageTitleLabelText("List Invoices");

		listProcedures.setVisible(true);
		titleBlock.setVisible(true);
		
	}

	protected void searchPatientsScreen() {
		setVisibilities();

		frame.getContentPane().add(searchPatients);
		titleBlock.setPageTitleLabelText("Search Patients");

		searchPatients.setVisible(true);
		titleBlock.setVisible(true);
		
	}
}
