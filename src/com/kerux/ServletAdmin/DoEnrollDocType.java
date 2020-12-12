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
 * Servlet implementation class DoEnrollDocType
 */
@WebServlet("/DoEnrollDocType")
public class DoEnrollDocType extends HttpServlet implements DBUtilityAdmin {
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
                String query = VALIDATION_DOC_TYPE;
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, request.getParameter("enrollDoctorType"));
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
                        
                        String query1 = INSERT_DOC_TYPE_ENROLLMENT;
                        PreparedStatement ps1 = con.prepareStatement(query1);
                        ps1.setString(1, request.getParameter("enrollDoctorType"));

                        ps1.executeUpdate();

                        endwrite="ADDED SUCCESSFULLY!";

                        

                    
                    } catch (Exception ex) {

                        endwrite = "Exceptions" + ex;
                    }
                }
               
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

            try {
                ps = con.prepareStatement(VALIDATION_DOC_TYPE);
                ps.setString(1, enrollDoctorType);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    hasRecord=true;

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            message = "Record already exists";

            if (enrollDoctorType.trim().equals("")) {
                message = "Please enter all fields";
            }
            else if (hasRecord){
                message = "Record already exists";
            }
            else {
                try {
                    if (con == null) {
                        message = "CANNOT ADD RECORD";

                    } else {
                        //inserting data of department to the database
                        String query = INSERT_DOC_TYPE_ENROLLMENT;
                        PreparedStatement ps1 = null;
                        try {
                            ps1 = con.prepareStatement(query);
                            ps1.setString(1, enrollDoctorType);
                            ps1.executeUpdate();
                            message = "Added Successfully!";
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        con.close();
                    }
                }
                catch (Exception ex)
                {
                    isSuccess = false;
                    message = "Exceptions"+ex;
                    Log.d("ex", ex.getMessage () + " Jheca");
                }
            }
            return message;
*/