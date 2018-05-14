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

	<div id="container">
	
	  <c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>
		
		<div id="wrapper">
			<div id="content" class="full-screen">
					<c:import url="/WEB-INF/views/includes/blog-menu.jsp"></c:import>
				
		      	<table class="admin-cat">
		      		<thead>
			      		<tr>
			      			<th>번호</th>
			      			<th>카테고리명</th>
			      			<th>포스트 수</th>
			      			<th>설명</th>
			      			<th>삭제</th>      			
			      		</tr>
		      		</thead>
		      		<tbody id=cateList>
					</tbody>
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<table id="admin-cat-add" >
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name" value=""></td>
		      			<td><input type="hidden" name="id" value="${bvo.id }"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="desc"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input id="btnAddCate" type="submit" value="카테고리 추가"></td>
		      		</tr>      		      		
		      	</table> 
		      	
			</div>
		</div>
		
			<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
	</div>
</body>
<script type="text/javascript">

$(document).ready(function() {
	List();

});


function List(){
	
	 var id = $("[name=id]").val();
    
    $.ajax({
           //요청할때
           url : "${pageContext.request.contextPath }/{id}/admin/list", 
           type : "post",
           data : {id:id},  
           
           //응답받을때
           dataType : "json",
           success : function(clist) {
                 console.log(clist);
                 //console.log(list[0].name); ==>생각
                 
                 for(var i = 0; i<clist.length; i++ ){
                        render(clist[i],"down");
                 }
           },
           error : function(XHR, status, error) {
                 console.error(status + " : " + error);
           }
      });
}



$("#btnAddCate").on("click", function(){
    console.log("add");
    
    var id = $("[name=id]").val();
    var cateName = $("[name=name]").val();
    var description = $("[name=desc]").val();

    
    $.ajax({
           //요청할때
           url : "${pageContext.request.contextPath }/{id}/admin/add", 
           type : "post",
           data : {id:id,cateName:cateName,description:description},  
           dataType : "json",
           success : function(catevo) {
                  console.log(catevo);
                  render(catevo,"down");
                  $("[name=name]").val("");
                  $("[name=desc]").val("");
           },
           error : function(XHR, status, error) {
                  console.error(status + " : " + error);
           }
      });
});

function render(CategoryVO, updown){
    var str = "";
    str +="<tr>";
    str +="    <td>"+CategoryVO.cateNo+"</td>";
    str +="    <td>"+CategoryVO.cateName+"</td>";
    str +="    <td>0</td>";
    str +="    <td>"+CategoryVO.description+"</td>";
    str +="    <td><img src='${pageContext.request.contextPath}/assets/images/delete.jpg' class='del' data-postnum='' data-cateNo="+CategoryVO.cateNo+"></td>";
    str +="</tr>";
    
    if(updown =="up"){
           $("#cateList").prepend(str);
    }else if(updown == "down"){
        $("#cateList").append(str);
    }else{
           console.log("오류")
    }
    
}
$(".del").on("click", function(){
	
	var cateNo = $(this).data("cateNo");
	console.log(cateNo);

});

</script>


</html>