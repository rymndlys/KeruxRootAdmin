package com.kerux.controllers;

import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;  

public class Login extends ActionSupport implements SessionAware {
private String username;
private String userpass;
private int rootAdminId;
SessionMap<String,String> sessionmap;



public int getRootAdminId() {
	return rootAdminId;
}

public void setRootAdminId(int rootAdminId) {
	this.rootAdminId = rootAdminId;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getUserpass() {
	return userpass;
}

public void setUserpass(String userpass) {
	this.userpass = userpass;
}

public String execute(){

		return "success";
	
}

public void validate() {
	boolean check = true;
	if(username.isEmpty()){
		addActionError("Name can't be empty");  
		check=false;
	}
	if(userpass.isEmpty()){
		addActionError("Password can't be empty");
		check=false;
	}
	if(check){
		this.setRootAdminId(LoginDao.validate(username, userpass));
		sessionmap.put("rootAdminId", Integer.toString(this.getRootAdminId()));
		if(this.getRootAdminId()>-1){
			
		
		}
		else{
			addActionError("The credentials you entered are not valid");  
		}
	}
		
}

public void setSession(Map map) {
	sessionmap=(SessionMap)map;
	sessionmap.put("login","true");
	
}

public String display(){
	sessionmap.invalidate();
	return "loggout";
}

}
