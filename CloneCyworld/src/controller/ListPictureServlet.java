package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.piclistDAO;



public class ListPictureServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application=getServletContext();
		String saveDirectory=application.getRealPath("/resources/img"); 
		// String realFolder="C:\\jsp\\CloneCyworld\\WebContent\\resources\\img"; // 실제 c:jsp 폴더에 저장 새로고침해야만 나옴
		int maxPostSize=1024*1000; // 파일 최대크기 (1MB)
		String encoding="utf-8"; //인코딩 방식
		
		try{
			// 1. MultipartRequest 객체 생성
			MultipartRequest mr=new MultipartRequest(request, saveDirectory, maxPostSize, encoding, new DefaultFileRenamePolicy());
			
			// 2. 폼값 받기
			String id = mr.getParameter("id");
			String title=mr.getParameter("title");
			String imgName=mr.getFilesystemName("imgName"); //현재 파일이름
			
			
			//3. DAO
			piclistDAO dao= new piclistDAO();
			SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String created = formatter.format(new Date());
			dao.addImage(id, title, created, imgName);
			dao.close();
			
			//7. 파일목록 jsp로 리다이렉션
			response.sendRedirect("piclist.jsp?id="+id);
			
		} catch(Exception e){
			e.printStackTrace();
			request.setAttribute("errorMessage", "사진등록 오류");
			request.getRequestDispatcher("fileupload.jsp").forward(request, response);
		}
	
	}

}
