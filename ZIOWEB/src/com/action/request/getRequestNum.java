package com.action.request;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.action.Action;
import com.dao.RequestDAO;

public class getRequestNum implements Action{
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		RequestDAO requestdao = new RequestDAO();
		JSONObject jobj = new JSONObject();
		int result = requestdao.getRequestNum();
		jobj.put("total", result);
		int result2 = requestdao.getFinished_RequestNum();
		jobj.put("finished", result2);
		int result3 = requestdao.getUnfinished_RequestNum();
		jobj.put("unfinished", result3);
		out.print(jobj);
	}
}
