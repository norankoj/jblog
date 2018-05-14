package com.javaex.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.CommentService;
import com.javaex.service.PostService;
import com.javaex.service.UserService;
import com.javaex.vo.BlogVO;
import com.javaex.vo.CategoryVO;
import com.javaex.vo.CommentsVO;
import com.javaex.vo.PostVO;


@Controller
public class MainController {
	
	@Autowired UserService service;
	@Autowired PostService postservice;
	@Autowired CommentService commentservice;
	
	@RequestMapping("/")
	public String main() {
		System.out.println("main-연결완료");
		return "main/index";
	}

	@RequestMapping("/{id}")
	public String blog(@PathVariable String id, Model model,
			           @RequestParam(value="cateNo", required=false, defaultValue="-1")Integer cateNo,
			           @RequestParam(value="postNo", required=false,defaultValue="-1")Integer postNo){
		
		BlogVO bvo = service.selectOne(id);
		model.addAttribute("bvo",bvo);
		
		List<CategoryVO> clist = service.selectCate(id);
		model.addAttribute("clist",clist);
		
		List<PostVO> plist = new ArrayList<PostVO>();
		
		
		if(!clist.isEmpty()) {
			if(cateNo==-1) {
		        plist =  postservice.plist(clist.get(0).getCateNo());
		        
		        System.out.println("clist.cateNo"+clist.get(0).getCateNo());
		        model.addAttribute("plist", plist);
			} else {
				plist =  postservice.plist(cateNo);
				System.out.println("cateNo"+cateNo);
				model.addAttribute("plist", plist);
			}
		
		}
		if(!plist.isEmpty()) {	
			
			if(postNo==-1) {
				PostVO pvo = postservice.pselect(plist.get(0).getPostNo());
				model.addAttribute("pvo", pvo);
			}else {
				PostVO pvo = postservice.pselect(postNo);
				model.addAttribute("pvo", pvo);
			}
		}
		
		return "blog/blog-main";
		}
	
	@ResponseBody
	@RequestMapping("/{id}/comment")
	public CommentsVO cmt(CommentsVO vo) {
		System.out.println(vo);
		CommentsVO cmtvo = commentservice.cmt(vo);
		System.out.println("들어와줘"+vo);
		return cmtvo;
	}
	
	@ResponseBody
	@RequestMapping("/{id}/comment/list")
	public List<CommentsVO> list(CommentsVO cmtvo){
		return commentservice.selectcmt();
	}
	
	}


