<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.comment.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    CommentService commentSvc = new CommentService();
    List<CommentVO> list = commentSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�Ҧ����׸�� - listAllComment.jsp</title>

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
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ����׸�� - listAllComment.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/comment/select_comment_page.jsp"><img src="<%=request.getContextPath()%>/comment_images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>���׽s��</th>
		<th>�|���s��</th>
		<th>�q�v�s��</th>
		<th>���פ��e</th>
		<th>�إ߮ɶ�</th>
		<th>�ק�ɶ�</th>
		<th>���ת��A</th>
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="commentVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${commentVO.commentno}</td>
			<td>${commentVO.memberno}</td>
			<td>${commentVO.movieno}</td>
			<td>${commentVO.content}</td>
			<td><fmt:formatDate value="${commentVO.creatdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td><fmt:formatDate value="${commentVO.modifydate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td>${commentVO.status}</td>
			<td>
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/comment/comment.do" style="margin-bottom: 0px;">   --%>
 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/comment/comment.do" style="margin-bottom: 0px;"> 
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="commentno"  value="${commentVO.commentno}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/comment/comment.do" style="margin-bottom: 0px;"> --%>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/comment/comment.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="commentno"  value="${commentVO.commentno}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>