package com.action.request;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.dao.RequestDAO;
import com.dto.RequestVO;

public class ViewRequestAction implements Action {
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="request/viewRequest.jsp";
		String id = request.getParameter("id");
		RequestDAO requestdao = new RequestDAO();
		RequestVO requestvo = requestdao.getRequset(id);
		request.setAttribute("requestvo", requestvo);
		RequestDispatcher ds = request.getRequestDispatcher(url);
		ds.forward(request, response);
	}
}
