package com.board.pds.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.board.pds.vo.FilesVo;
import com.board.pds.vo.PdsVo;

@Mapper
public interface PdsMapper {

	List<PdsVo> getPdsList(HashMap<String, Object> map);

	void setWrite(HashMap<String, Object> map);

	void setFileWriter(HashMap<String, Object> map);

	int count(String menu_id);

	int countsearch(
			@Param("menu_id") String menu_id,
			@Param("search") Object search,
			@Param("searchtext") Object searchtext);

	void setReadcountUpdate(HashMap<String, Object> map);

	PdsVo getPds(HashMap<String, Object> map);

	List<FilesVo> getFileList(HashMap<String, Object> map);

	void setUpdate(HashMap<String, Object> map);

	void updateFileWriter(HashMap<String, Object> map);

	FilesVo getFileInfo(Long file_num);





	
}
