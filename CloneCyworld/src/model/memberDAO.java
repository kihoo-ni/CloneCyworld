package model;

import java.sql.SQLException;

import common.DBConnector;

public class memberDAO extends DBConnector {
	
	public memberDAO() {
		super();
	}
	
	public int CreateMember(member cyMember) throws ClassNotFoundException {
		String INSERT_MEMBER_SQL = "insert into member (id, password, email, phone, isAdmin, imgName) values "
									+ "(?, ?, ?, ?, ?, ?)";
		
		int result = 0;
		
		try {
			psmt = con.prepareStatement(INSERT_MEMBER_SQL);
			psmt.setString(1, cyMember.getId());
			psmt.setString(2, cyMember.getPassword());
			psmt.setString(3, cyMember.getEmail());
			psmt.setString(4, cyMember.getPhone());
			psmt.setString(5, cyMember.getIsAdmin());
			psmt.setString(6, cyMember.getImgName());
			
			result = psmt.executeUpdate(); 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int CheckDuplicateId(String id) throws SQLException {
		int result=0;
		String GET_MEMBER_SQL="select id from  member where id=?";
		
		try {
			psmt=con.prepareStatement(GET_MEMBER_SQL);
			psmt.setString(1, id);
			rs=psmt.executeQuery();
			
			if(rs.next()) {
				result=1;
			} else {
				result=0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int CyworldLogin(String id, String pw) {
		int result=0;
		String CYWORLD_LOGIN_QUERY = "select password from member where id=?";
		
		try {
			psmt = con.prepareStatement(CYWORLD_LOGIN_QUERY);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString("password").equals(pw)) {
					result=1;
				} else {
					result=0;
				}
			}else {
				result=-1;
			}
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("db 로그인실패");
		}
		
		return result;
	}
	public member getMemberInfo(String id)  {
		member memberdto=new member();
		String sql="select * from member where id=?";
		try {
		psmt=con.prepareStatement(sql);
		psmt.setString(1, id);
		rs=psmt.executeQuery();
			if(rs.next()) {
				memberdto.setId(rs.getString(1));
				memberdto.setPassword(rs.getString(2));
				memberdto.setPhone(rs.getString(3));
				memberdto.setEmail(rs.getString(4));
				memberdto.setIsAdmin(rs.getString(5));
				memberdto.setImgName(rs.getString(6));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberdto;
	}
}
