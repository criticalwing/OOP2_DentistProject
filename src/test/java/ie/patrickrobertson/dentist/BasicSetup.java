package ie.patrickrobertson.dentist;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import ie.patrickrobertson.dentist.Invoice;
import ie.patrickrobertson.dentist.Patient;
import ie.patrickrobertson.dentist.Procedure;

import org.junit.Test;


public class BasicSetup {
	
	@Test
	public void createPatient(){
		
		Patient p = new Patient(1, "Jack Jones", "Cork, Ireland", "012 3456789", "Some Notes");
		
		assertEquals("Jack Jones", p.getPatientName());
		assertEquals("Cork, Ireland", p.getPatientAdd());
		
		
	}
	
	@Test
	public void createProcedure(){
		
		Procedure x = new Procedure("Test Procedure", 150.00);
		
		assertEquals("Test Procedure", x.getProcName());		
		
	}
	

}
