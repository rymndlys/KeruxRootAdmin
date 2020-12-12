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

/**
 * Servlet implementation class GeneratePDFDataAdminServlet
 */
@WebServlet("/GeneratePDFDataAdminServlet")
public class GeneratePDFDataAdminServlet extends HttpServlet implements DBUtilityAdmin {
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
                

				
				
				response.setStatus(HttpServletResponse.SC_OK);
                OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
				
				
				String querySERVED=QUEUES_SERVED;
				
				PreparedStatement ps2 = con.prepareStatement(querySERVED);
				
				ResultSet rs2=ps2.executeQuery();
				
				while (rs2.next())
				{
				    writer.write("Queues Served: \n");
				    writer.write(rs2.getString(1)+"\n");
				    writer.write(rs2.getString(2)+"\n");
				}
				
				String queryCANCELLED=QUEUES_CANCELLED;
				
				PreparedStatement ps3 = con.prepareStatement(queryCANCELLED);
				
				ResultSet rs3=ps3.executeQuery();
				
				while (rs3.next())
				{
				    writer.write("Queues Cancelled: \n");
				    writer.write(rs3.getString(1)+"\n");
				    writer.write(rs3.getString(2)+"\n");
				}
				
				String doctorQueues=HIGHEST_DOC_QUEUES;
				
				PreparedStatement ps4 = con.prepareStatement(doctorQueues);
				
				ResultSet rs4=ps4.executeQuery();
				
				while (rs4.next())
				{
					writer.write("Highest Doctor Queues: \n");
				    writer.write(rs4.getString(1)+"\n");
				    writer.write(rs4.getString(2)+"\n");
				}

				String deptQueues=HIGHEST_DEPT_QUEUES;
				
				PreparedStatement ps5 = con.prepareStatement(deptQueues);
				
				ResultSet rs5=ps5.executeQuery();
				
				while (rs5.next())
				{
					writer.write("Highest Department Queues: \n");
				    writer.write(rs5.getString(1)+"\n");
				    writer.write(rs5.getString(2)+"\n");
				}

              
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