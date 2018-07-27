package com.action.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.dao.RequestDAO;
import com.dto.BoardVO;

public class UpdateRequestForm implements Action {
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="issue/updateIssue.jsp";
		RequestDAO boarddao = new RequestDAO();
		BoardVO board = boarddao.getBoard(Integer.parseInt(request.getParameter("boardnum")));
		board.setBoardnum(Integer.parseInt(request.getParameter("boardnum")));
		request.setAttribute("board", board);
		RequestDispatcher ds = request.getRequestDispatcher(url);
		ds.forward(request, response);
	}
}
