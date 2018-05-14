package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVO;
import com.javaex.vo.CategoryVO;
import com.javaex.vo.UserVO;

@Repository
public class UserDAO {

	@Autowired
	private SqlSession sqlsession;

	public int insert(UserVO vo) {

		return sqlsession.insert("user.insert", vo);
	}
	
	public int binsert(BlogVO bvo) {

		return sqlsession.insert("user.binsert", bvo);
	}

	public int cinsert(CategoryVO cvo) {

		return sqlsession.insert("user.cinsert", cvo);
	}


	public UserVO login(UserVO vo) {

		return sqlsession.selectOne("user.selectUser", vo);
	}
	
	public UserVO selectid(String id) {

		return sqlsession.selectOne("user.selectid",id);
	}
	
	public BlogVO selectblog(String id) {
		
		return sqlsession.selectOne("user.selectblog",id);
	}
	
	public List<CategoryVO> selectCate(String id){
		
		return sqlsession.selectList("user.selectCate",id);
	}
	
}
