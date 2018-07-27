package com.action.request;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.dao.RequestDAO;
import com.dto.RequestVO;

public class WriteRequestAction implements Action {
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "Factory?cmd=getRequestList&page=1";
		RequestVO requestvo = new RequestVO();
		requestvo.setId(request.getParameter("id"));
		requestvo.setTitle(request.getParameter("title"));
		requestvo.setContent(request.getParameter("content"));
		requestvo.setUser_id(request.getParameter("writer"));
		RequestDAO requestdao = new RequestDAO();
		requestdao.writeRequest(requestvo);
		response.sendRedirect(url);
	}

}
