package ie.patrickrobertson.dentist;
//need to add static incremental number for History number	


	import java.util.Date;
	/*import java.text.DateFormat;
	import java.text.SimpleDateFormat;*/

public class History {
	
	private int histID;
	private String conditionName;
	private String medication;
	private Date dateOccured;
	
	public History(int histID, String conditionName, String medication,
			Date dateOccured) {
		this.histID = histID;
		this.conditionName = conditionName;
		this.medication = medication;
		this.dateOccured = dateOccured;
	}

	public int getHistID() {
		return histID;
	}

	public void setHistID(int histID) {
		this.histID = histID;
	}

	public String getConditionName() {
		return conditionName;
	}

	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}

	public String getMedication() {
		return medication;
	}

	public void setMedication(String medication) {
		this.medication = medication;
	}

	public Date getDateOccured() {
		return dateOccured;
	}

	public void setDateOccured(Date dateOccured) {
		this.dateOccured = dateOccured;
	}

	@Override
	public String toString() {
		return "History [histID=" + histID + ", conditionName=" + conditionName
				+ ", medication=" + medication + ", dateOccured=" + dateOccured
				+ "]";
	}
	

	

}
