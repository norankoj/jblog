<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
</head>
<body>
	<div class="center-content">
		
	<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		
		<form class="join-form" id="join-form" method="post" action="${pageContext.request.contextPath }/user/insert">
			<label class="block-label" for="name">이름</label>
			<input type="text" name="userName"  value="" />
			
			<label class="block-label" for="id">아이디</label>
			<input type="text" name="id"  value="" />
			
			<input id="btn-checkid" type="button" value="id 중복체크">
			<p id="checkid-msg" class="form-error">
			&nbsp;
			</p>
			
			<label class="block-label" for="password">패스워드</label>
			<input type="password" name="password"  value="" />

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form>
	</div>

</body>
<script type="text/javascript">
$("#btn-checkid").on("click", function(){
   // console.log("add");
    
    var id = $("[name=id]").val();

    
    $.ajax({
           //요청할때
           url : "${pageContext.request.contextPath }/user/idcheck", 
           type : "post",
           data : {id:id},  
           dataType : "json",
           success : function(isExists) {
                  console.log(isExists);
                  
                  if(isExists==true){
             		 $("#checkid-msg").html("사용중인 아이디 입니다.")
             	 }else{
             		 $("#checkid-msg").html("사용할 수 있는 아이디 입니다.")
             	 }
                  
           },
           error : function(XHR, status, error) {
                  console.error(status + " : " + error);
           }
      });
});



</script>


</html>