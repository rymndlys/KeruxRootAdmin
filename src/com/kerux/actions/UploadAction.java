package com.kerux.actions;


import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.kerux.controllers.MainControllerDB;
import com.opensymphony.xwork2.ActionSupport;

public class UploadAction extends ActionSupport {
   private File file;
   private String contentType;
   private String filename;

   private static final String UPLOAD_DIRECTORY = "upload";
   private static final int THRESHOLD_SIZE     = 1024 * 1024 * 3;  // 3MB
   private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
   private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
   
   public void setUpload(File file) {
      this.file = file;
   }

   public void setUploadContentType(String contentType) {
      this.contentType = contentType;
   }

   public void setUploadFileName(String filename) {
      this.filename = filename;
   }

   public String execute() {
	   

       return "success";

       
      
   }
   
	public void validate(){
		if(file.length()>2097152){
			addActionError("File size too large");
		}
		else{
		
			// constructs the directory path to store upload file
		       ServletContext context = ServletActionContext.getServletContext();
		       String uploadPath = context.getRealPath("")
		           + File.separator + UPLOAD_DIRECTORY;
		       // creates the directory if it does not exist
		       
		    // configures upload settings
		        DiskFileItemFactory factory = new DiskFileItemFactory();
		        factory.setSizeThreshold(THRESHOLD_SIZE);
		        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		         
		        ServletFileUpload upload = new ServletFileUpload(factory);
		        upload.setFileSizeMax(MAX_FILE_SIZE);
		        upload.setSizeMax(MAX_REQUEST_SIZE);
		         
		       
		       String succ="File successfuly uploaded";
		       
		       try {
		    	   
		          System.out.println("Src File name: " + file);
		          System.out.println("Dst File name: " + filename);
		      	    	 
		          File destFile  = new File(uploadPath, filename);
		         
		          FileUtils.copyFile(file, destFile);

		       } catch(IOException e) {
		    	   succ="Error in file upload";
		    	   
		          e.printStackTrace();
		          
		       } catch (Exception ex){
		    	   succ="error in file upload";
		    	   
		    	   ex.printStackTrace();
		       }

		    	   addActionMessage(succ);
		}
		

	}
	
}