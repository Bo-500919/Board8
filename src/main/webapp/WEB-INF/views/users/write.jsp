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

<script src="https://code.jquery.com/jquery.min.js"></script>
<style>
  td {
     padding : 10px;
  }
  td:nth-of-type(1) { 
     text-align: center;  
     width : 200px; 
     background : #666;
     color : white;
     font-weight: bold;   
     border : 1px solid white;    
  } 
  td:nth-of-type(2) { width : 600px; }
  
  tr:last-child > td { 
      background: white; 
      border: 1px solid black
  }
  
  input                  { width : 100%; } 
  input[type=submit]     { width : 100px;  } 
  input[name=userid]     { width : 25%;  } 
  #goList                { width : 100px;  } 
  #dupCheck, #dupCheck2  { width : 100px; }
  
  .red                   { color:red; }
  .green                 { color:green; }

</style>

</head>
<body>
  <main>  
    <h2>사용자 등록</h2>
    <form action="/Users/Write"  method="POST">
    <table>
     <tr>
      <td><span class="red">*</span>사용자 아이디</td>
      <td>
      <input type="text"    name="userid" />
      <input type="button"  id="dupCheck"   value="중복확인" />
      <input type="button"  id="dupCheck2"  value="중복확인(새창)" />
      <span id="dupResult"></span>
      </td>
     </tr>
     <tr>
      <td><span class="red">*</span>비밀번호</td>
      <td><input type="password" name="passwd" id="passwd1" /></td>
     </tr>
     <tr>
      <td><span class="red">*</span>비밀번호 확인</td>
      <td><input type="password" id="passwd2" /></td>
     </tr>
     <tr>
      <td><span class="red">*</span>사용자 이름</td>
      <td><input type="text" name="username" /></td>
     </tr>
     <tr>
      <td>이메일</td>
      <td><input type="email" name="email" /></td>
     </tr>  
     <tr>
      <td colspan="2">
       <input type="submit" value="회원가입" />
       <input type="button" value="목록" id="goList" />
      </td>
     </tr>
    
    </table>    
    </form>
  
    <script>
       let   dupCheckClicked = false;   
    
       const  goListEl = document.getElementById('goList')
       goListEl.onclick = function() {
          location.href = '/Users/List'
       }    
       
       const  formEl       = document.querySelector('form');
       const  useridEl     = document.querySelector('[name=userid]');
       const  passwd1El    = document.querySelector('#passwd1');
       const  passwd2El    = document.querySelector('#passwd2');
       const  usernameEl   = document.querySelector('[name=username]');
       const  dupCheckEl   = document.querySelector('#dupCheck');
       const  dupCheck2El  = document.querySelector('#dupCheck2');
       
       // 중복확인 버튼이 클리되면 
       dupCheckEl.onclick = function() {
           //  아이디를 중복확인 
    	   dupCheckClicked = true;  
       }
       
       // 중복확인(새창) 버튼이 클릭되면
       dupCheck2El.onclick = function() {
           // 새창을 연다
           let  html     = '/Users/DupCheck?userid=' +  useridEl.value
        		   // 새 창에 보여줄 html(jsp) 파일
           let  name     = 'dupcheck';  // 이름을 주면 새창은 한번만 열림
           let  features = 'height=300,width=500,top=250,left=800';
           window.open(html, name, features)
           //dupCheckClicked = true; 

       }
       
       // 회원가입버튼 클릭
       formEl.onsubmit   = function () {           
		   if(  useridEl.value.trim() == ''  ) {
               alert('아이디를 입력하세요')
               useridEl.focus()
           	   return  false;
		   } 
		   if( passwd1El.value.trim() == '' ) {
			   alert('비밀번호를 입력하세요')
               passwd1El.focus()
	           return  false;
		   }
		   if( passwd2El.value.trim() == '' ) {
			   alert('비밀번호확인을 입력하세요')
               passwd2El.focus()
	           return  false;
		   }          
           if( passwd1El.value != passwd2El.value ) {
        	   alert('비밀번호가 일치하지 않습니다')
               passwd2El.focus()
        	   return  false;
           }
           if( usernameEl.value.trim().length < 2 ) {
        	   alert('이름은 2자 이상 입력하세요')
               usernameEl.focus()
	           return  false;
		   }	
           
           // 중복확인 버튼을 클릭하였는가?
           // alert(dupCheckClicked)
           if(  dupCheckClicked == false ) {
        	   alert('중복확인을 하세요')
               return false;
           }
		   return  true;
	   }
    </script> 
    <script>
       $( function() {
           $('#dupCheck').on('click', function() {
               // ajax 는 페이지을 변경하지 않고 서버에서 조회한 결과돌려받는다
               // XHR(XMLHttpRequest), $.ajax(), fetch(), axios()
        	   $.ajax({
                   url  : '/Users/IdDupCheck',
                  // data : '?userid=' + $('[name=userid]').val()  
                   data : { userid : $('[name=userid]').val()  }  
        	       // data :  넘겨주는 파라미터를 담는 변수
               })
               .done( function( data ) { 
                   // data : 서버가 보내준 결과 (json)   
                   console.log(data)
                   if( data == '' ) {
                     //alert('사용가능한 아이디입니다')
                     let html = '사용가능한 아이디입니다'; // + data.userid
                     dupCheckClicked = true;
                     $('#dupResult').html(html).addClass('green')
                   }  else  {  
                     //alert('사용할수 없는 아이디입니다')
                     let html = '사용할수 없는 아이디입니다'
                     dupCheckClicked = false;
                   	 $('#dupResult').html(html).addClass('red')
                   }
               })
               .fail( function(err) {
                   console.log(err)
               }) 
           })
       })     
    </script>
    <script>
    // 새 창을 이용한 중복확인
    // https://yejip.com/web/2020-11-26-%EA%B2%8C%EC%8B%9C%ED%8C%903/
    
    
    
    </script>
  
  </main>
</body>
</html>















