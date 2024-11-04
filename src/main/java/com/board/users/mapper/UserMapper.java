package com.board.users.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.users.vo.UserVo;

@Mapper
public interface UserMapper {

	void insertUser(UserVo vo);

	List<UserVo> getUserList();

	void deleteUser(UserVo vo);

	UserVo getUser(UserVo vo);

	void updateUser(UserVo vo);

	UserVo idDupCheck(String userid);

	UserVo login(String userid, String passwd);

}







