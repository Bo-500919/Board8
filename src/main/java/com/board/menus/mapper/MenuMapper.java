package com.board.menus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.menus.vo.MenuVo;

@Mapper
public interface MenuMapper {

	List<MenuVo> getMenuList();

	void insertMenu(MenuVo menuVo);

	void insertMenu2(MenuVo menuVo);

	void deleteMenu(MenuVo menuVo);

	MenuVo getMenu(String menu_id);

	void updateMenu(MenuVo menuVo);

	String getMenuName(MenuVo menuVo);

	
}







