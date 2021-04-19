//01�� �u���d��

package com.report_comment.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.report_comment.model.*;

public class ReportCommentServletVAR01 extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getAll".equals(action)) {
			/***************************�}�l�d�߸�� ****************************************/
			ReportCommentDAO dao = new ReportCommentDAO();
			List<ReportCommentVO> list = dao.getAll();

			/***************************�d�ߧ���,�ǳ����(Send the Success view)*************/
			HttpSession session = req.getSession();
			session.setAttribute("list", list);    // ��Ʈw���X��list����,�s�Jsession
			// Send the Success view
			String url = "/01MVC/report_commentVAR01/listAllReportComment2_getFromSession.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// ���\���listAllComment2_getFromSession.jsp
			successView.forward(req, res);
			return;
		}


		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_report_comment_page.jsp���ШD //���g�b�e�� �Ҥp�����d��

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("reportno");
				if (str == null || (str.trim()).length() == 0) { //�[str == null ���bgetParameter("reportno")��reportno����ŭȪ����p
					errorMsgs.add("�п�J���|���׽s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/01MVC/report_commentVAR01/select_report_comment_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer reportno = null;
				try {
					reportno = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("���|���׽s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/01MVC/report_commentVAR01/select_report_comment_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
			ReportCommentDAO dao = new ReportCommentDAO();
			ReportCommentVO reportcommentVO = dao.findByPrimaryKey(reportno);
				if (reportcommentVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/01MVC/report_commentVAR01/select_report_comment_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("reportcommentVO", reportcommentVO); // ��Ʈw���X��reportcommentVO����,�s�Jreq
				String url = "/01MVC/report_commentVAR01/listOneReportComment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneReportComment.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/01MVC/report_commentVAR01/select_report_comment_page.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
