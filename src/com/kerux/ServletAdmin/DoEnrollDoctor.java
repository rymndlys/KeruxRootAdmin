package com.kerux.ServletAdmin;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kerux.Servlet.AndroidServlet;
import com.kerux.security.Security;
import com.kerux.utility.DBUtilityAdmin;
import com.kerux.utility.Mailer;

/**
 * Servlet implementation class DoEnrollDoctor
 */
@WebServlet("/DoEnrollDoctor")
public class DoEnrollDoctor extends HttpServlet implements DBUtilityAdmin {
	private static final long serialVersionUID = 1L;
       
	private static Connection getConnection(){
		Connection connection = null;
		
		try{
			Class.forName(Security.decrypt(jdbcDriverName)); //ensure that the library will be loaded to the memory
			//secure database connection string configuration
			connection = DriverManager.getConnection(Security.decrypt(jdbcUrl),
					Security.decrypt(dbUserName),Security.decrypt(dbPassword));
			
		}catch(ClassNotFoundException cfne){
			//should be displayed on a secure error page code
			System.err.println(cfne.getMessage());
		}catch(SQLException sqle){
			System.err.println(sqle.getMessage());
		}
		return connection;
	}
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        	try {
        		 String allvalues="";
                boolean hasRecord=false;
                String endwrite="";
                Connection con=getConnection();
                String query = VALIDATION_DOCTOR;
                String newdocid="";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, request.getParameter("docFName"));
                ps.setString(2, request.getParameter("docLName"));
                ps.setString(3, request.getParameter("clinic"));
                ps.setString(4, request.getParameter("dept"));
                ResultSet rs=ps.executeQuery();
                response.setStatus(HttpServletResponse.SC_OK);
                OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
                if(rs.next()){
                    hasRecord = true;
                }
                
                if (hasRecord){
                    endwrite="Record already exists";
                }
                else {
                    try {
                        
                        String query1 = INSERT_DOCTOR;
                        PreparedStatement ps1 = con.prepareStatement(query1);
                        ps1.setString(1, request.getParameter("docType"));
                        ps1.setString(2, request.getParameter("clinic"));
                        ps1.setString(3, request.getParameter("reason"));
                        ps1.setString(4, request.getParameter("docFName"));
                        ps1.setString(5, request.getParameter("docLName"));
                        ps1.setString(6, request.getParameter("dept"));
                        ps1.setString(7, request.getParameter("roomNum"));
                        ps1.setString(8, request.getParameter("sched1"));
                        ps1.setString(9, request.getParameter("sched2"));
                        ps1.setString(10, request.getParameter("docDays"));
                        ps1.setString(11, request.getParameter("status"));
                        ps1.setString(12, request.getParameter("email"));
                        ps1.setString(13, request.getParameter("prclicense"));
                        ps1.setString(14, request.getParameter("photo"));
                        allvalues=request.getParameter("docType")+"**"+request.getParameter("clinic")+"**"+
                        request.getParameter("reason")+"**"+request.getParameter("docFName")+"**"+
                        		request.getParameter("docLName")+"**"+request.getParameter("dept")+"**"+
                        request.getParameter("roomNum")+"**"+request.getParameter("sched1")+"**"+
                        		request.getParameter("sched2")+"**"+request.getParameter("docDays")+"**"+
                        request.getParameter("status");
                        ps1.execute();

                    

                        String query2 = SELECT_NEW_DOCTOR_ID;
                        PreparedStatement ps2 = con.prepareStatement(query2);
                        ResultSet rs1 = ps2.executeQuery();
                        while (rs1.next()) {
                            newdocid = rs1.getString(1);
                            String newdeptid = rs1.getString(1);

                            //inserting to qm enrollment
                            String query3 = INSERT_DOC_ENROLLMENT;
                            PreparedStatement ps3 = con.prepareStatement(query3);
                            ps3.setString(1, request.getParameter("getadminid"));
                            ps3.setString(2, request.getParameter("getclinicid"));
                            ps3.setString(3, newdeptid);
                            ps3.setString(4, newdocid);
                            ps3.executeUpdate();

                            endwrite="ADDED SUCCESSFULLY!";
                            String email = request.getParameter("email").trim();
                            String subject = "Kerux Doctor Enrollment";
                            String message = "Good day!\n" +
                                    "This is to inform you that you have been enrolled \nas a Doctor in the Kerux Mobile Queueing System,\n\n\n" +
                                    "We hope you have a wonderful time being part of the Kerux Family!";
                            Mailer.send("roychristian.yabut@benilde.edu.ph","33CYzrpj",email,subject,message);
                        }
                       

                    
                    } catch (Exception ex) {
                    	System.out.println(ex.getMessage()+allvalues);
                        endwrite = "Exceptions" + ex;
                    }
                }
               
