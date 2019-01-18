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
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.revature.project_1.Services.SubmitNewRequestService;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

@WebServlet
@MultipartConfig
public class SubmitNewRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			List<FileItem> multifiles = sf.parseRequest(request);
			for(FileItem item : multifiles) {
				item.write(new File("C:\\Users\\Carlos\\Documents\\GitHub\\project-one-CarlosJPortillo\\project_1\\src\\main\\webapp\\receipt_images\\" + item.getName()));
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		String fileName = request.getParameter("fileName");
		String reimbursementType = request.getParameter("reimbursementType");
		
		int employeeID = (Integer) session.getAttribute("employeeID");
		String username = (String) session.getAttribute("employeeUserName");
		SubmitNewRequestService sNRS = new SubmitNewRequestService();
		if(sNRS.createRequest(fileName, reimbursementType, employeeID)) {
			out.write("Request has been submitted!");
		}
	}
	
}
