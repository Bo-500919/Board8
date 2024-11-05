package com.board.pds.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PdsVo {
	//Baord 자료
	private int idx;
	private String title;
	private String content;
	private String writer;
	private String regdate;
	private int hit;
	
	//Files 자료 -> 파일 개수 가져오기
	private int filescount;
	
	//Menus 자료
	private String menu_id;
	private String menu_name;
	private String menu_seq;
	
}
