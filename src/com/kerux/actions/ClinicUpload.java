package com.kerux.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.kerux.controllers.MainControllerDB;
import com.opensymphony.xwork2.ActionSupport;

public class ClinicUpload extends ActionSupport{


	public String execute(){
		return "success";
	}
	
	public void validate(){
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();

		String result=(String)session.getAttribute("message");
		
		if(result.equals("Upload has been done successfully!")){
			addActionMessage("Upload has been done successfully!");
		}
		else{
			addActionError(result);
		}
	}
	
}