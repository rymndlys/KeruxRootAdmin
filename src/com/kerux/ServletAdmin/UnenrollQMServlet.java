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
 * Servlet implementation class UnenrollQMServlet
 */
@WebServlet("/UnenrollQMServlet")
public class UnenrollQMServlet extends HttpServlet implements DBUtilityAdmin {
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
        		
           
                String endwrite="";
                Connection con=getConnection();



                
                String query = UNENROLL_QM;
                String query1 = UNENROLL_QM_REASON;
                
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, request.getParameter("firstName"));
                
                
                PreparedStatement ps1 = con.prepareStatement(query1);
                ps1.setString(1, request.getParameter("reason"));
                ps1.setString(2, request.getParameter("firstName"));
                
                ps.executeUpdate();
                ps1.executeUpdate();

                response.setStatus(HttpServletResponse.SC_OK);
                OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
                

                endwrite="Unenrolled";
                String email = request.getParameter("email").trim();
                String subject = "Kerux Queue Manager Unenrollment";
                String message = "Good day!\n" +
                        "This is to inform you that you have been unenrolled \nas a Queue Manager in the Kerux Mobile Queueing System,\n\n\n" +
                        "Thank you for being part of Kerux, We hope to see you again!";
                Mailer.send("roychristian.yabut@benilde.edu.ph","33CYzrpj",email,subject,message);
       
               
                writer.write(endwrite);

                
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

        String query = UNENROLL_QM_REASON;
        PreparedStatement ps1 = null;

        try {
            ps = con.prepareStatement(UNENROLL_QM);
            ps.setString(1, firstName);

            ps1 = con.prepareStatement(query);
            ps1.setString(1, reason);
            ps1.setString(2, firstName);

            ps.executeUpdate();
            ps1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
*/