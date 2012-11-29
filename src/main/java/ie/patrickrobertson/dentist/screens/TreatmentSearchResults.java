package ie.patrickrobertson.dentist.screens;

import ie.patrickrobertson.dentist.Patient;
import ie.patrickrobertson.dentist.service.PatientTableModel;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.TableModel;
import javax.swing.JButton;

public class TreatmentSearchResults extends LayoutTemplate {

	private JTable patientListPanel;
	private TableModel tableModel;
	protected JComponent btnEdit;
	protected JComponent btnDelete;
	protected int patientID;
	private ArrayList<Patient> patients;

	
	public TreatmentSearchResults() {
		// TODO Auto-generated constructor stub
	}
	
	public TreatmentSearchResults(ArrayList<Patient> patients) {
		this.patients = patients;
		loadTableData();

		JPanel listOfPatients = new JPanel();
		listOfPatients.setBackground(Color.WHITE);
		listOfPatients.setBounds(10, 11, 620, 299);
		listOfPatients.setLayout(null);

		patientListPanel = new JTable(tableModel);
		patientListPanel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		patientListPanel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		patientListPanel.getColumnModel().getColumn(0).setPreferredWidth(25);
		patientListPanel.getColumnModel().getColumn(1).setPreferredWidth(150);
		patientListPanel.getColumnModel().getColumn(2).setPreferredWidth(310);
		patientListPanel.getColumnModel().getColumn(3).setPreferredWidth(120);
		patientListPanel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				patientID = (int) patientListPanel.getModel().getValueAt(
						patientListPanel.getSelectedRow(), 0);
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

		JScrollPane scroller = new JScrollPane(patientListPanel);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setBounds(0, 0, 620, 299);

		listOfPatients.add(scroller);
		add(listOfPatients);
		
		JButton btnView = new JButton("View");
		btnView.setBounds(10, 321, 89, 23);
		add(btnView);
		
		JButton btnNewsearch = new JButton("NewSearch");
		btnNewsearch.setBounds(109, 321, 89, 23);
		add(btnNewsearch);

	}


	private void loadTableData() {
		tableModel = new PatientTableModel(patients, 4);
	}

}
