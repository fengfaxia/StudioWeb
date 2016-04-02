package com.bjwz.dao;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.bjwz.model.News;
import com.bjwz.util.*;

/*
 * 新闻DAO相关操作
 * @Author zxy
 * 2015-03-06
 */
public class NewsDao {
	public static List<News> getList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<News> list = new ArrayList<News>();
			con = DB.getConn();
			try {
				pstmt = con.prepareStatement("select * from news");
				rs = pstmt.executeQuery();
				while(rs.next()) {
					News news = new News();
					news.setNewsId(rs.getInt(1));
					news.setNewsTitle(rs.getString(2));
					news.setNewsCreator(rs.getString(3));
					news.setNewsCreateTime(rs.getDate(4));
					list.add(news);
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally {
				DB.close(con);
				DB.close(rs);
				DB.close(pstmt);
			}
			return list;
		}
	
	public 	static void add(News news) {
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		try {
		con = DB.getConn();
		String sql = "insert into news(NewsId,NewsTitle,NewsCreator,NewsCreateTime)values(?,?,?,?)";
			pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, news.getNewsId());
			pStmt.setString(2, news.getNewsTitle());
			pStmt.setString(3, news.getNewsCreator());
			pStmt.setDate(4,  (Date) news.getNewsCreateTime());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public static void delete(int id) {
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		try {
		con = DB.getConn();
		String sql = "delete from news where id=?";
			pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, id);
			pStmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DB.close(con);
			DB.close(rs);
			DB.close(pStmt);
		}
	}

	public static News getModel(int id){
		//创建操作对象
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		News news = new News();
		SimpleDateFormat mattern = new SimpleDateFormat("yyyy-MM-dd");
		try {
			con = DB.getConn();
			pStmt = con.prepareStatement("select * from news where id=?");
			pStmt.setInt(1, id);
			rs = pStmt.executeQuery();
			if(rs.next()){
				
				news.setNewsId(rs.getInt(1));
				news.setNewsTitle(rs.getString(2));
				news.setNewsCreator(rs.getString(3));
				try {
					news.setNewsCreateTime(mattern.parse(rs.getString(4)));
				} catch (ParseException e) {
					
					e.printStackTrace();
				}
			}
			
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		finally{
			DB.close(con);
			DB.close(rs);
			DB.close(pStmt);
		}
		return news;
	}
	
	public static void update(int id){
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		SimpleDateFormat mattern1 = new SimpleDateFormat("yyyy-MM-dd");
		try {
			con = DB.getConn();
			pStmt = con.prepareStatement("select * from news where id=?");
			pStmt.setInt(1, id);
			rs = pStmt.executeQuery();
			if(rs.next()){
				News news = new News();
				news.setNewsId(rs.getInt(1));
				news.setNewsTitle(rs.getString(2));
				news.setNewsCreator(rs.getString(3));
				try {
					news.setNewsCreateTime(mattern1.parse(rs.getString(4)));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			DB.close(con);
			DB.close(rs);
			DB.close(pStmt);
		}
	}



}
