package com.kerux.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.kerux.bean.AccessLevelBean;
import com.kerux.bean.AdminBean;
import com.kerux.bean.AuditBean;
import com.kerux.bean.ClinicBean;
import com.kerux.bean.RatingBean;
import com.kerux.bean.StatBean;

public class MainController {

	List<ClinicBean> clinics;
	List<AdminBean> admins;
	List<RatingBean> ratings;
	List<String> timeofday;
	List<StatBean> stats;
	List<AuditBean> audits;
	List<AccessLevelBean> accessl;
	private List<String> servicelist;
	String rootadminname;
	String averating;
	
	
	
	public String getAverating() {
		return averating;
	}

	public void setAverating(String averating) {
		this.averating = averating;
	}

	public String getRootadminname() {
		return rootadminname;
	}

	public void setRootadminname(String rootadminname) {
		this.rootadminname = rootadminname;
	}

	public List<StatBean> getStats() {
		return stats;
	}

	public void setStats(List<StatBean> stats) {
		this.stats = stats;
	}

	public List<AuditBean> getAudits() {
		return audits;
	}

	public void setAudits(List<AuditBean> audits) {
		this.audits = audits;
	}
	
	
	
	public List<RatingBean> getRatings() {
		return ratings;
	}

	public void setRatings(List<RatingBean> ratings) {
		this.ratings = ratings;
	}

	public List<AccessLevelBean> getAccessl() {
		return accessl;
	}

	public void setAccessl(List<AccessLevelBean> accessl) {
		this.accessl = accessl;
	}

	public List<String> getTimeofday() {
		return timeofday;
	}

	public void setTimeofday(List<String> timeofday) {
		this.timeofday = timeofday;
	}

	public List<String> getServicelist() {
		return servicelist;
	}

	public void setServicelist(List<String> servicelist) {
		this.servicelist = servicelist;
	}

	public List<ClinicBean> getClinics() {
		return clinics;
	}

	public void setClinics(List<ClinicBean> clinics) {
		this.clinics = clinics;
	}  
	
	public List<AdminBean> getAdmins() {
		return admins;
	}

	public void setAdmins(List<AdminBean> admins) {
		this.admins = admins;
	}

	

	public String display() {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();

		String s=(String)session.getAttribute("login");
		if(s!=null && !s.equals("")){
			try {
				
				rootadminname=MainControllerDB.getRootadminname((String)session.getAttribute("rootAdminId"));
				stats=MainControllerDB.getStat();
				audits=MainControllerDB.getAudit();
				servicelist=MainControllerDB.getServices();
				accessl=MainControllerDB.getALevel();
				ratings=MainControllerDB.getRating();
				timeofday=getTime();
				clinics=MainControllerDB.getClinic();
				averating=MainControllerDB.getAveRating();
				System.out.println(Arrays.toString(clinics.toArray()));
				admins=MainControllerDB.getAdmin();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "success";
		}
		else{
			return "error";
		}
	}
	
	List<String> getTime(){
		List<String> time = new ArrayList<String>();
		String minString;

		for (int day=0;day<2;day++){
			String padding="0";
			String suffix="am";
			if (day==1)
				suffix="pm";
			for(int hours=0; hours<12; hours++) {
			    for(int mins=0; mins<60; mins+=30){
			    	if (mins==0){
			    		minString="00";
			    	}
			    	else{
			    		minString="30";
			    	}
			    	if (hours>9){
			    		padding="";
			    	}
			    	time.add(padding+hours+":"+minString+" "+suffix);
			    }
			}
			
		}
		        
		return time;
	}

	
}
