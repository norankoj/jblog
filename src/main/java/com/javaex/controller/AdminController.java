package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.service.CategoryService;
import com.javaex.service.PostService;
import com.javaex.service.UserService;
import com.javaex.vo.BlogVO;
import com.javaex.vo.CategoryVO;
import com.javaex.vo.PostVO;

@Controller
@RequestMapping("/{id}/admin")
public class AdminController {
	
	@Autowired private BlogService blogService;
	@Autowired private UserService userService;
	@Autowired private CategoryService categoryService;
	@Autowired private PostService postService;
	
	@RequestMapping("/basic")
	public String basic(@PathVariable String id, Model model) {
		BlogVO bvo = userService.selectOne(id);
		model.addAttribute("bvo",bvo);
		
		return "blog/admin/blog-admin-basic";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(@ModelAttribute BlogVO vo,@RequestParam("file") MultipartFile file,Model model) {
		System.out.println(file.toString());
		BlogVO bvo = blogService.modyfy(vo, file);
		model.addAttribute("bvo",bvo);
		
		return "blog/admin/blog-admin-basic";
	}
	
	
	@RequestMapping("/category")
	public String category(@PathVariable String id, Model model) {
		BlogVO bvo = userService.selectOne(id);
		model.addAttribute("bvo",bvo);
		return "blog/admin/blog-admin-cate";
	}
	
	@ResponseBody
	@RequestMapping("/add")
	public CategoryVO add(@ModelAttribute CategoryVO vo) {
		CategoryVO catevo = categoryService.add(vo);
		
		return catevo;
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public List<CategoryVO> list(@RequestParam ("id") String id){
		List<CategoryVO> clist =  categoryService.list(id);
		
		return clist;
	}
	
	@RequestMapping("/writer")
	public String writer(@PathVariable String id, Model model) {
		BlogVO bvo = userService.selectOne(id);
		model.addAttribute("bvo",bvo);
		List<CategoryVO> clist = categoryService.list(id);
		model.addAttribute("clist",clist);
		return "blog/admin/blog-admin-write";
	}
	
	@RequestMapping(value="/insertPost")
	public String insertPost(PostVO vo,Model model) {
		System.out.println(vo.toString());
		 postService.insert(vo);
		return "redirect:/{id}/admin/writer";
	}

	
}
