package com.board.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.board.board.mapper.BoardMapper;
import com.board.board.vo.BoardVo;
import com.board.menus.mapper.MenuMapper;
import com.board.menus.vo.MenuVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/Board")
public class BoardController {
	
	@Autowired
	private  MenuMapper    menuMapper;
	
	@Autowired
	private  BoardMapper   boardMapper;
	
	// http://localhost:9090/Board/List?menu_id=MENU01
	// /Board/List?menu_id=MENU02
	@RequestMapping("/List")
	public  ModelAndView  list(  MenuVo  menuVo ) {
		
		log.info("------" + menuVo );	
		// INFO c.b.b.c.BoardController -
		// ------MenuVo [menu_id=MENU01, menu_name=null, menu_seq=0] 
		
		// 메뉴목록 전체를 조회 -> menus.jsp 에 사용
		List<MenuVo> menuList  = menuMapper.getMenuList(); 
		
		/*
		MenuVo       mVo       = menuMapper.getMenu(menu_id);
		String       menu_name = mVo.getMenu_name();
		*/
		String       menu_id   = menuVo.getMenu_id();
		System.out.println( "menuVo : " +  menuVo  );
		String       menu_name = menuMapper.getMenuName( menuVo );
		
		//  게시글 목록 조회 -> model
		List<BoardVo>  boardList = boardMapper.getBoardList( menuVo );  
		
		// ModelAndView
		ModelAndView  mv  =  new ModelAndView();
		mv.addObject("menuList",  menuList);    // menuList
		mv.addObject("menu_id",   menu_id);
		mv.addObject("menu_name", menu_name);   // menuList
		mv.addObject("boardList", boardList );  // boardList 
		mv.setViewName("board/list");
		return        mv;
	}
	
	// http://localhost:9090/Board/WriteForm?menu_id=MENU01
	// /Board/WriteForm
	@RequestMapping("/WriteForm")
	public  ModelAndView  writeForm( MenuVo menuVo ) {
		// 메뉴목롤 조히
		List<MenuVo>  menuList = menuMapper.getMenuList();
 		
		ModelAndView  mv  = new ModelAndView();
		mv.addObject("menuList", menuList);
		mv.addObject("menu_id", menuVo.getMenu_id());
		mv.setViewName("board/write");
		return        mv;
	}
	
	// /Board/Write
	// menu_id, title, writer, content
	@RequestMapping("/Write")
	public  ModelAndView   write( BoardVo boardVo ) {
		// 굴쓰기
		boardMapper.insertBoard(  boardVo  );
		
		String  menu_id   =  boardVo.getMenu_id();		
		// 목록 조회
		ModelAndView  mv  =  new ModelAndView();
		mv.setViewName("redirect:/Board/List?menu_id=" + menu_id);
		return        mv;
	}
	
	// 게시물 내용보기
	// http://localhost:9090/Board/View?idx=5
	@RequestMapping("/View")
	public  ModelAndView  view( BoardVo  boardVo ) {
		
		// 상단 메뉴처리
		List<MenuVo>  menuList = menuMapper.getMenuList(); 
		
		// 조회수 증가
		boardMapper.incHit(  boardVo );
		
		// IDX 롤 글 조회
		BoardVo       vo       = boardMapper.getBoard( boardVo ); 	
		System.out.println("/Board/View vo:" + vo );
		
		// 조회한 글의 content 부분의 '\n' -> '<br>'
		// 출력하는 곳이 <td></td>
		String       content   =  vo.getContent().replace("\n", "<br>");
		vo.setContent( content );
						
		ModelAndView  mv  = new ModelAndView();
		mv.addObject("menuList", menuList);
		mv.addObject("vo",       vo);
		mv.setViewName("board/view");
		return        mv;
	}
	
	// 삭제
	// http://localhost:9090/Board/Delete?idx=7&menu_id=MENU01
	@RequestMapping("/Delete")
	public  ModelAndView   delete(BoardVo boardVo) {
		System.out.println("delete boardVo" + boardVo);
		// 게시물 삭제
		boardMapper.deleteBoard( boardVo );
		
		String  menu_id  = boardVo.getMenu_id() ; 
		// BoardVo vo       = boardMapper.getBoard(boardVo); 
		// String  menu_id  = vo.getMenu_id(); 
		
		// 게시물 목록으로 이동
		ModelAndView  mv  =  new ModelAndView();
		mv.setViewName("redirect:/Board/List?menu_id=" + menu_id);
		return        mv;		
	}
	
	// 수정
	// http://localhost:9090/Board/UpdateForm?idx=7&menu_id=MENU01
	@RequestMapping("/UpdateForm")
	public  ModelAndView  updateForm(BoardVo boardVo) { 
		// 메뉴 목록 조회
		List<MenuVo>  menuList  =  menuMapper.getMenuList();
				
		// 수정할 데이터를 조회
		BoardVo       vo        =  boardMapper.getBoard( boardVo );
		
		// 수정페이지로 이동
		ModelAndView  mv  =  new  ModelAndView();
		mv.addObject("menuList", menuList);
		mv.addObject("vo", vo);
		mv.setViewName("board/update");
		return        mv;
	}
	
	// http://localhost:9090/Board/Update
	// idx, menu_id, title, content
	@RequestMapping("/Update")
	public  ModelAndView  update( BoardVo boardVo  ) {
		// 넘어온 정보로 수정
		boardMapper.updateBoard( boardVo  );
		
		int            idx    =   boardVo.getIdx();
		// 목록조회로 이동 / 수정된 내용확인용 view 로 이동
		ModelAndView   mv     =  new ModelAndView();
		mv.setViewName("redirect:/Board/View?idx=" + idx);
		return         mv;
	}
	
}
















