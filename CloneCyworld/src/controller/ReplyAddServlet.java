package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.guestbookDAO;
import model.member;
import model.memberDAO;
import model.replyguestbookDAO;
import model.replyguestbookDTO;


public class ReplyAddServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		replyguestbookDAO replyguestbookdao= new replyguestbookDAO();
		
		HttpSession session=req.getSession();
		String id=(String) session.getAttribute("loginuserid");
		String content=req.getParameter("content");
		String owner_id=req.getParameter("owner_id");
		int guestnumber=Integer.parseInt(req.getParameter("no"));
		System.out.println("guestno:"+guestnumber);
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String created = formatter.format(new Date());
		
		replyguestbookdao.addReply(id, guestnumber, content, created);
		
		
		replyguestbookdao.close();
		req.getRequestDispatcher("ListBookServlet?id="+owner_id).forward(req, resp);
	}
}
