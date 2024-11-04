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
     text-align: center;
  }
  td:nth-of-type(1) { 
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
  input#goList       { width : 100px;  } 

</style>

</head>
<body>
  <main>  
    <h2>메뉴 등록</h2>
    <form action="/Menus/Write"  method="GET">
    <table>
     <tr>
      <td>메뉴 아이디</td>
      <td><input type="text" name="menu_id" /></td>
     </tr>
     <tr>
      <td>메뉴 이름</td>
      <td><input type="text" name="menu_name" /></td>
     </tr>
     <tr>
      <td>메뉴 순서</td>
      <td><input type="number" name="menu_seq" /></td>
     </tr>
     <tr>
      <td colspan="2">
       <input type="submit" value="추가" />
       <input type="button" value="목록" id="goList" />
      </td>
     </tr>
    
    </table>    
    </form>
  
    <script>
       const  goList = document.getElementById('goList')
       goList.onclick = function() {
          location.href = '/Menus/List'
       }    
    </script> 
  
  </main>
</body>
</html>















