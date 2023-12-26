package model;

import java.util.ArrayList;

import common.DBConnector;

public class guestbookDAO extends DBConnector {
	public guestbookDAO() {
		super();
	}
	
	
	
	public void WriteBookInsert(String id, String owner_id, String created, String content, String imgName) {
			
			String sql="insert into guestbook(id, owner_id, created, content, imgName) values (?,?,?,?,?)"; 
			try {
				psmt=con.prepareStatement(sql);
				psmt.setString(1, id);
				psmt.setString(2, owner_id);
				psmt.setString(3, created);
				psmt.setString(4, content);
				psmt.setString(5, imgName);
				psmt.executeUpdate();
				System.out.println("방명록글작성 성공");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("방명록글작성 실패");
			}
		}
	
	public ArrayList<guestbookDTO> ListBook(String owner_id) {
		ArrayList<guestbookDTO> booklist = new ArrayList<guestbookDTO>();
		String sql="select * from guestbook where owner_id=? order by no desc ";
		guestbookDAO dao=new guestbookDAO();
		try {
				psmt= con.prepareStatement(sql);
				psmt.setString(1, owner_id);
				rs=psmt.executeQuery();
				
				while(rs.next()) { 
					guestbookDTO list=new guestbookDTO();
						list.setNo(rs.getInt("no"));
						list.setId(rs.getString("id"));
						list.setCreated(rs.getString("created"));
						list.setContent(rs.getString("content"));
						list.setImgName(rs.getString("imgName"));
						booklist.add(list);
				}
			System.out.println("목록 불러오기 성공");
		} catch (Exception e) {
			System.out.println("목록 불러오기 실패");
			e.printStackTrace();
		}
		
		return booklist;
	
		}
	public int newAddguestbook(String todayDate, String owner_id) {
		int result=0;
		String sql="select count(*) from guestbook where date(created)=? and  owner_id=?";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, todayDate);
			psmt.setString(2, owner_id);
			rs=psmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);
			}
			System.out.println("오늘쓴 글갱신 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("오늘쓴 글갱신 실패");
		}
		return result;
	}
	
	public void deleteGuestbook(int no) { // 홈피주인이 지꺼지우는 메소드
			
			String sql="delete from guestbook where no=?"; 
			try {
				psmt=con.prepareStatement(sql);
				psmt.setInt(1, no);
				psmt.executeUpdate();
				System.out.println("방명록 본인홈피 글삭제 성공");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("방명록 본인홈피 글삭제 실패");
			}
		}

	
}
