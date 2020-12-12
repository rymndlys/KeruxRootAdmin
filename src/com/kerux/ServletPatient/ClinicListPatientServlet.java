package com.kerux.ServletPatient;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
 * Servlet implementation class ClinicListPatientServlet
 */
@WebServlet("/ClinicListPatientServlet")
public class ClinicListPatientServlet extends HttpServlet implements DBUtilityPatient {
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
                
                
                String query=SELECT_CLINIC;

                PreparedStatement ps = con.prepareStatement(query);
                // stmt.executeUpdate(query);


                ResultSet rs=ps.executeQuery();
                response.setStatus(HttpServletResponse.SC_OK);
                OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
                while (rs.next())
                {
                	writer.write(rs.getString(1)+" | "+rs.getString(2)+" | "+transformClinichours(rs.getString(3))+" | "+rs.getString(4)+" | "+rs.getString(5)+"\n");
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
    
    
    public String transformClinichours(String hours){
    	String newhours="";
    	
    	ArrayList<String> eachdays=new ArrayList<String>();
    	ArrayList<String> eachhours=new ArrayList<String>();
    	ArrayList<String> unique=new ArrayList<String>();
    	
    	String oldhours=hours;
    	Pattern pattern = Pattern.compile("(Monday:\\s\\d\\d:\\d\\d\\s[a|p]m\\s-\\s\\d\\d:\\d\\d\\s[a|p]m)");
    	Matcher matcher = pattern.matcher(hours);

    	if (matcher.find())
    	{
    	    
    	    String M=matcher.group(1);
    	    String hourM=M.replaceAll("Monday:\\s", "");
    	    eachhours.add(hourM);
    	    eachdays.add("M");
    	    boolean check=true;
    	    for (String day: unique){
    	    	if(hourM.equals(day)){
    	    		check=false;
    	    	}
    	    	
    	    }
    	    if(check){
    	    	unique.add(hourM);
    	    }
    	}

    	Pattern patternT = Pattern.compile("(Tuesday:\\s\\d\\d:\\d\\d\\s[a|p]m\\s-\\s\\d\\d:\\d\\d\\s[a|p]m)");
    	Matcher matcherT = patternT.matcher(hours);
    	
    	if (matcherT.find())
    	{
    	    
    	    String M=matcherT.group(1);
    	    String hourM=M.replaceAll("Tuesday:\\s", "");
    	    eachhours.add(hourM);
    	    eachdays.add("T");
    	    boolean check=true;
    	    for (String day: unique){
    	    	if(hourM.equals(day)){
    	    		check=false;
    	    	}
    	    	
    	    }
    	    if(check){
    	    	unique.add(hourM);
    	    }
    	}
    	
    	Pattern patternW = Pattern.compile("(Wednesday:\\s\\d\\d:\\d\\d\\s[a|p]m\\s-\\s\\d\\d:\\d\\d\\s[a|p]m)");
    	Matcher matcherW = patternW.matcher(hours);
    	
    	if (matcherW.find())
    	{
    	    
    	    String M=matcherW.group(1);
    	    String hourM=M.replaceAll("Wednesday:\\s", "");
    	    eachhours.add(hourM);
    	    eachdays.add("W");
    	    boolean check=true;
    	    for (String day: unique){
    	    	if(hourM.equals(day)){
    	    		check=false;
    	    	}
    	    	
    	    }
    	    if(check){
    	    	unique.add(hourM);
    	    }
    	}
    	
    	Pattern patternTH = Pattern.compile("(Thursday:\\s\\d\\d:\\d\\d\\s[a|p]m\\s-\\s\\d\\d:\\d\\d\\s[a|p]m)");
    	Matcher matcherTH = patternTH.matcher(hours);
    	
    	if (matcherTH.find())
    	{
    	    
    	    String M=matcherTH.group(1);
    	    String hourM=M.replaceAll("Thursday:\\s", "");
    	    eachhours.add(hourM);
    	    eachdays.add("TH");
    	    boolean check=true;
    	    for (String day: unique){
    	    	if(hourM.equals(day)){
    	    		check=false;
    	    	}
    	    	
    	    }
    	    if(check){
    	    	unique.add(hourM);
    	    }
    	}
    	
    	Pattern patternF = Pattern.compile("(Friday:\\s\\d\\d:\\d\\d\\s[a|p]m\\s-\\s\\d\\d:\\d\\d\\s[a|p]m)");
    	Matcher matcherF = patternF.matcher(hours);
    	
    	if (matcherF.find())
    	{
    	    
    	    String M=matcherF.group(1);
    	    String hourM=M.replaceAll("Friday:\\s", "");
    	    eachhours.add(hourM);
    	    eachdays.add("F");
    	    boolean check=true;
    	    for (String day: unique){
    	    	if(hourM.equals(day)){
    	    		check=false;
    	    	}
    	    	
    	    }
    	    if(check){
    	    	unique.add(hourM);
    	    }
    	}
    	
    	Pattern patternSat = Pattern.compile("(Saturday:\\s\\d\\d:\\d\\d\\s[a|p]m\\s-\\s\\d\\d:\\d\\d\\s[a|p]m)");
    	Matcher matcherSat = patternSat.matcher(hours);
    	
    	if (matcherSat.find())
    	{
    	    
    	    String M=matcherSat.group(1);
    	    String hourM=M.replaceAll("Saturday:\\s", "");
    	    eachhours.add(hourM);
    	    eachdays.add("Sat");
    	    boolean check=true;
    	    for (String day: unique){
    	    	if(hourM.equals(day)){
    	    		check=false;
    	    	}
    	    	
    	    }
    	    if(check){
    	    	unique.add(hourM);
    	    }
    	}
    	
    	Pattern patternSun = Pattern.compile("(Sunday:\\s\\d\\d:\\d\\d\\s[a|p]m\\s-\\s\\d\\d:\\d\\d\\s[a|p]m)");
    	Matcher matcherSun = patternSun.matcher(hours);
    	
    	if (matcherSun.find())
    	{
    	    
    	    String M=matcherSun.group(1);
    	    String hourM=M.replaceAll("Sunday:\\s", "");
    	    eachhours.add(hourM);
    	    eachdays.add("Sun");
    	    boolean check=true;
    	    for (String day: unique){
    	    	if(hourM.equals(day)){
    	    		check=false;
    	    	}
    	    	
    	    }
    	    if(check){
    	    	unique.add(hourM);
    	    }
    	}
    	
    	for (int i=0;i<unique.size();i++){
    		for(int y=0;y<eachhours.size();y++){
    			if(eachhours.get(y).equals(unique.get(i))){
    				newhours=newhours+eachdays.get(y);
    			}
    			
    			
    		}
    		if(i==(unique.size()-1)){
    			newhours=newhours+" - "+unique.get(i);
    		}
    		else{
    			newhours=newhours+" - "+unique.get(i)+" and";
    		}
    		
    	}
    	return newhours;
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
