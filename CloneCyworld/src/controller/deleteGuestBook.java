package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.guestbookDAO;



public class deleteGuestBook extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int no=Integer.parseInt(req.getParameter("no"));
		String owner_id=req.getParameter("owner_id");
		
		guestbookDAO dao=new guestbookDAO();
		dao.deleteGuestbook(no);
		dao.close();
		req.getRequestDispatcher("ListBookServlet?id="+owner_id).forward(req, resp);
	
		
	}
}