                writer.write(endwrite);
                writer.write(" | "+newdocid);

                
                writer.flush();
                writer.close();
                con.close();
               
                
            } catch (IOException e) {
                try{
                	System.out.println(e.getMessage());
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().print(e.getMessage());
                    response.getWriter().close();
                } catch (IOException ioe) {
                }
            }  
        	
           

        
    }

    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
	    try {
	        processRequest(request, response);
	    } catch (ClassNotFoundException ex) {
	        Logger.getLogger(AndroidServlet.class.getName()).log(Level.SEVERE, null, ex);
	    } catch (SQLException ex) {
	        Logger.getLogger(AndroidServlet.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
	    try {
	        processRequest(request, response);
	    } catch (ClassNotFoundException ex) {
	        Logger.getLogger(AndroidServlet.class.getName()).log(Level.SEVERE, null, ex);
	    } catch (SQLException ex) {
	        Logger.getLogger(AndroidServlet.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}

}

/*
 Connection con = connectionClass.CONN();
            PreparedStatement ps1 = null;
            try {
                ps1 = con.prepareStatement(VALIDATION_DOCTOR);
                ps1.setString(1, docFName);
                ps1.setString(2, docLName);
                ps1.setInt(3, clinic);
                ps1.setInt(4, dept);

                ResultSet rs = ps1.executeQuery();

                if (rs.next()) {
                    hasRecord=true;

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //checking if the record exists on the database
            if (hasRecord){
                message = "Record already exists";
            }
            else {
                try {
                    if (con == null) {
                        message = "Unsuccessful";
                    } else {

                        if(cboxMon!=null){
                            docDays+=cboxMon;
                        }
                        if(cboxTues!=null){
                            docDays+=cboxTues;
                        }
                        if(cboxWed!=null){
                            docDays+=cboxWed;
                        }
                        if(cboxThurs!=null){
                            docDays+=cboxThurs;
                        }
                        if(cboxFri!=null){
                            docDays+=cboxFri;
                        }
                        if(cboxSat!=null){
                            docDays+=cboxSat;
                        }
                        //inserting doctor in the database
                        String query = INSERT_DOCTOR;
                        PreparedStatement ps = con.prepareStatement(query);

                        ps.setInt (1, docType);
                        ps.setInt (2, clinic);
                        ps.setInt(3, reason);
                        ps.setString(4, docFName);
                        ps.setString(5, docLName);
                        ps.setInt (6, dept);
                        ps.setString(7, roomNum);
                        ps.setString(8, sched1);
                        ps.setString(9, sched2);
                        ps.setString(10, docDays);
                        ps.setString(11, status);

                        ps.execute();

                        String query2=SELECT_NEW_DOCTOR_ID;
                        PreparedStatement ps2 = con.prepareStatement(query2);
                        ResultSet rs1 = ps2.executeQuery();
                        while(rs1.next()){
                            String newdocid=rs1.getString(1);
                            String newdeptid=rs1.getString(1);

                            //inserting into doctor_enrollment
                            String query3=INSERT_DOC_ENROLLMENT;
                            PreparedStatement ps3 = con.prepareStatement(query3);
                            ps3.setString(1, session.getadminid());
                            ps3.setString(2, session.getclinicid());
                            ps3.setString(3, newdeptid);
                            ps3.setString(4, newdocid);
                            ps3.executeUpdate();

                            //inserting to audit log
                            String queryAUDIT=INSERT_AUDIT_LOG;
                            PreparedStatement psAUDIT=con.prepareStatement(queryAUDIT);
                            psAUDIT.setString(1, sec.encrypt("doctor"));
                            psAUDIT.setString(2, sec.encrypt("insert"));
                            psAUDIT.setString(3, sec.encrypt("Insert doctor record"));
                            psAUDIT.setString(4, sec.encrypt("none"));
                            psAUDIT.setString(5, sec.encrypt(String.valueOf(clinic)+", "+reason+", "+dept+", "+status));
                            psAUDIT.setString(6, sec.encrypt(session.getusername()));
                            psAUDIT.executeUpdate();
                            //inserting to audit log
                            PreparedStatement psAUDIT1=con.prepareStatement(queryAUDIT);
                            psAUDIT.setString(1, sec.encrypt("doctor_enrollment"));
                            psAUDIT.setString(2, sec.encrypt("insert"));
                            psAUDIT.setString(3, sec.encrypt("Insert into doctor_enrollment table record"));
                            psAUDIT.setString(4, sec.encrypt("none"));
                            psAUDIT.setString(5, sec.encrypt(session.getadminid()+", "+newdocid+", "+ ", "+ newdeptid + ", " + session.getclinicid()));
                            psAUDIT.setString(6,sec.encrypt(session.getusername()));
                            psAUDIT.executeUpdate();
                        }
                        con.close();
                        message = "ADDED";
                    }
                } catch (Exception ex) {
                    isSuccess = false;
                    message = "Exceptions" + ex;
                }
            }
            return message; 
  
 */
