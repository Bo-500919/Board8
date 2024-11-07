package com.board.pds.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.board.pds.vo.FilesVo;
import com.board.pds.vo.PdsVo;

public interface PdsService {

	List<PdsVo> getPdsList(HashMap<String, Object> map);

	void setWrite(HashMap<String, Object> map, MultipartFile[] uploadfiles);

	List<PdsVo> getPdsPagingList(HashMap<String, Object> map);

	void setReadcountUpdate(HashMap<String, Object> map);

	PdsVo getPds(HashMap<String, Object> map);

	List<FilesVo> getFileList(HashMap<String, Object> map);

	void setUpdate(HashMap<String, Object> map, MultipartFile[] uploadfiles);

	FilesVo getFileInfo(Long file_num);
	
}
