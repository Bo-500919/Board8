package com.board.pds.service;

import java.util.HashMap;
import java.util.List;

import com.board.pds.vo.PdsVo;

public interface PdsService {

	List<PdsVo> getPdsList(HashMap<String, Object> map);

}
