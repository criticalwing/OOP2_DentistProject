package ie.patrickrobertson.dentist.service;

import ie.patrickrobertson.dentist.Patient;
import ie.patrickrobertson.dentist.Procedure;

import java.util.ArrayList;

public interface DataAccess {

	ArrayList<Patient> retrievePatients();

	ArrayList<Procedure> retrieveProcedures();

	boolean savePatient();

	boolean saveProcedure();

	String fullReport();

}
