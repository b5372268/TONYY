<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.comment.model.*"%>

<%
  CommentVO commentVO = (CommentVO) request.getAttribute("commentVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>���׸�Ʒs�W - addComment.jsp</title>

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
		 <h3>���׸�Ʒs�W - addComment.jsp</h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/front-end/comment/select_comment_page.jsp"><img src="<%=request.getContextPath()%>/comment_images/comment1.jpg" width="100" height="100" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��Ʒs�W:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/comment/comment.do" name="form1">
<table>
	<tr>
		<td>�|���s��:</td>
		<td><input type="TEXT" name="memberno" size="45"
			 value="<%= (commentVO==null)? "9999" : commentVO.getMemberno()%>" /></td>
	</tr>
	<tr>
		<td>�q�v�s��:</td>
		<td><input type="TEXT" name="movieno" size="45"
			 value="<%= (commentVO==null)? "9999" : commentVO.getMovieno()%>" /></td>
	</tr>
	<tr>
		<td>���e:</td>
		<td><input type="TEXT" name="content" size="45"
			 value="<%= (commentVO==null)? "��٭�" : commentVO.getContent()%>" /></td>	</tr>
	
	<jsp:useBean id="movieSvc" scope="page" class="com.movie.model.MovieService" />
	<tr>
		<td>�q�v:<font color=red><b>*</b></font></td>
		<td><select size="1" name="movieno">
			<c:forEach var="movieVO" items="${movieSvc.all}">
				<option value="${movieVO.movieno}" ${(commentVO.movieno==movieVO.movieno)? 'selected':'' } >${movieVO.moviename}
			</c:forEach>
		</select></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>
</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->


</html>