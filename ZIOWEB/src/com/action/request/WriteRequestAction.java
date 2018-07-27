package com.action.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.dao.RequestDAO;
import com.dto.BoardVO;

public class WriteRequestAction implements Action {
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "Factory?cmd=viewBoard&page=1";
		BoardVO board = new BoardVO();
		board.setTitle(request.getParameter("title"));
		board.setWriter(request.getParameter("writer"));
		board.setContent(request.getParameter("content"));
		RequestDAO boarddao = new RequestDAO();
		if (boarddao.writeIssue(board)) {
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
