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
 * Servlet implementation class DoEnrollQMServlet
 */
@WebServlet("/DoEnrollQMServlet")
public class DoEnrollQMServlet extends HttpServlet implements DBUtilityAdmin {
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
                boolean hasRecord=false;
                String endwrite="";
                Connection con=getConnection();
                String query = VALIDATION_QM;
                String newqmid="";
                Security sec=new Security();
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, request.getParameter("QMFname"));
                ps.setString(2, request.getParameter("QMLname"));
                ps.setString(3, request.getParameter("clinic"));
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
                        
                        String query1 = INSERT_QM;
                        PreparedStatement ps1 = con.prepareStatement(query1);
                        ps1.setString(1, request.getParameter("clinic"));
                        ps1.setString(2, request.getParameter("dept"));
                        ps1.setString(3, request.getParameter("reason"));
                        ps1.setString(4, request.getParameter("secQMpw"));
                        ps1.setString(5, request.getParameter("QMFname"));
                        ps1.setString(6, request.getParameter("QMLname"));
                        ps1.setString(7, request.getParameter("QMEmail"));
                        ps1.setString(8, request.getParameter("status"));
			ps1.setString(9, request.getParameter("photo"));
                        ps1.execute();

                        
                        
                        

                        String query2 = SELECT_NEW_QUEUEMANAGER_ID;
                        PreparedStatement ps2 = con.prepareStatement(query2);
                        ResultSet rs1 = ps2.executeQuery();
                        while (rs1.next()) {
                            newqmid = rs1.getString(1);
                            String newdeptid = rs1.getString(1);

                            //inserting to qm enrollment
                            String query3 = INSERT_QM_ENROLLMENT;
                            PreparedStatement ps3 = con.prepareStatement(query3);
                            ps3.setString(1, newqmid);
                            ps3.setString(2, request.getParameter("getadminid"));
                            ps3.setString(3, request.getParameter("dept"));
                            ps3.setString(4, request.getParameter("clinic"));
                            ps3.executeUpdate();

                            endwrite="ADDED SUCCESSFULLY!";
                        }
                       
                      //sending email
                        String email = sec.decrypt(request.getParameter("QMEmail")).trim();
                        String subject = "Kerux Queue Manager Enrollment Credentials";
                        String message = "Good day!\n" +
                                "We've successfully enrolled you as a Queue Manager\n\n\n" +
                                "Here are your credentials" + "\n\nUsername: " + sec.decrypt(request.getParameter("QMEmail")).trim() +
                                "\nPassword: " + request.getParameter("QMpw").trim() + "\n\n You can now login on the kerux app using this credentials. \n" +
                                "Please change your password immediately after receiving this email\n\n" +
                                "\n\n Thank you!";
                        Mailer.send("roychristian.yabut@benilde.edu.ph","33CYzrpj",email,subject,message);
                    
                    } catch (Exception ex) {
                    	System.out.println(ex.getMessage());
                        endwrite = "Exceptions" + ex;
                    }
                }
               
                writer.write(endwrite);
                writer.write(" | "+newqmid);

                
                writer.flush();
                writer.close();
                con.close();
               
                
            } catch (IOException e) {
                try{
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
PreparedStatement ps = null;
try {
    ps = con.prepareStatement(VALIDATION_QM);
    ps.setString(1, QMuname);
    ps.setString(2, QMFname);
    ps.setString(3, QMLname);
    ps.setInt(4, clinic);

    ResultSet rs=ps.executeQuery();

    if(rs.next()){
        hasRecord = true;
    }
} catch (SQLException e) {
    e.printStackTrace();
}


if (hasRecord){
    message = "Record already exists";
}
else {
    try {
        if (con == null) {
            message = "Unsuccessful";
        } else {
            String query = INSERT_QM;
            PreparedStatement ps1 = con.prepareStatement(query);
            ps1.setInt(1, clinic);
            ps1.setInt(2, dept);
            ps1.setInt(3, reason);
            ps1.setString(4, sec.encrypt(QMuname));
            ps1.setString(5, sec.encrypt(QMpw));
            ps1.setString(6, QMFname);
            ps1.setString(7, QMLname);
            ps1.setString(8, QMEmail);
            ps1.setString(9, status);

            ps1.execute();

            //sending email
            String email = QMEmail.trim();
            String subject = "Kerux Queue Manager Enrollment Credentials";
            String message = "Good day!\n" +
                    "We've successfully enrolled you as a Queue Manager\n\n\n" +
                    "Here are your credentials" + "\n\nUsername: " + QMuname.trim() +
                    "\nPassword: " + QMpw.trim() + "\n\n You can now login on the kerux app using this credentials. \n" +
                    "Please change your password immediately after receiving this email\n\n" +
                    "\n\n Thank you!";

            SendMailTask sm = new SendMailTask(EnrollQM.this, email, subject, message);
            sm.execute();

            String query2 = SELECT_NEW_QUEUEMANAGER_ID;
            PreparedStatement ps2 = con.prepareStatement(query2);
            ResultSet rs1 = ps2.executeQuery();
            while (rs1.next()) {
                String newqmid = rs1.getString(1);
                String newdeptid = rs1.getString(1);

                //inserting to qm enrollment
                String query3 = INSERT_QM_ENROLLMENT;
                PreparedStatement ps3 = con.prepareStatement(query3);
                ps3.setString(1, newqmid);
                ps3.setString(2, session.getadminid());
                ps3.setString(3, String.valueOf(dept));
                ps3.setString(4, String.valueOf(clinic));
                ps3.executeUpdate();

                //inserting to audit log
                String queryAUDIT = INSERT_AUDIT_LOG;
                PreparedStatement psAUDIT = con.prepareStatement(queryAUDIT);
                psAUDIT.setString(1, sec.encrypt("queue manager"));
                psAUDIT.setString(2, sec.encrypt("insert"));
                psAUDIT.setString(3, sec.encrypt("Inserting Queue Manager record"));
                psAUDIT.setString(4, sec.encrypt("none"));
                psAUDIT.setString(5, sec.encrypt(String.valueOf(clinic) + ", " + reason + ", " + dept + ", " + status));
                psAUDIT.setString(6, sec.encrypt(session.getusername()));
                psAUDIT.executeUpdate();
                //inserting to audit log
                PreparedStatement psAUDIT1 = con.prepareStatement(queryAUDIT);
                psAUDIT.setString(1, sec.encrypt("qmenrollment"));
                psAUDIT.setString(2, sec.encrypt("insert"));
                psAUDIT.setString(3, sec.encrypt("Insert into qmenrollment table"));
                psAUDIT.setString(4, sec.encrypt("none"));
                psAUDIT.setString(5, sec.encrypt(session.getadminid() + ", " + newqmid + ", " + ", " + newdeptid + ", " + session.getclinicid()));
                psAUDIT.setString(6, sec.encrypt(session.getusername()));
                psAUDIT.executeUpdate();
            }
            con.close();

        }
    } catch (Exception ex) {
        isSuccess = false;
        message = "Exceptions" + ex;
    }
}
return message;
*/