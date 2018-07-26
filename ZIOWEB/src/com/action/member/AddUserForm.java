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

public class AddUserForm implements Action{
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/member/addUser.jsp";
		UsersDAO usersdao = new UsersDAO();
		String userid = usersdao.getUserID();
		ArrayList<CompanyVO> list = usersdao.getCompanyList();
		request.setAttribute("userid", userid);
		request.setAttribute("company", list);
		RequestDispatcher ds = request.getRequestDispatcher(url);
		ds.forward(request, response);
	}

}
