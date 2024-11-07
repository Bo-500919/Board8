package com.board.pds.contorller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.board.board.vo.BoardVo;
import com.board.menus.mapper.MenuMapper;
import com.board.menus.vo.MenuVo;
import com.board.paging.vo.Pagination;
import com.board.paging.vo.PagingResponse;
import com.board.paging.vo.SearchVo;
import com.board.pds.mapper.PdsMapper;
import com.board.pds.service.PdsService;
import com.board.pds.vo.FilesVo;
import com.board.pds.vo.PdsVo;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/Pds")
public class PdsController {
	
	@Autowired
	private PdsService pdsService;
	
	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private PdsMapper pdsMapper;
	
	//properties 에 써져있는 변수
	@Value("${part4.upload-path}")
	private String uploadPath;
	
	// /Pds/List?nowpage=1&menu_id=MENU01
	@RequestMapping("/List")
	public ModelAndView list(
           @RequestParam HashMap<String, Object> map) {
		//System.out.println(map); //{nowpage=1, menu_id=MENU01}
		
		List<MenuVo> menuList = menuMapper.getMenuList();
		
		ModelAndView mv = new ModelAndView();
		int count = 0;
		//menu_id, nowpage, offset, recordSize
		String menu_id = String.valueOf(map.get("menu_id"));
		if(map.get("search") != null && map.get("searchtext")!=null) {
			count = pdsMapper.countsearch(menu_id,map.get("search"), map.get("searchtext"));			
		}
		else {
			count = pdsMapper.count(menu_id);			
		}
		
		//자료수가 0일 경우
		PagingResponse<PdsVo> response = null;
	    if( count < 1 ) {   // 현재 Menu_id 조회한 자료가 없다면
	    	response = new PagingResponse<>(
	    		Collections.emptyList(), null);
	    	// Collections.emptyList() : 자료는 없는 빈 리스트를 채운다
	    }
	 // 페이징을 위한 초기설정
	    SearchVo  searchVo = new SearchVo();
	    int nowpage = Integer.parseInt(String.valueOf(map.get("nowpage")));
	    searchVo.setPage(nowpage);   // 현재 페이지 정보
	    searchVo.setRecordSize(10);  // 페이지당 10개
	    searchVo.setPageSize(10);    // paging.jsp 에 출력할 페이지번호수

	    // Pagination 설정
	    Pagination  pagination = new Pagination(count, searchVo);
	    searchVo.setPagination(pagination);
	    //-------------------------------
	    //title writer content : WHERE 조건문에 쓰임(검색시 사용)
	    /*String으로 변경시 오류
	    String title = String.valueOf(map.get("title"));
	    String writer = String.valueOf(map.get("writer"));
	    String content = String.valueOf(map.get("content"));
	    */
		int offset = searchVo.getOffset();
		int recordSize = searchVo.getRecordSize();
		
		map.put("search", map.get("search"));
	    map.put("searchtext", map.get("searchtext"));
	    //map.put("content", map.get("content"));
	    
		map.put("offset", offset);
		map.put("recordSize", recordSize);
		
		//System.out.println(map);
		List<PdsVo> pdsList = pdsService.getPdsList(map);
		//System.out.println("pdsList"+pdsList);
		response = new PagingResponse<>(pdsList, pagination);
		//System.out.println("pdsController response"+response);
		
		//mv.addObject("pdsList",pdsList);
		mv.addObject("menuList",menuList);
		mv.addObject("searchVo",  searchVo );
		mv.addObject("response", response );
		mv.addObject("map", map);
		mv.setViewName("/pds/list");
		return mv;
 	}
	
	@RequestMapping("/WriteForm")
	public ModelAndView writeForm(@RequestParam HashMap<String, Object> map) {
		ModelAndView mv = new ModelAndView();
		List<MenuVo> menuList = menuMapper.getMenuList(); 
		
		mv.addObject("map", map);
		mv.addObject("menuList", menuList);
		mv.setViewName("pds/write");
		return mv;
	}
	
