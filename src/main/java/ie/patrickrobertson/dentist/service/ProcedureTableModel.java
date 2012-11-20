package ie.patrickrobertson.dentist.service;

import ie.patrickrobertson.dentist.Procedure;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ProcedureTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -871339462145140734L;
	private List<Procedure> procedures;
	int columns;

	public ProcedureTableModel(List<Procedure> procedures, int columns) {
		this.procedures = procedures;
		this.columns = columns;
	}

	@Override
	public int getColumnCount() {
		return columns;
	}

	@Override
	public int getRowCount() {
		return procedures.size();
	}

	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "ID";
		case 1:
			return "Procedure";
		case 2:
			return "Cost";
		default:
			return "x";
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Procedure procedure = procedures.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return procedure.getProc();
		case 1:
			return procedure.getProcName();
		case 2:
			return procedure.getProcCost();
		default: return "x";
		}
	}

}
