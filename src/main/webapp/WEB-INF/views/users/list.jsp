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
  td {
    padding : 10px;
    width: 150px;
    text-align: center;     
  }
  
  tr:first-child {
    background-color: black;
    color : white;
    font-weight: bold; 
    /*  SCSS(SASS 문법의 일종) 문법에 적용   */
    td {
       border-color : white;
    }
  }
  
  /*
  tr:first-child > td {
     border-color : white;
  }
  */
  
  tr:nth-child(2) > td {
     text-align : right; 
  } 

</style>

</head>
<body>
	<main>  
	  <h2>사용자 목록( ${msg} )</h2>
	  <table>
	    <tr>
	      <td>아이디</td>
	      <td>이름</td>
	      <td>이메일</td>
	      <td>포인트</td>
	      <td>가입일</td>
	      <td>삭제</td>
	      <td>수정</td>
	    </tr>
	    <tr>
	      <td colspan="7">
	        [<a href="/Users/WriteForm">사용자 등록</a>]	      
	      </td>
	    </tr>
	    
	    <c:forEach var="user"  items="${ userList }">
	     <tr>
	      <td>${ user.userid   }</td>
	      <td>${ user.username }</td>
	      <td>${ user.email    }</td>
	      <td>${ user.upoint   }</td>
	      <td>${ user.regdate  }</td>
	      <td><a href="/Users/Delete?userid=${ user.userid  }">삭제</a></td>
	      <td><a href="/Users/UpdateForm?userid=${ user.userid   }">수정</a></td>
	     </tr> 
	    </c:forEach>
	 
	    
	  </table>
	
	</main>
</body>
</html>








