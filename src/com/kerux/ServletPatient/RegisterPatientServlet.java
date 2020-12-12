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
import com.kerux.utility.DBUtilityPatient;
import com.kerux.utility.Mailer;

/**
 * Servlet implementation class RegisterPatientServlet
 */
@WebServlet("/RegisterPatientServlet")
public class RegisterPatientServlet extends HttpServlet implements DBUtilityPatient {
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
                String query = REGISTER_PATIENT;
                String query1 = CHECK_PATIENT;

                PreparedStatement ps1 = con.prepareStatement(query1);
                ps1.setString(1, request.getParameter("pContact"));
                ps1.setString(2, request.getParameter("pEmail"));

                response.setStatus(HttpServletResponse.SC_OK);
                OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
                ResultSet rs = ps1.executeQuery();

                if(rs.next()) {




                    writer.write("Account already exists.");

                }
                else {
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, request.getParameter("pEmail"));
                    ps.setString(2, request.getParameter("pPass"));
                    ps.setString(3, request.getParameter("pType"));
                    ps.setString(4, request.getParameter("fName"));
                    ps.setString(5, request.getParameter("lName"));
                    ps.setString(6, request.getParameter("pContact"));

                    ps.executeUpdate();

                    writer.write("Registration successfull");
                    String email = sec.decrypt(request.getParameter("pEmail").trim());
                    String subject = "Kerux Registration";
                    String message = "Welcome to the Kerux Family!\n\n" +
                            "We're happy that you're here with us. You \ncan login to your account by using your \nregistered email and your registered \npassword.\n\n\n" +
                            "See you soon!\n\n\n" +
                            "Yours,\nThe Kerux Family";
                    Mailer.send("roychristian.yabut@benilde.edu.ph","33CYzrpj",email,subject,message);
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


                    Connection con = connectionClass.CONN();
                    if (con == null) {
                        z = "Please check your internet connection";
                    } else {

                        String query = REGISTER_PATIENT;



                        String query1 = CHECK_PATIENT;

                        PreparedStatement ps1 = con.prepareStatement(query1);
                        ps1.setString(1, pContact);
                        ps1.setString(2, pEmail);

                        ResultSet rs = ps1.executeQuery();

                            if(rs.next()) {

   
                                }



                                z = "Account already exists.";

                            }
                            else {
                                PreparedStatement ps = con.prepareStatement(query);
                                ps.setString(1, pEmail);
                                ps.setString(2, pPass);
                                ps.setString(3, pType);
                                ps.setString(4, fName);
                                ps.setString(5, lName);
                                ps.setString(6, pContact);

                                ps.executeUpdate();

                                isSuccess = true;
                                z = "Registration successfull";
                            }


                        }
*/