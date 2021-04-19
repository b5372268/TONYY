<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.movie.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	MovieService movieSvc = new MovieService();
	List<MovieVO> list = movieSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有電影資料 - listAllMovie.jsp</title>

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

	<h4>此頁練習採用 EL 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有電影資料 - listAllMovie.jsp</h3>${movieVO==null}
				<h4>
					<a
						href="<%=request.getContextPath()%>/front-end/movie/select_movie_page.jsp"><img
						src="<%=request.getContextPath()%>/movie_images/back1.gif" width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
			<th>電影編號</th>
			<th>電影名稱</th>
			<th>電影照片</th>
			<th>導演</th>
			<th>演員</th>
			<th>電影類型</th>
			<th>電影長度</th>
			<th>電影狀態</th>
			<th>上映日期</th>
			<th>下檔日期</th>
			<th>預告片</th>
			<th>電影分級</th>
			<th>評分</th>
			<th>期待度</th>
			<th>修改</th>
			<th>刪除</th>
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
<!-- 這兩支不能跑 為什麼				 -->
<%-- <td><img src="DBGifReader4.do?movieno=<%=movieVO.getMovieno()%>"></td> --%>
<%-- <td><img src="DBGifReader4.do?movieno=${movieVO.movieno}"></td> --%>

<!-- 用老師範例註冊去顯示圖片,並且使用註冊的/movie/DBGifReader4.do? 瀏覽器只會幫你押上http://localhost:8081,要加上${pageContext.request.contextPath}代表CEA103G3才是正確路徑-->
			<td><img src="${pageContext.request.contextPath}/movie/DBGifReader4.do?movieno=${movieVO.movieno}"></td> 	
<!-- 用MovieServlet的action=getPic寫法 (老師建議不要用 另外寫一支讀圖片的servlet比較好)-->
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
						<input type="submit" value="修改"> <input type="hidden"
							name="movieno" value="${movieVO.movieno}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/movie/movie.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
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