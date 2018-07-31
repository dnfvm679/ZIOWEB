package com.action.request;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.action.Action;
import com.dao.RequestDAO;

public class GetPeriodRequestNum implements Action{
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		RequestDAO requestdao = new RequestDAO();
		JSONObject jsonobj = requestdao.getPeriodRequestNum();
		out.print(jsonobj);
	}
}
