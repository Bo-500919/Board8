package com.board.paging.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchVo {

    private int    page;          // 현재 페이지 번호                 nowpage
    private int    recordSize;    // 페이지당 출력할 데이터 개수      pagecount
    private int    pageSize;      // 화면 하단에 출력할 페이지 사이즈 pagetotalcount (paging.jsp)
    private String keyword;       // 검색 키워드  : 검색가능한 문장
    private String searchType;    // 검색 유형    : title, content, writer
    
    private Pagination pagination;  // 페이지네이션 정보 ((paging.jsp  에서 사용할 변수 모음)

    // 생성자 : 초기값 설정  SearchVo  searchVo = new SearchVo()
    public SearchVo() {
        this.page = 1;
        this.recordSize = 10;
        this.pageSize   = 10;
    }

    public int getOffset() {
        return (page - 1) * recordSize;
    }

}

