package ie.patrickrobertson.dentist;
//need to add static incremental number for procedure number


public class Procedure {

	private int proc;
	private String procName;
	private double procCost;
	
	public Procedure(String procName, double procCost) {
		this.procName = procName;
		this.procCost = procCost;
	}

	public int getProc() {
		return proc;
	}

	public void setProc(int proc) {
		this.proc = proc;
	}

	public String getProcName() {
		return procName;
	}

	public void setProcName(String procName) {
		this.procName = procName;
	}

	public double getProcCost() {
		return procCost;
	}

	public void setProcCost(double procCost) {
		this.procCost = procCost;
	}

	@Override
	public String toString() {
		return "Procedure [proc=" + proc + ", procName=" + procName
				+ ", procCost=" + procCost + "]";
	}
	
	
	
	
	
}
