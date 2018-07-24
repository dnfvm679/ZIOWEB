package com.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UsersDAO;

public class LoginAction implements Action {
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String userpw = request.getParameter("userpw");
		String url = "index.jsp";
		UsersDAO usersdao = new UsersDAO();
		boolean result = usersdao.Login(userid, userpw);
		if (result) {
			request.getSession().setAttribute("userid", userid);
			response.sendRedirect(url);
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('Login Failed');");
			out.println("history.back();");
			out.println("</script>");
		}
	}
}
