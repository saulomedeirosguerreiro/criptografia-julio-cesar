package saulomedeiros.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class UploadServlet
 */

@MultipartConfig
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");

		final Part filePart = request.getPart("answer");
		Scanner scan = new Scanner(filePart.getInputStream());
		StringBuilder  conteudoArquivo = new StringBuilder();
		while(scan.hasNext()) {
			conteudoArquivo.append(scan.nextLine());
		}
		scan.close();
		//System.out.println(conteudoArquivo.toString());
		
		final String FILENAME = getFileName(filePart);
		System.out.println(FILENAME);

		final PrintWriter writer = response.getWriter();
		writer.println(conteudoArquivo.toString());
		writer.close();
	}
	
	private String getFileName(final Part part) {
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}

}
