<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="icon" type="image/png" href="/img/favicon.png" />
<link rel="stylesheet"  href="/css/common.css" />
<script src="https://cdn.jsdelivr.net/npm/browser-scss@1.0.3/dist/browser-scss.min.js"></script>
</head>
<body>
   <main>
     <form action="/Users/Login" method="POST">
       <input type="hidden" name="uri"      value="${ uri     }" />
       <input type="hidden" name="menu_id"  value="${ menu_id }" />
       <input type="hidden" name="nowpage"  value="${ nowpage }" />
       <h2>로그인</h2>
       <table>
         <tr>
           <td>아이디</td>
           <td><input type="text" name="userid"  value="sky" /></td>
         </tr>
         <tr>
           <td>암호</td>
           <td><input type="password" name="passwd" value="1234" /></td>
         </tr>
         <tr>
           <td colspan="2">
             <input type="submit" value="로그인" />
           </td>
         </tr>
       </table>
     </form>
   
   </main>
</body>
</html>






