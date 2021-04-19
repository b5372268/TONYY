package comment.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import movie.model.*;
import comment.model.*;


public class CommentServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

//		if ("getAll".equals(action)) {
//			/***************************開始查詢資料 ****************************************/
//			CommentDAO dao = new CommentDAO();
//			List<CommentVO> list = dao.getAll();
//
//			/***************************查詢完成,準備轉交(Send the Success view)*************/
//			HttpSession session = req.getSession();
//			session.setAttribute("list", list);    // 資料庫取出的list物件,存入session
//			// Send the Success view
//			String url = "/comment/listAllComment2_getFromSession.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交listAllComment2_getFromSession.jsp
//			successView.forward(req, res);
//			return;
//		}


		if ("getOne_For_Display".equals(action)) { // 來自select_comment_page.jsp的請求 //比對寫在前面 所小除錯範圍

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("commentno");
				if (str == null || (str.trim()).length() == 0) { //加str == null 防呆getParameter("commentno")的commentno取到空值的情況
					errorMsgs.add("請輸入評論編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/comment/select_comment_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer commentno = null;
				try {
					commentno = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("評論編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/comment/select_comment_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
			CommentDAO dao = new CommentDAO();
			CommentVO commentVO = dao.findByPrimaryKey(commentno);
				if (commentVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/comment/select_comment_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("commentVO", commentVO); // 資料庫取出的commentVO物件,存入req
				String url = "/front-end/comment/listOneComment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneComment.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/comment/select_comment_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllComment.jsp的請求  或  /movie/listComments_ByMovieno.jsp 的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/comment/listAllComment.jsp】 或  【/movie/listComments_Bymovieno.jsp】 或 【 /movie/listAllMovie.jsp】			
			try {
				/***************************1.接收請求參數****************************************/
				Integer commentno = new Integer(req.getParameter("commentno"));
				
				/***************************2.開始查詢資料****************************************/
				CommentService commentSvc = new CommentService();
				CommentVO commentVO = commentSvc.getOneComment(commentno);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("commentVO", commentVO);         // 資料庫取出的commentVO物件,存入req
				String url = "/front-end/comment/update_comment_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_comment_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/comment/listAllComment.jsp");
//				failureView.forward(req, res);
				errorMsgs.add("修改資料取出時失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_comment_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer commentno = new Integer(req.getParameter("commentno").trim());
				
				String content = req.getParameter("content").trim();
				if (content == null || content.trim().length() == 0) {
					errorMsgs.add("評論內容請勿空白");
				}	
				
				String status = req.getParameter("status").trim();
				if (status == null || status.trim().length() == 0) {
					errorMsgs.add("狀態請勿空白");
				}
				
				CommentVO commentVO = new CommentVO();
								
				commentVO.setCommentno(commentno);
				commentVO.setContent(content);
				commentVO.setStatus(status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("commentVO", commentVO); // 含有輸入格式錯誤的commentVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/comment/update_comment_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				CommentService commentSvc = new CommentService();
				commentVO = commentSvc.updateComment(commentno, content, status);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//0201版				req.setAttribute("commentVO", commentVO); // 資料庫update成功後,正確的的commentVO物件,存入req
//				String url = "/front-end/comment/listOneComment.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneComment.jsp
//				successView.forward(req, res);
//0403版				
                String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);   // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/comment/update_comment_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addComment.jsp的請求  
//			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer memberno = new Integer(req.getParameter("memberno").trim());
				
				Integer movieno = new Integer(req.getParameter("movieno").trim());
				
				
				String content = req.getParameter("content").trim();
				if (content == null || content.trim().length() == 0) {
					errorMsgs.add("評論內容請勿空白");
				}
			
				CommentVO commentVO = new CommentVO();
				commentVO.setMemberno(memberno);
				commentVO.setMovieno(movieno);
				commentVO.setContent(content);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("commentVO", commentVO); // 含有輸入格式錯誤的commentVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/comment/addComment.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				CommentService commentSvc = new CommentService();
				commentVO = commentSvc.addComment(memberno, movieno, content);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/comment/listAllComment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllComment.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/comment/addComment.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllComment.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
//0401	
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
			try {
				/***************************1.接收請求參數***************************************/
				Integer commentno = new Integer(req.getParameter("commentno"));
			
				/***************************2.開始刪除資料***************************************/
				CommentService commentSvc = new CommentService();
				CommentVO commentVO = commentSvc.getOneComment(commentno);
				commentSvc.deleteComment(commentno);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/front-end/comment/listAllComment.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//0403				
				MovieService movieSvc = new MovieService();
				if(requestURL.equals("/movie/listComments_ByMovieno.jsp") || requestURL.equals("/movie/listAllMovie.jsp"))
					req.setAttribute("listComments_ByMovieno",movieSvc.getCommentsByMovieno(commentVO.getMovieno())); // 資料庫取出的list物件,存入request
				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/comment/listAllComment.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
