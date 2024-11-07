package com.board.pds.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.board.pds.mapper.PdsMapper;
import com.board.pds.service.PdsService;
import com.board.pds.vo.FilesVo;
import com.board.pds.vo.PdsVo;

@Service
public class PdsServiceImpl implements PdsService{

	//Application.properties 의 part4.upload-path 를 가져오기
	@Value("${part4.upload-path}")
	private String uploadPath;
	
	@Autowired
	private PdsMapper pdsMapper;
	
	@Override
	public List<PdsVo> getPdsList(HashMap<String, Object> map) {
		List<PdsVo> pdsList = pdsMapper.getPdsList(map);
		return pdsList;
	}

	@Override
	public void setWrite(HashMap<String, Object> map, MultipartFile[] uploadfiles) {
		//파일저장 + 자료실 글쓰기
		//1. 파일저장
		//uploadfiles [] -> D:\dev\data\
		map.put("uploadPath", uploadPath);
		

		//PdsFile class (별도 생성) ->  파일처리 전담 클래스
		System.out.println("PdsFile 호출 이전 map:"+map);
		PdsFile.save(map, uploadfiles);
		System.out.println("PdsFile 호출 이후 map:"+map);
		
		//Board db저장
		pdsMapper.setWrite(map);
		
		//Files db저장
		List<FilesVo> fileList = (List<FilesVo>) map.get("fileList");
		if(fileList.size() > 0) {
			pdsMapper.setFileWriter(map); //넘어온 여러개의 파일을 저장
		}
	}

	@Override
	public List<PdsVo> getPdsPagingList(HashMap<String, Object> map) {
		List<PdsVo> pdsList = pdsMapper.getPdsList(map);
		return pdsList;
	}

	@Override
	public void setReadcountUpdate(HashMap<String, Object> map) {
		pdsMapper.setReadcountUpdate(map);
	}

	@Override
	public PdsVo getPds(HashMap<String, Object> map) {
		PdsVo pdsVo = pdsMapper.getPds(map);
		return pdsVo;
	}

	@Override
	public List<FilesVo> getFileList(HashMap<String, Object> map) {
		List<FilesVo> fileList = pdsMapper.getFileList(map);
		System.out.println("PdsSerImp fileList : "+fileList);
		return fileList;
	}

	@Override
	public void setUpdate(HashMap<String, Object> map, MultipartFile[] uploadfiles) {
		// 업로드된 파일경로 -> map
		map.put("uploadPath", uploadPath);
		
		//업로드된 파일을 폴더에 저장 -> 저장된 정보 -> map
		PdsFile.save(map, uploadfiles);
		
		//Files table 정보저장 <- map정보를 이용해서
		List<FilesVo> fileList = (List<FilesVo>) map.get("fileList");
		if(fileList.size() > 0) {
			pdsMapper.updateFileWriter(map);
		}
		
		//Board table 정보저장
		pdsMapper.setUpdate(map);
		
	}

	@Override
	public FilesVo getFileInfo(Long file_num) {
		FilesVo fileVo = pdsMapper.getFileInfo(file_num);
		return fileVo;
	}
	
}
