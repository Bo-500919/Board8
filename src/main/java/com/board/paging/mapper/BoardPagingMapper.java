package com.board.paging.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.board.vo.BoardVo;

@Mapper
public interface BoardPagingMapper {

	int count(BoardVo boardVo);

	List<BoardVo> getBoardPagingList(String menu_id, String title, String writer, String content, int offset,
			int recordSize);

	void incHit(BoardVo boardVo);

	BoardVo getBoard(BoardVo boardVo);

	void insertBoard(BoardVo boardVo);

	void deleteBoard(BoardVo boardVo);

	void updateBoard(BoardVo boardVo);
	
}
