package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.AdminDAO;
import com.javaex.vo.BlogVO;

@Service
public class BlogService {
	
	@Autowired AdminDAO dao;
	
	
	public BlogVO modyfy(BlogVO vo, MultipartFile file) {
		
        String saveDir ="D:\\JavaStudy\\upload2";
        
        
        
        if(!file.isEmpty()) {
		
		//오리지날 파일명
		String OrgName = file.getOriginalFilename();
		System.out.println("오리지날 파일명 = "+OrgName);
		
		//확장자
		String exname =file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")); 
		System.out.println("확장자 = "+exname);
		
		//저장파일명
		String saveName = System.currentTimeMillis()+UUID.randomUUID().toString() + exname;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
		System.out.println("저장파일명 = "+saveName );
		
		//파일패스//D-javastudy-upload2라는 폴더에 저장할 것 이다.
		String filePath = saveDir+"\\"+saveName;
		System.out.println("파일패스 = "+filePath);
		
		//파일사이즈
		long fileSize = file.getSize();
		System.out.println("파일사이즈 = "+ fileSize);
		
		//다오 연결DB** 
		if(vo.getBlogTitle()==null) {
		vo.setBlogTitle(vo.getBlogTitle());
		}
		vo.setLogoFile(saveName);
		int no= dao.update(vo);
		System.out.println(vo.toString());
		System.out.println(no+"등록완료");
	 

		 
		//파일 서버에 복사//하드에 복사 
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(saveDir +"/"+  saveName);
			BufferedOutputStream bout = new BufferedOutputStream(out);
			
			bout.write(fileData);
			
			if(bout != null) {
				bout.close();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
        }
        else{
        	String saveName=vo.getLogoFile();
        	vo.setLogoFile(saveName);
        	dao.update(vo);
	}
        return dao.select(vo.getId());
		
	}
		
		
	}


