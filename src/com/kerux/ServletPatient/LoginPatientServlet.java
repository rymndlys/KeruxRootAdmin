package com.kerux.ServletPatient;

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
import com.kerux.utility.DBUtilityPatient;

/**
 * Servlet implementation class LoginPatientServlet
 */
@WebServlet("/LoginPatientServlet")
public class LoginPatientServlet extends HttpServlet implements DBUtilityPatient {
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
                String query=LOGIN_PATIENT;

                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, request.getParameter("username"));
                ps.setString(2, request.getParameter("password"));
                // stmt.executeUpdate(query);


                ResultSet rs=ps.executeQuery();


               // DataOutputStream dout=new DataOutputStream();
                response.setStatus(HttpServletResponse.SC_OK);
                OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
                while(rs.next())
                {
                	writer.write(rs.getString(1)+"\n");
                	writer.write(rs.getString(2)+"\n");
                	writer.write(rs.getString(3)+"\n");
                	writer.write(rs.getString(4)+"\n");
                	writer.write(rs.getString(5)+"\n");
                	writer.write(rs.getString(6)+"\n");
                	writer.write(rs.getString(7)+"\n");   
                	System.out.print(rs.getString(1)+"\n");
                	System.out.print(rs.getString(2)+"\n");
                	System.out.print(rs.getString(3)+"\n");
                	System.out.print(rs.getString(4)+"\n");
                	System.out.print(rs.getString(5)+"\n");
                	System.out.print(rs.getString(6)+"\n");
                	System.out.print(rs.getString(7)+"\n");   
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
