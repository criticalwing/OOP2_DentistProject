package ie.patrickrobertson.dentist.service;

import ie.patrickrobertson.dentist.Patient;
import ie.patrickrobertson.dentist.Procedure;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SerializedService extends BaseSerializeService {


	public SerializedService() {
		super();
		getData("src/main/resources/patients");
		getData("src/main/resources/procedures");
	}

	public SerializedService(ArrayList<Patient> patients,
			ArrayList<Procedure> procedures, ArrayList<String> batchFullReport,
			ArrayList<String> errorLog) {
		super();
		this.patients = patients;
		this.procedures = procedures;

	}
	
	private boolean getData(String source) {
		File directory = new File(source);
		if (directory.exists()) {
			if (directory.listFiles().length > 0) {
				File files[] = directory.listFiles();
				for (File f : files) {
					FileInputStream f_in;
					try {
						f_in = new FileInputStream(f);
						ObjectInputStream obj_in = new ObjectInputStream(f_in);
						Object obj = obj_in.readObject();
						
						if(obj instanceof Patient){
						patients.add((Patient) obj);
						} else{
							procedures.add((Procedure) obj);
						}
						
						obj_in.close();

					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			} else {
				System.out.println("folder contains no files");
				return false;
			}
		} else {
			System.out.println("error folder does not exist");
			return false;
		}
		return true;
	}

	public boolean saveData() {
		deleteExistingData("src/main/resources/patients");
		deleteExistingData("src/main/resources/procedure");
		for (Patient p : patients) {
			try {
				String location = "src/main/resources/patients/"
						+ p.getPatient() + ".data";
				FileOutputStream f_out = new FileOutputStream(location);
				
				ObjectOutputStream obj_out = new ObjectOutputStream(f_out);
				obj_out.writeObject(p);
				obj_out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for (Procedure p : procedures) {
			try {
				String location = "src/main/resources/procedures/"
						+ p.getProc() + ".data";
				FileOutputStream f_out = new FileOutputStream(location);
				ObjectOutputStream obj_out = new ObjectOutputStream(f_out);
				obj_out.writeObject(p);
				obj_out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return false;

	}

	private void deleteExistingData(String location) {
		File directory = new File(location);
		System.out.println("Trying to Delete"+location);
		if (directory.exists()) {
			if (directory.listFiles()!=null) {
				File files[] = directory.listFiles();
				for (File f : files) {
					if (f.delete()) {
						System.out.println("file deleted");
					} else {
						System.out.println("error File NOT deleted");
					}
				}
			}
		}
	}

}
