package com.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.board.domain.BoardDTO;
import com.board.mapper.BoardMapper;
import com.board.menus.domain.MenuDTO;
import com.board.menus.mapper.MenuMapper;



@Controller
@RequestMapping("/Board")
public class BoardController {
	
	@Autowired
	private MenuMapper menuMapper;
	@Autowired
	private BoardMapper boardMapper;
	
	// http://localhost:9090/Board/TotalList?menu_id=MENU04
	@RequestMapping("/TotalList")  // menu_id
	public ModelAndView list( MenuDTO menuDTO) {
		// 메뉴 리스트
		List<MenuDTO> menuList = menuMapper.getMenuList();
		
		// 게시물 목록처리
		List<BoardDTO> boardList = boardMapper.getTotalList(menuDTO);
		
		MenuDTO menudto = menuMapper.getMenu(menuDTO);
		ModelAndView mv = new ModelAndView();
		mv.addObject("menudto",menudto);
		mv.addObject("boardList",boardList);
		mv.addObject("menuList",menuList);
		mv.setViewName("board/boardtotallist"); // jsp 파일
		return mv;
	}
	
	@RequestMapping("")
	public ModelAndView list( @RequestParam("menu_name") String menu_name ) {
		List<BoardDTO> boardList = boardMapper.getBoardList(menu_name);
		ModelAndView mv = new ModelAndView();
		mv.addObject("boardList", boardList); // model.addAttribute 역할
		mv.setViewName("board/boardlist"); // jsp 파일
		return mv;
		/*
		List<BoardDTO> list = boardMapper.getBoardList(menu_name);
		model.addAttribute("boardList", list);
		System.out.println(list);
		return "board/boardlist";
		*/
	}
	
	
	@RequestMapping("/WriteForm")
	// http://localhost:9090/Board/WriteForm?menu_id=MENU01
									   // menu_id
	public ModelAndView writeForm(MenuDTO menuDTO) {
			
		// 메뉴 목록을 조회
		List<MenuDTO> menuList = menuMapper.getMenuList();
		
		menuDTO    = menuMapper.getMenu(menuDTO); 
		
		String menu_id = menuDTO.getMenu_id();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("menuDTO", menuDTO);
		mv.addObject("menuList", menuList);
		mv.setViewName("/board/boardwrite");
		return mv;
	}
	
	@RequestMapping("/Write")  // menu_id, title, content, writer
	public ModelAndView write(BoardDTO boardDTO) {
		boardMapper.insertBoard(boardDTO);
		ModelAndView mv = new ModelAndView();
		
		String menu_id = boardDTO.getMenu_id();
		
		mv.setViewName("redirect:/Board/TotalList?menu_id=" + menu_id);
		System.out.println("추가:" + boardDTO);
		return mv;
	}	
	
	@RequestMapping("/View")          // idx         // menu_id
	public ModelAndView view(BoardDTO boardDTO,MenuDTO menuDTO) {
		BoardDTO boardList = boardMapper.getView(boardDTO);
		
		// 메뉴 목록 조회
		List<MenuDTO> menuList = menuMapper.getMenuList();
		
		
		// board 테이블의 idx에 해당하는 글번호 조회수 1증가
		boardMapper.incHit(boardDTO);
		
		System.out.println("View View View View View View View :" + boardList);
		
		
		// menu_id 로 메뉴 조회
		menuDTO = menuMapper.getMenu(menuDTO);	
		/*
		// view.jsp 에 출력할 내용 검색
		BoardDTO board = boardMapper.getView(boardDTO);  // 받은 idx로 다시 select
		String menu_id = board.getMenu_id();             // select 한것들에 menu_id 를 get하기   
		MenuDTO menuDTO = new MenuDTO(menu_id, null ,0); // get 쓰기위해 menuDTO 생성자에 파마미터넣기     
	    menuDTO = menuMapper.getMenu(menuDTO);
	    */
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("boardList", boardList);
		mv.addObject("menuDTO", menuDTO);
		mv.addObject("menuList", menuList);
		mv.setViewName("/board/view");
		return mv;
	}
	
	@RequestMapping("/DelView")        // idx
	public ModelAndView delete(BoardDTO boardDTO) {
		
		// boardDTO (idx)로 삭제
		boardMapper.deleteView(boardDTO);
		String menu_id = boardDTO.getMenu_id();
		
		System.out.println(menu_id);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/Board/TotalList?menu_id=" + menu_id);
		return mv;
	}
	
	@RequestMapping("/UpdateForm")
	public ModelAndView getUpdateList(BoardDTO boardDTO) {
		System.out.println(boardDTO);
		BoardDTO updateList = boardMapper.getViewList(boardDTO);
		List<MenuDTO> menuList = menuMapper.getMenuList();
		
		System.out.println(updateList);
		
		BoardDTO board = boardMapper.getView(boardDTO);  // 받은 idx로 다시 select
		String menu_id = board.getMenu_id();             // select 한것들에 menu_id 를 get하기   
		MenuDTO menuDTO = new MenuDTO(menu_id, null ,0); // get 쓰기위해 menuDTO 생성자에 파마미터넣기     
	    menuDTO = menuMapper.getMenu(menuDTO);           // menu_name을 가져옴
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("updateList",updateList);
		mv.addObject("menuList", menuList);
		mv.addObject("menuDTO", menuDTO);
		mv.setViewName("/board/updateview");
		return mv;
	}
	
	@RequestMapping("/Update")
	public ModelAndView update(BoardDTO boardDTO) {
		System.out.println("Update Update Update Update: " + boardDTO);
		String menu_id = boardDTO.getMenu_id();
		
		//받은 정보를 수정
        boardMapper.update(boardDTO);
		
        // 돌아가기
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/Board/TotalList?menu_id=" + menu_id);
		return mv;
	}
}





