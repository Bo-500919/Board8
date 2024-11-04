package com.board.interceptor;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {
	
	// Interceptor : 페이지가 이동될때 Controller 앞에서 가로채기하는 클래스
	// 전처리(로그인)
	@Override
	public boolean preHandle(
			HttpServletRequest  request, 
			HttpServletResponse response, 
			Object handler)
			throws Exception {	  
		
		String   requestURI     =  request.getRequestURI();
		String   menu_id        =  request.getParameter("menu_id");
    	String   nowpage        =  request.getParameter("nowpage");
	
	    System.out.println("요청 주소:" +  requestURI);
	    
	    // /Users/LoginForm , //Users/Login 를 제외
	    if(  requestURI.contains("/UsersLoginForm")  ) {
	    	return   true;
	    }
	    if(  requestURI.contains("/Users/Login")  ) {
	    	return   true;
	    }
	   
	    // 0/1    /2 
	    //  /Board/List 
	    //  /BoardPaging/List 
	    String  uri = requestURI.split("/")[1]; 
	    
	    HttpSession  session  =  request.getSession();
	    // 사용자 로그인정보를 세션 메모리에 user 저장 
	    Object       login     = session.getAttribute("login");
	    System.out.println( login );
	    if( login == null ) {
	    	// 로그인되어 있지 않다, 로그인페이지로 이동
	    
	    	String  fmt     =  "/Users/LoginForm?uri=%s&menu_id=%s&nowpage=%s"; 
	    	String  loc     = String.format(fmt, uri, menu_id, nowpage);
	    	response.sendRedirect( loc );
	    	return  false;
	    }
	    
	    // preHandle 의 return 문의 의미는
	    //   컨트롤러 요청 url 로 가도되나 안되나 결정
	    //   retrun : 컨트롤러 url 로 가게 된다 
	    	    
		return true;
	}

    /*
	// 후처리
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println( "======= postHandle() ==========" );	    
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	*/
	
	
	
	
	
}











