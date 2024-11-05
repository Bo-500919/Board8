package com.board.pds.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.pds.mapper.PdsMapper;
import com.board.pds.service.PdsService;
import com.board.pds.vo.PdsVo;

@Service
public class PdsServiceImpl implements PdsService{

	@Autowired
	private PdsMapper pdsMapper;
	
	@Override
	public List<PdsVo> getPdsList(HashMap<String, Object> map) {
		List<PdsVo> pdsList = pdsMapper.getPdsList(map);
		return pdsList;
	}
	
}
