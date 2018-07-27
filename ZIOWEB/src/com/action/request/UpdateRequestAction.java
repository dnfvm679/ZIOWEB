package com.action.request;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.dao.RequestDAO;
import com.dto.RequestVO;

public class UpdateRequestAction implements Action {
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "Factory?cmd=viewRequest&id=" + request.getParameter("id");
		RequestVO requestvo = new RequestVO();
		requestvo.setId(request.getParameter("id"));
		requestvo.setTitle(request.getParameter("title"));
		requestvo.setContent(request.getParameter("content"));
		RequestDAO requestdao = new RequestDAO();
		if (requestdao.updateRequest(requestvo)) {
			response.sendRedirect(url);
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('Write Failed');");
			out.println("history.back();");
			out.println("</script>");
		}
	}
}
