package com.action.member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.dao.UsersDAO;
import com.dto.CompanyVO;
import com.dto.UserVO;

public class UpdateUserForm implements Action{
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "member/updateUser.jsp";
		String userid = request.getParameter("userid");
		UsersDAO usersdao = new UsersDAO();
		UserVO user = usersdao.getUser(userid);
		ArrayList<CompanyVO> list = usersdao.getCompanyList();
		request.setAttribute("company", list);
		request.setAttribute("user", user);
		RequestDispatcher ds = request.getRequestDispatcher(url);
		ds.forward(request, response);
	}
;
}
