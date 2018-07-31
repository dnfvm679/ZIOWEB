package com.action.request;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.dao.RequestDAO;
import com.dto.RequestVO;

public class FinishRequestAction implements Action {
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "Factory?cmd=viewRequest&id="+ request.getParameter("id");
		String id = request.getParameter("id");
		String time = request.getParameter("time")+request.getParameter("unit");
		String date = request.getParameter("date");
		String content = request.getParameter("content");
		
		RequestVO requestvo = new RequestVO();
		requestvo.setComplete_date(date);
		requestvo.setProcess_content(content);
		requestvo.setId(id);
		requestvo.setProcess_hour(time);
		
		RequestDAO requestdao = new RequestDAO();
		if(requestdao.finishRequest(requestvo)) {
			response.sendRedirect(url);
		}else{
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('Finish Failed');");
			out.println("history.back();");
			out.println("</script>");
		}
		
	}

}
