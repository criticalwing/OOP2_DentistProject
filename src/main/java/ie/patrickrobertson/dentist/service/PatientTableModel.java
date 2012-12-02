package ie.patrickrobertson.dentist.service;

import ie.patrickrobertson.dentist.Invoice;
import ie.patrickrobertson.dentist.Patient;

import java.text.DecimalFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class PatientTableModel extends AbstractTableModel {

	private List<Patient> patients;
	int columns;
	String type;

	public PatientTableModel(List<Patient> patients, int columns, String type) {
		this.patients = patients;
		this.columns = columns;
		this.type = type;
	}

	@Override
	public int getColumnCount() {
		return columns;
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
		case 4:
			if (type.equals("debtors")) {
				return "Total Debt";
			} else
				return "Paid Total";
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
		case 4:
			int amount = 0;
			for (Invoice i : patient.getP_Invoice()) {
				if (type.equals("debtors")) {
					if (!i.isInvoicePaid()) {
						amount += i.getInvoiceAmt();
					}
				} else {
					if (i.isInvoicePaid()) {
						amount += i.getInvoiceAmt();
					}
				}
			}
			Double x = ((double) amount) / 100;
			DecimalFormat decF = new DecimalFormat("#.##");
			decF.setPositivePrefix("€");
			decF.setMinimumFractionDigits(2);
			return decF.format(x);
		default:
			return "x";
		}
	}
}
