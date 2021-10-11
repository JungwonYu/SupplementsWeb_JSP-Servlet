package com.dao;

import static common.JDBCTemplate.Close;

import java.sql.*;

import com.vo.UserVo;

public class UserDaoImpl implements UserDao{

	Connection conn=null;
	
	public UserDaoImpl(Connection conn) {
		this.conn=conn;
	}
	
	public int checkLoginUser(String userID, String userPassword) {
		PreparedStatement psmt=null;
		ResultSet rs=null;
		UserVo ans=null;
		try {
			psmt=conn.prepareStatement(checkLoginUser);
			psmt.setString(1, userID);
			rs=psmt.executeQuery();
			if(rs.next()){
				if(rs.getString(1).equals(userPassword)) {
					return 1; 
				}
				else
					return 0;
			}
			return -1;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Close(rs);
			Close(psmt);
		}
		return -2;
	}
	public int joinUser(UserVo vo) {
		PreparedStatement psmt=null;
		try {
			psmt = conn.prepareStatement(joinUser);
			psmt.setString(1, vo.getUserID());
			psmt.setString(2, vo.getUserPassword());
			psmt.setString(3, vo.getUserName());
			psmt.setString(4, vo.getUserGender());
			psmt.setString(5, vo.getUserEmail());
			psmt.setString(6, vo.getUserBirth());
			
			System.out.println("joinUser 함수");
			System.out.println(vo.getUserID());
			System.out.println(vo.getUserEmail());
			System.out.println(vo.getUserBirth());
			return psmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			Close(psmt);
		}
		return -1;
	}
}
