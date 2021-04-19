package com.movie.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.movie.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MovieServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("movieno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�q�v�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/movie/select_movie_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer movieno = null;
				try {
					movieno = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�q�v�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/movie/select_movie_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				MovieService movieSvc = new MovieService();
				MovieVO movieVO = movieSvc.getOneMovie(movieno);
				if (movieVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/movie/select_movie_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("movieVO", movieVO); // ��Ʈw���X��movieVO����,�s�Jreq
				String url = "/front-end/movie/listOneMovie.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���listOneMovie.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/movie/select_movie_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllMovie.jsp 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
//			String requestURL = req.getParameter("requestURL"); // �e�X�ק諸�ӷ��������|: �i�ର�i/emp/listAllEmp.jsp�j ��  �i/dept/listEmps_ByDeptno.jsp�j �� �i /dept/listAllDept.jsp�j			
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer movieno = new Integer(req.getParameter("movieno"));
				
				/***************************2.�}�l�d�߸��****************************************/
				MovieService movieSvc = new MovieService();
				MovieVO movieVO = movieSvc.getOneMovie(movieno);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("movieVO", movieVO); // ��Ʈw���X��movieVO����,�s�Jreq
				String url = "/back-end//movie/update_movie_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���update_movie_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƨ��X�ɥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/movie/listAllMovie.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_movie_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
//			String requestURL = req.getParameter("requestURL"); // �e�X�ק諸�ӷ��������|: �i�ର�i/emp/listAllEmp.jsp�j ��  �i/dept/listEmps_ByDeptno.jsp�j �� �i /dept/listAllDept.jsp�j

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				Integer movieno = new Integer(req.getParameter("movieno").trim());

				String moviename = req.getParameter("moviename");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
				if (moviename == null || moviename.trim().length() == 0) {
					errorMsgs.add("�q�v�W��: �ФŪť�");
				} else if(!moviename.trim().matches(enameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�q�v�W��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��30����");
	            }
				
//������
				Part part = req.getPart("moviepicture");
				InputStream in = part.getInputStream();
				byte[] moviepicture = new byte[in.available()];
				in.read(moviepicture);
				in.close();
				//���P�U��
//				req.getPart("moviepicture").getInputStream().read(new byte[req.getPart("moviepicture").getInputStream().available()]);
//				req.getPart("moviepicture").getInputStream().close();
//���հ��ɦW����				
				System.out.println("�ɦW="+getServletContext().getMimeType(part.getSubmittedFileName()));
				System.out.println(part.getSubmittedFileName());
//���շӤ�				
				System.out.println("�W�ǤF�S: "+ moviepicture.toString());


				
				String director = req.getParameter("director").trim();
				if (director == null || director.trim().length() == 0) {
					errorMsgs.add("�ɺt�W�r�ФŪť�");
				}
				
				String actor = req.getParameter("actor").trim();
				if (actor == null || actor.trim().length() == 0) {
					errorMsgs.add("�t���W�r�ФŪť�");
				}	
				
				String category = req.getParameter("category").trim();
				if (category == null || category.trim().length() == 0) {
					errorMsgs.add("�q�v�����ФŪť�");
				}
				
				Integer length = new Integer(req.getParameter("length").trim());
				
				String status = req.getParameter("status").trim();
				if (status == null || status.trim().length() == 0 ) {
					errorMsgs.add("�q�v���A�ФŪť�");//��input type="TEXT"�Ϊ�
				}else if(status.equals("9")) {
					errorMsgs.add("�п�ܹq�v���A");//��select�U�Ԧ����C�@�ӯd�ťեΪ�
				}
								
				java.sql.Date premiredate = null;
				try {
					premiredate = java.sql.Date.valueOf(req.getParameter("premiredate").trim());
				} catch (IllegalArgumentException e) {
					premiredate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J�W�M���!");
				}
				
				java.sql.Date offdate = null;
				try {
					offdate = java.sql.Date.valueOf(req.getParameter("offdate").trim());
				} catch (IllegalArgumentException e) {
					offdate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J�U�ɤ��!");
				}
				
				String trailor = req.getParameter("trailor").trim();
				
				String grade = req.getParameter("grade").trim();
				if (grade == null || grade.trim().length() == 0) {
					errorMsgs.add("�q�v���ŽФŪť�");//��input type="TEXT"�Ϊ�
				}else if(grade.equals("9")) {
					errorMsgs.add("�п�ܹq�v����");//��select�U�Ԧ����Ϊ�
				}
				
				Double rating = null;
				try {
					rating = new Double(req.getParameter("rating").trim());
				} catch (NumberFormatException e) {
					rating = 0.0;
					errorMsgs.add("�����ж�Ʀr.");
				}

				Double expectation = null;
				try {
					expectation = new Double(req.getParameter("expectation").trim());
				} catch (NumberFormatException e) {
					expectation = 0.0;
					errorMsgs.add("���ݫ׽ж�Ʀr.");
				}

				MovieVO movieVO = new MovieVO();				
				movieVO.setMovieno(movieno);
				movieVO.setMoviename(moviename);
				movieVO.setMoviepicture(moviepicture);
				movieVO.setDirector(director);
				movieVO.setActor(actor);
				movieVO.setCategory(category);
				movieVO.setLength(length);
				movieVO.setStatus(status);
				movieVO.setPremiredate(premiredate);
				movieVO.setOffdate(offdate);
				movieVO.setTrailor(trailor);
				movieVO.setGrade(grade);
				movieVO.setRating(rating);
				movieVO.setExpectation(expectation);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("movieVO", movieVO); // �t����J�榡���~��movieVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/movie/update_movie_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				MovieService movieSvc = new MovieService();
				movieVO = movieSvc.updateMovie(movieno,moviename,moviepicture,director,actor,category,
							length,status,premiredate,offdate,trailor,grade,rating,expectation);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/				
				req.setAttribute("movieVO", movieVO); // ��Ʈwupdate���\��,���T����movieVO����,�s�Jreq
				String url = "/front-end/movie/listOneMovie.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneMovie.jsp
				successView.forward(req, res);

//                String url = requestURL;
//				RequestDispatcher successView = req.getRequestDispatcher(url);   // �ק令�\��,���^�e�X�ק諸�ӷ�����
//				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/movie/update_movie_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // �Ӧ�addMovie.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				String moviename = req.getParameter("moviename");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
				if (moviename == null || moviename.trim().length() == 0) {
					errorMsgs.add("�q�v�W��: �ФŪť�");
				} else if(!moviename.trim().matches(enameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�q�v�W��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��30����");
	            }
				
//������
				Part part = req.getPart("moviepicture");
				InputStream in = part.getInputStream();
				byte[] moviepicture = new byte[in.available()];
				in.read(moviepicture);
				in.close();
				
				String director = req.getParameter("director").trim();
				if (director == null || director.trim().length() == 0) {
					errorMsgs.add("�ɺt�W�r�ФŪť�");
				}
				
				String actor = req.getParameter("actor").trim();
				if (actor == null || actor.trim().length() == 0) {
					errorMsgs.add("�t���W�r�ФŪť�");
				}	
				
				String category = req.getParameter("category").trim();
				if (category == null || category.trim().length() == 0) {
					errorMsgs.add("�q�v�����ФŪť�");
				}
				
				Integer length = new Integer(req.getParameter("length").trim());
				
				String status = req.getParameter("status").trim();
				if (status == null || status.trim().length() == 0 ) {
					errorMsgs.add("�q�v���A�ФŪť�");//��input type="TEXT"�Ϊ�
				}else if(status.equals("9")) {
					errorMsgs.add("�п�ܹq�v���A");//��select�U�Ԧ����C�@�ӯd�ťեΪ�
				}
				
				java.sql.Date premiredate = null;
				try {
					premiredate = java.sql.Date.valueOf(req.getParameter("premiredate").trim());
				} catch (IllegalArgumentException e) {
					premiredate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J�W�M���!");
				}
				
				java.sql.Date offdate = null;
				try {
					offdate = java.sql.Date.valueOf(req.getParameter("offdate").trim());
				} catch (IllegalArgumentException e) {
					offdate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J�U�ɤ��!");
				}
				
				String trailor = req.getParameter("trailor").trim();
				
				String grade = req.getParameter("grade").trim();
				if (grade == null || grade.trim().length() == 0) {
					errorMsgs.add("�q�v���ŽФŪť�");//��input type="TEXT"�Ϊ�
				}else if(grade.equals("9")) {
					errorMsgs.add("�п�ܹq�v����");//��select�U�Ԧ����Ϊ�
				}
				
				Double rating = null;
				try {
					rating = new Double(req.getParameter("rating").trim());
				} catch (NumberFormatException e) {
					rating = 0.0;
					errorMsgs.add("�����ж�Ʀr.");
				}

				Double expectation = null;
				try {
					expectation = new Double(req.getParameter("expectation").trim());
				} catch (NumberFormatException e) {
					expectation = 0.0;
					errorMsgs.add("���ݫ׽ж�Ʀr.");
				}

				MovieVO movieVO = new MovieVO();				
				movieVO.setMoviename(moviename);
				movieVO.setMoviepicture(moviepicture);
				movieVO.setDirector(director);
				movieVO.setActor(actor);
				movieVO.setCategory(category);
				movieVO.setLength(length);
				movieVO.setStatus(status);
				movieVO.setPremiredate(premiredate);
				movieVO.setOffdate(offdate);
				movieVO.setTrailor(trailor);
				movieVO.setGrade(grade);
				movieVO.setRating(rating);
				movieVO.setExpectation(expectation);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("movieVO", movieVO); // �t����J�榡���~��movieVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/movie/addMovie.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				MovieService movieSvc = new MovieService();
				movieVO = movieSvc.addMovie(moviename,moviepicture,director,actor,category,
						length,status,premiredate,offdate,trailor,grade,rating,expectation);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/front-end/movie/listAllMovie.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllMovie.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/movie/addMovie.jsp");
				failureView.forward(req, res);
			}
		}
		
       
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp ��  /dept/listEmps_ByDeptno.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
//			String requestURL = req.getParameter("requestURL"); // �e�X�R�����ӷ��������|: �i�ର�i/emp/listAllEmp.jsp�j ��  �i/dept/listEmps_ByDeptno.jsp�j �� �i /dept/listAllDept.jsp�j

			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer movieno = new Integer(req.getParameter("movieno"));
				
				/***************************2.�}�l�R�����***************************************/
				MovieService movieSvc = new MovieService();
				movieSvc.deleteMovie(movieno);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/
//				DeptService deptSvc = new DeptService();
//				if(requestURL.equals("/dept/listEmps_ByDeptno.jsp") || requestURL.equals("/dept/listAllDept.jsp"))
//					req.setAttribute("listEmps_ByDeptno",deptSvc.getEmpsByDeptno(movieVO.getDeptno())); // ��Ʈw���X��list����,�s�Jrequest
				
				String url = "/front-end/movie/listAllMovie.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/movie/listAllMovie.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if("getPicForDisplay".equals(action)) {
			Integer movieno = new Integer(req.getParameter("movieno")); 
			MovieService movieSvc = new MovieService();
			MovieVO movieVO = movieSvc.getOneMovie(movieno);
			byte[] moviepicture = movieVO.getMoviepicture();
			if(moviepicture != null) {
				res.getOutputStream().write(moviepicture);
			}else {
				InputStream in = getServletContext().getResourceAsStream("/NoData/none2.jpg");
				byte[] b = new byte[in.available()];
				in.read(b);
				res.getOutputStream().write(b);
				in.close();
			}
		}
		
	}
}
