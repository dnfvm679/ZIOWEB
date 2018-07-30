package com.action.request;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.dao.RequestDAO;
import com.dto.RequestVO;

public class ProcessChangeAction implements Action {
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "Factory?cmd=viewRequest&id=" + request.getParameter("request_id");
		RequestVO requestvo = new RequestVO();
		requestvo.setId(request.getParameter("request_id"));
		requestvo.setProcess_form_id(request.getParameter("process_form_id"));
		requestvo.setProcess_type_id(request.getParameter("process_type_id"));
		requestvo.setProcess_state_id(request.getParameter("process_state_id"));
		requestvo.setManager_id(request.getParameter("manager_id"));
		RequestDAO requestdao = new RequestDAO();
		if(requestdao.updateProcess(requestvo)) {
			response.sendRedirect(url);
		}else {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('Process Update Failed');");
			out.println("history.back();");
			out.println("</script>");
		}
	}
}
