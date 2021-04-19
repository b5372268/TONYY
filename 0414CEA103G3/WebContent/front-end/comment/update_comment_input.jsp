<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.comment.model.*"%>

<%
  CommentVO commentVO = (CommentVO) request.getAttribute("commentVO"); //CommentServlet.java (Concroller) 存入req的commentVO物件 (包括幫忙取出的commentVO, 也包括輸入資料錯誤時的commentVO物件)
%>
<%= commentVO==null %>--${commentVO.movieno}--
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>評論資料修改 - update_comment_input.jsp</title>

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
	<tr><td>
		 <h3>評論資料修改 - update_comment_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/comment/select_comment_page.jsp"><img src="<%=request.getContextPath()%>/comment_images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/comment/comment.do" name="form1">
<table>
	<tr>
		<td>評論編號:<font color=red><b>*</b></font></td>
		<td><%=commentVO.getCommentno()%></td>
	</tr>
	<tr>
		<td>評論內容:</td>
		<td><input type="TEXT" name="content" size="45" value="<%=commentVO.getContent()%>" /></td>
	</tr>
	<tr>
		<td>評論狀態:</td>
		<td><input type="TEXT" name="status" size="45"	value="<%=commentVO.getStatus()%>" /></td>
	</tr>
	

<%-- 	<jsp:useBean id="movieSvc" scope="page" class="com.movie.model.MovieService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>電影:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="movieno"> -->
<%-- 			<c:forEach var="movieVO" items="${movieSvc.all}"> --%>
<%-- 				<option value="${movieVO.movieno}" ${(commentVO.movieno==movieVO.movieno)? 'selected':'' } >${movieVO.moviename} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="commentno" value="<%=commentVO.getCommentno()%>">
<input type="submit" value="送出修改"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

</html>