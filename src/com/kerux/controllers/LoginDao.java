package com.kerux.controllers;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kerux.security.Security;
import com.kerux.utility.DBUtility;

public class LoginDao implements DBUtility {

	public static int validate(String username,String userpass){
		boolean status=false;
		int rootAdminId=-1;
		try{
			Class.forName(Security.decrypt(jdbcDriverName));
			Connection con=DriverManager.getConnection(Security.decrypt(jdbcUrl),
					Security.decrypt(dbUserName),Security.decrypt(dbPassword));
			PreparedStatement ps=con.prepareStatement(LOGIN_CRED);
			ps.setString(1,Security.encrypt(username));
			ps.setString(2,Security.encrypt(userpass));
			System.out.print(Security.encrypt(username)+"----"+Security.encrypt(userpass));
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				rootAdminId=rs.getInt(1);
				status=true;
			}
			
		}catch(Exception e){e.printStackTrace();}
		System.out.print(rootAdminId+"----------------");
		return rootAdminId;
	}
}
