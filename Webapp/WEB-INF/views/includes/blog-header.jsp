<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


		<!-- 블로그 해더 -->
		<div id="header">
			<h1><a href="${pageContext.request.contextPath}/${bvo.id}">${bvo.blogTitle}</a></h1>
			<ul>
			 <c:choose>
			<c:when test="${empty authUser }">
				<!-- 로그인 전 메뉴 -->
				<li><a href="${pageContext.request.contextPath}/user/loginform">로그인</a></li>
			</c:when>
			<c:otherwise>
				<!-- 로그인 후 메뉴 -->
				<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
				<c:if test="${authUser.id eq bvo.id }">
				<li><a href="${pageContext.request.contextPath}/${authUser.id}/admin/basic">내블로그 관리</a></li>
				</c:if>
			</c:otherwise>
			</c:choose>		
			</ul>
		</div>