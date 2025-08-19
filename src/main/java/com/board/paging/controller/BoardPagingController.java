package com.board.paging.controller;

import java.text.MessageFormat;
import java.util.Collections;
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
import com.board.paging.domain.PageResponse;
import com.board.paging.domain.Pagination;
import com.board.paging.domain.SearchDTO;
import com.board.paging.mapper.BoardPagingMapper;

@Controller
public class BoardPagingController {
	
	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private BoardPagingMapper boardPagingMapper;
	
//	http://localhost:9090/BoardPaging/TotalList?nowpage=1&menu_id=MENU01
	@RequestMapping("/BoardPaging/List")
	public ModelAndView list(int nowpage, MenuDTO menuDTO) {
		
		//메뉴 목록 조회
		List<MenuDTO> menuList = menuMapper.getMenuList();
		
		// 게시물 목록 조회
		// menu_id = MENU01
		// nowpage : 2
		// oracle 줄번호 11 ~ 20 번까지의 자료를 조회
		// 총자료수 구하기
		int count = boardPagingMapper.count(menuDTO); // menu_id
		
		System.out.println("자료수: " + count);
		
		// page로 조회한 결과를 담아놓을 객체
		PageResponse<BoardDTO> response = null;
		if( count < 1 ) { // Menu_id 의 자료가 없다면 
			response = new PageResponse<BoardDTO>( // BoardDTO 생략 가능
					Collections.emptyList(), null
					); // 생성자를 이용해서 초기화 하겠다
				 // Collections.emptyList() : 자료가 없는 빈 리스트를 만들어서 채운다
		}
		
		// 페이징을 위한 초기설정
		SearchDTO searchDTO = new SearchDTO();
		searchDTO.setPage(nowpage);  // 현제 페이지 정보
		searchDTO.setRecordSize(2); // 페이지당 2 rows 가지고 온다
		searchDTO.setPageSize(10); // paging.jsp 에 출력할 페이지번호수
		
		// Pagination 설정
		Pagination pagination = new Pagination(count, searchDTO);
		searchDTO.setPagination(pagination);
		
		// -------------------------------------------------------------------------------
		int offset     = searchDTO.getOffset();     // 30 
		int recordSize = searchDTO.getRecordSize(); // 10
		String menu_id = menuDTO.getMenu_id();
		
		List<BoardDTO> list = boardPagingMapper.getBoardPagingList( 
				    // list : nowpage에 있는 BOARD 의 row들
					menu_id, offset, recordSize
				);
		System.out.println("0: " + list);
		// [BoardDTO(idx=19, menu_name=null, menu_id=MENU01, title=ㄹㄹㄹ, 
		//           content=null, writer=백승목, regdate=2025-08-12, hit=1)
		
		response = new PageResponse<>(list, pagination);
		
		System.out.println("menuDTO: " + menuDTO);
		menuDTO = menuMapper.getMenu(menuDTO);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("menuDTO",  menuDTO);
		System.out.println("맨밑 menuDTO : " + menuDTO);
		mv.addObject("menuList", menuList);
		mv.addObject("response", response);
		mv.addObject("searchDTO",searchDTO);
		mv.addObject("nowpage", nowpage);
		mv.setViewName("/boardpaging/list");
		
		return mv;
		
	}
	
	@RequestMapping("/BoardPaging/WriteForm")   // menu_id
	public ModelAndView writeForm(int nowpage, MenuDTO menuDTO) {
		
		ModelAndView mv = new ModelAndView();
		
		List<MenuDTO> menuList = menuMapper.getMenuList();
	    menuDTO = menuMapper.getMenu(menuDTO);
		
		mv.addObject("menuList", menuList);
		mv.addObject("nowpage", nowpage);
		mv.addObject("menuDTO", menuDTO);
		mv.setViewName("boardpaging/write");
		return mv;				
	}
	
	@RequestMapping("/BoardPaging/Write")
	public ModelAndView writeForm(int nowpage, BoardDTO boardDTO) {
		
		// 넘어온 글 저장
		boardPagingMapper.insertBoard(boardDTO);
		
		ModelAndView mv = new ModelAndView();
		String fmt = "redirect:/BoardPaging/List?menu_id=%s&nowpage=%d";
		String loc = String.format(fmt, boardDTO.getMenu_id(), nowpage);
		mv.setViewName(loc);
		return mv;
	}
	
	@RequestMapping("/BoardPaging/View") // idx,menu_id ; nowpage
	public ModelAndView view(int idx, String menu_id, int nowpage) {
		
		List<MenuDTO> menuList = menuMapper.getMenuList();
		
//		boardDTO = boardPagingMapper.getBoard(boardDTO);
		MenuDTO menuDTO = menuMapper.getMenuName(menu_id);
		
		//해당게시물의 조회수를 증가한다
		boardPagingMapper.incHit(idx);
		
		
		// 보여줄 게시물의 정보 조회(idx)
		BoardDTO board = boardPagingMapper.getBoardByIdx(idx);
		
		// board Content \n -> <br>
		String content = board.getContent();
		content        =content.replace("\n","<br>");
		board.setContent(content);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("menuList", menuList);
		mv.addObject("board", board);
		mv.addObject("menuDTO",menuDTO);
		mv.addObject("nowpage",nowpage);
		mv.setViewName("boardpaging/view");
		return mv;
	}
	
	@RequestMapping("/BoardPaging/DelView")
	public ModelAndView delete(int idx, String menu_id, int nowpage) {
		
		// 넘어온 idx 로 게시글 삭제
		boardPagingMapper.deleteBoard(idx);
		
		ModelAndView mv = new ModelAndView();
		String fmt = "redirect:/BoardPaging/List?menu_id={0}&nowpage={1}";
		String loc = MessageFormat.format(fmt, menu_id, nowpage);
		mv.setViewName(loc);
		return mv;
	}
	
	@RequestMapping("BoardPaging/UpdateForm")
	public ModelAndView  updateForm(int idx, String menu_id, int nowpage) {
		
		List<MenuDTO> menuList = menuMapper.getMenuList();
		
		MenuDTO menuDTO = menuMapper.getMenuName(menu_id);
		BoardDTO board = boardPagingMapper.getBoardByIdx(idx);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("menuDTO", menuDTO);
		mv.addObject("board", board);
		mv.addObject("menuList", menuList);
		mv.addObject("nowpage", nowpage);
		mv.setViewName("boardpaging/update");
		return mv;
	}
	
	@RequestMapping("/BoardPaging/Update") // *
	public ModelAndView update(BoardDTO boardDTO, int nowpage) {
		
		boardPagingMapper.updatePagingBoard(boardDTO); 
		String menu_id = boardDTO.getMenu_id();
		
		ModelAndView mv = new ModelAndView(); 
		mv.setViewName("redirect:/BoardPaging/List?menu_id=" + menu_id + "&nowpage=" + nowpage);
		return mv;
	}
}




