package com.board.menus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.menus.domain.MenuDTO;
import com.board.menus.mapper.MenuMapper;


@Controller
@RequestMapping("/Menus")
public class MenuController {
	
	@Autowired // 자동 new
	private MenuMapper menuMapper;
	
	@RequestMapping("/List")
	public String list(Model model) {
		
		// <MenuDTO> - MenuDTO 타입의 객체만 받을거라는 의미
		// menuMapper.getMenuList() 결국 리턴하는게 dto 타입의 객체들
		List<MenuDTO> menuList = menuMapper.getMenuList(); // 인터페이스안에 getMenuList()를 실행
														   // 이 함수는 @Mapping으로 연결된 xml 쿼리문임
		System.out.println(menuList );                    
		// 실행결과 안에 값들
		// menuList.add( new MenuDTO("Menu01", "Spring", 1) )
		// menuList.add( new MenuDTO("Menu02", "Java", 2) )
		// ...
						  //key(jstl)  value
		model.addAttribute("menuList",menuList);
		
		return "menus/list";
	}
	
	@RequestMapping("/WriteForm")
	public String writeForm() {
		return "menus/write";
	}
	@RequestMapping("/WriteForm2")
	public String updateForm2(@RequestParam("menu_name") String menu_name) {
		return "menus/write2";
	}
	
	/*
	@RequestMapping("/Menus/Write")
	public String write(MenuDTO menuDTO, Model model) {
		
		List<MenuDTO> menuList = menuMapper.getMenuList();
		model.addAttribute("menuList", menuList);
		System.out.println(menuDTO);
		menuMapper.insertMenu(menuDTO);
		
		return "menus/list";
	*/
	@RequestMapping("/Write")
	public String write(MenuDTO menuDTO) {
		
		System.out.println(menuDTO);
		menuMapper.insertMenu(menuDTO);
			
//		return "menus/list";  // Model 없으면 값을 못받아버림
		return "redirect:/Menus/List"; // response.sendRedirect()
	}
	
	@RequestMapping("/Write2")
	public String wirte2( MenuDTO menuDTO ) {
		// menu_name 만 넘어온
		System.out.println(menuDTO);
		
		// 메뉴 추가
		menuMapper.insertMenu2(menuDTO);
		return "redirect:/Menus/List";
	}
	
	@RequestMapping("/Delete")
	public String delete(MenuDTO menuDTO ) {
		
		System.out.println(menuDTO);
		menuMapper.deleteMenu(menuDTO);
		
		return "redirect:/Menus/List";
	}
	
	@RequestMapping("/UpdateForm")
	public String updateForm(MenuDTO menuDTO,
							  Model model) {
		//넘어온 정보 확인 (MENU_ID)
		System.out.println( menuDTO );
		
		// 넘어온 정보를 menu 에 담음
		MenuDTO menu = menuMapper.getMenu(menuDTO);
		// model 에 menu 추가
		model.addAttribute("menu",menu);
		return "menus/update";
	}
	

	@RequestMapping("/Update")
	public String update(MenuDTO menuDTO) {
		
		menuMapper.updateMenu(menuDTO);
		return "redirect:/Menus/List";
	}
}






