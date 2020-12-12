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
 * Servlet implementation class DoEnrollDepartmentServlet
 */
@WebServlet("/DoEnrollDepartmentServlet")
public class DoEnrollDepartmentServlet extends HttpServlet implements DBUtilityAdmin {
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
                String query = VALIDATION_DEPT;
                String newdepid=null;
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, request.getParameter("depName"));
                ps.setString(2, request.getParameter("getclinicid"));
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
                        
                        String query1 = INSERT_DEPT;
                        PreparedStatement ps1 = con.prepareStatement(query1);
                        ps1.setString(1, request.getParameter("getclinicid"));
                        ps1.setString(2, request.getParameter("reason"));
                        ps1.setString(3, request.getParameter("depName"));
                        ps1.setString(4, request.getParameter("Status"));

                        ps1.executeUpdate();

                     

                        String query2 = SELECT_NEW_DEPARTMENT_ID;
                        PreparedStatement ps2 = con.prepareStatement(query2);
                        ResultSet rs1 = ps2.executeQuery();
                        while (rs1.next()) {
                            newdepid = rs1.getString(1);
                            

                            //inserting to qm enrollment
                            String query3 = INSERT_DEPT_ENROLLMENT;
                            PreparedStatement ps3 = con.prepareStatement(query3);
                            ps3.setString(1, request.getParameter("getadminid"));
                            ps3.setString(2, newdepid);
                            ps3.setString(3, request.getParameter("getclinicid"));
                            ps3.executeUpdate();

                            endwrite = "ADDED SUCCESSFULLY!";
                        }
                       

                    
                    } catch (Exception ex) {

                        endwrite = "Exceptions" + ex;
                    }
                }
               
                writer.write(endwrite);
                writer.write(" | "+newdepid);

                
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
