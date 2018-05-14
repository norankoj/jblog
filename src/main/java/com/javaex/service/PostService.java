package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.AdminDAO;
import com.javaex.vo.PostVO;

@Service
public class PostService {
	
	@Autowired private AdminDAO dao;
	
	public void insert(PostVO vo) {
		
		int no = dao.insertPost(vo);
		System.out.println(no+"포스트 등록완료");
	}
	
	public List<PostVO> plist(int cateNo){
		
		return dao.plist(cateNo);
	}
	
	public PostVO pselect(int cateNo) {
		return dao.pselect(cateNo);
	}

}
