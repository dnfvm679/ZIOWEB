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
		String url = "Factory?cmd=viewUser&userid=" + request.getParameter("id");
		UsersDAO usersdao = new UsersDAO();
		UserVO user = new UserVO();
		user.setCompany_id(request.getParameter("company_id"));
		user.setId(request.getParameter("id"));
		user.setName(request.getParameter("name"));
		System.out.println(request.getParameter("position"));
		user.setPosition(request.getParameter("position"));
		user.setTeam(request.getParameter("team"));
		user.setTel(request.getParameter("tel"));
		user.setPhone(request.getParameter("phone"));
		user.setEmail(request.getParameter("email"));
		user.setAddress(request.getParameter("address"));
		System.out.println(request.getParameter("name"));
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