	//MultipartFile[] : upfile 몇개의 파일(<- HttpServletRequest(request))
	@PostMapping("/Write")
	public ModelAndView write(@RequestParam HashMap<String, Object> map,
			            @RequestParam(value="upfile") MultipartFile[] uploadfiles) {
		//System.out.println("map : "+map);
		//map : {menu_id=MENU01, nowpage=1, title=asd, writer=asd, content=asd}
		//System.out.println("upfile : "+uploadfiles);
		//upfile : [Lorg.springframework.web.multipart.MultipartFile;@408b1a81
		pdsService.setWrite(map, uploadfiles);
		
		ModelAndView mv = new ModelAndView();
		/*
		String fmt = "redirect:/Pds/List?menu_id=%s&nowpage=%d";
		String loc = String.format(fmt, String.valueOf(map.get("menu_id")), Integer.parseInt(String.valueOf(map.get("nowpage"))));
		*/
		String fmt = "redirect:/Pds/List?menu_id=%s&nowpage=%s";
		String loc = String.format(fmt, map.get("menu_id"), map.get("nowpage"));
		mv.setViewName(loc);
		return mv;
	}
	
	//{idx=950, menu_id=MENU01, nowpage=1}
	@RequestMapping("/View")
	public ModelAndView view(
			@RequestParam HashMap<String, Object> map) {
		List<MenuVo> menuList = menuMapper.getMenuList();
		System.out.println("PdsCon map:"+map);
		
		//조회수 증가
		pdsService.setReadcountUpdate(map);
		
		//조회할 자료실 게시물 정보
		PdsVo pdsVo = pdsService.getPds(map);
		String content = pdsVo.getContent().replace("\n", "<br>");
		pdsVo.setContent(content);
		//조회할 파일 정보
		List<FilesVo> fileList = pdsService.getFileList(map);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("menuList",menuList);
		mv.addObject("map",map);
		mv.addObject("vo",pdsVo);
		mv.addObject("fileList",fileList);
		mv.setViewName("pds/view");
		return mv;
	}
	
	@RequestMapping("/UpdateForm")
	public ModelAndView updateForm(@RequestParam HashMap<String, Object> map) {
		List<MenuVo> menuList = menuMapper.getMenuList();
		System.out.println("PdsUpdate map : "+map);
		PdsVo vo = pdsMapper.getPds(map);
		List<FilesVo> fileList = pdsService.getFileList(map);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("menuList",menuList);
		mv.addObject("map", map);
		mv.addObject("vo", vo);
		mv.addObject("fileList",fileList);
		mv.setViewName("pds/update");
		return mv;
	}
	
	@RequestMapping("/Update")
	public ModelAndView update(
			@RequestParam HashMap<String, Object> map,
			@RequestParam(value="upfile") MultipartFile[] uploadfiles) {
		pdsService.setUpdate(map, uploadfiles);
		
		ModelAndView mv = new ModelAndView();
		String fmt = "redirect:/Pds/List?menu_id=%s&nowpage=%s";
		String loc = String.format(fmt, map.get("menu_id"),map.get("nowpage"));
		mv.setViewName(loc);
		return mv;
	}
	
	///Pds/filedownload/${file.file_num }
	   @RequestMapping("/filedownload/{file_num}") //pathvariable은 /를 써서 ?나 $를 쓰지않는다.
	   @ResponseBody
	   public ModelAndView downloadfile(
	         HttpServletResponse res,
	         @PathVariable(value="file_num") Long file_num) throws UnsupportedEncodingException { //파일 경로는 long타입으로 처리
	      //파일을 조회(Files Table)
	      FilesVo fileInfo = pdsService.getFileInfo(file_num);

	      //파일경로
	      Path saveFilepath = Paths.get(
	    		  uploadPath + File.separator + fileInfo.getSfilename());
	      
	      //http 헤더 설정
	      setFileHeader(res, fileInfo);
	      
	      //파일 복사 -> 함수(서버 -> 클라이언트)
	      fileCopy(res, saveFilepath);
	      
	      return null;
	   }

	//파일복사 : 실제 바이너리 데이터를 다운로드 하는 기능
	private void fileCopy(HttpServletResponse res, Path saveFilepath) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(saveFilepath.toFile());
			FileCopyUtils.copy(fis, res.getOutputStream());
			res.getOutputStream().flush(); //버퍼에 남아있는 데이터를 보냄
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	//다운 받을 파일의 header 정보 설정
	private void setFileHeader(HttpServletResponse res, FilesVo fileInfo) throws UnsupportedEncodingException {
		   res.setHeader("Content-Disposition",
				   "attachment; filename=\"" +
		           URLEncoder.encode(
	               (String) fileInfo.getFilename(), "UTF-8") + "\";");
	        res.setHeader("Content-Transfer-Encoding", "binary");
	        res.setHeader("Content-Type", "application/download; utf-8");
	        res.setHeader("Pragma", "no-cache;");
	        res.setHeader("Expires", "-1;");
	}
	
}
