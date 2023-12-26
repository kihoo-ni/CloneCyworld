package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.guestbookDAO;
import model.guestbookDTO;
import model.member;
import model.memberDAO;



public class WriteBookServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		guestbookDAO dao= new guestbookDAO();
		memberDAO memberdao=new memberDAO();
		HttpSession session=req.getSession();
		String owner_id=req.getParameter("id");
		String id=(String) session.getAttribute("loginuserid");
		member memberdto=memberdao.getMemberInfo(id);
		
		String imgName=memberdto.getImgName();
		String content=req.getParameter("content");
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String created = formatter.format(new Date());
		dao.WriteBookInsert(id,owner_id, created, content, imgName);
		dao.close();
		//resp.sendRedirect("home.jsp?id="+id);
		resp.sendRedirect("ListBookServlet?id="+owner_id);
	}
}
