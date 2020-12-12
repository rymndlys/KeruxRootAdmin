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
import com.kerux.utility.DBUtilityQM;

/**
 * Servlet implementation class QueuePatientServlet
 */
@WebServlet("/QueuePatientServlet")
public class QueuePatientServlet extends HttpServlet implements DBUtilityPatient {
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
                
                try{
                	
                String query=SELECT_QUEUE;

                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, request.getParameter("getdepval"));
                ps.setString(2, request.getParameter("getdocval"));
                

                ResultSet rs= ps.executeQuery();

                while (rs.next()) {

                    String qID=rs.getString(1);
                    writer.write(qID+" | ");
                    String query2=QUEUE_PATIENT;

                    PreparedStatement ps2 = con.prepareStatement(query2);
                    ps2.setString(1, request.getParameter("getpatientid"));
                    ps2.setString(2, qID);
                    ps2.setString(3, request.getParameter("getpatienttype"));
                    ps2.setString(4, "Active");
                    ps2.setString(5, request.getParameter("isPriority"));

                    ps2.executeUpdate();

                    String query3=SELECT_NEW_INSTANCE; //GETS THE INSTANCE ID OF THE NEW INSTANCE CREATED
                    PreparedStatement ps3 = con.prepareStatement(query3);
                    ResultSet rs1 = ps3.executeQuery();
                    while(rs1.next()){
                        String instanceid=rs1.getString(1);//THIS IS THE INSTANCE ID
                        //WE NEED TO PUT THIS IN A SESSION SO THAT WE CAN VIEW OUR QUEUENUMBER IN THE OTHER PAGE
                        writer.write(instanceid+"\n");
                        String query4=SELECT_COUNT_QUEUELIST;//COUNTS THE NUMBER OF PEOPLE ON THE QUEUE SO WE CAN ASSIGN A QUEUE NUMBER
                        PreparedStatement ps4=con.prepareStatement(query4);
                        ps4.setString(1,qID);
                        ResultSet rs2=ps4.executeQuery();
                        while(rs2.next()){
                            int count=rs2.getInt(1);
                            int queuenumber=count+1; //THIS IS THE QUEUE NUMBER
                            String query5=INSERT_QUEUE_LIST;
                            PreparedStatement ps5=con.prepareStatement(query5);
                            ps5.setString(1, qID);
                            ps5.setString(2, instanceid);
                            ps5.executeUpdate(); //WE INSERT DATA IN THE QUEUELIST

                            String query6=UPDATE_QUEUE_NUMBER;
                            PreparedStatement ps6=con.prepareStatement(query6);
                            ps6.setInt(1, queuenumber);
                            ps6.setString(2, instanceid);
                            ps6.executeUpdate(); //UPDATE INSTANCE SO IT HAS QUEUENUMBER

                        }


                    }
                }

                }
                catch(Exception e){
                	 System.out.println(e.getMessage());
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
 String query=SELECT_QUEUE;

                        PreparedStatement ps = con.prepareStatement(query);
                        ps.setString(1, getDeptValue);
                        ps.setString(2, getDoctorValue.trim());
                        session.setchosendept(getDeptValue);
                        session.setchosendoc(getDoctorValue.trim());


                        ResultSet rs= ps.executeQuery();
                        boolean once=true;
                        while (rs.next()&&once) {
                            once=false;
                            String qID=rs.getString(1);
                            session.setqueueid(qID);
                            String query2=QUEUE_PATIENT;

                            PreparedStatement ps2 = con.prepareStatement(query2);
                            ps2.setString(1, session.getpatientid());
                            ps2.setString(2, qID);
                            ps2.setString(3, session.getpatienttype());
                            ps2.setString(4, "InLine");
                            ps2.setString(5, isPriority);

                            ps2.execute();

                            String query3=SELECT_NEW_INSTANCE; //GETS THE INSTANCE ID OF THE NEW INSTANCE CREATED
                            PreparedStatement ps3 = con.prepareStatement(query3);
                            ResultSet rs1 = ps3.executeQuery();
                            while(rs1.next()){
                                String instanceid=rs1.getString(1);//THIS IS THE INSTANCE ID
                                //WE NEED TO PUT THIS IN A SESSION SO THAT WE CAN VIEW OUR QUEUENUMBER IN THE OTHER PAGE
                                session.setinstanceid(instanceid);
                                String query4=SELECT_COUNT_QUEUELIST;//COUNTS THE NUMBER OF PEOPLE ON THE QUEUE SO WE CAN ASSIGN A QUEUE NUMBER
                                PreparedStatement ps4=con.prepareStatement(query4);
                                ps4.setString(1,qID);
                                ResultSet rs2=ps4.executeQuery();
                                while(rs2.next()){
                                    int count=rs2.getInt(1);
                                    int queuenumber=count+1; //THIS IS THE QUEUE NUMBER
                                    String query5=INSERT_QUEUE_LIST;
                                    PreparedStatement ps5=con.prepareStatement(query5);
                                    ps5.setString(1, qID);
                                    ps5.setString(2, instanceid);
                                    ps5.setInt(3, queuenumber);
                                    ps5.executeQuery(); //WE INSERT DATA IN THE QUEUELIST

                                    String query6=UPDATE_QUEUE_NUMBER;
                                    PreparedStatement ps6=con.prepareStatement(query6);
                                    ps6.setInt(1, queuenumber);
                                    ps6.setString(2, instanceid);
                                    ps6.executeUpdate(); //UPDATE INSTANCE SO IT HAS QUEUENUMBER

                                }


                            }
                        }


                        isSuccess=true;
                        z = "Queueing successfull";
*/