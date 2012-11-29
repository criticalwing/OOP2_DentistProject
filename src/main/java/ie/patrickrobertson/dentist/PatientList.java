package ie.patrickrobertson.dentist;
import java.util.ArrayList;


public class PatientList {
	
	ArrayList<Patient> patientList;
	
	public PatientList(){

		}
	
	public boolean addPatient(Patient p){
		patientList.add(p);
		return true;
	}
	
	public boolean removePatient(int x){
		patientList.remove(x);
		return true;
	}
	
	public boolean isEmpty(){
		return patientList.isEmpty();
	}
	
	public int patientListSize(){
		return patientList.size();
	}

	@Override
	public String toString() {
		return patientList.toString();
	}

	
}
