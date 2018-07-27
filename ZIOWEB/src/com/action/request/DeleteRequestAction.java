package com.action.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.dao.RequestDAO;
import com.dto.BoardVO;

public class DeleteRequestAction implements Action {
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "Factory?cmd=getBoardList&page=1";

		RequestDAO boarddao = new RequestDAO();
		if (boarddao.deleteIssue(Integer.parseInt(request.getParameter("boardnum")))) {
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
