package model;


import java.util.List;
import java.util.Vector;

import common.DBConnector;

public class piclistDAO extends DBConnector {

	public piclistDAO() {
		super();
	}

	public void addImage(String id, String title, String created, String imgName) {
		try {
			String query="insert into piclist(id, title, created, imgName) values(?, ?, ?, ?)";
			psmt=con.prepareStatement(query);
			psmt.setString(1, id);
			psmt.setString(2, title);
			psmt.setString(3, created);
			psmt.setString(4, imgName);
			psmt.executeUpdate();
			
			System.out.println("insert 성공");
		} catch (Exception e) {
			System.out.println("insert 실패");
			e.printStackTrace();
		}
		
	}
	
	public List<piclistDTO> myFileList(){
		List<piclistDTO> imageList=new Vector<>();
		
		String query="select * from piclist order by p_no desc";
		try {
			psmt=con.prepareStatement(query);
			rs=psmt.executeQuery();
			
			while(rs.next()) {
				piclistDTO dto=new piclistDTO();
				dto.setP_no(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setCreated(rs.getString(4));
				dto.setImgName(rs.getString(5));
				imageList.add(dto);
			}
			
			
			System.out.println("사진목록가져오기 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("사진목록가져오기 실패");
		}
		
		return imageList;
	}
	
	public int newAddpic(String todayDate, String id) {
		int result=0;
		String sql="select count(*) from piclist where date(created)=? and id=?";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, todayDate);
			psmt.setString(2, id);
			rs=psmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);
			}
			System.out.println("오늘올린 사진갱신 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("오늘올린 사진갱신 실패");
		}
		return result;
	}
	
	public String getpicTitle(String id) {
		String result="";
		String sql="select title from piclist where id = ? ORDER BY p_no desc limit 1";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, id);
			rs=psmt.executeQuery();
			if(rs.next()) {
				result=rs.getString(1);
			}
			System.out.println("가장최근에 작성한 글 제목 가져오기 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("가장최근에 작성한 글 제목 가져오기 실패");
		}
		return result;
	}
	
	
	
}
