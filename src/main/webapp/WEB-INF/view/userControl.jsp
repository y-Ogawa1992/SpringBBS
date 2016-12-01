<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
<title>Welcome</title>
</head>
<body>
	<a href="${pageContext.request.contextPath}/bbs/">ホーム</a>
	<a href="${pageContext.request.contextPath}/bbs/userControl/">管理画面</a>
	<a href="${pageContext.request.contextPath}/bbs/signUp/">新規登録</a>
	<a href="${pageContext.request.contextPath}/bbs/login/">ログイン</a>
	<a href="${pageContext.request.contextPath}/bbs/newMessage/">新規投稿</a>
	<h1>${message}</h1>



	<c:forEach items="${users}" var="user">
		<p>
			<c:out value="${user.name}"></c:out>
			<c:out value="${user.loginId}"></c:out>
			<c:if test="${user.stop == false}">動いてます</c:if>
			<c:if test="${user.stop == true}">停止中です</c:if>
			<a href="${pageContext.request.contextPath}/userUpdate/update/input/${user.id}/">編集</a>
		</p>
	</c:forEach>
</body>
</html>