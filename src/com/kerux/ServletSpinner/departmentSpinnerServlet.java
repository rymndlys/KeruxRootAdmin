package com.kerux.ServletSpinner;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kerux.Servlet.AndroidServlet;
import com.kerux.SpinnerObjects.Clinic;
import com.kerux.SpinnerObjects.Department;
import com.kerux.security.Security;
import com.kerux.utility.DBUtilitySpinner;

/**
 * Servlet implementation class departmentSpinnerServlet
 */
@WebServlet("/departmentSpinnerServlet")
public class departmentSpinnerServlet extends HttpServlet implements DBUtilitySpinner {
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
                String query=departmentSpinner;

                PreparedStatement ps = con.prepareStatement(query);


                ResultSet rs=ps.executeQuery();
                response.setStatus(HttpServletResponse.SC_OK);
                OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
             
                List<Department> departments =new ArrayList<Department>();
                
                while(rs.next())
                {
                	Department department=new Department(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5));
                	departments.add(department);
                    
                }
                
                Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create(); 
                String jsons=gson.toJson(departments );
                System.out.println(jsons);
                writer.write(jsons);
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