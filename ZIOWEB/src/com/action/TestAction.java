package com.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestAction implements Action{
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "Test.jsp";
		RequestDispatcher ds = request.getRequestDispatcher(url);
		ds.forward(request, response);
	}
}
