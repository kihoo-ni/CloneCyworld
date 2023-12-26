package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.memberDAO;

public class IdCheckServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		memberDAO cyMemberDAO = new memberDAO();
		int result=0;
		try {
			result=cyMemberDAO.CheckDuplicateId(id);
			cyMemberDAO.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("userId", id);
		request.setAttribute("result", result);
		request.getRequestDispatcher("idCheck.jsp").forward(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	}

}
