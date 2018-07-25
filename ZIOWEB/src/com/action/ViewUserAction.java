package com.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UsersDAO;
import com.dto.usersVO;

public class ViewUserAction implements Action{
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "member/viewUser.jsp";
		String userid = request.getParameter("userid");
		UsersDAO usersdao = new UsersDAO();
		usersVO user = usersdao.getUser(userid);
		request.setAttribute("user", user);
		RequestDispatcher ds = request.getRequestDispatcher(url);
		ds.forward(request, response);
	}
;
}
