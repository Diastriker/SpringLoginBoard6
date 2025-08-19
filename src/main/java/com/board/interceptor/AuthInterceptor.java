package com.board.interceptor;

import java.text.MessageFormat;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {
	
	// Interceptor : 페이지가 이동할때 Controller 실행전 가로채는 클라스
	//전처리(로그인)
	@Override
	public boolean preHandle(HttpServletRequest request,
					 		 HttpServletResponse response, 
					 		 Object handler)
					 		 throws Exception {
		
		String requestURI = request.getRequestURI();
		String menu_id    = request.getParameter("menu_id");
		String nowpage    = request.getParameter("nowpage");
		
		log.info("uri:", requestURI); // sysout 보다 가벼움
		
		// /Users/LoginForm, /Users/Login 을 제외
		// 이게 없으면 무한 반복 호출(무한 로그인 지옥)
		if(requestURI.contains("/Users/LoginForm")) {
			return true;
		}
		if(requestURI.contains("/Users/Login")) {
			return true;
		}
		
		String uri = requestURI.split("/")[1];
		
		HttpSession session = request.getSession();
		Object      login   = session.getAttribute("login");
		
		if(login == null) { // 로그인이 안돼있다면, 로그인 페이지로 이동
			String fmt = "/Users/LoginForm?uri={0}&menu_id={1}&nowpage={2}";
			String loc = MessageFormat.format(fmt, 
										uri, menu_id, nowpage);
			response.sendRedirect(loc); // GET방식 요청 
			return false;
		}
		
	    // preHandle 의 return 문의 의미는
	    //   컨트롤러 요청 url 로 가도되나 안되나 결정
	    //   retrun : 컨트롤러 url 로 가게 된다 
		
//		 return HandlerInterceptor.super.preHandle(request, response, handler);
	     return true;
	}
	
	/*
	// 후처리
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	*/
	
	
}
