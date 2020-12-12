package com.kerux.actions;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.kerux.controllers.MainControllerDB;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ClinicEnrollment extends ActionSupport{

	private int clinicid;
	private String clinicname;
	private String address;
	private String street;
	private String barangay;
	private String city;
	private String province;
	private String clinichours1M;
	private String clinichours2M;
	private String clinichours1T;
	private String clinichours2T;
	private String clinichours1W;
	private String clinichours2W;
	private String clinichours1TH;
	private String clinichours2TH;
	private String clinichours1F;
	private String clinichours2F;
	private String clinichours1Sat;
	private String clinichours2Sat;
	private String clinichours1Sun;
	private String clinichours2Sun;
	private String clinicmins1M;
	private String clinicmins2M;
	private String clinicmins1T;
	private String clinicmins2T;
	private String clinicmins1W;
	private String clinicmins2W;
	private String clinicmins1TH;
	private String clinicmins2TH;
	private String clinicmins1F;
	private String clinicmins2F;
	private String clinicmins1Sat;
	private String clinicmins2Sat;
	private String clinicmins1Sun;
	private String clinicmins2Sun;
	private String clinicsuffix1M;
	private String clinicsuffix2M;
	private String clinicsuffix1T;
	private String clinicsuffix2T;
	private String clinicsuffix1W;
	private String clinicsuffix2W;
	private String clinicsuffix1TH;
	private String clinicsuffix2TH;
	private String clinicsuffix1F;
	private String clinicsuffix2F;
	private String clinicsuffix1Sat;
	private String clinicsuffix2Sat;
	private String clinicsuffix1Sun;
	private String clinicsuffix2Sun;
	
	private String clinicdays;
	private String services;
	private String addservices;
	private String contactno;
	private String status;
	private List<String> servicelist;
	private List<String> cliniclist;
	
	

	public void prepare() throws Exception{
		servicelist=MainControllerDB.getServices();
		
	}

	public ClinicEnrollment() {
		servicelist=MainControllerDB.getServices();
	}

	public String execute(){
		return "success";
	}
	
	public void validate(){
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();

		String rootAdminId=(String)session.getAttribute("rootAdminId");
		String t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14;
		
		t1=toOneTime(clinichours1M,clinicmins1M,clinicsuffix1M);
		t2=toOneTime(clinichours2M,clinicmins2M,clinicsuffix2M);
		t3=toOneTime(clinichours1T,clinicmins1T,clinicsuffix1T);
		t4=toOneTime(clinichours2T,clinicmins2T,clinicsuffix2T);
		t5=toOneTime(clinichours1W,clinicmins1W,clinicsuffix1W);
		t6=toOneTime(clinichours2W,clinicmins2W,clinicsuffix2W);
		t7=toOneTime(clinichours1TH,clinicmins1TH,clinicsuffix1TH);
		t8=toOneTime(clinichours2TH,clinicmins2TH,clinicsuffix2TH);
		t9=toOneTime(clinichours1F,clinicmins1F,clinicsuffix1F);
		t10=toOneTime(clinichours2F,clinicmins2F,clinicsuffix2F);
		t11=toOneTime(clinichours1Sat,clinicmins1Sat,clinicsuffix1Sat);
		t12=toOneTime(clinichours2Sat,clinicmins2Sat,clinicsuffix2Sat);
		t13=toOneTime(clinichours1Sun,clinicmins1Sun,clinicsuffix1Sun);
		t14=toOneTime(clinichours2Sun,clinicmins2Sun,clinicsuffix2Sun);
		
		
		String error="";
		if(clinicname.isEmpty()||street.isEmpty()||barangay.isEmpty()||city.isEmpty()||province.isEmpty()||clinicdays.isEmpty()||services.isEmpty()||contactno.isEmpty()){
			error="Please fill all the fields";
			addActionError(error);
		}
		else {
			System.out.print(services+", "+addservices);
			
			String result=MainControllerDB.validate(clinicname, street, barangay, city, province, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, clinicdays, services, addservices, contactno, rootAdminId);
			if(result.equals("")){
				addActionMessage("Clinic successfully inserted");
			}
			else if(result.equals("Add service")){
				addActionError("Please add a service");
			}
			else if(result.equals("CLINICNAME THE SAME")){
				addActionError("Clinic name already taken");
			}
			else{
				addActionError(error);
			}
		}
		
	}
	
	public String toOneTime(String t1, String t2, String t3){
		String result="";
		result=t1+":"+t2+" "+t3;
		return result;
	}
	
//	public void validate() {
//		if("pankaj".equalsIgnoreCase(getUsername()) && "admin".equalsIgnoreCase(getPassword())){
//			addActionMessage("Welcome Admin, do some work.");
//		}else{
//			if(!"pankaj".equalsIgnoreCase(getUsername())){
//				addActionError("User name is not valid");
//			}
//			if(!"admin".equalsIgnoreCase(getPassword())){
//				addActionError("Password is wrong");
//			}
//		}
//	}

	
	
	public String getStatus() {
		return status;
	}


	public String getClinicmins1M() {
		return clinicmins1M;
	}

	public void setClinicmins1M(String clinicmins1m) {
		clinicmins1M = clinicmins1m;
	}

	public String getClinicmins2M() {
		return clinicmins2M;
	}

	public void setClinicmins2M(String clinicmins2m) {
		clinicmins2M = clinicmins2m;
	}

	public String getClinicmins1T() {
		return clinicmins1T;
	}

	public void setClinicmins1T(String clinicmins1t) {
		clinicmins1T = clinicmins1t;
	}

	public String getClinicmins2T() {
		return clinicmins2T;
	}

	public void setClinicmins2T(String clinicmins2t) {
		clinicmins2T = clinicmins2t;
	}

	public String getClinicmins1W() {
		return clinicmins1W;
	}

	public void setClinicmins1W(String clinicmins1w) {
		clinicmins1W = clinicmins1w;
	}

	public String getClinicmins2W() {
		return clinicmins2W;
	}

	public void setClinicmins2W(String clinicmins2w) {
		clinicmins2W = clinicmins2w;
	}

	public String getClinicmins1TH() {
		return clinicmins1TH;
	}

	public void setClinicmins1TH(String clinicmins1th) {
		clinicmins1TH = clinicmins1th;
	}

	public String getClinicmins2TH() {
		return clinicmins2TH;
	}

	public void setClinicmins2TH(String clinicmins2th) {
		clinicmins2TH = clinicmins2th;
	}

	public String getClinicmins1F() {
		return clinicmins1F;
	}

	public void setClinicmins1F(String clinicmins1f) {
		clinicmins1F = clinicmins1f;
	}

	public String getClinicmins2F() {
		return clinicmins2F;
	}

	public void setClinicmins2F(String clinicmins2f) {
		clinicmins2F = clinicmins2f;
	}

	public String getClinicmins1Sat() {
		return clinicmins1Sat;
	}

	public void setClinicmins1Sat(String clinicmins1Sat) {
		this.clinicmins1Sat = clinicmins1Sat;
	}

	public String getClinicmins2Sat() {
		return clinicmins2Sat;
	}

	public void setClinicmins2Sat(String clinicmins2Sat) {
		this.clinicmins2Sat = clinicmins2Sat;
	}

	public String getClinicmins1Sun() {
		return clinicmins1Sun;
	}

	public void setClinicmins1Sun(String clinicmins1Sun) {
		this.clinicmins1Sun = clinicmins1Sun;
	}

	public String getClinicmins2Sun() {
		return clinicmins2Sun;
	}

	public void setClinicmins2Sun(String clinicmins2Sun) {
		this.clinicmins2Sun = clinicmins2Sun;
	}

	public String getClinicsuffix1M() {
		return clinicsuffix1M;
	}

	public void setClinicsuffix1M(String clinicsuffix1m) {
		clinicsuffix1M = clinicsuffix1m;
	}

	public String getClinicsuffix2M() {
		return clinicsuffix2M;
	}

	public void setClinicsuffix2M(String clinicsuffix2m) {
		clinicsuffix2M = clinicsuffix2m;
	}

	public String getClinicsuffix1T() {
		return clinicsuffix1T;
	}

	public void setClinicsuffix1T(String clinicsuffix1t) {
		clinicsuffix1T = clinicsuffix1t;
	}

	public String getClinicsuffix2T() {
		return clinicsuffix2T;
	}

	public void setClinicsuffix2T(String clinicsuffix2t) {
		clinicsuffix2T = clinicsuffix2t;
	}

	public String getClinicsuffix1W() {
		return clinicsuffix1W;
	}

	public void setClinicsuffix1W(String clinicsuffix1w) {
		clinicsuffix1W = clinicsuffix1w;
	}

	public String getClinicsuffix2W() {
		return clinicsuffix2W;
	}

	public void setClinicsuffix2W(String clinicsuffix2w) {
		clinicsuffix2W = clinicsuffix2w;
	}

	public String getClinicsuffix1TH() {
		return clinicsuffix1TH;
	}

	public void setClinicsuffix1TH(String clinicsuffix1th) {
		clinicsuffix1TH = clinicsuffix1th;
	}

	public String getClinicsuffix2TH() {
		return clinicsuffix2TH;
	}

	public void setClinicsuffix2TH(String clinicsuffix2th) {
		clinicsuffix2TH = clinicsuffix2th;
	}

	public String getClinicsuffix1F() {
		return clinicsuffix1F;
	}

	public void setClinicsuffix1F(String clinicsuffix1f) {
		clinicsuffix1F = clinicsuffix1f;
	}

	public String getClinicsuffix2F() {
		return clinicsuffix2F;
	}

	public void setClinicsuffix2F(String clinicsuffix2f) {
		clinicsuffix2F = clinicsuffix2f;
	}

	public String getClinicsuffix1Sat() {
		return clinicsuffix1Sat;
	}

	public void setClinicsuffix1Sat(String clinicsuffix1Sat) {
		this.clinicsuffix1Sat = clinicsuffix1Sat;
	}

	public String getClinicsuffix2Sat() {
		return clinicsuffix2Sat;
	}

	public void setClinicsuffix2Sat(String clinicsuffix2Sat) {
		this.clinicsuffix2Sat = clinicsuffix2Sat;
	}

	public String getClinicsuffix1Sun() {
		return clinicsuffix1Sun;
	}

	public void setClinicsuffix1Sun(String clinicsuffix1Sun) {
		this.clinicsuffix1Sun = clinicsuffix1Sun;
	}

	public String getClinicsuffix2Sun() {
		return clinicsuffix2Sun;
	}

	public void setClinicsuffix2Sun(String clinicsuffix2Sun) {
		this.clinicsuffix2Sun = clinicsuffix2Sun;
	}

	public void setServicelist(List<String> servicelist) {
		this.servicelist = servicelist;
	}

	public void setCliniclist(List<String> cliniclist) {
		this.cliniclist = cliniclist;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getBarangay() {
		return barangay;
	}

	public void setBarangay(String barangay) {
		this.barangay = barangay;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getAddservices() {
		return addservices;
	}

	public void setAddservices(String addservices) {
		this.addservices = addservices;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public int getClinicid() {
		return clinicid;
	}


	public void setClinicid(int clinicid) {
		this.clinicid = clinicid;
	}


	public List<String> getServicelist() {
		return servicelist;
	}


	public List<String> getCliniclist() {
		return cliniclist;
	}



	public String getClinicname() {
		return clinicname;
	}




	public void setClinicname(String clinicname) {
		this.clinicname = clinicname;
	}




	public String getAddress() {
		return address;
	}




	public void setAddress(String address) {
		this.address = address;
	}


	public String getClinichours1M() {
		return clinichours1M;
	}

	public void setClinichours1M(String clinichours1m) {
		clinichours1M = clinichours1m;
	}

	public String getClinichours2M() {
		return clinichours2M;
	}

	public void setClinichours2M(String clinichours2m) {
		clinichours2M = clinichours2m;
	}

	public String getClinichours1T() {
		return clinichours1T;
	}

	public void setClinichours1T(String clinichours1t) {
		clinichours1T = clinichours1t;
	}

	public String getClinichours2T() {
		return clinichours2T;
	}

	public void setClinichours2T(String clinichours2t) {
		clinichours2T = clinichours2t;
	}

	public String getClinichours1W() {
		return clinichours1W;
	}

	public void setClinichours1W(String clinichours1w) {
		clinichours1W = clinichours1w;
	}

	public String getClinichours2W() {
		return clinichours2W;
	}

	public void setClinichours2W(String clinichours2w) {
		clinichours2W = clinichours2w;
	}

	public String getClinichours1TH() {
		return clinichours1TH;
	}

	public void setClinichours1TH(String clinichours1th) {
		clinichours1TH = clinichours1th;
	}

	public String getClinichours2TH() {
		return clinichours2TH;
	}

	public void setClinichours2TH(String clinichours2th) {
		clinichours2TH = clinichours2th;
	}

	public String getClinichours1F() {
		return clinichours1F;
	}

	public void setClinichours1F(String clinichours1f) {
		clinichours1F = clinichours1f;
	}

	public String getClinichours2F() {
		return clinichours2F;
	}

	public void setClinichours2F(String clinichours2f) {
		clinichours2F = clinichours2f;
	}

	public String getClinichours1Sat() {
		return clinichours1Sat;
	}

	public void setClinichours1Sat(String clinichours1Sat) {
		this.clinichours1Sat = clinichours1Sat;
	}

	public String getClinichours2Sat() {
		return clinichours2Sat;
	}

	public void setClinichours2Sat(String clinichours2Sat) {
		this.clinichours2Sat = clinichours2Sat;
	}

	public String getClinichours1Sun() {
		return clinichours1Sun;
	}

	public void setClinichours1Sun(String clinichours1Sun) {
		this.clinichours1Sun = clinichours1Sun;
	}

	public String getClinichours2Sun() {
		return clinichours2Sun;
	}

	public void setClinichours2Sun(String clinichours2Sun) {
		this.clinichours2Sun = clinichours2Sun;
	}

	public String getClinicdays() {
		return clinicdays;
	}




	public void setClinicdays(String clinicdays) {
		this.clinicdays = clinicdays;
	}




	public String getServices() {
		return services;
	}


	public void setServices(String services) {
		this.services = services;
	}




	public String getContactno() {
		return contactno;
	}




	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

}
