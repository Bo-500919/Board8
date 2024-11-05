<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="icon" type="image/png" href="/img/favicon.png" />
<link rel="stylesheet"  href="/css/common.css" />
<script src="https://cdn.jsdelivr.net/npm/browser-scss@1.0.3/dist/browser-scss.min.js"></script>

<style>
  
  #table {
     td {
       padding : 10px;
       text-align:center;
     }
     
     td:nth-of-type(1) {width : 100px; }
     td:nth-of-type(2) {width : 380px; text-align: left; }
     td:nth-of-type(3) {width : 110px; }
     td:nth-of-type(4) {width : 110px; }
     td:nth-of-type(5) {width : 100px; }
     
     tr:first-child {
        background: #333;
        color : white;
        font-weight: 700;   /* 700 : bold */
        td {
           border-color: silver; 
        }   
        & > td:nth-of-type(2) { text-align :center;  }      
     }
     tr:nth-child(2) > td {
         text-align: right;
     }
  
  }
  
  
</style>

</head>
<body>
	<main>
	
	 <%@include file = "/WEB-INF/include/menus.jsp" %>
	  
	  <h2>${ menu_name } 자료실 목록 </h2>
	  <table id="table">
	    <tr>
	      <td>번호</td>
	      <td>제목</td>
	      <td>작성자</td>
	      <td>파일수</td>
	      <td>작성일</td>
	      <td>조회수</td>
	    </tr>
	    <tr>
	      <td colspan="6">
	        [<a href="/Pds/WriteForm?menu_id=${ menu_id }">새 글 추가</a>]	      
	      </td>
	    </tr>
	    
	    <c:forEach var="pds"  items="${ pdsList }">
	     <tr>
	      <td>${ pds.idx      }</td>
	      <td>
	        <a href="/Pds/View?idx=${pds.idx}&menu_id=${map.menu_id}&nowpage=${map.nowpage}">
	         ${ pds.title    }
	        </a> 
	      </td>
	      <td>${ pds.writer   }</td>
	      <td>${ pds.filescount   }</td>
	      <td>${ pds.regdate  }</td>
	      <td>${ pds.hit      }</td>
	     </tr> 
	    </c:forEach>
	 
	    
	  </table>
	
	</main>
</body>
</html>








