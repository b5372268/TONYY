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
//			/***************************�}�l�d�߸�� ****************************************/
//			CommentDAO dao = new CommentDAO();
//			List<CommentVO> list = dao.getAll();
//
//			/***************************�d�ߧ���,�ǳ����(Send the Success view)*************/
//			HttpSession session = req.getSession();
//			session.setAttribute("list", list);    // ��Ʈw���X��list����,�s�Jsession
//			// Send the Success view
//			String url = "/comment/listAllComment2_getFromSession.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);// ���\���listAllComment2_getFromSession.jsp
//			successView.forward(req, res);
//			return;
//		}


		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_comment_page.jsp���ШD //���g�b�e�� �Ҥp�����d��

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("commentno");
				if (str == null || (str.trim()).length() == 0) { //�[str == null ���bgetParameter("commentno")��commentno����ŭȪ����p
					errorMsgs.add("�п�J���׽s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/comment/select_comment_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer commentno = null;
				try {
					commentno = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("���׽s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/comment/select_comment_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
			CommentDAO dao = new CommentDAO();
			CommentVO commentVO = dao.findByPrimaryKey(commentno);
				if (commentVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/comment/select_comment_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("commentVO", commentVO); // ��Ʈw���X��commentVO����,�s�Jreq
				String url = "/front-end/comment/listOneComment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneComment.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/comment/select_comment_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllComment.jsp���ШD  ��  /movie/listComments_ByMovieno.jsp ���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // �e�X�ק諸�ӷ��������|: �i�ର�i/comment/listAllComment.jsp�j ��  �i/movie/listComments_Bymovieno.jsp�j �� �i /movie/listAllMovie.jsp�j			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer commentno = new Integer(req.getParameter("commentno"));
				
				/***************************2.�}�l�d�߸��****************************************/
				CommentService commentSvc = new CommentService();
				CommentVO commentVO = commentSvc.getOneComment(commentno);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("commentVO", commentVO);         // ��Ʈw���X��commentVO����,�s�Jreq
				String url = "/front-end/comment/update_comment_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_comment_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
//				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/comment/listAllComment.jsp");
//				failureView.forward(req, res);
				errorMsgs.add("�ק��ƨ��X�ɥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_comment_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // �e�X�ק諸�ӷ��������|: �i�ର�i/emp/listAllEmp.jsp�j ��  �i/dept/listEmps_ByDeptno.jsp�j �� �i /dept/listAllDept.jsp�j
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				Integer commentno = new Integer(req.getParameter("commentno").trim());
				
				String content = req.getParameter("content").trim();
				if (content == null || content.trim().length() == 0) {
					errorMsgs.add("���פ��e�ФŪť�");
				}	
				
				String status = req.getParameter("status").trim();
				if (status == null || status.trim().length() == 0) {
					errorMsgs.add("���A�ФŪť�");
				}
				
				CommentVO commentVO = new CommentVO();
								
				commentVO.setCommentno(commentno);
				commentVO.setContent(content);
				commentVO.setStatus(status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("commentVO", commentVO); // �t����J�榡���~��commentVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/comment/update_comment_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				CommentService commentSvc = new CommentService();
				commentVO = commentSvc.updateComment(commentno, content, status);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
//0201��				req.setAttribute("commentVO", commentVO); // ��Ʈwupdate���\��,���T����commentVO����,�s�Jreq
//				String url = "/front-end/comment/listOneComment.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneComment.jsp
//				successView.forward(req, res);
//0403��				
                String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);   // �ק令�\��,���^�e�X�ק諸�ӷ�����
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/comment/update_comment_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // �Ӧ�addComment.jsp���ШD  
//			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				Integer memberno = new Integer(req.getParameter("memberno").trim());
				
				Integer movieno = new Integer(req.getParameter("movieno").trim());
				
				
				String content = req.getParameter("content").trim();
				if (content == null || content.trim().length() == 0) {
					errorMsgs.add("���פ��e�ФŪť�");
				}
			
				CommentVO commentVO = new CommentVO();
				commentVO.setMemberno(memberno);
				commentVO.setMovieno(movieno);
				commentVO.setContent(content);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("commentVO", commentVO); // �t����J�榡���~��commentVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/comment/addComment.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				CommentService commentSvc = new CommentService();
				commentVO = commentSvc.addComment(memberno, movieno, content);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/front-end/comment/listAllComment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllComment.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/comment/addComment.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllComment.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
//0401	
			String requestURL = req.getParameter("requestURL"); // �e�X�R�����ӷ��������|: �i�ର�i/emp/listAllEmp.jsp�j ��  �i/dept/listEmps_ByDeptno.jsp�j �� �i /dept/listAllDept.jsp�j
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer commentno = new Integer(req.getParameter("commentno"));
			
				/***************************2.�}�l�R�����***************************************/
				CommentService commentSvc = new CommentService();
				CommentVO commentVO = commentSvc.getOneComment(commentno);
				commentSvc.deleteComment(commentno);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
//				String url = "/front-end/comment/listAllComment.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
//				successView.forward(req, res);
//0403				
				MovieService movieSvc = new MovieService();
				if(requestURL.equals("/movie/listComments_ByMovieno.jsp") || requestURL.equals("/movie/listAllMovie.jsp"))
					req.setAttribute("listComments_ByMovieno",movieSvc.getCommentsByMovieno(commentVO.getMovieno())); // ��Ʈw���X��list����,�s�Jrequest
				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/comment/listAllComment.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
