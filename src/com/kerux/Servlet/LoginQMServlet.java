package com.kerux.Servlet;

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

import com.kerux.security.Security;
import com.kerux.utility.DBUtilityQM;

/**
 * Servlet implementation class LoginQMServlet
 */
@WebServlet("/LoginQMServlet")
public class LoginQMServlet extends HttpServlet implements DBUtilityQM {
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
        		
                
                Security sec=new Security();
                Connection con=getConnection();
                String query=LOGIN_CRED;

                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, sec.encrypt(request.getParameter("username")));
                ps.setString(2, sec.encrypt(request.getParameter("password")));
                // stmt.executeUpdate(query);
                System.out.println(request.getParameter("username"));
                System.out.println(request.getParameter("password"));
                

                ResultSet rs=ps.executeQuery();

             


               // DataOutputStream dout=new DataOutputStream();
                response.setStatus(HttpServletResponse.SC_OK);
                OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
                while(rs.next())
                {
                	writer.write(rs.getString(1)+"\n");
                	writer.write(rs.getString(2)+"\n");
                	writer.write(rs.getString(3)+" "+rs.getString(4)+"\n");
                	writer.write(sec.decrypt(rs.getString(3))+"\n");
                	writer.write(sec.decrypt(rs.getString(4))+"\n");
                	writer.write(sec.decrypt(rs.getString(5))+"\n");
                    
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
