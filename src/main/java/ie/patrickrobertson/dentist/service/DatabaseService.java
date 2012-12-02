package ie.patrickrobertson.dentist.service;

import ie.patrickrobertson.dentist.History;
import ie.patrickrobertson.dentist.Invoice;
import ie.patrickrobertson.dentist.Patient;
import ie.patrickrobertson.dentist.Procedure;
import java.sql.*;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

import ie.patrickrobertson.dentist.service.DataAccess;

public class DatabaseService implements DataAccess {

	private Connection DBconnect;

	public DatabaseService() {
		DBConnect();

	}

	private void DBConnect() {
		try {

			Class.forName("org.gjt.mm.mysql.Driver"); // Load the driver

			DBconnect = DriverManager.getConnection(
					"jdbc:mysql://localhost/dentistsurgery", "root", ""); // Connect

		}

		catch (Exception err) {
		}

	}

	@Override
	public ArrayList<Patient> getPatients() {
		ArrayList<Patient> patients = new ArrayList<Patient>();

		String sql = "SELECT * FROM patient";
		try {
			ResultSet rs = DBconnect.createStatement().executeQuery(sql);
			while (rs.next()) {
				Patient p = new Patient(rs.getInt("patient"),
						rs.getString("patientName"),
						rs.getString("patientAdd"),
						rs.getString("patientPhone"), rs.getString("notes"));

				// fetch History and add it to the Patient
				ArrayList<History> history = new ArrayList<History>();
				String historySql = "SELECT * FROM history WHERE patientID = '"
						.concat(String.valueOf(rs.getInt("patient"))).concat(
								"'");
				ResultSet historyRs = DBconnect.createStatement().executeQuery(
						historySql);
				while (historyRs.next()) {
					History h = new History(historyRs.getInt("histID"),
							historyRs.getString("conditionName"),
							historyRs.getString("medication"),
							historyRs.getDate("dateOccured"));
					history.add(h);
				}
				p.setP_History(history);

				// fetch Invoices and add them to the Patient
				ArrayList<Invoice> invoices = new ArrayList<Invoice>();
				String invoiceSql = "SELECT * FROM invoice WHERE patientID = '"
						.concat(String.valueOf(rs.getInt("patient"))).concat(
								"'");
				ResultSet invoiceRs = DBconnect.createStatement().executeQuery(
						invoiceSql);
				while (invoiceRs.next()) {
					Invoice i = new Invoice(invoiceRs.getInt("invoice"),
							invoiceRs.getInt("invoiceAmt"),
							invoiceRs.getDate("invoiceDate"),
							invoiceRs.getBoolean("invoicePaid"));
					// fetch procedures to add to Invoice
					ArrayList<Procedure> procs = new ArrayList<Procedure>();
					String procSql = "SELECT * FROM `procedure`, invoiceprocedures, patient WHERE procedure.proc = invoiceprocedures.procedureID AND invoiceprocedures.invoiceID="
							+ String.valueOf(invoiceRs.getInt("invoice"))
							+ " AND invoiceprocedures.patientID = "
							+ String.valueOf(rs.getInt("patient"));
					ResultSet procRs = DBconnect.createStatement()
							.executeQuery(procSql);
					while (procRs.next()) {
						Procedure proc = new Procedure(procRs.getInt("proc"),
								procRs.getString("procName"),
								procRs.getInt("procCost"));
						procs.add(proc);
					}
					// add procedures to Invoice
					i.setProcList(procs);
					// add invoice to arraylist
					invoices.add(i);
				}
				// add invoice array to patient
				p.setP_Invoice(invoices);
				// add patient to patient list
				patients.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return patients;
	}

	@Override
	public ArrayList<Procedure> getProcedures() {
		ArrayList<Procedure> procs = new ArrayList<Procedure>();
		String sql = "SELECT * FROM `procedure`";
		try {
			ResultSet procRs = DBconnect.createStatement().executeQuery(sql);
			while (procRs.next()) {
				Procedure proc = new Procedure(procRs.getInt("proc"),
						procRs.getString("procName"), procRs.getInt("procCost"));
				procs.add(proc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return procs;
	}

	@Override
	public ArrayList<Invoice> getInvoices() {
		ArrayList<Invoice> invoices = new ArrayList<Invoice>();
		String invoiceSql = "SELECT * FROM invoice";
		try {
			ResultSet invoiceRs = DBconnect.createStatement().executeQuery(
					invoiceSql);
			while (invoiceRs.next()) {
				Invoice i = new Invoice(invoiceRs.getInt("invoiceID"),
						invoiceRs.getInt("invoiceAmt"),
						invoiceRs.getDate("invoiceDate"),
						invoiceRs.getBoolean("invoicePaid"));
				invoices.add(i);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return invoices;
	}

	@Override
	public void addPatient(Patient patient) {
		String patientInsert = "INSERT INTO `patient`"
				+ "(`patient`, `patientName`, `patientAdd`, `patientPhone`, `notes`) "
				+ "VALUES ( '"
				+ String.valueOf(patient.getPatient())
				+ "', '"
				+ patient.getPatientName()
				+ "', '"
				+ patient.getPatientAdd()
				+ "', '"
				+ patient.getPatientPhone()
				+ "', '"
				+ patient.getNotes()
				+ "')";
		try {
			DBconnect.createStatement().execute(patientInsert);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addProcedure(Procedure procedure) {
		String procedureInsert = "INSERT INTO `procedure`"
				+ "(`proc`, `procName`, `procCost`) " + "VALUES ('"
				+ String.valueOf(procedure.getProc()) + "', '"
				+ procedure.getProcName() + "', '"
				+ String.valueOf(procedure.getProcCost()) + "')";
		try {
			DBconnect.createStatement().execute(procedureInsert);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<Procedure> findProcedureByName(String name) {

		ArrayList<Procedure> procs = new ArrayList<Procedure>();
		String sql = "SELECT * FROM procedure WHERE procname is LIKE '%"
				.concat(name).concat("%'");
		try {
			ResultSet procRs = DBconnect.createStatement().executeQuery(sql);
			while (procRs.next()) {
				Procedure proc = new Procedure(procRs.getInt("proc"),
						procRs.getString("procName"), procRs.getInt("procCost"));
				procs.add(proc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return procs;
	}

	@Override
	public Procedure findProcedureByID(int ID) {
		ArrayList<Procedure> procs = new ArrayList<Procedure>();
		String sql = "SELECT * FROM `procedure` WHERE proc ='".concat(
				String.valueOf(ID)).concat("'");
		try {
			ResultSet procRs = DBconnect.createStatement().executeQuery(sql);
			while (procRs.next()) {
				Procedure proc = new Procedure(procRs.getInt("proc"),
						procRs.getString("procName"), procRs.getInt("procCost"));
				procs.add(proc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (procs.isEmpty()) {
			return null;
		} else {
			return procs.get(0);
		}
	}

	@Override
	public ArrayList<Patient> findPatientByName(String name) {

		ArrayList<Patient> patients = new ArrayList<Patient>();
		String sql = "SELECT * FROM patient WHERE patientname LIKE '%".concat(
				name).concat("%'");
		try {
			ResultSet rs = DBconnect.createStatement().executeQuery(sql);
			while (rs.next()) {
				Patient p = new Patient(rs.getInt("patient"),
						rs.getString("patientName"),
						rs.getString("patientAdd"),
						rs.getString("patientPhone"), rs.getString("notes"));

				// fetch History and add it to the Patient
				ArrayList<History> history = new ArrayList<History>();
				String historySql = "SELECT * FROM history WHERE patientID = '"
						.concat(String.valueOf(rs.getInt("patient"))).concat(
								"'");
				ResultSet historyRs = DBconnect.createStatement().executeQuery(
						historySql);
				while (historyRs.next()) {
					History h = new History(historyRs.getInt("histID"),
							historyRs.getString("conditionName"),
							historyRs.getString("medication"),
							historyRs.getDate("dateOccured"));
					history.add(h);
				}
				p.setP_History(history);

				// fetch Invoices and add them to the Patient
				ArrayList<Invoice> invoices = new ArrayList<Invoice>();
				String invoiceSql = "SELECT * FROM invoice WHERE patientID = '"
						.concat(String.valueOf(rs.getInt("patient"))).concat(
								"'");
				ResultSet invoiceRs = DBconnect.createStatement().executeQuery(
						invoiceSql);
				while (invoiceRs.next()) {
					Invoice i = new Invoice(invoiceRs.getInt("invoice"),
							invoiceRs.getInt("invoiceAmt"),
							invoiceRs.getDate("invoiceDate"),
							invoiceRs.getBoolean("invoicePaid"));
					// fetch procedures to add to Invoice
					ArrayList<Procedure> procs = new ArrayList<Procedure>();
					String procSql = "SELECT * FROM `procedure`, invoiceprocedures WHERE procedure.proc = invoiceprocedures.procedureID AND invoiceprocedures.invoiceID="
							+ String.valueOf(invoiceRs.getInt("invoice"));
					ResultSet procRs = DBconnect.createStatement()
							.executeQuery(procSql);
					while (procRs.next()) {
						Procedure proc = new Procedure(procRs.getInt("proc"),
								procRs.getString("procName"),
								procRs.getInt("procCost"));
						procs.add(proc);
					}
					// add procedures to Invoice
					i.setProcList(procs);
					// add invoice to arraylist
					invoices.add(i);
				}
				// add invoice array to patient
				p.setP_Invoice(invoices);
				// add patient to patient list
				patients.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return patients;
	}

	@Override
	public Patient findPatientByID(int ID) {

		ArrayList<Patient> patients = new ArrayList<Patient>();
		String sql = "SELECT * FROM patient WHERE patient ='".concat(
				String.valueOf(ID)).concat("'");
		try {
			ResultSet rs = DBconnect.createStatement().executeQuery(sql);
			while (rs.next()) {
				Patient p = new Patient(rs.getInt("patient"),
						rs.getString("patientName"),
						rs.getString("patientAdd"),
						rs.getString("patientPhone"), rs.getString("notes"));

				// fetch History and add it to the Patient
				ArrayList<History> history = new ArrayList<History>();
				String historySql = "SELECT * FROM history WHERE patientID = '"
						.concat(String.valueOf(rs.getInt("patient"))).concat(
								"'");
				ResultSet historyRs = DBconnect.createStatement().executeQuery(
						historySql);
				while (historyRs.next()) {
					History h = new History(historyRs.getInt("histID"),
							historyRs.getString("conditionName"),
							historyRs.getString("medication"),
							historyRs.getDate("dateOccured"));
					history.add(h);
				}
				p.setP_History(history);

				// fetch Invoices and add them to the Patient
				ArrayList<Invoice> invoices = new ArrayList<Invoice>();
				String invoiceSql = "SELECT * FROM invoice WHERE patientID = '"
						.concat(String.valueOf(rs.getInt("patient"))).concat(
								"'");
				ResultSet invoiceRs = DBconnect.createStatement().executeQuery(
						invoiceSql);
				while (invoiceRs.next()) {
					Invoice i = new Invoice(invoiceRs.getInt("invoice"),
							invoiceRs.getInt("invoiceAmt"),
							invoiceRs.getDate("invoiceDate"),
							invoiceRs.getBoolean("invoicePaid"));
					// fetch procedures to add to Invoice
					ArrayList<Procedure> procs = new ArrayList<Procedure>();
					String procSql = "SELECT * FROM `procedure`, invoiceprocedures WHERE procedure.proc = invoiceprocedures.procedureID AND invoiceprocedures.invoiceID="
							+ String.valueOf(invoiceRs.getInt("invoice"));
					ResultSet procRs = DBconnect.createStatement()
							.executeQuery(procSql);
					while (procRs.next()) {
						Procedure proc = new Procedure(procRs.getInt("proc"),
								procRs.getString("procName"),
								procRs.getInt("procCost"));
						procs.add(proc);
					}
					// add procedures to Invoice
					i.setProcList(procs);
					// add invoice to arraylist
					invoices.add(i);
				}
				// add invoice array to patient
				p.setP_Invoice(invoices);
				// add patient to patient list
				patients.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (patients.isEmpty()) {
			return null;
		} else {
			return patients.get(0);
		}
	}

	@Override
	public void deletePatient(Patient p) {
		String patientDelete = "DELETE FROM patient WHERE patient = "
				+ p.getPatient();
		try {
			DBconnect.createStatement().execute(patientDelete);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void updatePatient(int ID, Patient updatePatient) {
		String patientUpdate = "UPDATE `patient` SET "
				+ "`patient`="
						.concat(String.valueOf(updatePatient.getPatient()))
				+ "," + "`patientName`=".concat(updatePatient.getPatientName())
				+ "," + "`patientAdd`=".concat(updatePatient.getPatientAdd())
				+ ","
				+ "`patientPhone`=".concat(updatePatient.getPatientPhone())
				+ "," + "`notes`=".concat(updatePatient.getNotes()) + " "
				+ "WHERE patient =".concat(String.valueOf(ID));
		try {
			DBconnect.createStatement().execute(patientUpdate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<Patient> findPatientInvoice(String type) {
		ArrayList<Patient> patientInvoices = new ArrayList<Patient>();
		String invoicesSQL;
		if (type.equals("debtors")) {
			invoicesSQL = "SELECT DISTINCT patientID FROM Invoice WHERE invoicePaid = 0";
		} else {
			invoicesSQL = "SELECT DISTINCT patientID FROM Invoice WHERE invoicePaid = 1";
		}
		ResultSet rs;
		try {
			rs = DBconnect.createStatement().executeQuery(invoicesSQL);

			while (rs.next()) {
				patientInvoices.add(findPatientByID(rs.getInt("patientID")));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return patientInvoices;
	}

	@Override
	public void updatePatientHistory(int PatientID, History h) {
		SimpleDateFormat sDF = new SimpleDateFormat("yyyy/MM/dd");
		String updateHistory = "UPDATE `history`" + "SET "
				+ "`conditionName`='" + h.getConditionName()
				+ "',`medication`='" + h.getMedication() + "',`dateOccured`='"
				+ sDF.format(h.getDateOccured()) + "' WHERE histID='"
				+ String.valueOf(h.getHistID()) + "' AND `patientID`= '"
				+ PatientID + "'";
		System.out.println(updateHistory);
		try {
			DBconnect.createStatement().execute(updateHistory);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void addPatientHistory(int patientID, History h) {
		SimpleDateFormat sDF = new SimpleDateFormat("yyyy/MM/dd");
		String insertHistory = "INSERT INTO `history`"
				+ "(`histID`, `patientID`, `conditionName`, `medication`, `dateOccured`) "
				+ "VALUES ('" + String.valueOf(h.getHistID()) + "','"
				+ String.valueOf(patientID) + "','" + h.getConditionName()
				+ "','" + h.getMedication() + "','"
				+ sDF.format(h.getDateOccured()) + "')";
		try {
			DBconnect.createStatement().execute(insertHistory);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void deletePatientHistory(int patientID, int historyID) {
		String deleteHistory = "DELETE FROM `history` WHERE " + "patientID='"
				+ String.valueOf(patientID) + "' AND histID= '"
				+ String.valueOf(historyID) + "'";
		try {
			DBconnect.createStatement().execute(deleteHistory);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public History findPatientHistory(int patientID, int historyID) {
		History hReturn = null;
		for (History h : findPatientByID(patientID).getP_History()) {
			if (h.getHistID() == historyID) {
				hReturn = h;
			}
		}
		return hReturn;
	}

	@Override
	public void addInvoicetoPatient(int patientID, Invoice i) {
		SimpleDateFormat sDF = new SimpleDateFormat("yyyy/MM/dd");
		String insertInvoice = "INSERT INTO `invoice`"
				+ "(`invoice`, `patientID`, `invoiceAmt`, `invoiceDate`, `invoicePaid`) "
				+ "VALUES ('" + String.valueOf(i.getInvoice()) + "','"
				+ String.valueOf(patientID) + "','"
				+ String.valueOf(i.getInvoiceAmt()) + "','"
				+ sDF.format(i.getInvoiceDate()) + "','0'); ";

		try {
			DBconnect.createStatement().execute(insertInvoice);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Procedure p : i.getProcList()) {
			String insertInvProc = "INSERT INTO `invoiceprocedures` (`invoiceID`, `procedureID`, `patientID`)"
					+ "VALUES ('"
					+ String.valueOf(i.getInvoice())
					+ "','"
					+ String.valueOf(p.getProc())
					+ "','"
					+ String.valueOf(patientID) + "')";
			try {
				DBconnect.createStatement().execute(insertInvProc);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void markInvoicePaid(int patientID, int invoiceID) {

		String payInv = "UPDATE `invoice` SET `invoicePaid` = '1' WHERE invoice = "
				+ String.valueOf(invoiceID)
				+ " AND patientID = "
				+ String.valueOf(patientID);
		try {
			DBconnect.createStatement().execute(payInv);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void deleteInvoice(int patientID, int invoiceID) {
		String delInv = "DELETE FROM `invoice` WHERE `invoice` = "
				+ String.valueOf(invoiceID) + " AND `patientID` = "
				+ String.valueOf(patientID);
		try {
			DBconnect.createStatement().execute(delInv);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<Patient> treatmentSearch(int selectedProcedureID,
			Date dateAfter, Date dateBefore) {

		SimpleDateFormat sDF = new SimpleDateFormat("yyyy-MM-dd");
		String dateAfterSQL = sDF.format(dateAfter);
		String dateBeforeSQL = sDF.format(dateBefore);
		ArrayList<Patient> patients = new ArrayList<Patient>();
		String searchSQL = "";
		if (selectedProcedureID > 0) {
			searchSQL = "SELECT DISTINCT i.patientID FROM invoice i , invoiceprocedures ip WHERE i.invoice = ip.invoiceID AND ip.procedureID = '"
					+ selectedProcedureID
					+ "' AND i.invoiceDate BETWEEN '"
					+ dateBeforeSQL + "' AND '" + dateAfterSQL + "'";
		} else {
			searchSQL = "SELECT DISTINCT patientID FROM Invoice WHERE invoiceDate BETWEEN '"
					+ dateBeforeSQL + "' AND '" + dateAfterSQL + "'";
		}
		ResultSet rs;
		try {
			rs = DBconnect.createStatement().executeQuery(searchSQL);

			while (rs.next()) {
				patients.add(findPatientByID(rs.getInt("patientID")));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return patients;

	}

	@Override
	public void deleteProcedure(int procedureID) {
		String delProc = "DELETE FROM `procedure` WHERE `proc` = "
				+ String.valueOf(procedureID);
		try {
			DBconnect.createStatement().execute(delProc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void updateProcedure(Procedure p) {
		String updateProc = "UPDATE `procedure`" + "SET " + "`procName`='"
				+ p.getProcName() + "',`procCost`='"
				+ String.valueOf(p.getProcCost()) + "' WHERE `proc` = "
				+ String.valueOf(p.getProc());
		try {
			DBconnect.createStatement().execute(updateProc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
