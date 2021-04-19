<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Movie: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM Movie: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Movie: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllMovie.jsp'>List</a> all Movies.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/movie/movie.do" >
        <b>輸入電影編號 (如1):</b>
        <input type="text" name="movieno">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="movieSvc" scope="page" class="com.movie.model.MovieService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/movie/movie.do" >
       <b>選擇電影編號:</b>
       <select size="1" name="movieno">
         <c:forEach var="movieVO" items="${movieSvc.all}" > 
          <option value="${movieVO.movieno}">${movieVO.movieno}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/movie/movie.do" >
       <b>選擇電影名稱:</b>
       <select size="1" name="movieno">
         <c:forEach var="movieVO" items="${movieSvc.all}" > 
          <option value="${movieVO.movieno}">${movieVO.moviename}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>電影管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/movie/addMovie.jsp'>Add</a> a new Movie.</li>
</ul>

</body>
</html>