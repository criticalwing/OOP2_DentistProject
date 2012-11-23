package ie.patrickrobertson.dentist.service;

import ie.patrickrobertson.dentist.History;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class HistoryTableModel extends AbstractTableModel {
	private List<History> history;
	int columns;

	public HistoryTableModel(List<History> history, int columns) {
		this.history = history;
		this.columns = columns;
	}

	@Override
	public int getColumnCount() {
		return columns;
	}

	@Override
	public int getRowCount() {
		return history.size();
	}

	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "ID";
		case 1:
			return "Condition Name";
		case 2:
			return "Medication";
		case 3:
			return "Date";
		default:
			return "x";
		}
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		History h = history.get(rowIndex);
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		switch (columnIndex) {
		case 0:
			return h.getHistID();
		case 1:
			return h.getConditionName();
		case 2:
			return h.getMedication();
		case 3:
			return df.format(h.getDateOccured());
		default: return "x";
		}
	}

	
	
	
	
	
}
