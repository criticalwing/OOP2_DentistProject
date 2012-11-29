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
import javax.swing.JLabel;
import java.awt.Font;

public class TreatmentSearchResults extends LayoutTemplate {

	private JTable patientListPanel;
	private TableModel tableModel;
	private int patientID;
	private ArrayList<Patient> patients;
	private JButton btnNewsearch;
	private JButton btnView;

	public TreatmentSearchResults() {
		// TODO Auto-generated constructor stub
	}

	public TreatmentSearchResults(ArrayList<Patient> patients) {
		this.patients = patients;

		btnNewsearch = new JButton("New Search");
		btnNewsearch.setBounds(109, 321, 112, 23);
		add(btnNewsearch);
		
		btnView = new JButton("View");
		btnView.setBounds(10, 321, 89, 23);
		add(btnView);
		btnView.setVisible(false);
		

		if (patients.isEmpty()) {
			JLabel lblNoPatientsHave = new JLabel(
					"No Patients have procedures between the dates provided.");
			lblNoPatientsHave.setFont(new Font("Lucida Grande", Font.BOLD, 14));
			lblNoPatientsHave.setBounds(10, 6, 556, 36);
			add(lblNoPatientsHave);
		} else {
			loadTableData();

			JPanel listOfPatients = new JPanel();
			listOfPatients.setBackground(Color.WHITE);
			listOfPatients.setBounds(10, 11, 620, 299);
			listOfPatients.setLayout(null);

			patientListPanel = new JTable(tableModel);
			patientListPanel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			patientListPanel
					.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			patientListPanel.getColumnModel().getColumn(0)
					.setPreferredWidth(25);
			patientListPanel.getColumnModel().getColumn(1)
					.setPreferredWidth(150);
			patientListPanel.getColumnModel().getColumn(2)
					.setPreferredWidth(310);
			patientListPanel.getColumnModel().getColumn(3)
					.setPreferredWidth(120);
			patientListPanel.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					patientID = (int) patientListPanel.getModel().getValueAt(
							patientListPanel.getSelectedRow(), 0);
					btnView.setVisible(true);
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
		

		}
	}
	
	public JButton getBtnNewsearch() {
		return btnNewsearch;
	}

	public void setBtnNewsearch(JButton btnNewsearch) {
		this.btnNewsearch = btnNewsearch;
	}


	public int getPatientID() {
		return patientID;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	public JButton getBtnView() {
		return btnView;
	}

	public void setBtnView(JButton btnView) {
		this.btnView = btnView;
	}

	private void loadTableData() {
		tableModel = new PatientTableModel(patients, 4);
	}
}
