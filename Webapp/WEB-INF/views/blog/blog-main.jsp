<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
</head>
<body>

	<div id="container">

		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>
		
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
				<c:choose>
				<c:when test="${empty pvo}" >
				<h4>등록된 글이 없습니다.</h4>
				</c:when>
				<c:otherwise>
					<h4>${pvo.postTitle } </h4>
					<p>
					   ${pvo.postContent}
					</p>
					</c:otherwise>
					</c:choose>
				</div>
				<c:if test="${!empty authUser }">
				<div id="comm">
				<table style='border: 1px black solid; width:100%'> 
                 <tr>
                 <td>${authUser.userName}</td>
                 <td><input type='text' name='comment' value='' style='width:100%; border:none a'></td>
                 <td><input type="hidden" name='cmtPostNo' value='${pvo.postNo }' style='width:100%; border:none a'></td>
                 <td><input type="hidden" name='cmtUserNo' value='${authUser.userNo}' style='width:100%; border:none a'></td>
                 <td><input type="hidden" name='cmtUserName' value='${authUser.userName}' style='width:100%; border:none a'></td>
                 <td><input type='button' id='commentPush' name='commentPush' value='저장'></td>
                 </tr>
                 </table>
                 <table> 
                 </table>
                </div>
                </c:if>
            <br>
				
				 <ul class="blog-list">
				 <c:forEach items="${plist}" var="post">
					<li>
						<a href="${pageContext.request.contextPath}/${bvo.id}?cateNo=${post.cateNo}&postNo=${post.postNo}">${post.postTitle}</a> 
						<span>${post.regDate}</span>
					</li>
				 </c:forEach>	
				</ul> 
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath }/upload/${bvo.logoFile}">				
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items="${clist }" var="CategoryVO">
				<li><a href="${pageContext.request.contextPath}/${bvo.id}?cateNo=${CategoryVO.cateNo}">${CategoryVO.cateName}</a></li>
				</c:forEach>
			</ul>
		</div>
		
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
		
	</div>
</body>
<script type="text/javascript">
$(document).ready(function() {
	List();

});


function List(){
	
    $.ajax({
           //요청할때
           url : "${pageContext.request.contextPath }/{id}/comment/list", 
           type : "post",
           //  data : {id:id},  
           
           //응답받을때
           dataType : "json",
           success : function(cmtlist) {
                 console.log(cmtlist);
                 
                 for(var i = 0; i<cmtlist.length; i++ ){
                        render(cmtlist[i],"down");
                 }
           },
           error : function(XHR, status, error) {
                 console.error(status + " : " + error);
           }
      });
}

$("#commentPush").on("click", function(){
	    
	    var PostNo = $("[name=cmtPostNo]").val();
	    var userNo = $("[name=cmtUserNo]").val();
	    var cmtContent = $("[name=comment]").val();

	    
	    $.ajax({
	           //요청할때
	           url : "${pageContext.request.contextPath }/{id}/comment", 
	           type : "post",
	           data : {postNo:PostNo,userNo:userNo,cmtContent:cmtContent}, 
	           
	           dataType : "json",
	           success : function(cmtvo) {
	                  console.log(cmtvo);
	                  render(cmtvo,"down");
	                  $("[name=comment]").val("");
	                  
	           },
	           error : function(XHR, status, error) {
	                  console.error(status + " : " + error);
	           }
	      });
	});

function render(CmtVO, updown){
    var str = "";
    //str +="<table style='border: 1px black solid; width:100%'> ";
    str +="    <tr>";
    str +="      <td>"+CmtVO.userName+"</td>";
    str +="      <td>"+CmtVO.cmtContent+"</td>";
    str +="      <td>"+CmtVO.regDate+"</td>";
    str +="    </tr>";
   // str +="</table>";
    
    if(updown =="up"){
           $("#comm").prepend(str);
    }else if(updown == "down"){
        $("#comm").append(str);
    }else{
           console.log("오류")
    }
    
}

</script>
</html>