package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.member;
import model.memberDAO;


public class LoginServlet extends HttpServlet {
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		memberDAO cyMemberDAO = new memberDAO();
		member dto=new member();
		int result=cyMemberDAO.CyworldLogin(id, pw);
		dto=cyMemberDAO.getMemberInfo(id);
		cyMemberDAO.close();
		HttpSession session=request.getSession();
		
		if(result==1) {
			session.setAttribute("loginuserid", id);
			session.setAttribute("memberdto", dto);
			response.sendRedirect("home.jsp?id="+id);
			//el태그 사용하고싶으면 dipatcher 사용하기
		} else if(result==0) {
			session.setAttribute("message", "비밀번호가 맞지 않습니다.");
			response.sendRedirect("login.jsp");
		} else if(result==-1) {
			session.setAttribute("message", "존재하지 않는 아이디입니다.");
			response.sendRedirect("login.jsp");
		}
		
		
//		  else if(result==0) {
//			request.setAttribute("message", "비밀번호가 맞지 않습니다.");
//			request.getRequestDispatcher("login.jsp").forward(request, response);
//		} else if(result==-1) {
//			request.setAttribute("message", "존재하지 않는 아이디입니다.");
//			request.getRequestDispatcher("login.jsp").forward(request, response);
//		}
//		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	}

}
