package ie.patrickrobertson.dentist.service;

import ie.patrickrobertson.dentist.Patient;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class PatientTableModel extends AbstractTableModel {

	private List<Patient> patients;

	public PatientTableModel(List<Patient> patients) {
		this.patients = patients;
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		return patients.size();
	}

	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "ID";
		case 1:
			return "Name";
		case 2:
			return "Address";
		case 3:
			return "Contact";
		default:
			return "x";
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Patient patient = patients.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return patient.getPatient();
		case 1:
			return patient.getPatientName();
		case 2:
			return patient.getPatientAdd();
		case 3:
			return patient.getPatientPhone();
		default: return "x";
		}
	}

	
	
	
	
	
}
