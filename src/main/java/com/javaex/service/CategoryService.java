package com.javaex.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.AdminDAO;
import com.javaex.vo.CategoryVO;

@Service
public class CategoryService {

	@Autowired private AdminDAO adminDao;
	
	public CategoryVO add(CategoryVO vo) {
		
		int cateNO = adminDao.add(vo);
		
		return adminDao.selectCate(cateNO);
	}
	
	public List<CategoryVO> list(String id){
		return adminDao.clist(id);
		
	}
}
