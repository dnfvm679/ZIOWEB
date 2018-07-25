package com.action.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.dao.UsersDAO;
import com.dto.UserVO;

public class UpdateUserAction implements Action {
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "Factory?cmd=viewUser&userid=" + request.getParameter("userid");
		UsersDAO usersdao = new UsersDAO();
		UserVO user = new UserVO();
		user.setId(request.getParameter("userid"));
		user.setName(request.getParameter("userName"));
		user.setEmail(request.getParameter("email"));
		user.setPosition(request.getParameter("position"));
		if (usersdao.updateUser(user)) {
			response.sendRedirect(url);
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('User update failed');");
			out.println("history.back();");
			out.println("</script>");
		}
	}
}
