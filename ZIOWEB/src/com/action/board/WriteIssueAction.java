package com.action.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.dao.BoardDAO;
import com.dto.BoardVO;

public class WriteIssueAction implements Action {
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "Factory?cmd=viewBoard&page=1";
		BoardVO board = new BoardVO();
		board.setTitle(request.getParameter("title"));
		board.setWriter(request.getParameter("writer"));
		board.setContent(request.getParameter("content"));
		BoardDAO boarddao = new BoardDAO();
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
