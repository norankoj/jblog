package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVO;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired UserService service;
	
	@RequestMapping(value="/loginform")
	public String loginform() {
		System.out.println("로그인 페이지 연결");
		return "user/loginForm";
		
	}
	
	@RequestMapping(value="/join")
	public String joinform() {
		System.out.println("회원가입 페이지 연결");
		return "user/joinForm";
	}
	

	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String join(@ModelAttribute UserVO vo) {
		System.out.println("insert");
		System.out.println(vo.toString());
		
	    service.write(vo);
		return "user/joinSuccess";
	}
	
	@RequestMapping("/login")
	public String login(@ModelAttribute UserVO vo, HttpSession session) {
		UserVO authUser = service.login(vo);
		if (authUser != null) {
			session.setAttribute("authUser", authUser);
			return "redirect:/";
			
		} else {

			return "redirect:/user/loginform?result=fail";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		System.out.println("로그아웃");
		return "redirect:/";
	}
	
	@ResponseBody
	@RequestMapping("/idcheck")
	public Boolean idcheck(@RequestParam("id") String id) {
		boolean isExists = service.idcheck(id);
		return isExists;
	}
	

}
