package com.action.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.dao.UsersDAO;
import com.dto.UserVO;

public class AddUserAction implements Action{
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");

		UserVO user = new UserVO();
		user.setCompany_id(request.getParameter("company_id"));
		user.setId(request.getParameter("id"));
		user.setName(request.getParameter("name"));
		user.setPosition(request.getParameter("position"));
		user.setTeam(request.getParameter("team"));
		user.setTel(request.getParameter("tel"));
		user.setPhone(request.getParameter("phone"));
		user.setEmail(request.getParameter("email"));
		user.setAddress(request.getParameter("address"));
		System.out.println(request.getParameter("name"));
		
		UsersDAO usersdao = new UsersDAO();
		if(usersdao.addUser(user)) {
			String url = "/ZIOWEB/Factory?cmd=userManagement&page=1";
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
