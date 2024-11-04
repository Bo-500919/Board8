package com.board.menus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.board.menus.mapper.MenuMapper;
import com.board.menus.vo.MenuVo;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/Menus")
public class MenuController {
	
	@Autowired
	private  MenuMapper  menuMapper;
	
	// 메뉴 목록 조회  http://localhost:9090/Menus/List
	@RequestMapping("/List")   
	public   String   list( Model model ) {
		// 조회한결과를 ArrayList 로 돌려준디
		List<MenuVo> menuList = menuMapper.getMenuList();
		System.out.println(menuList);
				
		// 조회 결과를 넘겨준다 ( Model 에 담아서 )
		model.addAttribute("msg", "하하" );
		model.addAttribute("menuList", menuList );
		//System.out.println( "MenuController list() menuList:" + menuList );
		
		return "menus/list";
	}
	
	// /Menus/WriteForm
	// 새 메뉴 추가기능
	@RequestMapping("/WriteForm")
	public  String   writeForm() {
		return  "menus/write";
	}
	
	// /Menus/Write
	///Menus/Write?menu_id=MENU04&menu_name=Javascript&menu_seq=4
	@RequestMapping("/Write")
	public  String  write( MenuVo menuVo, Model model ) {
		// 넘어오는 값을 받아서 db 에 저장하고
		menuMapper.insertMenu( menuVo );
		
		return "redirect:/Menus/List";
		/*
		// list.jsp 에 출력할 data를 조회하여 model 에 담는다
		List<MenuVo> menuList = menuMapper.getMenuList();
		
		model.addAttribute("menuList", menuList);
		
		// 목록보기 페이지(list.jsp)로 이동
		return  "menus/list";
		*/
		
	}
	
	//----------------------
	// /Menus/WriteForm2
	// 새 메뉴 추가기능
	@RequestMapping("/WriteForm2")
	public  String   writeForm2() {
		return  "menus/write2";
	}
	
	@RequestMapping("/Write2")
	public  String   write2(MenuVo menuVo, Model model) {
		// 메뉴추가
		menuMapper.insertMenu2( menuVo );
		
		// 목록조회
		return  "redirect:/Menus/List";
	}
	
	// http://localhost:9090/Menus/Delete?menu_id=MENU01
	@RequestMapping("/Delete")
	public   String   delete(MenuVo menuVo) {
		// 삭제할 메뉴아이디를 받아서 mapper 롤 삭제하고
		menuMapper.deleteMenu(menuVo);
		
		// 다시 목록조회로 이동		
		return   "redirect:/Menus/List";
	}
	
	// http://localhost:9090/Menus/UpdateForm?menu_id=MENU01
	@RequestMapping("/UpdateForm")
	public  String  updateForm( MenuVo menuVo, Model model ) {
		
		// 수정할 자료를 조회 (?menu_id=MENU01)
		MenuVo  menu  =  menuMapper.getMenu( menuVo.getMenu_id() ); 
		System.out.println( menu );
		// 수정할 화면으로 이동
		model.addAttribute("menu", menu);
		
		return "menus/update";
	} 
	// /Menus/Update
	@RequestMapping("/Update")
	public  String   update( MenuVo menuVo ) {
		// 수정하기
		menuMapper.updateMenu( menuVo );
		
		// 수정후 목록조회
		return  "redirect:/Menus/List";
	}  
	
}


























