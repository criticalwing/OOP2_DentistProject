package ie.patrickrobertson.dentist.service;

import ie.patrickrobertson.dentist.Patient;
import ie.patrickrobertson.dentist.Procedure;

import java.util.ArrayList;

public interface DataAccess {

	ArrayList<Patient> getPatients();

	ArrayList<Procedure> getProcedures();

	boolean savePatient();

	boolean saveProcedure();
	
	void addPatient(Patient patient);
	
	void addProcedure(Procedure procedure);

	String fullReport();

}
