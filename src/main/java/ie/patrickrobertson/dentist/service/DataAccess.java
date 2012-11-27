package ie.patrickrobertson.dentist.service;

import ie.patrickrobertson.dentist.History;
import ie.patrickrobertson.dentist.Invoice;
import ie.patrickrobertson.dentist.Patient;
import ie.patrickrobertson.dentist.Procedure;

import java.util.ArrayList;

public interface DataAccess {

	ArrayList<Patient> getPatients();

	ArrayList<Procedure> getProcedures();
	
	ArrayList<Invoice> getInvoices();
	
	void addPatient(Patient patient);
	
	void addProcedure(Procedure procedure);

	ArrayList<Procedure> findProcedureByName(String name);
	
	Procedure findProcedureByID(int ID);
	
	ArrayList<Patient> findPatientByName(String name);
	
	Patient findPatientByID(int ID);
	
	void deletePatient(Patient p);
	
	void updatePatient(int ID, Patient updatePatient);
	
	void updatePatientHistory(int patientID, History h);
	
	void addPatientHistory(int patientID, History h);
	
	void deletePatientHistory(int patientID, int historyID);
	
	void addInvoicetoPatient(int patientID, Invoice i);
	
	History findPatientHistory(int patientID, int historyID);
	
	ArrayList<Patient> findPatientInvoice(String type);
	
	void markInvoicePaid(int patientID, int invoiceID);


}
