package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.guestbookDAO;
import model.guestbookDTO;
import model.member;
import model.memberDAO;
import model.replyguestbookDAO;
import model.replyguestbookDTO;



public class ListBookServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		HttpSession session=req.getSession();
		String owner_id=req.getParameter("id");
		System.out.println("owner_id:"+owner_id);
		String id=(String)session.getAttribute("loginuserid");
		System.out.println("id:"+id);
		
		memberDAO memberdao=new memberDAO();
		member memberdto=memberdao.getMemberInfo(id);
		String imgName=memberdto.getImgName();
		
		guestbookDAO dao= new guestbookDAO();
		List<guestbookDTO> booklist = new ArrayList<guestbookDTO>();
		booklist=dao.ListBook(owner_id);
		
		
		replyguestbookDAO replydao=new replyguestbookDAO();
		replyguestbookDTO replydto=new replyguestbookDTO();
		
		List<replyguestbookDTO> replylist= new ArrayList<replyguestbookDTO>();
		replylist=replydao.ListReply(owner_id);
		
		dao.close();
		req.setAttribute("id", id);
		req.setAttribute("owner_id", owner_id);
		req.setAttribute("imgName", imgName);
		req.setAttribute("booklist", booklist);
		req.setAttribute("replylist", replylist);
		req.getRequestDispatcher("guestbook.jsp").forward(req, resp);
		
	}
}
