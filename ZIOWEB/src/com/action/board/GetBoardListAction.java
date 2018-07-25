package com.action.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.dao.BoardDAO;
import com.dto.BoardVO;

public class GetBoardListAction implements Action {
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "issue/board.jsp";
		BoardDAO boarddao = new BoardDAO();
		ArrayList<BoardVO> list = boarddao.getBoards(Integer.parseInt(request.getParameter("page")));
		int totalBoard = boarddao.totalBoard();
		int lastPage = 0;
		if (totalBoard % 10 == 0) {
			totalBoard = totalBoard - 1;
		}
		lastPage = totalBoard / 10 + 1;
		request.setAttribute("boardList", list);
		request.setAttribute("lastPage", lastPage);
		RequestDispatcher ds = request.getRequestDispatcher(url);
		ds.forward(request, response);
	}
}
