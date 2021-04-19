<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.movie.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	MovieService movieSvc = new MovieService();
	List<MovieVO> list = movieSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>�Ҧ��q�v��� - listAllMovie.jsp</title>

<style>
tr td>img{
	width:150px;
	height:150px;
}

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
		<tr>
			<td>
				<h3>�Ҧ��q�v��� - listAllMovie.jsp</h3>${movieVO==null}
				<h4>
					<a
						href="<%=request.getContextPath()%>/front-end/movie/select_movie_page.jsp"><img
						src="<%=request.getContextPath()%>/movie_images/back1.gif" width="100" height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
			<th>�q�v�s��</th>
			<th>�q�v�W��</th>
			<th>�q�v�Ӥ�</th>
			<th>�ɺt</th>
			<th>�t��</th>
			<th>�q�v����</th>
			<th>�q�v����</th>
			<th>�q�v���A</th>
			<th>�W�M���</th>
			<th>�U�ɤ��</th>
			<th>�w�i��</th>
			<th>�q�v����</th>
			<th>����</th>
			<th>���ݫ�</th>
			<th>�ק�</th>
			<th>�R��</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="movieVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
<!-- 			<td><FORM METHOD="post" -->
<%--      		 ACTION="<%=request.getContextPath()%>/place_order_details/place_order_details.do" --%>
<!--       		style="margin-bottom: 0px;"> -->
<%--       		<input type="submit" name="place_order_details" value="${place_orderVO.plc_ord_no}" style="border: none;"> --%>
<!--       		<input type="hidden" name="action" value="getplace_order_details"> -->
<!--     		 </FORM></td> -->
			
			
				<td>${movieVO.movieno}</td>
				<td>${movieVO.moviename}</td>
<!-- �o��䤣��] ������				 -->
<%-- <td><img src="DBGifReader4.do?movieno=<%=movieVO.getMovieno()%>"></td> --%>
<%-- <td><img src="DBGifReader4.do?movieno=${movieVO.movieno}"></td> --%>

<!-- �ΦѮv�d�ҵ��U�h��ܹϤ�,�åB�ϥε��U��/movie/DBGifReader4.do? �s�����u�|���A��Whttp://localhost:8081,�n�[�W${pageContext.request.contextPath}�N��CEA103G3�~�O���T���|-->
			<td><img src="${pageContext.request.contextPath}/movie/DBGifReader4.do?movieno=${movieVO.movieno}"></td> 	
<!-- ��MovieServlet��action=getPic�g�k (�Ѯv��ĳ���n�� �t�~�g�@��Ū�Ϥ���servlet����n)-->
<%-- 			<td><img src="${pageContext.request.contextPath}/movie/movie.do?action=getPicForDisplay&movieno=${movieVO.movieno}"></td>				 --%>
				
				<td>${movieVO.director}</td>
				<td>${movieVO.actor}</td>
				<td>${movieVO.category}</td>
				<td>${movieVO.length}</td>
				<td>${movieVO.status}</td>
				<td><fmt:formatDate value="${movieVO.premiredate}"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td><fmt:formatDate value="${movieVO.offdate}"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>${movieVO.trailor}</td>
				<td>${movieVO.grade}</td>
				<td>${movieVO.rating}</td>
				<td>${movieVO.expectation}</td>

				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/movie/movie.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="�ק�"> <input type="hidden"
							name="movieno" value="${movieVO.movieno}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/movie/movie.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="�R��"> <input type="hidden"
							name="movieno" value="${movieVO.movieno}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>