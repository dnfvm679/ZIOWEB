package com.action.request;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.dao.CommonCodeDAO;
import com.dao.RequestDAO;
import com.dto.CommonCodeVO;
import com.dto.RequestVO;

public class ProcessChangeForm2 implements Action{
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="request/processChange.jsp";
		String id = request.getParameter("id");
		RequestDAO requestdao = new RequestDAO();
		RequestVO requestvo = requestdao.getRequset(id);
		CommonCodeDAO commoncodedao = new CommonCodeDAO();
		ArrayList<CommonCodeVO> list = commoncodedao.getCommonCode();
		request.setAttribute("requestvo", requestvo);
		request.setAttribute("codelist", list);
		RequestDispatcher ds = request.getRequestDispatcher(url);
		ds.forward(request, response);
	}
}
