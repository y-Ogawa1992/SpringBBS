<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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

    <form:form modelAttribute="userForm">
        <p>ID: ${user.id}</p>
  		<p>NAME: <form:input path="name" /></p>
  		<p>LOGINID: <form:input path="loginId" /></p>
  		<p>BRANCH: <form:select path="branchId" /></p>
  		<p>DEPARTMENT <form:select path="departmentId" /></p>

        <input type="submit">
    </form:form>
</body>
</html>