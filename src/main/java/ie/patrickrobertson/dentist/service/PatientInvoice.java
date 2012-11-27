package ie.patrickrobertson.dentist.service;

import ie.patrickrobertson.dentist.Invoice;
import ie.patrickrobertson.dentist.Patient;

public class PatientInvoice {

	Patient patient;
	Invoice invoice;

	public PatientInvoice(Patient patient, Invoice invoice) {
		super();
		this.patient = patient;
		this.invoice = invoice;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	@Override
	public String toString() {
		return "Debtor [Patient=" + patient.getPatientName() + ", Invoice ID=" + invoice.getInvoice() + "]";
	}

	
}
