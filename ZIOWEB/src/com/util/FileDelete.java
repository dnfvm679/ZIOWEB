package com.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileDelete
 */
@WebServlet("/FileDelete")
public class FileDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FileDelete() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/ZIOWEB/Factory?cmd=updateRequestForm&id="+request.getParameter("id");
		String dir = request.getSession().getServletContext().getRealPath("/filestorage/"+request.getParameter("id")+"/");
		String fileName = request.getParameter("fileName");
		File file = new File(dir+fileName);
		if(file.exists()) file.delete();
		response.sendRedirect(url);
	}

}
