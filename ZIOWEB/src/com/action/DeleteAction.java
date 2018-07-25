package com.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UsersDAO;

public class DeleteAction implements Action{
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "Factory?cmd=useradmin&page=1";
		UsersDAO usersdao = new UsersDAO();
		String userid = request.getParameter("userid");
		usersdao.deleteUser(userid);
		response.sendRedirect(url);
	}
}
