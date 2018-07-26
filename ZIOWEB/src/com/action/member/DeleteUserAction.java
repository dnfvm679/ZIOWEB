package com.action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.dao.UsersDAO;

public class DeleteUserAction implements Action{
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "Factory?cmd=userManagement&page=1";
		UsersDAO usersdao = new UsersDAO();
		String userid = request.getParameter("userid");
		usersdao.deleteUser(userid);
		response.sendRedirect(url);
	}
}
