<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>追加ページ</title>
</head>
<body>
<header>
<a href="">ホーム</a>
<a href="">ログアウト</a>
<p>さん、こんにちは</p>
</header>

<h3>追加したい値を選んでください</h3>

<form:form action ="insert" modelAttribute="product" method="post">
<label>商品名</label><br>
<form:errors key="productName"/>
<form:input path="productName"/><br>

<label>ブランド名</label><br>
<form:errors key="brandName"/>
<form:input path="brandName"/><br>


<label>カテゴリー名</label>
<form:select path="categoryId">
<c:forEach var="c" items="${category}">
<form:option value="${c.getCategoryId()}">${fn:escapeXml(c.getCategoryName())}</form:option>
</c:forEach>
</form:select><br>

<label>購入日</label><br>
<form:errors key="purchaseDate"/>
<form:input path="purchaseDate" type="date"/><br>

<label>使用開始日</label><br>
<form:errors key="startingDate"/>
<form:input path="startingDate" type="date"/><br>

<label>使用期限</label><br>
<form:errors key="expirationDate"/>
<form:input path="expirationDate" type="date"/><br>

<label>お気に入り</label>

<form:checkbox path="favorite" value= "1"/>

<label>使用済み</label>
<form:hidden path="finished" value="0"/>
<form:checkbox path="finished" value= "1"/>

<form:button name="insert">追加</form:button>
<form:button name="back">戻る</form:button>
</form:form>


</body>
</html>