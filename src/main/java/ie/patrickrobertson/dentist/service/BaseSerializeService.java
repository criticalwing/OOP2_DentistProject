package ie.patrickrobertson.dentist.service;

import ie.patrickrobertson.dentist.History;
import ie.patrickrobertson.dentist.Invoice;
import ie.patrickrobertson.dentist.Patient;
import ie.patrickrobertson.dentist.Procedure;

import java.util.ArrayList;
import java.util.Collections;

public class BaseSerializeService implements DataAccess {

	
	ArrayList<Patient> patients;
	ArrayList<Procedure> procedures;
	ArrayList<Invoice> invoices;

	public BaseSerializeService() {
		patients = new ArrayList<Patient>();
		procedures = new ArrayList<Procedure>();
	}
	public ArrayList<Patient> getPatients() {
		return patients;
	}

	public ArrayList<Procedure> getProcedures() {
		return procedures;
	}

	@Override
	public ArrayList<Patient> findPatientInvoice(String type) {

		if (type.equals("debtors")) {
			ArrayList<Patient> debtors = new ArrayList<Patient>();
			for (Patient p : patients) {
				for (Invoice i : p.getP_Invoice()) {
					if (!i.isInvoicePaid()) {
						if (!debtors.contains(p)) {
							System.out.print(p.getPatientName().concat("debt\n"));
							debtors.add(p);
						}
					}
				}
			}
			return debtors;
		} else {
			ArrayList<Patient> paid = new ArrayList<Patient>();
			for (Patient p : patients) {
				for (Invoice i : p.getP_Invoice()) {
					if (i.isInvoicePaid()) {
						if (!paid.contains(p)) {
							System.out.print(p.getPatientName().concat("paid\n"));
							paid.add(p);
						}
					}
				}
			}
			return paid;
		}
	}

	@Override
	public void addPatient(Patient patient) {
		ArrayList<Integer> tempIntArray = new ArrayList<Integer>();
		for (Patient p : patients) {
			tempIntArray.add(p.getPatient());
		}
		int i = Collections.max(tempIntArray);
		patient.setPatient(i + 1);
		patients.add(patient);
	}

	@Override
	public void addProcedure(Procedure procedure) {
		ArrayList<Integer> tempIntArray = new ArrayList<Integer>();
		for (Procedure p : procedures) {
			tempIntArray.add(p.getProc());
		}
		int i = Collections.max(tempIntArray);
		procedure.setProc(i + 1);
		procedures.add(procedure);
	}

	@Override
	public ArrayList<Procedure> findProcedureByName(String name) {
		name = name.toLowerCase();
		ArrayList<Procedure> pList = new ArrayList<Procedure>();
		for (Procedure p : procedures) {
			if (p.getProcName().toLowerCase().contains(name)) {
				pList.add(p);
			}
		}
		return pList;
	}

	@Override
	public Procedure findProcedureByID(int ID) {
		for (Procedure p : procedures) {
			if (ID == p.getProc()) {
				return p;
			}
		}
		return null;
	}

	@Override
	public ArrayList<Patient> findPatientByName(String name) {
		name = name.toLowerCase();
		ArrayList<Patient> pList = new ArrayList<Patient>();
		for (Patient p : patients) {
			if (p.getPatientName().toLowerCase().contains(name)) {
				pList.add(p);
			}
		}
		return pList;
	}

	@Override
	public Patient findPatientByID(int ID) {
		for (Patient p : patients) {
			if (ID == p.getPatient()) {
				return p;
			}
		}
		return null;
	}

	@Override
	public ArrayList<Invoice> getInvoices() {
		for (Patient p : patients) {
			for (Invoice i : p.getP_Invoice()) {
				invoices.add(i);
			}
		}
		return invoices;
	}

	@Override
	public void deletePatient(Patient p) {
		patients.remove(p);
	}

	@Override
	public void updatePatient(int ID, Patient updatePatient) {
		findPatientByID(ID).setPatientName(updatePatient.getPatientName());
		findPatientByID(ID).setPatientAdd(updatePatient.getPatientAdd());
		findPatientByID(ID).setPatientPhone(updatePatient.getPatientPhone());
	}

	@Override
	public void updatePatientHistory(int PatientID, History h) {
		int historyIndex = 0;
		for(History i:findPatientByID(PatientID).getP_History()){
			if(i.getHistID()==h.getHistID()){
				historyIndex = findPatientByID(PatientID).getP_History().indexOf(i);
			}
		}
		findPatientByID(PatientID).getP_History().get(historyIndex).setConditionName(h.getConditionName());
		findPatientByID(PatientID).getP_History().get(historyIndex).setMedication((h.getMedication()));
		findPatientByID(PatientID).getP_History().get(historyIndex).setDateOccured((h.getDateOccured()));
	}

	@Override
	public void addPatientHistory(int patientID, History h) {
		findPatientByID(patientID).getP_History().add(h);
		
	}

	@Override
	public void deletePatientHistory(int patientID, int historyID) {
		int historyIndex = 0;
		for(History i:findPatientByID(patientID).getP_History()){
			if(i.getHistID()==historyID){
				historyIndex = findPatientByID(patientID).getP_History().indexOf(i);
			}
		}
		findPatientByID(patientID).getP_History().remove(historyIndex);
		
	}

	@Override
	public History findPatientHistory(int patientID, int historyID) {
		int historyIndex = 0;
		for(History i:findPatientByID(patientID).getP_History()){
			if(i.getHistID()==historyID){
				historyIndex = findPatientByID(patientID).getP_History().indexOf(i);
			}
		}
		return findPatientByID(patientID).getP_History().get(historyIndex);
	}

	@Override
	public void addInvoicetoPatient(int patientID, Invoice i) {
		findPatientByID(patientID).addPatientInvoice(i);
	}

	@Override
	public void markInvoicePaid(int patientID, int invoiceID) {
		for(Invoice i :findPatientByID(patientID).getP_Invoice()){
			if(i.getInvoice()==invoiceID){
				i.setInvoicePaid(true);
			}
		}
		
	}

	@Override
	public void deleteInvoice(int patientID, int invoiceID) {
		int index = 0;
		for(Invoice x : findPatientByID(patientID).getP_Invoice()){
			if(x.getInvoice()==invoiceID){
				index = findPatientByID(patientID).getP_Invoice().indexOf(x);
			}
		}
		findPatientByID(patientID).getP_Invoice().remove(index);
		
	}

}
