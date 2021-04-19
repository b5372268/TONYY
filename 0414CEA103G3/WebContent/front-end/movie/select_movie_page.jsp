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

<h3>��Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
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
        <b>��J�q�v�s�� (�p1):</b>
        <input type="text" name="movieno">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="movieSvc" scope="page" class="com.movie.model.MovieService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/movie/movie.do" >
       <b>��ܹq�v�s��:</b>
       <select size="1" name="movieno">
         <c:forEach var="movieVO" items="${movieSvc.all}" > 
          <option value="${movieVO.movieno}">${movieVO.movieno}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/movie/movie.do" >
       <b>��ܹq�v�W��:</b>
       <select size="1" name="movieno">
         <c:forEach var="movieVO" items="${movieSvc.all}" > 
          <option value="${movieVO.movieno}">${movieVO.moviename}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>


<h3>�q�v�޲z</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/movie/addMovie.jsp'>Add</a> a new Movie.</li>
</ul>

</body>
</html>