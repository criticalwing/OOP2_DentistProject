package ie.patrickrobertson.dentist;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import ie.patrickrobertson.dentist.Patient;


public class GUITest {
	
	@Test
	public void testDayPanel(){
		
		Patient o = new Patient(1, "Jack Jones", "Cork, Ireland", "012 3456789");
		Patient p = new Patient(2, "Jack Jones", "Cork, Ireland", "012 3456789");
		Patient q = new Patient(3, "Jack Jones", "Cork, Ireland", "012 3456789");
		
		ArrayList<Patient> patientList = new ArrayList<Patient>();
		patientList.add(o);
		patientList.add(p);
		patientList.add(q);
		
		
		
	}
	
	

}
