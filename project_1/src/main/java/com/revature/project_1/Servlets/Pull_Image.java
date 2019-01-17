package com.revature.project_1.Servlets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Pull_Image extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileName = request.getParameter("filename");
		String pathToFile = "http://localhost:8080/project_1/receipt_images/" + fileName;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print(pathToFile);
		

		
	}

}
