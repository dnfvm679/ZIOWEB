
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class Upload
 */
@WebServlet("/Upload")
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Upload() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String saveDir = request.getSession().getServletContext().getRealPath("/filestorage/A/");
		File dir = new File(saveDir);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		int maxSize = 1024 * 1024 * 100;
		String encType = "UTF-8";

		MultipartRequest multipartRequest = new MultipartRequest(request, saveDir, maxSize, encType,
				new DefaultFileRenamePolicy());
		PrintWriter out = response.getWriter();
		out.write("이름 : " + multipartRequest.getParameter("name") + "<br>");
		out.write("파일 : " + multipartRequest.getParameter("file") + "<br>"); // null 값을 갖는다.
		out.write("업로드파일명 : " + multipartRequest.getFilesystemName("file") + "<br>");
		out.write("원래파일명 : " + multipartRequest.getOriginalFileName("file") + "<br>");

	}

}
