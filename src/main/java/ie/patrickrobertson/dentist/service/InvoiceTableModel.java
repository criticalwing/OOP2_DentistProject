package ie.patrickrobertson.dentist.service;

import ie.patrickrobertson.dentist.Invoice;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class InvoiceTableModel extends AbstractTableModel {

	private List<Invoice> invoices;
	int columns;

	public InvoiceTableModel(List<Invoice> invoices, int columns) {
		this.invoices = invoices;
		this.columns = columns;
	}

	@Override
	public int getColumnCount() {
		return columns;
	}

	@Override
	public int getRowCount() {
		return invoices.size();
	}

	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "ID";
		case 1:
			return "Date";
		case 2:
			return "Invoice Paid";
		case 3:
			return "Total";
		default:
			return "x";
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		SimpleDateFormat sDF = new SimpleDateFormat("dd/MM/yyyy");
		Invoice invoice = invoices.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return invoice.getInvoice();
		case 1:
			return sDF.format(invoice.getInvoiceDate().getTime());
		case 2:
			return invoice.isInvoicePaid();
		case 3:
			Double x = ((double) invoice.getInvoiceAmt())/100;
			DecimalFormat df = new DecimalFormat("#.##");
			df.setPositivePrefix("€");
			df.setMinimumFractionDigits(2);
			return df.format(x);
		default:
			return "x";
		}
	}
}
