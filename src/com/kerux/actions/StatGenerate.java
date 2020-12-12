package com.kerux.actions;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.kerux.bean.AuditBean;
import com.kerux.bean.StatBean;
import com.kerux.controllers.MainControllerDB;

public class StatGenerate {
	List<StatBean> stats;
	List<AuditBean> audits;

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

	public String display() {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();

		String s=(String)session.getAttribute("login");
		if(s!=null && !s.equals("")){
			try {
				stats=MainControllerDB.getStat();
				audits=MainControllerDB.getAudit();
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
}
