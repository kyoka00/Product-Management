<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログインページ</title>
</head>
<body>

<h1>ログイン</h1>
<p>${msg}</p>
<form:form action = "login" modelAttribute="user" method="post">
<label>ログインID：</label><br>
<form:input path="loginId"/><br>
<form:errors key = "loginId"/><br>
<label>パスワード：</label><br>
<form:password path="password"/><br>
<form:errors key = "password"/><br>
<form:button>ログイン</form:button>
</form:form>

</body>
</html>