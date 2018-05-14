package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.AdminDAO;
import com.javaex.vo.CommentsVO;

@Service
public class CommentService {
	
@Autowired private AdminDAO adminDao;
	
	public CommentsVO cmt(CommentsVO vo) {
		
		int cmtNO = adminDao.addCmt(vo);
		
		return adminDao.selectCmt(cmtNO);
	}
	
	public List<CommentsVO> selectcmt(){
		return adminDao.cmtlist();
		
	}

}
