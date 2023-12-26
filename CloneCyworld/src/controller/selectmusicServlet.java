package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.musicDAO;

public class selectmusicServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String m_no=req.getParameter("m_no");
		String id=req.getParameter("id");
		
		int result=0;
		musicDAO dao= new musicDAO();
		try {
			dao.selectbestmusic(m_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		req.setAttribute("result", result);
		
		req.getRequestDispatcher("jukebox.jsp?id="+id).forward(req, resp);
	}

}
