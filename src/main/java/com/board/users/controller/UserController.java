package com.board.users.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.board.users.mapper.UserMapper;
import com.board.users.vo.UserVo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/Users")
public class UserController {
	
	@Autowired
	private  UserMapper  userMapper;
	
	// 사용자 추가
	// /Users/WriteForm
	@RequestMapping("/WriteForm")
	public  ModelAndView   writeForm() {	
		ModelAndView  mv = new ModelAndView();
		mv.setViewName("users/write");
		return  mv;
	}
	
	// /Users/Write
	@RequestMapping("/Write")
	public  ModelAndView  write( UserVo vo ) {
		// write.jsp 가 넘겨준 데이터를 저장
		userMapper.insertUser( vo );
		
		// /Users/List 를 수행
		ModelAndView  mv  =  new ModelAndView();
		mv.setViewName("redirect:/Users/List");
		return  mv;
	}
	
	// 사용자 목록
	// http://localhost:9090/Users/List
	@RequestMapping("/List")
	public  ModelAndView  list() {
		List<UserVo>  userList = userMapper.getUserList();		
		
		ModelAndView  mv  =  new ModelAndView();
		mv.setViewName("users/list");   // /WEB-INF/views/users/list.jsp
		mv.addObject("msg", "/Users/List");
		mv.addObject("userList", userList);
		return        mv;
	}
	
	// 회원 삭제
	// http://localhost:9090/Users/Delete?userid=sky2
	@RequestMapping("/Delete")
	public  ModelAndView  delete(UserVo vo) {
		// 삭제
		userMapper.deleteUser( vo ); 
		
		// 목록조회
		ModelAndView  mv  =  new ModelAndView();
		mv.setViewName("redirect:/Users/List");
		return        mv;
	}
	
	// 회원정보 수정
	// http://localhost:9090/Users/UpdateForm?userid=sky
	@RequestMapping("/UpdateForm")
	public  ModelAndView  updateForm(UserVo vo) {
		// 수정할 정보를 조회
		System.out.println(vo);
		UserVo  userVo =  userMapper.getUser( vo );
		
		// 수정할 정보를 입력받는 페이지 이동
		ModelAndView  mv  =  new ModelAndView();
		mv.setViewName("users/update");	
		mv.addObject("userVo", userVo);
		return        mv;
	}
	
	// /Users/Update
	@RequestMapping("/Update")
	public  ModelAndView  update(UserVo vo) {
		// db 수정
		userMapper.updateUser(vo);
		
		// 목록조회
		ModelAndView  mv  =  new ModelAndView();
		mv.setViewName("redirect:/Users/List");
		return        mv;
	}
	
	//-------------------------------
	// 새창방식 중복확인
	// /Users/DupCheck?userid=sky
	@RequestMapping("/DupCheck")
	public  ModelAndView  dupCheck(UserVo vo) {
		// 중복확인을 위한 조회
		UserVo  userVo  =  userMapper.getUser(vo);
		
		// 결과를 새창에 출력할 jsp 를 리턴 
		ModelAndView  mv  = new ModelAndView();
		mv.setViewName("users/dupcheck");   // 새창에 보여줄 jsp 
		mv.addObject("userVo", userVo);   // 새창에 보여줄 결과    
		return        mv;
	}
	
	//---------------------------------------
	// ajax 방식 호출
	// 아이디 중복확인 - 페이지를 변경하지 않고 data 조회
	// 비동기 호출로 구현 AJAX
	// @ResponseBody - jsp 파일명이 아닌 결과문자열(json) 리턴 
	// http://localhost:9090/Users/IdDupCheck?userid=aaa
	@RequestMapping(
		value   = "/IdDupCheck",
		method  = RequestMethod.GET,
		headers = "Accept=application/json" )  // 결과를 json 으로 처리 
	@ResponseBody                              // json 문자열 리턴하겠다
	public  UserVo   idDupCheck(String userid) {
		String  result = "";  // 조회결과
		//  db 조회
		UserVo  userVo = userMapper.idDupCheck( userid  );		
		return  userVo;
	} 
	
	//------------------------------------
	// Login
	
	// /Users/LoginForm
	@GetMapping("/LoginForm")
	public  String  loginForm(
			String uri, String menu_id, String nowpage,  Model model) {
		model.addAttribute("uri",     uri);
		model.addAttribute("menu_id", menu_id);
		model.addAttribute("nowpage", nowpage);
		return "users/login";
	}	
	
	// /Users/Login
	@PostMapping("/Login")
	public  String   login(
		HttpServletRequest   request,
		HttpServletResponse  response
		) {
		String       userid    = request.getParameter("userid");
		String       passwd    = request.getParameter("passwd");
		String       uri       = request.getParameter("uri");
		String       menu_id   = request.getParameter("menu_id");
		String       nowpage   = request.getParameter("nowpage");
		
		// db 조회
		UserVo       vo      = userMapper.login(userid, passwd);
		System.out.println(vo);
			
		HttpSession  session = request.getSession();
		session.setAttribute("login", vo );
		
		return  "redirect:/" + uri + "/List"  
		        + "?menu_id="      + menu_id
		        + "&nowpage="      + nowpage ;
		
	}
	
	//  /Users/Logout
	@RequestMapping(value="/Logout",
		method = RequestMethod.GET)
	public   String   logout(
		HttpServletRequest    request,
		HttpServletResponse   response,
		HttpSession           session
			) {
		
		//Object url = session.getAttribute("URL");
		session.invalidate();
		
		//return "redirect:" + (String) url;
		return  "redirect:/";
	}
	
	
}







