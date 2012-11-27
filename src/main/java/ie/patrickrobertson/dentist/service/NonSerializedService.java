package ie.patrickrobertson.dentist.service;

import ie.patrickrobertson.dentist.History;
import ie.patrickrobertson.dentist.Invoice;
import ie.patrickrobertson.dentist.Patient;
import ie.patrickrobertson.dentist.Procedure;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class NonSerializedService implements DataAccess {

	ArrayList<Patient> patients;
	ArrayList<Procedure> procedures;
	ArrayList<Invoice> invoices;
	ArrayList<String> batchFullReport;
	ArrayList<String> errorLog;

	public NonSerializedService() {
		patients = new ArrayList<Patient>();
		procedures = new ArrayList<Procedure>();
		batchFullReport = new ArrayList<String>();
		invoices = new ArrayList<Invoice>();
		errorLog = new ArrayList<String>();
		getData();
	}

	private void getData() {
		processLines(convertFiletoStrings("src/main/resources/NSPatientList"),
				"~", "Patient");
		processLines(
				convertFiletoStrings("src/main/resources/NSProceduresList"),
				"~", "Procedure");
		processLines(
				convertFiletoStrings("src/main/resources/NSPatientHistory"),
				"~", "History");
		System.out.print(errorLog.toString());
	}

	public NonSerializedService(ArrayList<Patient> patients,
			ArrayList<Procedure> procedures, ArrayList<String> batchFullReport,
			ArrayList<String> errorLog) {
		super();
		this.patients = patients;
		this.procedures = procedures;
		this.batchFullReport = batchFullReport;
		this.errorLog = errorLog;
	}

	// Processing Methods
	public ArrayList<String> convertFiletoStrings(String fileLocation) {
		ArrayList<String> lines = new ArrayList<String>();

		try {

			BufferedReader reader = new BufferedReader(new FileReader(
					fileLocation));
			String line;
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
			reader.close();
		} catch (FileNotFoundException fNFE) {
			System.out.print(fNFE.getMessage());
		} catch (IOException iOE) {
			System.out.print(iOE.getMessage());
		}

		return lines;
	}

	public void processLines(ArrayList<String> sourceParts, String delineator,
			String type) {

		for (String x : sourceParts) {

			String[] parts = x.split(delineator);

			if (type.equals("Procedure")) {
				// checks if each of the parts of the array match up to the
				// requirements in order to ensure no exceptions
				// second part passes the line that is being processes so that
				// any errors can be logged
				if (validateProcedureStringArray(parts,
						(sourceParts.indexOf(x) + 1))) {
					procedures.add(new Procedure(Integer.valueOf(parts[0]),
							parts[1], Integer.valueOf(parts[2])));
				} else {
					batchFullReport
							.add("*COULD NOT BE PROCESSED*, see Error Log");
				}
			} else if (type.equals("Patient")) {
				if (validatePatientStringArray(parts,
						(sourceParts.indexOf(x) + 1))) {
					Patient patient = new Patient(Integer.valueOf(parts[0]),
							parts[1], parts[2], parts[3]);
					System.out.print(patient.toString().concat("\n"));
					patients.add(patient);
				} else {
					batchFullReport
							.add("*COULD NOT BE PROCESSED*, see Error Log");
				}
			} else if (type.equals("History")) {
				if (validatePatientHistoryStringArray(parts,
						(sourceParts.indexOf(x) + 1))) {
					History h = new History(Integer.parseInt(parts[1]),
							parts[2], parts[3], createDate(parts[4]));
					findPatientByID(Integer.parseInt(parts[0])).getP_History()
							.add(h);
				} else {
					batchFullReport
							.add("*COULD NOT BE PROCESSED*, see Error Log");
				}
			} else {
				batchFullReport
						.add("UNRECOGNISED TYPE, NO PROCESSING COULD BE COMPLETED");
			}
		}

	}

	private Date createDate(String string) {
		int[] parts = convertDateString(string);
		Calendar cal = Calendar.getInstance();
		cal.clear();

		cal.set(Calendar.YEAR, parts[2]);
		cal.set(Calendar.MONTH, parts[1]);
		cal.set(Calendar.DATE, parts[0]);

		return cal.getTime();
	}

	private boolean validatePatientHistoryStringArray(String[] parts,
			int position) {

		// check if array holds five elements for
		// processing
		if (parts.length != 5) {
			errorLog.add("Line " + position
					+ " does not contain the correct amount of elements");
			return false;
		}
		// check that the first and second characters are ints
		if (!validateInt(parts[0]) || !validateInt(parts[1])) {
			errorLog.add("Line " + position
					+ " does not contain a reference number");
			return false;
		}
		// check that the final part is a date
		if (validateDate(parts[4])) {
			errorLog.add("Line " + position + " does not contain a valid date");
			return false;
		}
		return true;
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

	public boolean validatePatientStringArray(String[] parts, int position) {

		// check if array holds either six, seven or three elements for
		// processing
		if (parts.length > 5 || parts.length < 4) {
			errorLog.add("Line " + position
					+ " does not contain the correct amount of elements");
			return false;
		}
		// check that the first character is an int
		if (!validateInt(parts[0])) {
			errorLog.add("Line " + position
					+ " does not contain a reference number");
			return false;
		}
		return true;
	}

	public boolean validateProcedureStringArray(String[] parts, int position) {

		// check if array holds either six, seven or three elements for
		// processing
		if (parts.length != 3) {
			errorLog.add("Line " + position
					+ " does not contain the correct amount of elements");
			return false;
		}
		// check that the first character is an int
		if (!validateInt(parts[0])) {
			errorLog.add("Line " + position
					+ " does not contain a reference number");
			return false;
		}
		if (!validateDouble(parts[2])) {
			errorLog.add("Line " + position + " does not contain a valid price");
			return false;
		}
		return true;
	}

	private boolean validateDouble(String input) {
		try {
			Double.parseDouble(input);
		} catch (NumberFormatException nFE) {
			return false;
		}
		return true;
	}

	private boolean validateInt(String input) {
		try {
			Integer.parseInt(input);
		} catch (NumberFormatException nFE) {
			return false;
		}
		return true;
	}

	private boolean validateDate(String input) {
		// takes the string input [1]and checks to see that it can be converted
		// to a date
		String[] parts = input.split("/");

		if (parts.length != 3) {
			return false;
		}
		for (String x : parts) {
			if (!validateInt(x)) {
				return false;
			}
		}

		int[] intParts = new int[parts.length];
		for (int x = 0; x < intParts.length; x++) {
			intParts[x] = Integer.parseInt(parts[x]);
		}
		// ensure date is in the years between 2000 and 2050
		if (intParts[2] < 2000 || intParts[2] > 2050) {
			return false;
		}
		// ensure the month is between 1 and 12
		if (intParts[1] > 12 || intParts[1] < 1) {
			return false;
		}

		// check that there are the correct amount of days for the given month
		if (intParts[0] > 1) {
			return false;
		}
		if (intParts[1] == 9 || intParts[1] == 4 || intParts[1] == 6
				|| intParts[1] == 11) {
			if (intParts[0] > 30) {
				return false;
			}
		} else if (intParts[1] == 2) {
			if ((intParts[2] % 400 == 0)
					|| ((intParts[2] % 4 == 0) && (intParts[2] % 100 != 0))) {
				if (intParts[0] > 29) {
					return false;
				}
			} else {
				if (intParts[0] > 28) {
					return false;
				}
			}
		}
		return true;
	}

	private int[] convertDateString(String input) {
		String[] parts = input.split("/");
		int[] intParts = new int[parts.length];
		for (int x = 0; x < intParts.length; x++) {
			intParts[x] = Integer.valueOf(parts[x]);
		}
		return intParts;

	}

	// String Report
	// String Outputs
	public String fullReport() {
		String output = "------------ FULL REPORT -------------\n";
		int x = 1;
		for (String line : batchFullReport) {
			output = output + x + ". " + line + "\n";
			x++;
		}

		output = output + "--------------------------------------\n";
		return output;
	}

	public String report() {
		int booksAdded = 0;
		int booksUpdated = 0;
		int booksAvail = 0;
		int booksUnavail = 0;
		int errors = 0;

		for (String line : batchFullReport) {
			if (line.contains(" added")) {
				booksAdded++;
			}
			if (line.contains(" updated")) {
				booksUpdated++;
			}
			if (line.contains(" available")) {
				booksAvail++;
			}
			if (line.contains(" unavailable")) {
				booksUnavail++;
			}
			if (line.contains(" Error")) {
				errors++;
			}
		}

		String output = "----------- REPORT -----------------\n"
				+ batchFullReport.size() + " total record"
				+ sReturn(batchFullReport.size()) + " processed\n" + booksAdded
				+ " book" + sReturn(booksAdded) + " added\n" + booksUpdated
				+ " book" + sReturn(booksUpdated) + " updated\n" + booksAvail
				+ " book" + sReturn(booksAvail) + " made available\n"
				+ booksUnavail + " book" + sReturn(booksUnavail)
				+ " made unavailable\n" + errors + " error" + sReturn(errors)
				+ " found\n" + "------------------------------------\n\n";
		return output;
	}

	public String sReturn(int x) {
		if (x > 1 || x == 0) {
			return "s";
		} else {
			return "";
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

}
