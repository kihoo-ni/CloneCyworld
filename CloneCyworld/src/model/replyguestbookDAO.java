package model;

import java.util.ArrayList;

import common.DBConnector;

public class replyguestbookDAO extends DBConnector{
	public replyguestbookDAO() {
		super();
	}
	

	public void addReply(String id, int b_no, String content, String created) {
			
			String sql="insert into guestbookreply(b_no, id, created, content) values (?,?,?,?)"; 
			try {
				psmt=con.prepareStatement(sql);
				psmt.setInt(1, b_no);
				psmt.setString(2, id);
				psmt.setString(3, created);
				psmt.setString(4, content);
				psmt.executeUpdate();
				System.out.println("방명록 댓글작성 성공");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("방명록 댓글작성 실패");
			}
		}
	
	public ArrayList<replyguestbookDTO> ListReply(String owner_id) {
		ArrayList<replyguestbookDTO> listReply = new ArrayList<replyguestbookDTO>();
		String sql="select guestbookreply.* from guestbookreply join guestbook on guestbookreply.b_no = guestbook.no where guestbook.owner_id = ? order by r_no desc";
		try {
				psmt= con.prepareStatement(sql);
				psmt.setString(1, owner_id);
				rs=psmt.executeQuery();
				
				
				while(rs.next()) { 
					replyguestbookDTO list=new replyguestbookDTO();
					list.setR_no(rs.getInt("r_no"));
					list.setB_no(rs.getInt("b_no"));
					list.setId(rs.getString("id"));
					list.setCreated(rs.getString("created"));
					list.setContent(rs.getString("content"));
					listReply.add(list);
				}
			System.out.println("댓글 불러오기 성공");
		} catch (Exception e) {
			System.out.println("댓글 불러오기 실패");
			e.printStackTrace();
		}
		
		return listReply;
	
		}
	
	public void deleteReply(int r_no) {
		
		String sql="delete from guestbookreply where r_no=?"; 
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, r_no);
			psmt.executeUpdate();
			System.out.println("방명록 댓글삭제 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("방명록 댓글삭제 실패");
		}
	}

	
	
}
