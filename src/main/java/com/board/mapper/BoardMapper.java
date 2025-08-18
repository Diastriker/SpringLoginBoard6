package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.board.domain.BoardDTO;
import com.board.menus.domain.MenuDTO;

@Mapper
public interface BoardMapper {
	List<BoardDTO> getBoardList(@Param("menu_name") String menu_name);

	List<BoardDTO> getTotalList( MenuDTO menuDTO);

	void insertBoard(BoardDTO boardDTO);

	void deleteView(BoardDTO boardDTO);

	BoardDTO getView(BoardDTO boardDTO);

	BoardDTO getViewList(BoardDTO boardDTO);

	void update(BoardDTO boardDTO);

	void incHit(BoardDTO boardDTO);
}
