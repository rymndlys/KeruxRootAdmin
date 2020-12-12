package com.kerux.SpinnerObjects;

public class DoctorType {
	int doctortype_id;
	String doctorType;
	
	
	public DoctorType(int doctortype_id, String doctorType) {
		super();
		this.doctortype_id = doctortype_id;
		this.doctorType = doctorType;
	}
	public int getDoctortype_id() {
		return doctortype_id;
	}
	public void setDoctortype_id(int doctortype_id) {
		this.doctortype_id = doctortype_id;
	}
	public String getDoctorType() {
		return doctorType;
	}
	public void setDoctorType(String doctorType) {
		this.doctorType = doctorType;
	}
	
	
}
