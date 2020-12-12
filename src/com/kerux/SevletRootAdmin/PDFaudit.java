package com.kerux.SevletRootAdmin;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.kerux.bean.AuditBean;
import com.kerux.security.Security;
import com.kerux.utility.DBUtility;

/**
 * Servlet implementation class PDFaudit
 */
@WebServlet("/PDFaudit")
public class PDFaudit extends HttpServlet implements DBUtility {

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
        throws ServletException, IOException {

    response.setContentType("application/pdf");
    //
    
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	Date date = new Date();
	String timeStamp=formatter.format(date);
    
    
    
    //
  
    OutputStream out=response.getOutputStream();
    try {
        Document document = new Document();
        /* Basic PDF Creation inside servlet */
        PdfWriter.getInstance(document, out);
        document.open();
        document.add(new Paragraph("KERUX AUDIT LOG REPORT"));
        document.add(new Paragraph(timeStamp));
        document.add(new Paragraph("Report generated by: Roy Christian Yabut"));
        document.add(new Paragraph("**********"));
        PdfPTable table = new PdfPTable(8); 
        table.setWidthPercentage(100);
        PdfPCell cell9 = new PdfPCell(new Paragraph("Log ID"));
        PdfPCell cell10 = new PdfPCell(new Paragraph("Table Name"));
        PdfPCell cell11 = new PdfPCell(new Paragraph("Event"));
        PdfPCell cell12 = new PdfPCell(new Paragraph("SqlCommand"));
        PdfPCell cell13 = new PdfPCell(new Paragraph("Old Data"));
        PdfPCell cell14 = new PdfPCell(new Paragraph("New Data"));
        PdfPCell cell15= new PdfPCell(new Paragraph("Login Name"));
        PdfPCell cell16 = new PdfPCell(new Paragraph("Time Stamp"));

        table.addCell(cell9);
        table.addCell(cell10);
        table.addCell(cell11);
        table.addCell(cell12);
        table.addCell(cell13);
        table.addCell(cell14);
        table.addCell(cell15);
        table.addCell(cell16);


        try{
			Connection connection = getConnection();
			Security sec = new Security();
			PreparedStatement ps=connection.prepareStatement(SELECT_AUDIT);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				
				PdfPCell cell1 = new PdfPCell(new Paragraph(rs.getString("Log_ID")));
				PdfPCell cell2 = new PdfPCell(new Paragraph(sec.decrypt(rs.getString("TableName"))));
				PdfPCell cell3 = new PdfPCell(new Paragraph(sec.decrypt(rs.getString("Event"))));
				PdfPCell cell4 = new PdfPCell(new Paragraph(sec.decrypt(rs.getString("SqlCommand"))));
				PdfPCell cell5 = new PdfPCell(new Paragraph(sec.decrypt(rs.getString("OldData"))));
				PdfPCell cell6 = new PdfPCell(new Paragraph(sec.decrypt(rs.getString("NewData"))));
				PdfPCell cell7 = new PdfPCell(new Paragraph(sec.decrypt(rs.getString("LoginName"))));
				PdfPCell cell8 = new PdfPCell(new Paragraph(rs.getString("TimeStamp")));
				table.addCell(cell1);
		        table.addCell(cell2);
		        table.addCell(cell3);
		        table.addCell(cell4);
		        table.addCell(cell5);
		        table.addCell(cell6);
		        table.addCell(cell7);
		        table.addCell(cell8);
				
			}
			
			

		}catch(Exception e){e.printStackTrace();}
        
        
        

       

        document.add(table);
        
        document.close();
    }
            catch (DocumentException exc){
            throw new IOException(exc.getMessage());
            }
    finally {            
        out.close();
    }
}
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    processRequest(request, response);
}
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    processRequest(request, response);
}

@Override
public String getServletInfo() {
    return "This Servlet Generates PDF Using iText Library";
}





}