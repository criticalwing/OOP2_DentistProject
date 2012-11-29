package ie.patrickrobertson.dentist;

import java.io.Serializable;



public class Procedure implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int proc;
	private String procName;
	//changed it to 'pennies' to sort calc issues
	private int procCost;
	
	public Procedure(String procName, int procCost) {
		this.procName = procName;
		this.procCost = procCost;
	}
	
	public Procedure(int proc,String procName, int procCost) {
		this.proc = proc;
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

	public int getProcCost() {
		return procCost;
	}

	public void setProcCost(int procCost) {
		this.procCost = procCost;
	}

	@Override
	public String toString() {
		return "Procedure [proc=" + proc + ", procName=" + procName
				+ ", procCost=" + procCost + "]";
	}
	
	
	
	
	
}
