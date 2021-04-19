package com.rating.model;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.DataSource;

public class RatingDAO implements RatingDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Project");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "insert into RATING (MEMBER_NO,MOVIE_NO,RATING) values (?, ?, ?)";
	private static final String UPDATE_STMT = "update RATING set RATING=?, MODIFY_DT=default where MEMBER_NO=? and MOVIE_NO=?";
	private static final String DELETE_STMT = "delete from RATING where MEMBER_NO=? and MOVIE_NO=?";
//	private static final String GET_ONE_BY_MEMBER_STMT = 
//			"select * from RATING where MEMBER_NO = ?";	
	private static final String GET_ONE_BY_MOVIENO_STMT = "select * from RATING where MOVIE_NO = ?";
//	private static final String GET_ALL_BY_MEMBER_STMT = 
//			"select * from RATING order by MEMBER_NO";
	private static final String GET_ALL_BY_MOVIENO_STMT = "select * from RATING order by MOVIE_NO";

	@Override
	public void insert(RatingVO ratingVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, ratingVO.getMemberno());
			pstmt.setInt(2, ratingVO.getMovieno());
			pstmt.setDouble(3, ratingVO.getRating());

			pstmt.executeUpdate();

			// Handle any SQL errors
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
	public void update(RatingVO ratingVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setDouble(1, ratingVO.getRating());
			pstmt.setInt(2, ratingVO.getMemberno());
			pstmt.setInt(3, ratingVO.getMovieno());

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
	public void delete(Integer memberno,Integer movieno) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, memberno);
			pstmt.setInt(2, movieno);

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
	public RatingVO findByMovieNo(Integer movieno) {
		RatingVO ratingVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BY_MOVIENO_STMT);

			pstmt.setInt(1, movieno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// commentVo 也稱為 Domain objects
				ratingVO = new RatingVO();
				ratingVO.setMemberno(rs.getInt("MEMBER_NO"));
				ratingVO.setMovieno(rs.getInt("MOVIE_NO"));
				ratingVO.setRating(rs.getDouble("RATING"));
				ratingVO.setCreatdate(rs.getTimestamp("CRT_DT"));
				ratingVO.setModifydate(rs.getTimestamp("MODIFY_DT"));
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
		return ratingVO;
	}

	@Override
	public List<RatingVO> getAllByMovieNo() {
		List<RatingVO> list = new ArrayList<RatingVO>();
		RatingVO ratingVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_MOVIENO_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// ratingVO 也稱為 Domain objects
				ratingVO = new RatingVO();
				ratingVO.setMemberno(rs.getInt("MEMBER_NO"));
				ratingVO.setMovieno(rs.getInt("MOVIE_NO"));
				ratingVO.setRating(rs.getDouble("RATING"));
				ratingVO.setCreatdate(rs.getTimestamp("CRT_DT"));
				ratingVO.setModifydate(rs.getTimestamp("MODIFY_DT"));
				list.add(ratingVO); // Store the row in the list
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
