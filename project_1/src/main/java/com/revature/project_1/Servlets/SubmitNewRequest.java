package com.revature.project_1.Servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

@WebServlet
@MultipartConfig
public class SubmitNewRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//	String requestType = request.getParameter("requestType");
		//	request.removeAttribute("requestType");
		ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			List<FileItem> multifiles = sf.parseRequest(request);
			for(FileItem item : multifiles) {
				System.out.println(item.getName());
				item.write(new File("/Users/Carlos/Documents/GitHub/project-one-CarlosJPortillo/project_1/receipt_images/" + item.getName()));
				out.write(item.getName());		
			}
		} 
		catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.write("Failed Upload");
		}
		catch(Exception e) {
			e.printStackTrace();
			out.write("Failed Upload");
		}
		
		
	}

}
