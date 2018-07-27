package com.action.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.dao.UsersDAO;

public class LoginAction implements Action {
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String userpw = request.getParameter("userpw");
		String url = "index.jsp";
		UsersDAO usersdao = new UsersDAO();
		String name = usersdao.Login(userid, userpw);
		if (name != null) {
			request.getSession().setAttribute("userid", userid);
			request.getSession().setAttribute("userName", name);
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
