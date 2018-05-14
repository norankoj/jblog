package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVO;
import com.javaex.vo.CategoryVO;
import com.javaex.vo.CommentsVO;
import com.javaex.vo.PostVO;

@Repository
public class AdminDAO {

	@Autowired
	private SqlSession sqlsession;

	public int update(BlogVO vo) {

		return sqlsession.update("admin.update", vo);
	}

	public BlogVO select(String id) {
		System.out.println(id + "아이디 값 확인");
		return sqlsession.selectOne("user.selectblog", id);
	}

	public int add(CategoryVO vo) {
		System.out.println(vo.toString());
		sqlsession.insert("admin.cinsert", vo);
		System.out.println(vo.toString());
		return vo.getCateNo();
	}
	
	public int addCmt(CommentsVO vo) {
		
		sqlsession.insert("admin.cmtinsert",vo);
		
		return vo.getCmtNo();
		
	}

	public CategoryVO selectCate(int cateNO) {

		return sqlsession.selectOne("admin.selectbyCateNO", cateNO);
	}
	public List<CategoryVO> clist(String id) {
		
		return sqlsession.selectList("user.selectCate", id);
	}

	public CommentsVO selectCmt(int cmtNo) {

		return sqlsession.selectOne("admin.selectbyCmtNO", cmtNo);
	}

	public List<CommentsVO> cmtlist() {

		return sqlsession.selectList("admin.selectCmt");
	}

	public int insertPost(PostVO vo) {

		return sqlsession.insert("admin.insertPost", vo);
	}

	public List<PostVO> plist(int cateNo) {

		return sqlsession.selectList("admin.plist", cateNo);
	}

	public PostVO pselect(int cateNo) {
		return sqlsession.selectOne("admin.pselect", cateNo);
	}

}
