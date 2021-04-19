package comment.model;

import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.DataSource;

public class CommentDAO implements CommentDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Project");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
			"insert into COMMENT (MEMBER_NO,MOVIE_NO,CONTENT) values (?, ?, ?)";
	private static final String UPDATE_STMT = 
			"update COMMENT set CONTENT=?, MODIFY_DT=default, STATUS=? where COMMENT_NO = ?";
	private static final String DELETE_STMT = 
			"delete from COMMENT where COMMENT_NO = ?";
	private static final String GET_ONE_STMT = 
			"select * from COMMENT where COMMENT_NO = ?";	
//	private static final String GET_MEMBER_COMMENT_STMT = 
//			"select * from COMMENT where MEMBER_NO = ?";
//	private static final String GET_MOVIE_COMMENT_STMT = 
//			"select * from COMMENT where MOVIE_NO = ?";
	private static final String GET_ALL_STMT = 
			"select * from COMMENT order by COMMENT_NO";
	
	@Override
	public void insert(CommentVO commentVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, commentVO.getMemberno());
			pstmt.setInt(2, commentVO.getMovieno());
			pstmt.setString(3, commentVO.getContent());
//			pstmt.setDate(4, commentVO.getCreatdate());
//			pstmt.setDate(5, commentVO.getModifydate());
//			pstmt.setString(6, commentVO.getStatus());

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
	public void update(CommentVO commentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, commentVO.getContent());
			pstmt.setString(2, commentVO.getStatus());
			pstmt.setInt(3, commentVO.getCommentno());

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
	public void delete(Integer commentno) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, commentno);

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
	public CommentVO findByPrimaryKey(Integer commentno) {
		CommentVO commentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, commentno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// commentVo �]�٬� Domain objects
				commentVO = new CommentVO();
				commentVO.setCommentno(rs.getInt("COMMENT_NO"));
				commentVO.setMemberno(rs.getInt("MEMBER_NO"));
				commentVO.setMovieno(rs.getInt("MOVIE_NO"));
				commentVO.setContent(rs.getString("CONTENT"));
				commentVO.setCreatdate(rs.getTimestamp("CRT_DT"));
				commentVO.setModifydate(rs.getTimestamp("MODIFY_DT"));
				commentVO.setStatus(rs.getString("STATUS"));
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
		return commentVO;
	}
	
//	@Override
//	public CommentVO findByMemberNo(Integer memberno) {
//		CommentVO commentVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_MEMBER_COMMENT_STMT);
//
//			pstmt.setInt(1, memberno);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// commentVo �]�٬� Domain objects
//				commentVO = new CommentVO();
//				commentVO.setCommentno(rs.getInt("COMMENT_NO"));
//				commentVO.setMemberno(rs.getInt("MEMBER_NO"));
//				commentVO.setMovieno(rs.getInt("MOVIE_NO"));
//				commentVO.setContent(rs.getString("CONTENT"));
//				commentVO.setCreatdate(rs.getDate("CRT_DT"));
//				commentVO.setModifydate(rs.getDate("MODIFY_DT"));
//				commentVO.setStatus(rs.getString("STATUS"));
//			}
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return commentVO;
//	}
//	
//	@Override
//	public CommentVO findByMovieNo(Integer movieno) {
//		CommentVO commentVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_MOVIE_COMMENT_STMT);
//
//			pstmt.setInt(1, movieno);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// commentVo �]�٬� Domain objects
//				commentVO = new CommentVO();
//				commentVO.setCommentno(rs.getInt("COMMENT_NO"));
//				commentVO.setMemberno(rs.getInt("MEMBER_NO"));
//				commentVO.setMovieno(rs.getInt("MOVIE_NO"));
//				commentVO.setContent(rs.getString("CONTENT"));
//				commentVO.setCreatdate(rs.getDate("CRT_DT"));
//				commentVO.setModifydate(rs.getDate("MODIFY_DT"));
//				commentVO.setStatus(rs.getString("STATUS"));
//			}
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return commentVO;
//	}

	@Override
	public List<CommentVO> getAll() {
		List<CommentVO> list = new ArrayList<CommentVO>();
		CommentVO commentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// commentVO �]�٬� Domain objects
				commentVO = new CommentVO();
				commentVO.setCommentno(rs.getInt("COMMENT_NO"));
				commentVO.setMemberno(rs.getInt("MEMBER_NO"));
				commentVO.setMovieno(rs.getInt("MOVIE_NO"));
				commentVO.setContent(rs.getString("CONTENT"));
				commentVO.setCreatdate(rs.getTimestamp("CRT_DT"));
				commentVO.setModifydate(rs.getTimestamp("MODIFY_DT"));
				commentVO.setStatus(rs.getString("STATUS"));
				list.add(commentVO); // Store the row in the list
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
