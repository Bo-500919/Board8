package com.board.paging.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PagingResponse<T> {
	// 현재 페이지에 보여줄 db 자료 : 10 줄 짜리 record - select 결과
	private   List<T>      list = new ArrayList<>();  
	
	// 아래의 pagins.jsp 에서 사용할 변수들
	private   Pagination   pagination;

	public PagingResponse(List<T> list, Pagination pagination) {
		this.list.addAll( list );
		this.pagination = pagination;
	} 
	
	
	
}






