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
 * Servlet implementation class CheckQMListServlet
 */
@WebServlet("/CheckQMListServlet")
public class CheckQMListServlet extends HttpServlet implements DBUtilityAdmin {
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



                
                String query = SELECT_UNENROLLED_QM;

                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, request.getParameter("qmStatus"));
                ps.setString(2, request.getParameter("clinicid"));
                
            
                ResultSet rs=ps.executeQuery();


                // DataOutputStream dout=new DataOutputStream();
                 response.setStatus(HttpServletResponse.SC_OK);
                 OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
                 if(rs.next())
                 {
                 	writer.write("true");
                     
                 }
                 else{
                	 writer.write("false");
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
/*
 if(con != null){ //means that we have a valid db connection
            try{//inserting records; called INSERT_REC from DBUtility.java
                // use of parameterized query such as PreparedStatement prevents SQL injection which is considered a way to
                //prevent threat in any web app
                String query = SELECT_UNENROLLED_QM;
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, qmStatus);

                ResultSet rs=ps.executeQuery();
                if(rs.next()){
                    Log.d("WENT HERE", "DIDNT GO IN");
                }
                else{
                    Log.d("WENT HERE", "WENT IN");
                    allInactiveRec=true;

                }
            } catch(SQLException sqle){
                System.err.println(sqle.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
 */
