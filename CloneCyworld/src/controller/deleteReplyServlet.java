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



public class deleteReplyServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int r_no=Integer.parseInt(req.getParameter("r_no"));
		String owner_id=req.getParameter("owner_id");
	
		replyguestbookDAO replydao=new replyguestbookDAO();
		replydao.deleteReply(r_no);
		replydao.close();
		req.getRequestDispatcher("ListBookServlet?id="+owner_id).forward(req, resp);
	
		
	}
}
