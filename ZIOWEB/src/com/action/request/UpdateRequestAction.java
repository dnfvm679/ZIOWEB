package com.action.request;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.dao.RequestDAO;
import com.dto.RequestVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UpdateRequestAction implements Action {
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int maxSize = 1024 * 1024 * 100;
		String encType = "UTF-8";
		String id = request.getParameter("id");
		String dir = request.getSession().getServletContext().getRealPath("/filestorage/" + id + "/");
		File savedir = new File(dir);
		if (!savedir.exists()) {
			savedir.mkdirs();
		}
		MultipartRequest mr = new MultipartRequest(request, dir, maxSize, encType, new DefaultFileRenamePolicy());
		String url = "Factory?cmd=viewRequest&id="+ mr.getParameter("id");
		RequestVO requestvo = new RequestVO();
		requestvo.setId(mr.getParameter("id"));
		requestvo.setTitle(mr.getParameter("title"));
		requestvo.setContent(mr.getParameter("content"));
		RequestDAO requestdao = new RequestDAO();
		if (requestdao.updateRequest(requestvo)) {
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
