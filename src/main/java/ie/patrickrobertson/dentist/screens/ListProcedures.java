package ie.patrickrobertson.dentist.screens;

import ie.patrickrobertson.dentist.service.DataAccess;
import ie.patrickrobertson.dentist.service.ProcedureTableModel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;

public class ListProcedures extends LayoutTemplate {

	DataAccess dataAccess;
	
	/**
	 * Create the panel.
	 */
	public ListProcedures(DataAccess dataAccess) {
		this.dataAccess = dataAccess;

		JPanel listOfProcedures = new JPanel();
		listOfProcedures.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		listOfProcedures.setBackground(Color.WHITE);
		listOfProcedures.setBounds(10, 26, 620, 220);
		
		JTable procedureListPanel = new JTable(listProcedures());
		procedureListPanel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		procedureListPanel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		procedureListPanel.getColumnModel().getColumn(0).setPreferredWidth(55);
		procedureListPanel.getColumnModel().getColumn(1).setPreferredWidth(455);
		procedureListPanel.getColumnModel().getColumn(2).setPreferredWidth(110);
		listOfProcedures.setLayout(null);
		
		JScrollPane scroller = new JScrollPane(procedureListPanel);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroller.setBounds(0, 0, 620, 220);
		
		listOfProcedures.add(scroller);
		
		add(listOfProcedures);
		
		
		JButton btnAddPatient = new JButton("Search");
		btnAddPatient.setBounds(197, 333, 105, 23);
		add(btnAddPatient);
		
		JLabel lblNewLabel_1 = new JLabel("Procedure List");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 11, 197, 14);
		add(lblNewLabel_1);
		
		JPanel patientContactNo = new JPanel();
		patientContactNo.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		patientContactNo.setBackground(Color.WHITE);
		patientContactNo.setBounds(10, 299, 292, 23);
		add(patientContactNo);
		
		JLabel lblPatientContactNumber = new JLabel("Procedure - Name");
		lblPatientContactNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPatientContactNumber.setBounds(10, 284, 139, 14);
		add(lblPatientContactNumber);
		
		JLabel lblRefineResults = new JLabel("Refine Results");
		lblRefineResults.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRefineResults.setBounds(10, 257, 139, 14);
		add(lblRefineResults);
		
		
		
		
		
	}

	private TableModel listProcedures(){
		return new ProcedureTableModel(dataAccess.getProcedures(),3);
	}
}
