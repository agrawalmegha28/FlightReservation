package edu.npu.fr.domain;

import java.util.Date;

public class Passenger {
	private long id;
	private String fName;
	private String mName;
	private String lName;
	private String dob;
	private String gender;
	
	public Passenger() {
	}

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getfName() {
		return fName;
	}


	public void setfName(String fName) {
		this.fName = fName;
	}


	public String getlName() {
		return lName;
	}


	public void setlName(String lName) {
		this.lName = lName;
	}


	public String getmName() {
		return mName;
	}


	public void setmName(String mName) {
		this.mName = mName;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getDob() {
		return dob;
	}


	public void setDob(String dob) {
		this.dob = dob;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "firstName : " + fName + " lastName : " + lName + " dob : " + dob;
	}

	public boolean isValid() {
		return fName != null && lName != null && dob != null && !(fName.equals("") || lName.equals("") || dob.equals(""));
	}
}
