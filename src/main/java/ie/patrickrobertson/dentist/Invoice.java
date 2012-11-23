package ie.patrickrobertson.dentist;
//need to add static incremental number for invoice number

	import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
	/*import java.text.DateFormat;
	import java.text.SimpleDateFormat;*/
	

public class Invoice {
	
	private int invoice;
	private double invoiceAmt;
	private Date invoiceDate;
	private boolean invoicePaid;
	ArrayList<Procedure> procList;
	
	public Invoice(int invoice, Date date,
			boolean invoicePaid, ArrayList<Procedure> procList) {
		this.invoice = invoice;
		this.invoiceDate = date;
		this.invoicePaid = invoicePaid;
		this.procList = procList;
		invoiceAmt = getProcedureTotal(procList);		
		
	}
	
	private double getProcedureTotal(ArrayList<Procedure> procList) {
		
		double total = 0;
		
		for(Procedure p : procList){
			
			total = total+p.getProcCost();
			
		}
		
		return total;
	}
	
	public int getInvoice() {
		return invoice;
	}
	
	public void setInvoice(int invoice) {
		this.invoice = invoice;
	}
	public double getInvoiceAmt() {
		return invoiceAmt;
	}
	public void setInvoiceAmt(double invoiceAmt) {
		this.invoiceAmt = invoiceAmt;
	}
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public boolean isInvoicePaid() {
		return invoicePaid;
	}
	public void setInvoicePaid(boolean invoicePaid) {
		this.invoicePaid = invoicePaid;
	}
	public ArrayList<Procedure> getProcList() {
		return procList;
	}
	public void setProcList(ArrayList<Procedure> procList) {
		this.procList = procList;
	}
	
	@Override
	public String toString() {
		return "Invoice [invoice=" + invoice + ", invoiceAmt=" + invoiceAmt
				+ ", invoiceDate=" + invoiceDate + ", invoicePaid="
				+ invoicePaid + "]";
	}
	
	
	
	
	

}
