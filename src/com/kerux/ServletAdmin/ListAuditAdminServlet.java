package com.kerux.ServletAdmin;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.kerux.security.Security;
import com.kerux.utility.DBUtilityAdmin;

/**
 * Servlet implementation class ListAuditAdminServlet
 */
@WebServlet("/ListAuditAdminServlet")
public class ListAuditAdminServlet extends HttpServlet implements DBUtilityAdmin {
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
        		Security sec = new Security();
        		 Connection con=getConnection();
                 response.setStatus(HttpServletResponse.SC_OK);
                 OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
                 
                 Statement st = con.createStatement();
                 ResultSet rset = st.executeQuery(SELECT_AUDIT);
                 ResultSetMetaData rsmd = rset.getMetaData();

                 List<Map<String, String>> data = null;
                 data = new ArrayList<Map<String, String>> ();

                 while (rset.next()) {
                     HashMap<String, String> datanum = new HashMap<String, String>();
                     datanum.put("first", sec.decrypt(rset.getString(1).toString()));
                     datanum.put("second", sec.decrypt(rset.getString(2).toString()));
                     datanum.put("third", rset.getString(3).toString());
                     data.add(datanum);
                 }

                 Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create(); 
                 String jsons=gson.toJson(data );
                 System.out.print(jsons);
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

}/*
  String result = "Database Connection Successful\n";
                Statement st = con.createStatement();
                ResultSet rset = st.executeQuery(SELECT_AUDIT);
                ResultSetMetaData rsmd = rset.getMetaData();

                List<Map<String, String>> data = null;
                data = new ArrayList<Map<String, String>> ();

                while (rset.next()) {
                    HashMap<String, String> datanum = new HashMap<String, String>();
                    datanum.put("first", rset.getString(1).toString());
                    datanum.put("second", rset.getString(2).toString());
                    datanum.put("third", rset.getString(3).toString());
                    datanum.put("fourth", rset.getString(4).toString());
                    datanum.put("fifth", rset.getString(5).toString());
                    datanum.put("sixth", rset.getString(6).toString());
                    datanum.put("seventh", rset.getString(7).toString());
                    datanum.put("eight", rset.getString(8).toString());
                    data.add(datanum);
                }

                listAdapter = new SimpleAdapter (ViewAuditReportsActivity.this, data,
                        R.layout.listview_row_audit, new String[] {"first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eight"},
                        new int[] {R.id.FIRST_COL, R.id.SECOND_COL, R.id.THIRD_COL, R.id.FOURTH_COL, R.id.FIFTH_COL, R.id.SIXTH_COL,
                                    R.id.SEVENTH_COL, R.id.EIGHT_COL});


                while (rset.next()) {
                    result += rset.getString(1).toString() + "\n";
                }
                message = result;
                
*/