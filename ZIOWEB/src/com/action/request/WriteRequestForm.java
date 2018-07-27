package com.action.request;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.dao.CommonCodeDAO;
import com.dao.RequestDAO;
import com.dao.UsersDAO;
import com.dto.CommonCodeVO;

public class WriteRequestForm implements Action {
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "request/writeRequest.jsp";
		String userid = (String)request.getSession().getAttribute("userid");
		RequestDAO requestdao = new RequestDAO();
		UsersDAO usersdao = new UsersDAO();
		CommonCodeDAO commcodedao = new CommonCodeDAO();
		String company_id = usersdao.getCompanyID(userid);
		ArrayList<CommonCodeVO> list = commcodedao.getCommonCode("PROCESS_TYPE");
		String board_num = requestdao.getBoardNum(company_id);
		request.setAttribute("company_id", company_id);
		request.setAttribute("board_num", board_num);
		request.setAttribute("commoncode", list);
		RequestDispatcher ds = request.getRequestDispatcher(url);
		ds.forward(request, response);
	}
}
