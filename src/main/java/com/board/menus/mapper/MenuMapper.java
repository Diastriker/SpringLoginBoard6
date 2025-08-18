package com.board.menus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.BoardDTO;
import com.board.menus.domain.MenuDTO;

// 이 인터페이스가 xml에 id=insertMenu랑 여기insertMenu 연결시키는 역할
@Mapper 
public interface MenuMapper {
	void insertMenu(MenuDTO menuDTO); 
	void insertMenu2(MenuDTO menuDTO);
	void deleteMenu(MenuDTO menuDTO);
	void updateMenu(MenuDTO menuDTO);
	MenuDTO getMenu(MenuDTO menuDTO);
	List<MenuDTO> getMenuList();
	MenuDTO getMenuName(String menu_id);


}

