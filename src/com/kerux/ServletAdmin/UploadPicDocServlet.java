package com.kerux.ServletAdmin;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import com.kerux.security.Security;
import com.kerux.utility.DBUtilityAdmin;

/**
 * Servlet implementation class UploadPicDocServlet
 */
@WebServlet("/UploadPicDocServlet")
@MultipartConfig
public class UploadPicDocServlet extends HttpServlet  implements DBUtilityAdmin {
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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

//	        InputStream inputStream = null; // input stream of the upload file
//	        // obtains the upload file part in this multipart request
//	        Part filePart = request.getPart("photo");
//	        if (filePart != null) {
//	            // prints out some information for debugging
//	            System.out.println(filePart.getName());
//	            System.out.println(filePart.getSize());
//	            System.out.println(filePart.getContentType());
//
//	            //obtains input stream of the upload file
//	            //the InputStream will point to a stream that contains
//	            //the contents of the file
//	            inputStream = filePart.getInputStream();
//	        }
//
//	        Connection conn = null; // connection to the database
//	        String message = null; // message will be sent back to client
//	        try {
//	            // connects to the database
//	            conn = getConnection();
//	            // constructs SQL statement
//	            String sql = "INSERT INTO contacts (first_name, last_name, photo) values (?, ?, ?)";
//	            //Using a PreparedStatement to save the file
//	            PreparedStatement pstmtSave = conn.prepareStatement(sql);
//	            pstmtSave.setString(1, firstName);
//	            pstmtSave.setString(2, lastName);
//
//	            if (inputStream != null) {
//	                //files are treated as BLOB objects in database
//	                //here we're letting the JDBC driver
//	                //create a blob object based on the
//	                //input stream that contains the data of the file
//	                pstmtSave.setBlob(3, inputStream);
//	            }
//	            //sends the statement to the database server
//	            int row = pstmtSave.executeUpdate();
//	            if (row > 0) {
//	                message = "File uploaded and saved into database";
//	            }
//
//	            String filepath = "D:/Dev/JavaWorkSpaceNew/FileUploadDatabase/WebContent/FromDb.jpg";
//	            //Obtaining the file from database
//	            //Using a second statement
//	            String sql1 = "SELECT photo FROM contacts WHERE first_name=? AND last_name=?";
//	            PreparedStatement pstmtSelect = conn.prepareStatement(sql1);
//	            pstmtSelect.setString(1, firstName);
//	            pstmtSelect.setString(2, lastName);
//	            ResultSet result = pstmtSelect.executeQuery();
//	            if (result.next()) {
//	                Blob blob = result.getBlob("photo");
//	                InputStream inputStream1 = blob.getBinaryStream();
//	                OutputStream outputStream = new FileOutputStream(filepath);
//	                int bytesRead = -1;
//	                byte[] buffer = new byte[BUFFER_SIZE];
//	                while ((bytesRead = inputStream1.read(buffer)) != -1) {
//	                    outputStream.write(buffer, 0, bytesRead);
//	                }
//	                inputStream1.close();
//	                outputStream.close();
//	                System.out.println("File saved");
//	            }
//	        } catch (SQLException ex) {
//	            message = "ERROR: " + ex.getMessage();
//	            ex.printStackTrace();
//	        }
	}
	
	public static long copy(InputStream input, OutputStream output) throws IOException {
	    byte[] buffer = new byte[4096];

	    long count = 0L;
	    int n = 0;

	    while (-1 != (n = input.read(buffer))) {
	        output.write(buffer, 0, n);
	        count += n;
	    }
	    return count;
	}

}
