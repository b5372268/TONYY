package com.movie.model;

import java.sql.*;
import java.io.*;
import java.util.*;
import javax.naming.*;
import javax.sql.DataSource;

import com.comment.model.CommentVO;

import oracle.jdbc.oracore.PickleContext;

public class MovieDAO implements MovieDAO_interface{
	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Movie");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
			"insert into MOVIE (MOVIE_NAME,MOVIE_PIC,DIRECTOR,ACTOR,CATEGORY,LENGTH,STATUS,PREMIERE_DT,OFF_DT,TRAILOR,GRADE,RATING,EXPECTATION) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = 
			"update MOVIE set MOVIE_NAME=?, MOVIE_PIC=?, DIRECTOR=?, ACTOR=?, CATEGORY=?, LENGTH=?, STATUS=?, PREMIERE_DT=?, OFF_DT=?, TRAILOR=?, GRADE=?, RATING=?, EXPECTATION=? where MOVIE_NO = ?";
	private static final String DELETE_STMT = 
			"delete from MOVIE where MOVIE_NO = ?";
	private static final String GET_ONE_STMT = 
			"select * from MOVIE where MOVIE_NO = ?";
	private static final String GET_ALL_STMT = 
			"select * from MOVIE order by MOVIE_NO";
	public static final String GET_ONE_PIC_FOR_DISPLAY = "select MOVIE_PIC from MOVIE where MOVIE_NO = ?";
	
	@Override
	public void insert(MovieVO movieVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, movieVO.getMoviename());
//測試寫照片49.50	
//			byte[] pic = movieVO.getMoviepicture();
//			pic = getPictureByteArray("pic/gun1.jpg");
//			pstmt.setBytes(2, pic);
			pstmt.setBytes(2, movieVO.getMoviepicture());
			pstmt.setString(3, movieVO.getDirector());
			pstmt.setString(4, movieVO.getActor());
			pstmt.setString(5, movieVO.getCategory());
			pstmt.setInt(6, movieVO.getLength());
			pstmt.setString(7, movieVO.getStatus());
			pstmt.setDate(8, movieVO.getPremiredate());
			pstmt.setDate(9, movieVO.getOffdate());
			pstmt.setString(10, movieVO.getTrailor());
			pstmt.setString(11, movieVO.getGrade());
			pstmt.setDouble(12, movieVO.getRating());
			pstmt.setDouble(13, movieVO.getExpectation());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
//		}catch (IOException ie) {
//			throw new RuntimeException("A database error occured. "
//					+ ie.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public void update(MovieVO movieVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, movieVO.getMoviename());
			pstmt.setBytes(2, movieVO.getMoviepicture());
			pstmt.setString(3, movieVO.getDirector());
			pstmt.setString(4, movieVO.getActor());
			pstmt.setString(5, movieVO.getCategory());
			pstmt.setInt(6, movieVO.getLength());
			pstmt.setString(7, movieVO.getStatus());
			pstmt.setDate(8, movieVO.getPremiredate());
			pstmt.setDate(9, movieVO.getOffdate());
			pstmt.setString(10, movieVO.getTrailor());
			pstmt.setString(11, movieVO.getGrade());
			pstmt.setDouble(12, movieVO.getRating());
			pstmt.setDouble(13, movieVO.getExpectation());
			pstmt.setInt(14, movieVO.getMovieno());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
	@Override
	public void delete(Integer movieno) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, movieno);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
	@Override
	public MovieVO findByPrimaryKey(Integer movieno) {
		
		MovieVO movieVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, movieno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// movieVo 也稱為 Domain objects
				movieVO = new MovieVO();
				movieVO.setMovieno(rs.getInt("MOVIE_NO"));
				movieVO.setMoviename(rs.getString("MOVIE_NAME"));
				movieVO.setMoviepicture(rs.getBytes("MOVIE_PIC"));
				movieVO.setDirector(rs.getString("DIRECTOR"));
				movieVO.setActor(rs.getString("ACTOR"));
				movieVO.setCategory(rs.getString("CATEGORY"));
				movieVO.setLength(rs.getInt("LENGTH"));
				movieVO.setStatus(rs.getString("STATUS"));
				movieVO.setPremiredate(rs.getDate("PREMIERE_DT"));
				movieVO.setOffdate(rs.getDate("OFF_DT"));
				movieVO.setTrailor(rs.getString("TRAILOR"));
				movieVO.setGrade(rs.getString("GRADE"));
				movieVO.setRating(rs.getDouble("RATING"));
				movieVO.setExpectation(rs.getDouble("EXPECTATION"));
				
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return movieVO;
	}
	
	@Override
	public List<MovieVO> getAll() {
		List<MovieVO> list = new ArrayList<MovieVO>();
		MovieVO movieVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// movieVO 也稱為 Domain objects
				movieVO = new MovieVO();
				movieVO.setMovieno(rs.getInt("MOVIE_NO"));
				movieVO.setMoviename(rs.getString("MOVIE_NAME"));
				movieVO.setMoviepicture(rs.getBytes("MOVIE_PIC"));
				movieVO.setDirector(rs.getString("DIRECTOR"));
				movieVO.setActor(rs.getString("ACTOR"));
				movieVO.setCategory(rs.getString("CATEGORY"));
				movieVO.setLength(rs.getInt("LENGTH"));
				movieVO.setStatus(rs.getString("STATUS"));
				movieVO.setPremiredate(rs.getDate("PREMIERE_DT"));
				movieVO.setOffdate(rs.getDate("OFF_DT"));
				movieVO.setTrailor(rs.getString("TRAILOR"));
				movieVO.setGrade(rs.getString("GRADE"));
				movieVO.setRating(rs.getDouble("RATING"));
				movieVO.setExpectation(rs.getDouble("EXPECTATION"));
				list.add(movieVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
}
