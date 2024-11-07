<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="icon" type="image/png" href="/img/favicon.png" />
<link rel="stylesheet"  href="/css/common.css" />

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/browser-scss@1.0.3/dist/browser-scss.min.js"></script>
<script src="https://code.jquery.com/jquery.min.js"></script>
<style>
 #table {
  margin-bottom : 150px; 
  td {
     text-align : center;
     padding    : 10px;
     border     : 1px solid silver;
     
     &:nth-of-type(1) {
         width             :  150px;
         background-color  :  black;
         color             :  white;
     }
     &:nth-of-type(2) { width : 250px; }
     &:nth-of-type(3) {
         width             :  150px;
         background-color  :  black;
         color             :  white;
     }
     &:nth-of-type(4) { width : 250px;  }
  }  
  
  tr:nth-of-type(3) td:nth-of-type(2) {
     text-align : left;
  }
  
  tr:nth-of-type(4) td:nth-of-type(2) {
     height     : 250px;
     width      : 600px; 
     text-align : left;
     vertical-align: baseline;      
  }
  
  tr:last-of-type  td {
     background: white;
     color :     black; 
  }
   
 }
 
 /* class="btn btn-dark btn-sm" */
 a.btn.btn-dark.btn-sm:hover {
    text-decoration: none;    
 }

</style>

</head>
<body>
  <main>  
    <%@include file="/WEB-INF/include/pagingpdsmenus.jsp" %>
   
    <h2>자료실 내용 보기(${ map.menu_id })</h2>
    <table id="table">
     <tr>
      <td>글번호</td>
      <td>${  map.idx      }</td>
      <td>조회수</td>
      <td>${  vo.hit      }</td>
     </tr>
     <tr>
      <td>작성자</td>
      <td>${  vo.writer   }</td>
      <td>작성일</td>
      <td>${  vo.regdate  }</td>
     </tr>
     <tr>
      <td>제목</td>
      <td colspan="3">${ vo.title }</td>
     </tr>
     <tr>
      <td>내용</td>
      <td colspan="3">${ vo.content }</td>
     </tr>
     <tr>
       <td>파일</td>
       <td colspan="3" id="tdfile">
         <div>
           <c:forEach items="${ fileList }" var="file">
             <a href="/Pds/filedownload/${ file.file_num }">${ file.filename }</a><br>
           </c:forEach>
         </div>
       </td>
     </tr>
     <tr>
      <td colspan="4"> 	
       <a class="btn btn-dark btn-sm"
          href="/Pds/WriteForm?menu_id=${map.menu_id}&nowpage=${map.nowpage}">새 글 쓰기</a>
      
       <a class="btn btn-dark btn-sm"
          href="/Pds/UpdateForm?idx=${map.idx}&menu_id=${map.menu_id}&nowpage=${map.nowpage}">수정</a>
       <a class="btn btn-dark btn-sm"
          href="/Pds/Delete?idx=${map.idx}&menu_id=${map.menu_id}&nowpage=${map.nowpage}">삭제</a>

       <a class="btn btn-dark btn-sm"
          href="/Pds/List?menu_id=${ map.menu_id }&nowpage=${map.nowpage}">목록</a>
       <a class="btn btn-dark btn-sm"
          href="/">Home</a>
      </td>
     </tr>
    
    </table>    
  
  
  </main>
</body>
</html>















