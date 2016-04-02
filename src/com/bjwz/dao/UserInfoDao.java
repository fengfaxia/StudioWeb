package com.bjwz.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.bjwz.model.UserInfo;
import com.bjwz.util.DB;

/*
 * 用户信息的CRUD  
 * @author zxy
 * 2015-03-04
 */

public class UserInfoDao {

	public void insertUserInfo(UserInfo ui) {
		Connection con = DB.getConn();
		String sql = "insert into userinfo values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = DB.prepare(con, sql);
		try {
			pstmt.setInt(1, ui.getStuId());
			pstmt.setString(2, ui.getStuName());
			pstmt.setString(3, ui.getStuSex());
			pstmt.setInt(4, ui.getStuAge());
			pstmt.setString(5, ui.getStuAddress());
			pstmt.setString(6, ui.getStuPhone());
			pstmt.setString(7, ui.getStuHome());
			pstmt.setDate(8, new java.sql.Date(ui.getStuBirthday().getTime()));
			pstmt.setInt(9, ui.getStuPassword());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(pstmt);
			DB.close(con);
		}
	}

	public boolean deleteUserinfo(int id) {
		Connection con = DB.getConn();
		String sql = "delete from userinfo where StuId=" + id + "";
		PreparedStatement pstmt = DB.prepare(con, sql);
		boolean b = false;

		try {
			pstmt.executeUpdate();
			b = true;
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DB.close(pstmt);
			DB.close(con);
		}
		return b;
	}

	public void update(int Id, UserInfo user) {
		Connection conn = DB.getConn();
		String sql = "update userinfo set StuId = ?,StuName = ?,StuSex = ?,StuAge = ?,StuAddress = ?,StuPhone = ?,StuHome = ?,StuBirthday = ?,StuPassword = ? where StuId=" + Id + "";
		PreparedStatement pstmt = DB.prepare(conn, sql);
		try {
			pstmt.setInt(1, user.getStuId());
			pstmt.setString(2, user.getStuName());
			pstmt.setString(3, user.getStuSex());
			pstmt.setInt(4, user.getStuAge());
			pstmt.setString(5, user.getStuAddress());
			pstmt.setString(6, user.getStuPhone());
			pstmt.setString(7, user.getStuHome());
			pstmt.setDate(8, new java.sql.Date(user.getStuBirthday().getTime()));
			pstmt.setInt(9, user.getStuPassword());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(pstmt);
			DB.close(conn);
		}
	}

	public ArrayList<UserInfo> getUserInfo() {
		ArrayList<UserInfo> user = new ArrayList<UserInfo>();
		Connection con = DB.getConn();
		String sql = "select * from userinfo";
		Statement stmt = DB.getStatement(con);
		ResultSet rs = DB.getResultSet(stmt, sql);
		try {
			while (rs.next()) {
				UserInfo u = new UserInfo();
				u.setStuId(rs.getInt("StuId"));
				u.setStuName(rs.getString("StuName"));
				u.setStuSex(rs.getString("StuSex"));
				u.setStuAge(rs.getInt("StuAge"));
				u.setStuAddress(rs.getString("StuAddress"));
				u.setStuPhone(rs.getString("StuPhone"));
				u.setStuHome(rs.getString("StuHome"));
				u.setStuBirthday(rs.getDate("StuBirthday"));
				u.setStuPassword(rs.getInt("StuPassword"));
				user.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(stmt);
			DB.close(con);
		}
		return user;
	}

	public UserInfo searchById(int id) {
		Connection con = DB.getConn();
		String sql = "select * from userinfo where StuId=" + id + "";
		Statement stmt = DB.getStatement(con);
		ResultSet rs = DB.getResultSet(stmt, sql);
		UserInfo u = new UserInfo();
		try {
			while (rs.next()) {
			u.setStuId(rs.getInt("StuId"));
			u.setStuName(rs.getString("StuName"));
			u.setStuSex(rs.getString("StuSex"));
			u.setStuAge(rs.getInt("StuAge"));
			u.setStuAddress(rs.getString("StuAddress"));
			u.setStuPhone(rs.getString("StuPhone"));
			u.setStuHome(rs.getString("StuHome"));
			u.setStuBirthday(rs.getDate("StuBirthday"));
			u.setStuPassword(rs.getInt("StuPassword"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(stmt);
			DB.close(con);
		}
		return u;
	}

}
