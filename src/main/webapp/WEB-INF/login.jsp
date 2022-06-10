<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<link href="css/commons.css" type="text/css" rel="stylesheet">
<meta charset="UTF-8">
<title>ログインページ</title>
</head>
<body>
<div class="login">
<h1>ログイン</h1>
<p>${msg}</p>
<form:form action = "login" modelAttribute="user" method="post">
<label>ログインID：</label><br>
<form:errors path = "loginId"  cssStyle="color: red"/><br>
<form:input path="loginId"/><br>

<label>パスワード：</label><br>
<form:errors path = "password" cssStyle="color: red"/><br>
<form:password path="password"/><br>
<form:button>ログイン</form:button>
</form:form>
</div>
</body>
</html>