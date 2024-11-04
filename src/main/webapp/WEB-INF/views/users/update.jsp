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
<style>
  td {
     padding : 10px;
  }
  td:nth-of-type(1) { 
     text-align: center;  
     width : 200px; 
     background : black;
     color : white;
     font-weight: bold;   
     border : 1px solid white;    
  } 
  td:nth-of-type(2) { width : 600px; }
  
  tr:last-child > td { 
      background: white; 
      border: 1px solid black
  }
  
  input { width : 100%; } 
  input[type=submit] { width : 100px;  } 
  input[name=userid] { width : 50%;  } 
  #goList            { width : 100px;  } 
  #dupCheck          { width : 100px; }

</style>

</head>
<body>
  <main>  
    <h2>사용자 수정</h2>
    <form action="/Users/Update"  method="POST">
    <table>
     <tr>
      <td>사용자 아이디</td>
      <td>
      <input type="text"  name="userid" value="${ userVo.userid }" readonly />     
      </td>
     </tr>
     <tr>
      <td>새 비밀번호</td>
      <td><input type="password" name="passwd" id="passwd1" /></td>
     </tr>
     <tr>
      <td>새 비밀번호 확인</td>
      <td><input type="password" id="passwd2" /></td>
     </tr>
     <tr>
      <td>사용자 이름</td>
      <td><input type="text"    name="username" value="${ userVo.username }" /></td>
     </tr>
     <tr>
      <td>이메일</td>
      <td><input type="email"   name="email" value="${ userVo.email }"/></td>
     </tr>  
     <tr>
      <td>포인트</td>
      <td><input type="number"  value="${ userVo.upoint  }" readonly /></td>
     </tr>  
     <tr>
      <td>가입일</td>
      <td><input type="text"    value="${ userVo.regdate }" readonly /></td>
     </tr>  
     <tr>
      <td colspan="2">
       <input type="submit" value="수정" />
       <input type="button" value="목록" id="goList" />
      </td>
     </tr>
    
    </table>    
    </form>
  
    <script>
       const  goList = document.getElementById('goList')
       goList.onclick = function() {
          location.href = '/Users/List'
       }    
       
       
    </script> 
  
  </main>
</body>
</html>















