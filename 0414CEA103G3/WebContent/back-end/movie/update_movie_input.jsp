<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.movie.model.*"%>

<%
	MovieVO movieVO = (MovieVO) request.getAttribute("movieVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<%-- <%= empVO==null %>--${empVO.deptno}-- --%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>電影資料修改 - update_movie_input.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>電影資料修改 - update_movie_input.jsp</h3>${movieVO.grade}${movieVO.grade.equals("0")}
				<h4>
					<a href="<%=request.getContextPath()%>/front-end/movie/select_movie_page.jsp">
					<img src="<%=request.getContextPath()%>/movie_images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/movie/movie.do" name="form1" enctype="multipart/form-data">
		<table>
			<tr>
				<td>電影編號:<font color=red><b>*</b></font></td>
				<td><%=movieVO.getMovieno()%></td>
			</tr>
			<tr>
				<td>電影名稱:</td>
				<td><input type="TEXT" name="moviename" size="45"
					value="<%=(movieVO == null) ? "天竺鼠車車" : movieVO.getMoviename()%>" /></td>
			</tr>
			<tr>
				<td>電影照片:</td>
				<td><input type="file" name="moviepicture" size="45"
					value="<%=(movieVO == null) ? "QAQ" : movieVO.getMoviepicture()%>" /></td>
			</tr>
			<tr>
				<td>導演:</td>
				<td><input type="TEXT" name="director" size="45"
					value="<%=(movieVO == null) ? "吳永志" : movieVO.getDirector()%>" /></td>
			</tr>
			<tr>
				<td>演員:</td>
				<td><input type="TEXT" name="actor" size="45"
					value="<%=(movieVO == null) ? "吳冠宏" : movieVO.getActor()%>" /></td>
			</tr>
			<tr>
				<td>電影類型:</td>
				<td><input type="TEXT" name="category" size="45"
					value="<%=(movieVO == null) ? "10000" : movieVO.getCategory()%>" /></td>
			</tr>
			<tr>
				<td>電影長度:</td>
				<td><input type="TEXT" name="length" size="45"
					value="<%=(movieVO == null) ? "10000" : movieVO.getLength()%>" /></td>
			</tr>
			<tr>
				<td>電影狀態:</td>
				<td><select name="status" size="1"> 
<!-- 				<option selected="true" value="9"></option>預設空白 -->
				<option value="9" <%= (movieVO.getStatus().equals("9")? "selected":"")%>></option>
				<option value="0" <%= (movieVO.getStatus().equals("0")? "selected":"")%>>上映中</option>
				<option value="1" <%= (movieVO.getStatus().equals("1")? "selected":"")%>>未上映</option>
				<option value="2" <%= (movieVO.getStatus().equals("2")? "selected":"")%>>已下檔</option>
				</select></td>
			</tr>
			<tr>
				<td>上映日期:</td>
				<td><input name="premiredate" id="f_date1" type="text"></td>
			</tr>
			<tr>
				<td>下檔日期:</td>
				<td><input name="offdate" id="f_date2" type="text"></td>
			</tr>
			<tr>
				<td>預告片:</td>
				<td><input type="TEXT" name="trailor" size="45"
					value="<%=(movieVO == null) ? "10000" : movieVO.getTrailor()%>" /></td>
			</tr>
			<tr>
				<td>電影分級:</td>
				<td><select name="grade" size="1"> 
				<option value="9" <%= (movieVO.getGrade().equals("9")? "selected":"")%>></option>
				<option value="0" <%= (movieVO.getGrade().equals("0")? "selected":"")%>>普遍級</option> 
 				<option value="1" <%= (movieVO.getGrade().equals("1")? "selected":"")%>>保護級</option> 
 				<option value="2" <%= (movieVO.getGrade().equals("2")? "selected":"")%>>輔導級</option> 
				<option value="3" <%= (movieVO.getGrade().equals("3")? "selected":"")%>>限制級</option>  
			
<!-- EL寫法				 -->
<%-- 				<option value="0" ${movieVO.grade.equals("0")? "selected":""}>普遍級</option>				 --%>
<%-- 				<option value="1" ${movieVO.grade.equals("1")? "selected":""}>保護級</option> --%>
<%-- 				<option value="2" ${movieVO.grade.equals("2")? "selected":""}>輔導級</option> --%>
<%-- 				<option value="3" ${movieVO.grade.equals("3")? "selected":""}>限制級</option> --%>
				</select></td>
			</tr>
			<tr>
				<td>評分:</td>
				<td><input type="TEXT" name="rating" size="45"
					value="<%=(movieVO == null) ? "100" : movieVO.getRating()%>" /></td>
			</tr>
			<tr>
				<td>期待度:</td>
				<td><input type="TEXT" name="expectation" size="45"
					value="<%=(movieVO == null) ? "100" : movieVO.getExpectation()%>" /></td>
			</tr>
		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="movieno" value="<%=movieVO.getMovieno()%>">
		<input type="submit" value="送出修改">
	</FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=movieVO.getPremiredate()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        $.datetimepicker.setLocale('zh');
        $('#f_date2').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=movieVO.getOffdate()%>', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});

	// ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

	//      1.以下為某一天之前的日期無法選擇
	//      var somedate1 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      2.以下為某一天之後的日期無法選擇
	//      var somedate2 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});
</script>
</html>