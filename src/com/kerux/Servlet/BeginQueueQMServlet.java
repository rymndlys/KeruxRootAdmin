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
import com.kerux.utility.DateUtility;

/**
 * Servlet implementation class BeginQueueQMServlet
 */
@WebServlet("/BeginQueueQMServlet")
public class BeginQueueQMServlet extends HttpServlet implements DBUtilityQM {
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
        		
//        		String sql = "CREATE EVENT "+ produktnamn +" ON SCHEDULE AT '"+ slutdatum +"' DO UPDATE Auktion SET AvslutadAuktion = 1 WHERE produktnamn = "+ produktnamn;
//
//        	    System.out.println("Skapar event");
//
//
//        	    PreparedStatement stm = conn.prepareStatement("CREATE EVENT ? ON SCHEDULE AT ? DO UPDATE Auktion SET AvslutadAuktion = 1 WHERE produktnamn = ?",
//        	            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//        	    stm.setString(1, produktnamn);
//        	    stm.setString(2, slutdatum);
//        	    stm.setString(3, produktnamn);
//
//        	    PreparedStatement stm2 = conn.prepareStatement(sql,
//        	            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//
//        	    PreparedStatement stm3 = conn.prepareStatement("CREATE EVENT TestProdukt ON SCHEDULE AT '2015-02-15 15:15:00' DO UPDATE Auktion SET AvslutadAuktion = 1 WHERE produktnamn = TestProdukt",
//        	            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//
//
//        	    stm.execute();
//        	    System.out.println("Skapat Skiten");
//
//        	    stm.close();
//        		
//        		CREATE EVENT queuename+Begin
//      		  ON SCHEDULE
//      		    EVERY 1 DAY
//      		    STARTS (TIMESTAMP(CURRENT_DATE) + INTERVAL 1 DAY + INTERVAL x HOUR_MINUTE)
//      		  DO
//      		  Update queue set EndTime = NULL, Status='Active' WHERE queue_id=?
        		

                
                Connection con=getConnection();
                
                String queueid="";
                String schedule1="";
                String schedule2="";
                String scheduleBegin="";
                String scheduleEnd="";
                String query2 = INSERT_INTO_QUEUE;
                String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
                String mCurrentTime = DateUtility.getDateTimeFromTimeStamp(System.currentTimeMillis(), DATE_FORMAT);


                PreparedStatement ps1 = con.prepareStatement(query2);
                ps1.setString(1, request.getParameter("queuename"));
                ps1.setString(2, mCurrentTime);
                ps1.setString(3, request.getParameter("docid"));
                ps1.setString(4, request.getParameter("depid"));

                int i = ps1.executeUpdate();
                response.setStatus(HttpServletResponse.SC_OK);
                OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
                if (i == 1) {
                    String query3 = GET_NEW_QUEUE_ID;

                    PreparedStatement ps2 = con.prepareStatement(query3);

                    ResultSet rs1 = ps2.executeQuery();
                    
                    while (rs1.next()) {
                    	writer.write(rs1.getString(1)+"\n");
                        queueid=rs1.getString(1);
                    }
                    String query4 = INSERT_INTO_QUEUE_CONNECTOR;

                    PreparedStatement ps3 = con.prepareStatement(query4);
                    ps3.setString(1, queueid);
                    ps3.setString(2, request.getParameter("qmid"));
                    int x=ps3.executeUpdate();
                }
                
                
        		PreparedStatement psSc = con.prepareStatement(GET_SCHEDULE);
        		psSc.setString(1, request.getParameter("docid"));
        		ResultSet rsSc = psSc.executeQuery();
        		while(rsSc.next()){
        			schedule1 = rsSc.getString(1);
        			schedule2 = rsSc.getString(2);
        			System.out.println(schedule1);
        			scheduleBegin = schedule1.substring(0, schedule1.length() - 3);
        			scheduleEnd = schedule2.substring(0, schedule2.length() - 3);
        			System.out.println(scheduleBegin);
        		}
        		String eventname=request.getParameter("queuename").replaceAll("\\s","");
        	    PreparedStatement stm = con.prepareStatement("CREATE EVENT "+eventname+"Begin ON SCHEDULE EVERY 1 DAY STARTS (TIMESTAMP(CURRENT_DATE) + INTERVAL 1 DAY + INTERVAL '"+scheduleBegin+"' HOUR_MINUTE) DO Update queue set EndTime = NULL, Status='Active' WHERE queue_id=?");
			    stm.setString(1, queueid);
			    stm.execute();

        		    
			    PreparedStatement st = con.prepareStatement("CREATE EVENT "+eventname+"End ON SCHEDULE EVERY 1 DAY STARTS (TIMESTAMP(CURRENT_DATE) + INTERVAL 1 DAY + INTERVAL '"+scheduleEnd+"' HOUR_MINUTE) DO Update queue set EndTime = CURRENT_TIMESTAMP, Status='Closed' WHERE queue_id=?");
			    st.setString(1, queueid);
			    st.execute();
                
                
                
                
                
                
                
                
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
