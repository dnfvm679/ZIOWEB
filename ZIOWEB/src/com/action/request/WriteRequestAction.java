package com.action.request;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.dao.RequestDAO;
import com.dto.RequestVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class WriteRequestAction implements Action {
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int maxSize = 1024 * 1024 * 100;
		String encType = "UTF-8";
		String id = request.getParameter("id");
		String dir = request.getSession().getServletContext().getRealPath("/filestorage/" + id + "/");
		File savedir = new File(dir);
		if (!savedir.exists()) {
			savedir.mkdirs();
		}
		MultipartRequest mr = new MultipartRequest(request, dir, maxSize, encType, new DefaultFileRenamePolicy());
		String url = "Factory?cmd=getRequestList&page=1";
		RequestVO requestvo = new RequestVO();
		requestvo.setId(id);
		requestvo.setTitle(mr.getParameter("title"));
		requestvo.setContent(mr.getParameter("content"));
		requestvo.setUser_id(mr.getParameter("user"));
		RequestDAO requestdao = new RequestDAO();
		requestdao.writeRequest(requestvo);
		response.sendRedirect(url);
	}

}
