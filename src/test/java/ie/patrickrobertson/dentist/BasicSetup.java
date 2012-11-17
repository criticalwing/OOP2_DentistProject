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
	
	@Test
	public void createInvoice(){
		
		
		Procedure x = new Procedure("Test Procedure", 150.00);
		Procedure y = new Procedure("Test Procedure2", 300.00);
		
		ArrayList<Procedure> arrayPro = new ArrayList<Procedure>();
		arrayPro.add(x);
		arrayPro.add(y);
		
		Date d = new Date("01/01/12");
				
		Invoice i = new Invoice(1, d, false, arrayPro);
		
		//check correct creation
		String a = arrayPro.toString();
		String b = i.getProcList().toString();
		
		assertEquals(a, b);
		
		//check values of Procedures are adding up
		assertEquals(450.00,i.getInvoiceAmt(), 1.0);
		
	}


}
