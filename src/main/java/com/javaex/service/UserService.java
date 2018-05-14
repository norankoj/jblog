package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDAO;
import com.javaex.vo.BlogVO;
import com.javaex.vo.CategoryVO;
import com.javaex.vo.UserVO;

@Service
public class UserService {

	@Autowired
	private UserDAO dao;

	public void write(UserVO vo) {

		dao.insert(vo);

		String id = vo.getId();
		String blogTitle = vo.getUserName() + "의 블로그";
		String logoFile = "spring-logo.jpg";

		BlogVO bvo = new BlogVO(id, blogTitle, logoFile);

		String cateName = "미분류";
		String description = "자바 잘하고 싶다.";

		CategoryVO cvo = new CategoryVO(id, cateName, description);

		dao.binsert(bvo);
		dao.cinsert(cvo);

	}

	public UserVO login(UserVO vo) {

		return dao.login(vo);
	}

	public BlogVO selectOne(String id) {

		return dao.selectblog(id);
	}

	public List<CategoryVO> selectCate(String id) {

		return dao.selectCate(id);
	}

	public boolean idcheck(String id) {

		UserVO vo = dao.selectid(id);

		boolean isExists;

		if (vo != null) {
			isExists = true;
		} else {
			isExists = false;
		}
		return isExists;
	}

}
