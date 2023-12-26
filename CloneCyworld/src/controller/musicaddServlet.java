package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.musicDAO;

public class musicaddServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String id=req.getParameter("id");
		String artist=req.getParameter("artist");
		String title=req.getParameter("title");
		String youtube_id=req.getParameter("youtube_id");
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String created = formatter.format(new Date());
		Boolean picked=false;
		int result=0;
		musicDAO dao= new musicDAO();
		try {
			
			result=dao.addMusic(id, artist, title, created, youtube_id, picked);
		} catch (Exception e) {
			e.printStackTrace();
		}
		req.setAttribute("result", result);
		
		req.getRequestDispatcher("addmusic.jsp?id="+id).forward(req, resp);
	}

}
