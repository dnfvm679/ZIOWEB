package com.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UsersDAO;
import com.dto.usersVO;

public class AddUserAction implements Action{
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		usersVO user = new usersVO();
		user.setId(request.getParameter("userid"));
		user.setName(request.getParameter("userName"));
		user.setEmail(request.getParameter("email"));
		user.setPosition(request.getParameter("position"));
		UsersDAO usersdao = new UsersDAO();
		if(usersdao.addUser(user)) {
			String url = "/ZIOWEB/Factory?cmd=useradmin&page=1";
			response.sendRedirect(url);
		}else {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('User Add Failed');");
			out.println("history.back();");
			out.println("</script>");
		}
	}
}
