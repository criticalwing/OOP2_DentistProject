package ie.patrickrobertson.dentist;
//need to add static incremental number for patient number

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Patient {
	
	private int patient;
	private String patientName, patientAdd, patientPhone, notes;
	private ArrayList<Invoice> p_Invoice;
	private ArrayList<History> p_History;
		
	public Patient(){
		p_Invoice = new ArrayList<Invoice>();
		p_History = new ArrayList<History>();
	}
	
	public Patient(int patient, String patientName, String patientAdd,
			String patientPhone, String notes) {
		this.patient = patient;
		this.patientName = patientName;
		this.patientAdd = patientAdd;
		this.patientPhone = patientPhone;
		this.notes = notes;
		p_Invoice = new ArrayList<Invoice>();
		p_History = new ArrayList<History>();
	}
	public Patient(int patient, String patientName, String patientAdd,
			String patientPhone) {
		this.patient = patient;
		this.patientName = patientName;
		this.patientAdd = patientAdd;
		this.patientPhone = patientPhone;
		p_Invoice = new ArrayList<Invoice>();
		p_History = new ArrayList<History>();
	}

	public int getPatient() {
		return patient;
	}
	public void setPatient(int patient) {
		this.patient = patient;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getPatientAdd() {
		return patientAdd;
	}
	public void setPatientAdd(String patientAdd) {
		this.patientAdd = patientAdd;
	}
	public String getPatientPhone() {
		return patientPhone;
	}
	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
	}
	
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public ArrayList<Invoice> getP_Invoice() {
		return p_Invoice;
	}
	public void setP_Invoice(ArrayList<Invoice> p_Invoice) {
		this.p_Invoice = p_Invoice;
	}
	public boolean addPatientInvoice(Invoice i){
		p_Invoice.add(i);
		return true;
	}
	public boolean removePatientInvoice(int x){
		p_Invoice.remove(x);
		return true;
	}
	public boolean isEmptyPatientInvoice(){
		return p_Invoice.isEmpty();
	}
	public int sizePatientInvoice(){
		return p_Invoice.size();
	}
	
	public ArrayList<History> getP_History() {
		return p_History;
	}
	public void setP_History(ArrayList<History> p_History) {
		this.p_History = p_History;
	}
	public boolean addToPatientHistory(History h){
		p_History.add(h);
		return true;
	}
	public boolean removeFromPatientHistory(int x){
		p_History.remove(x);
		return true;
	}
	public boolean isEmptyPatientHistory(){
		return p_History.isEmpty();
	}
	public int sizePatientHistory(){
		return p_History.size();
	}

	//working methods
	
	public Invoice createNewPatientInvoice(double amount, ArrayList<Procedure> procList, Calendar date){
		
		int invoiceNo = sizePatientInvoice()+1;
		Invoice x = new Invoice(invoiceNo, date,
			false, procList);		
		
		return x;
		
	}
	
	
	@Override
	public String toString() {
		return  patientName + " " + patientAdd + " "+ patientPhone;
	}

}
