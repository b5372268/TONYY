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
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("movieno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入電影編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/movie/select_movie_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer movieno = null;
				try {
					movieno = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("電影編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/movie/select_movie_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MovieService movieSvc = new MovieService();
				MovieVO movieVO = movieSvc.getOneMovie(movieno);
				if (movieVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/movie/select_movie_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("movieVO", movieVO); // 資料庫取出的movieVO物件,存入req
				String url = "/front-end/movie/listOneMovie.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneMovie.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/movie/select_movie_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllMovie.jsp 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
//			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】			
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer movieno = new Integer(req.getParameter("movieno"));
				
				/***************************2.開始查詢資料****************************************/
				MovieService movieSvc = new MovieService();
				MovieVO movieVO = movieSvc.getOneMovie(movieno);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("movieVO", movieVO); // 資料庫取出的movieVO物件,存入req
				String url = "/back-end//movie/update_movie_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update_movie_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/movie/listAllMovie.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_movie_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
//			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer movieno = new Integer(req.getParameter("movieno").trim());

				String moviename = req.getParameter("moviename");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
				if (moviename == null || moviename.trim().length() == 0) {
					errorMsgs.add("電影名稱: 請勿空白");
				} else if(!moviename.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電影名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
	            }
				
//缺驗證
				Part part = req.getPart("moviepicture");
				InputStream in = part.getInputStream();
				byte[] moviepicture = new byte[in.available()];
				in.read(moviepicture);
				in.close();
				//等同下方
//				req.getPart("moviepicture").getInputStream().read(new byte[req.getPart("moviepicture").getInputStream().available()]);
//				req.getPart("moviepicture").getInputStream().close();
//測試副檔名驗證				
				System.out.println("檔名="+getServletContext().getMimeType(part.getSubmittedFileName()));
				System.out.println(part.getSubmittedFileName());
//測試照片				
				System.out.println("上傳了沒: "+ moviepicture.toString());


				
				String director = req.getParameter("director").trim();
				if (director == null || director.trim().length() == 0) {
					errorMsgs.add("導演名字請勿空白");
				}
				
				String actor = req.getParameter("actor").trim();
				if (actor == null || actor.trim().length() == 0) {
					errorMsgs.add("演員名字請勿空白");
				}	
				
				String category = req.getParameter("category").trim();
				if (category == null || category.trim().length() == 0) {
					errorMsgs.add("電影類型請勿空白");
				}
				
				Integer length = new Integer(req.getParameter("length").trim());
				
				String status = req.getParameter("status").trim();
				if (status == null || status.trim().length() == 0 ) {
					errorMsgs.add("電影狀態請勿空白");//給input type="TEXT"用的
				}else if(status.equals("9")) {
					errorMsgs.add("請選擇電影狀態");//給select下拉式選單低一個留空白用的
				}
								
				java.sql.Date premiredate = null;
				try {
					premiredate = java.sql.Date.valueOf(req.getParameter("premiredate").trim());
				} catch (IllegalArgumentException e) {
					premiredate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入上映日期!");
				}
				
				java.sql.Date offdate = null;
				try {
					offdate = java.sql.Date.valueOf(req.getParameter("offdate").trim());
				} catch (IllegalArgumentException e) {
					offdate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入下檔日期!");
				}
				
				String trailor = req.getParameter("trailor").trim();
				
				String grade = req.getParameter("grade").trim();
				if (grade == null || grade.trim().length() == 0) {
					errorMsgs.add("電影分級請勿空白");//給input type="TEXT"用的
				}else if(grade.equals("9")) {
					errorMsgs.add("請選擇電影分級");//給select下拉式選單用的
				}
				
				Double rating = null;
				try {
					rating = new Double(req.getParameter("rating").trim());
				} catch (NumberFormatException e) {
					rating = 0.0;
					errorMsgs.add("評分請填數字.");
				}

				Double expectation = null;
				try {
					expectation = new Double(req.getParameter("expectation").trim());
				} catch (NumberFormatException e) {
					expectation = 0.0;
					errorMsgs.add("期待度請填數字.");
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
					req.setAttribute("movieVO", movieVO); // 含有輸入格式錯誤的movieVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/movie/update_movie_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MovieService movieSvc = new MovieService();
				movieVO = movieSvc.updateMovie(movieno,moviename,moviepicture,director,actor,category,
							length,status,premiredate,offdate,trailor,grade,rating,expectation);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/				
				req.setAttribute("movieVO", movieVO); // 資料庫update成功後,正確的的movieVO物件,存入req
				String url = "/front-end/movie/listOneMovie.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMovie.jsp
				successView.forward(req, res);

//                String url = requestURL;
//				RequestDispatcher successView = req.getRequestDispatcher(url);   // 修改成功後,轉交回送出修改的來源網頁
//				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/movie/update_movie_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addMovie.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String moviename = req.getParameter("moviename");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
				if (moviename == null || moviename.trim().length() == 0) {
					errorMsgs.add("電影名稱: 請勿空白");
				} else if(!moviename.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電影名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
	            }
				
//缺驗證
				Part part = req.getPart("moviepicture");
				InputStream in = part.getInputStream();
				byte[] moviepicture = new byte[in.available()];
				in.read(moviepicture);
				in.close();
				
				String director = req.getParameter("director").trim();
				if (director == null || director.trim().length() == 0) {
					errorMsgs.add("導演名字請勿空白");
				}
				
				String actor = req.getParameter("actor").trim();
				if (actor == null || actor.trim().length() == 0) {
					errorMsgs.add("演員名字請勿空白");
				}	
				
				String category = req.getParameter("category").trim();
				if (category == null || category.trim().length() == 0) {
					errorMsgs.add("電影類型請勿空白");
				}
				
				Integer length = new Integer(req.getParameter("length").trim());
				
				String status = req.getParameter("status").trim();
				if (status == null || status.trim().length() == 0 ) {
					errorMsgs.add("電影狀態請勿空白");//給input type="TEXT"用的
				}else if(status.equals("9")) {
					errorMsgs.add("請選擇電影狀態");//給select下拉式選單低一個留空白用的
				}
				
				java.sql.Date premiredate = null;
				try {
					premiredate = java.sql.Date.valueOf(req.getParameter("premiredate").trim());
				} catch (IllegalArgumentException e) {
					premiredate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入上映日期!");
				}
				
				java.sql.Date offdate = null;
				try {
					offdate = java.sql.Date.valueOf(req.getParameter("offdate").trim());
				} catch (IllegalArgumentException e) {
					offdate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入下檔日期!");
				}
				
				String trailor = req.getParameter("trailor").trim();
				
				String grade = req.getParameter("grade").trim();
				if (grade == null || grade.trim().length() == 0) {
					errorMsgs.add("電影分級請勿空白");//給input type="TEXT"用的
				}else if(grade.equals("9")) {
					errorMsgs.add("請選擇電影分級");//給select下拉式選單用的
				}
				
				Double rating = null;
				try {
					rating = new Double(req.getParameter("rating").trim());
				} catch (NumberFormatException e) {
					rating = 0.0;
					errorMsgs.add("評分請填數字.");
				}

				Double expectation = null;
				try {
					expectation = new Double(req.getParameter("expectation").trim());
				} catch (NumberFormatException e) {
					expectation = 0.0;
					errorMsgs.add("期待度請填數字.");
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
					req.setAttribute("movieVO", movieVO); // 含有輸入格式錯誤的movieVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/movie/addMovie.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				MovieService movieSvc = new MovieService();
				movieVO = movieSvc.addMovie(moviename,moviepicture,director,actor,category,
						length,status,premiredate,offdate,trailor,grade,rating,expectation);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/movie/listAllMovie.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMovie.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/movie/addMovie.jsp");
				failureView.forward(req, res);
			}
		}
		
       
		if ("delete".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
//			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】

			try {
				/***************************1.接收請求參數***************************************/
				Integer movieno = new Integer(req.getParameter("movieno"));
				
				/***************************2.開始刪除資料***************************************/
				MovieService movieSvc = new MovieService();
				movieSvc.deleteMovie(movieno);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
//				DeptService deptSvc = new DeptService();
//				if(requestURL.equals("/dept/listEmps_ByDeptno.jsp") || requestURL.equals("/dept/listAllDept.jsp"))
//					req.setAttribute("listEmps_ByDeptno",deptSvc.getEmpsByDeptno(movieVO.getDeptno())); // 資料庫取出的list物件,存入request
				
				String url = "/front-end/movie/listAllMovie.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
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
