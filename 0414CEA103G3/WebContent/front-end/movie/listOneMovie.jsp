<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.movie.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
	MovieVO movieVO = (MovieVO) request.getAttribute("movieVO"); //MovieServlet.java(Concroller), �s�Jreq��movieVO����
%>

<html>
<head>
<title>�q�v��� - listOneMovie.jsp</title>

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
	width: 600px;
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

	<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>�q�v��� - ListOneMovie.jsp</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/front-end/movie/select_movie_page.jsp"><img
						src="<%=request.getContextPath()%>/movie_images/back1.gif" width="100" height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

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
		</tr>
		<tr>
<!-- 			<td><FORM METHOD="post" -->
<%--      		 ACTION="<%=request.getContextPath()%>/place_order_details/place_order_details.do" --%>
<!--       		style="margin-bottom: 0px;"> -->
<%--       		<input type="submit" name="place_order_details" value="${place_orderVO.plc_ord_no}" style="border: none;"> --%>
<!--       		<input type="hidden" name="action" value="getplace_order_details"> -->
<!--     		 </FORM></td> -->
     
     
			<td><%=movieVO.getMovieno()%></td>
			<td><%=movieVO.getMoviename()%></td>
<!-- �ΦѮv�d�ҥh��ܹϤ� �å�web.xml���U��DBGifReader4.do�h�ϥ�,�s�����|�۰����A��Whttp://localhost:8081/CEA103G3�[�Wweb.xml���U��/movie/DBGifReader4.do-->
			<td><img src="DBGifReader4.do?movieno=<%=movieVO.getMovieno()%>"></td>
<!-- �ΦѮv�d�ҵ��U�h��ܹϤ�,�åB�ϥε��U��/movie/DBGifReader4.do? �s�����u�|���A��Whttp://localhost:8081,�n�[�W${pageContext.request.contextPath}�N��CEA103G3�~�O���T���|-->
<%-- 			<td><img src="${pageContext.request.contextPath}/movie/DBGifReader4.do?movieno=<%=movieVO.getMovieno()%>"></td> 	 --%>
<!-- ��MovieServlet��action=getPic�g�k (�Ѯv��ĳ���n�� �t�~�g�@��Ū�Ϥ���servlet����n)-->
<%-- 			<td><img src="${pageContext.request.contextPath}/movie/movie.do?action=getPicForDisplay&movieno=<%=movieVO.getMovieno()%>"></td> --%>
			<td><%=movieVO.getDirector()%></td>
			<td><%=movieVO.getActor()%></td>
			<td><%=movieVO.getCategory()%></td>
			<td><%=movieVO.getLength()%></td>
			<td><%=movieVO.getStatus()%></td>
			<td><%=movieVO.getPremiredate()%></td>
			<td><%=movieVO.getOffdate()%></td>
			<td><%=movieVO.getTrailor()%></td>
			<td><%=movieVO.getGrade()%></td>
			<td><%=movieVO.getRating()%></td>
			<td><%=movieVO.getExpectation()%></td>
		</tr>
	</table>

</body>
</html>