<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Comment: Home</title>

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
   <tr><td><h3>Report Comment: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Report Comment: Home</p>

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
  <li><a href='listAllReportComment1_byDAO.jsp'>List</a> all Report Comments    <h4>(byDAO).         </h4></li>
  <li><a href='reportcomment.do?action=getAll'> List</a> all Report Comments    <h4>(getFromSession).</h4> <br><br><br></li>
  
  <li>
    <FORM METHOD="post" ACTION="reportcomment.do" >
        <b>��J���|���׽s�� (�p1):</b>
        <input type="text" name="reportno">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">                   <h4>(��Ʈ榡����  by Controller ).</h4> 
    </FORM>
  </li>
  
  <li>
    <FORM METHOD="post" ACTION="reportcomment.do" name="form1">
        <b>��J���|���׽s�� (�p1):</b>
        <input type="text" name="reportno">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="button" value="�e�X" onclick="fun1()">  <h4>(��Ʈ榡����  by Java Script).</h4> 
    </FORM>
  </li>

  <jsp:useBean id="dao" scope="page" class="com.report_comment.model.ReportCommentDAO" />
   
  <li>
     <FORM METHOD="post" ACTION="reportcomment.do" >
       <b>������|���׽s��:</b>
       <select size="1" name="reportno">
         <c:forEach var="reportcommentVO" items="${dao.all}" > 
          <option value="${reportcommentVO.reportno}">${reportcommentVO.reportno}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="reportcomment.do" >
       <b>��ܳЫخɶ�:</b>
       <select size="1" name="reportno">
         <c:forEach var="reportcommentVO" items="${dao.all}" > 
          <option value="${reportcommentVO.reportno}">${reportcommentVO.creatdate}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>

<script>    
   function fun1(){
      with(document.form1){
         if (reportcommentno.value=="") 
             alert("�п�J���|���׽s��!");
         else if (isNaN(reportcommentno.value)) 
             alert("���|���׽s���榡�����T!");
         else if ((reportcommentno.value < 1) || (reportcommentno.value > 9999)) 
             alert("�ж�g����1�M9999�������ƶq!");
         else
             submit();
      }
   }
</script>

</body>
</html>