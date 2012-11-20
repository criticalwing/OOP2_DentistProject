package ie.patrickrobertson.dentist.service;

import ie.patrickrobertson.dentist.Patient;
import ie.patrickrobertson.dentist.Procedure;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class NonSerializedService implements DataAccess {

	ArrayList<Patient> patients;
	ArrayList<Procedure> procedures;
	ArrayList<String> batchFullReport;
	ArrayList<String> errorLog;
	
	public NonSerializedService(){
		patients = new ArrayList<Patient>();
		procedures = new ArrayList<Procedure>();
		batchFullReport= new ArrayList<String>();
		errorLog = new ArrayList<String>();
		getData();
	}
	
	private void getData(){
		processLines(convertFiletoStrings("src/main/resources/NSPatientList"),
				"~", "Patient");
		processLines(
				convertFiletoStrings("src/main/resources/NSProceduresList"),
				"~", "Procedure");
		
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
				if (validateProcedureStringArray(parts, (sourceParts.indexOf(x) + 1))) {
					procedures.add(new Procedure(Integer.valueOf(parts[0]),
							parts[1], Double.valueOf(parts[2])));
				} else{
					batchFullReport.add("*COULD NOT BE PROCESSED*, see Error Log");
				}
			}else if (type.equals("Patient")) {
					if (validatePatientStringArray(parts, (sourceParts.indexOf(x) + 1))) {
						Patient patient = new Patient(Integer.valueOf(parts[0]),
								parts[1], parts[2], parts[3]);
						System.out.print(patient.toString().concat("\n"));
						patients.add(patient);
					}
					else{
						batchFullReport.add("*COULD NOT BE PROCESSED*, see Error Log");
					}
			} else {
				batchFullReport.add("UNRECOGNISED TYPE, NO PROCESSING COULD BE COMPLETED");
			}
		}

	}


	public ArrayList<Patient> getPatients() {
		return patients;
	}

	public ArrayList<Procedure> getProcedures() {
		return procedures;
	}

	@Override
	public boolean savePatient() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveProcedure() {
		// TODO Auto-generated method stub
		return false;
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
			errorLog.add("Line " + position
					+ " does not contain a valid price");
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

	// String Report
	// String Outputs
	@Override
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
		for(Patient p : patients){
			tempIntArray.add(p.getPatient());
		}
		int i = Collections.max(tempIntArray);
		patient.setPatient(i+1);
		patients.add(patient);
	}

	@Override
	public void addProcedure(Procedure procedure) {
		ArrayList<Integer> tempIntArray = new ArrayList<Integer>();
		for(Procedure p : procedures){
			tempIntArray.add(p.getProc());
		}
		int i = Collections.max(tempIntArray);
		procedure.setProc(i+1);
		procedures.add(procedure);
	}
}
