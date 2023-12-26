package model;

import java.util.ArrayList;
import java.util.List;


import common.DBConnector;

public class musicDAO extends DBConnector {

	public musicDAO() {
		super();
	}

	public int addMusic (String id, String artist, String title, String created,
			String youtube_id, boolean picked) {
		int result=0;
		
		try {
			String query="insert into music(id, artist, title, created, youtube_id, picked) values(?, ?, ?, ?, ?, ?)";
			psmt=con.prepareStatement(query);
			psmt.setString(1, id);
			psmt.setString(2, artist);
			psmt.setString(3, title);
			psmt.setString(4, created);
			psmt.setString(5, youtube_id);
			psmt.setBoolean(6, picked);
			psmt.executeUpdate();
			result=1;
			System.out.println("music insert 성공");
		} catch (Exception e) {
			System.out.println("music insert 실패");
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public List<musicDTO> myMusiclist(String id){
		List<musicDTO> musiclist=new ArrayList<musicDTO>();
		
		String query="select * from music where id=? order by m_no desc";
		try {
			psmt=con.prepareStatement(query);
			psmt.setString(1, id);
			rs=psmt.executeQuery();
			
			while(rs.next()) {
				musicDTO dto=new musicDTO();
				dto.setM_no(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setArtist(rs.getString(3));
				dto.setTitle(rs.getString(4));
				dto.setCreated(rs.getString(5));
				dto.setYoutube_id(rs.getString(6));
				dto.setPicked(rs.getBoolean(7));
				musiclist.add(dto);
			}
			
			
			System.out.println("음악목록가져오기 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("음악목록가져오기 실패");
		}
		
		return musiclist;
	}
	
	
	
	public String getpicTitle(String id) {
		String result="";
		String sql="select title from music where id = ? ORDER BY m_no desc limit 1";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, id);
			rs=psmt.executeQuery();
			if(rs.next()) {
				result=rs.getString(1);
			}
			System.out.println("가장최근에 등록한 음악타이틀 가져오기 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("가장최근에 등록한 음악타이틀 가져오기 실패");
		}
		return result;
	}
	
	public void selectbestmusic(String m_no) {
		String sql="update music set picked=true where m_no=?";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, m_no);
			psmt.executeUpdate();
			System.out.println("대표노래선정성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("대표노래선정실패");
		}
	}
	
	public List<musicDTO> musicinfo(Boolean picked, String id) {
		List<musicDTO> infolist=new ArrayList<>();
		String sql="select title, artist, youtube_id from music where picked=? and id=?";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setBoolean(1, picked);
			psmt.setString(2, id);
			rs=psmt.executeQuery();
			while(rs.next()) {
				musicDTO dto= new musicDTO();
				dto.setTitle(rs.getString(1));
				dto.setArtist(rs.getString(2));
				dto.setYoutube_id(rs.getString(3));
				infolist.add(dto);
			}
			
			System.out.println("노래정보가져오기성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("노래정보가져오기실패");
		}
		return infolist;
	}
	
	
	
	
}
