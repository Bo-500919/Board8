<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="icon" type="image/png" href="/img/favicon.png" />
<link rel="stylesheet"  href="/css/common.css" />
<style>
  .green { color:green; }
  .red   { color:red; }
</style>

<script src="https://cdn.jsdelivr.net/npm/browser-scss@1.0.3/dist/browser-scss.min.js"></script>

</head>
<body>
  <main>
   <form action="/Users/DupCheck" method="post">
    <h2>Id 중복확인</h2>
    <input type="text"    name="userid" value="${ param.userid }" />
    <input type="submit"  value="검색" /> 
   </form>
   <div style="height:20px;"></div>
   <c:choose>
    <c:when test="${ userVo eq null }">
     <p class="green">
      ${ param.userid }는 사용가능한 아이디입니다
      <input type="button" id="btnClose" value="ID 사용하기" /> 
     </p>
    </c:when>
    <c:otherwise>
     <p class="red">${ param.userid }는 사용할 수 없는 아이디입니다</p>
    </c:otherwise>
   </c:choose>
  </main>   
  
  <script>
     const  btnCloseEl = document.getElementById('btnClose')
     btnCloseEl.onclick = function() {
        // 넘겨줄 창의 userid 에 조회괸 결과를 보낸다
        // 넘겨줄 창(내창을 open한 window) : window.opener -> mf
        // 내창 : window                                   -> cf
        const  mfUseridEl =  window.opener.document.querySelector('[name=userid]');
        const  cfUseridEl =  window.document.querySelector('[name=userid]')
        mfUseridEl.value  =  cfUseridEl.value;
        window.close();
     }
  
  </script>
</body>
</html>







