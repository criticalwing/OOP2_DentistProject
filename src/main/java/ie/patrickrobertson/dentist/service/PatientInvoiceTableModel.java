package ie.patrickrobertson.dentist.service;

import ie.patrickrobertson.dentist.History;
import ie.patrickrobertson.dentist.Invoice;
import ie.patrickrobertson.dentist.Patient;
import ie.patrickrobertson.dentist.Procedure;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class PatientInvoiceTableModel extends AbstractTableModel {

	private List<PatientInvoice> patientsList;
	int columns;

	public PatientInvoiceTableModel(List<Patient> patients, int columns, String type) {

		if(type.equals("debtors")){
		patientsList = genDebtorList(patients);
		}else{
			patientsList = genPaidList(patients);
		}
		this.columns = columns;
	}

	@Override
	public int getColumnCount() {
		return columns;
	}

	@Override
	public int getRowCount() {
		return patientsList.size();
	}

	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "Patient Name";
		case 1:
			return "Invoice";
		case 2:
			return "Amount";
		case 3:
			return "Invoice Date";
		case 4:
			return "Procedures";
		default:
			return "x";
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		PatientInvoice d = patientsList.get(rowIndex);
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		switch (columnIndex) {
		case 0:
			return d.getPatient().getPatientName();
		case 1:
			return d.getInvoice().getInvoice();
		case 2:
			Double x = ((double) d.getInvoice().getInvoiceAmt()) / 100;
			DecimalFormat decF = new DecimalFormat("#.##");
			decF.setPositivePrefix("€");
			decF.setMinimumFractionDigits(2);
			return decF.format(x);
		case 3:
			return df.format(d.getInvoice().getInvoiceDate());
		case 4:
			String output = "";
			for (Procedure p : d.getInvoice().getProcList()) {
				output = output.concat(p.getProcName());
				if (d.getInvoice().getProcList().indexOf(p) == d.getInvoice().getProcList()
						.size() - 1) {
				} else {
					output = output.concat(", ");
				}
			}
			return output;
		default:
			return "x";
		}

	}
	private ArrayList<PatientInvoice> genPaidList(List<Patient> patients) {

		ArrayList<PatientInvoice> paid = new ArrayList<PatientInvoice>();
		for (Patient p : patients) {
			for (Invoice i : p.getP_Invoice()) {
				if (i.isInvoicePaid()) {
					PatientInvoice x = new PatientInvoice(p, i);
					paid.add(x);
				}
			}
		}
		return paid;

	}
	

	private ArrayList<PatientInvoice> genDebtorList(List<Patient> patients) {

		ArrayList<PatientInvoice> debtors = new ArrayList<PatientInvoice>();
		for (Patient p : patients) {
			for (Invoice i : p.getP_Invoice()) {
				if (!i.isInvoicePaid()) {
					PatientInvoice x = new PatientInvoice(p, i);
					debtors.add(x);
				}
			}
		}
		return debtors;

	}

}
