package com.kerux.Servlet;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kerux.security.Security;
import com.kerux.utility.DBUtilityQM;

/**
 * Servlet implementation class MarkServedQMServlet
 */
@WebServlet("/MarkServedQMServlet")
public class MarkServedQMServlet extends HttpServlet implements DBUtilityQM {
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
        		
                
                
                Connection con=getConnection();
                
                
                String query = UPDATE_MARK_PATIENT_AS_SERVED;

                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1,  request.getParameter("instanceid"));

                String query2= UPDATE_QUEUELIST_AS_SERVED;
                PreparedStatement ps2=con.prepareStatement(query2);
                ps2.setString(1,  request.getParameter("instanceid"));
                ps2.setString(2, request.getParameter("queueid"));

                ps.executeUpdate();
                ps2.executeUpdate();
                
                
                response.setStatus(HttpServletResponse.SC_OK);
                OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
                writer.write("success");
                writer.flush();
                writer.close();

                
              
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

