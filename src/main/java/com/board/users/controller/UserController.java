package com.board.users.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.users.domain.UserDTO;
import com.board.users.mapper.UserMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/Users") // 전체를 mapping에 적용
public class UserController {
	@Autowired
	private UserMapper userMapper;
	@RequestMapping("/List")
	public String userlist(Model model) {
		
		List<UserDTO> userList = userMapper.getUserList();
		System.out.println(userList);
		
		model.addAttribute("userList",userList);
		return "users/userlist";
	}
	
	@RequestMapping("/WriteForm")
	public String writeForm() {
		return "users/write";
	}
	
	@RequestMapping("/Write")
	public String write(UserDTO userDTO) {
		userMapper.insertUser(userDTO);
		System.out.println(userDTO);
		return "redirect:/Users/List";
	}
	
	@RequestMapping("/Delete")
	public String delete( @RequestParam("userid") String userid ) {
		System.out.println(userid);
		
		userMapper.deleteUser(userid);
		return"redirect:/Users/List";
	}
	
	@RequestMapping("/UpdateForm")
	public String getUpdateUser(@RequestParam("userid") String userid,
								Model model) {
		UserDTO user = userMapper.getUser(userid);
		System.out.println("3:" + userid);
		model.addAttribute("user",user);
		return "users/update";
	}
	
	@RequestMapping("/Update")
	public String update(UserDTO userDTO) {
		userMapper.updateUser(userDTO);
		
		return "redirect:/Users/List";
	}
	
	// ------------------------------------------------
	// 로그인 
	// /Users/LoginForm
	// response.sendRedirect() - Get방식 호출
	// GetMapping으로 처리 : 로그인페이지로 이동
	// postMapping 사용 안됨
	@GetMapping("/LoginForm")
	public String loginForm(
			String uri, String menu_id, String nowpage, Model model) {
		model.addAttribute("uri",uri);
		model.addAttribute("menu_id",menu_id);
		model.addAttribute("nowpage",nowpage);
		System.out.println("첫 nowpage: " + nowpage);
		return "users/login";
	}
	
	// /Users/Login <- <form>
	@PostMapping("/Login")
	public String login(HttpServletRequest request,
						HttpServletRequest response) {
		// 넘어온 로그인정보 처리
		String userid  = request.getParameter("userid");
		String passwd  = request.getParameter("passwd");
		String uri     = request.getParameter("uri");
		String menu_id = request.getParameter("menu_id");
		String nowpage = request.getParameter("nowpage");
				
		//db 조회
		UserDTO user = userMapper.login(userid, passwd);
		System.out.println(user);
		
		// 다른페이지에서 볼수있도록 session 에 저장
		HttpSession session = request.getSession();
		session.setAttribute("login", user);
		System.out.println("uri: " + uri);
		System.out.println("두번째 nowpage: " + nowpage);
		//돌아갈 주소 설정	
		return "redirect:/"  + uri + "/List"
				+ "?menu_id=" + menu_id 
				+ "&nowpage=" + nowpage;
	}
	
	// /Users/Logout
	@RequestMapping(value = "/Logout",
					method = RequestMethod.GET ) // == @GetMapping
	public String louout(HttpServletRequest request,
						 HttpServletRequest response,
						 HttpSession        session) {
		
		session.invalidate(); // session을 초기화
		
		/*
		Object url = session.getAttribute("URL");
		return "redirect:" + (String url);
		*/
		return "redirect:/";
	}
}






