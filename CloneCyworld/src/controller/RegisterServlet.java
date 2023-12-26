package controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.member;
import model.memberDAO;

public class RegisterServlet extends HttpServlet {
	
	private memberDAO cymemberDAO;
	
	public void init() {
		cymemberDAO = new memberDAO();
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext application=getServletContext();
		String saveDirectory=application.getRealPath("/resources/img"); //저장할 디렉토리
		String realFolder="C:\\jsp\\CloneCyworld\\WebContent\\resources\\img"; // 실제 c:jsp 폴더에 저장
		
		int maxPostSize=1024*1000; // 파일 최대크기 (1MB)
		String encoding="utf-8"; 
		try {
		MultipartRequest mr=new MultipartRequest(req, realFolder, maxPostSize, encoding, new DefaultFileRenamePolicy());
		
		String id = mr.getParameter("id");
		String pw = mr.getParameter("pw");
		String email = mr.getParameter("email") + "@" + mr.getParameterValues("com")[0];
		String phone = mr.getParameterValues("phone-1")[0] +
				"-" + mr.getParameter("phone-2") + "-" + mr.getParameter("phone-3");
		String isAdmin = mr.getParameter("grant");
		
		
		/*
		 * Enumeration files=mr.getFileNames(); 
		 * String imgName=(String)files.nextElement();
		 */
		
		
		String fileName=mr.getFilesystemName("imgName");
		
		member cyMember = new member();
		cyMember.setId(id);
		cyMember.setPassword(pw);
		cyMember.setEmail(email);
		cyMember.setPhone(phone);
		cyMember.setIsAdmin(isAdmin);
		cyMember.setImgName(fileName);
		
		
		int result =cymemberDAO.CreateMember(cyMember);
		if( result==1) {
			
			HttpSession session=req.getSession();
			session.setAttribute("id", id);
			resp.sendRedirect("./RegisterSuccess.jsp");
		} else {
			resp.sendRedirect("./Register.jsp");
		}
			
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("error.jsp");
		}
	}
}
