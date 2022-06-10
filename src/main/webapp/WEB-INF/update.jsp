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
<title>更新画面</title>
</head>
<body>
<nav>
		<ul>
		<li><a href="menu" >ホーム</a></li>
		<li><a href="allMenu">全履歴表示</a></li>
		<li><a href="logout">ログアウト</a></li>
		</ul>
	</nav>
	<p class="right">${fn:escapeXml(userName)}さん、こんにちは</p>
	<div class="outside">

<h3>更新したい値を入力してください</h3>

<form:form action ="update" modelAttribute="product" method="post">
<form:hidden path ="productId" value="${fn:escapeXml(chosenProduct.getProductId())}"/>
<label>商品名</label><br>
<form:errors key="productName"/>
<form:input path="productName" value="${fn:escapeXml(chosenProduct.getProductName())}"/><br>

<label>ブランド名</label><br>
<form:errors key="brandName"/>
<form:input path="brandName" value="${fn:escapeXml(chosenProduct.getBrandName())}"/><br>

<label>カテゴリ名</label><br>
<form:select path="categoryId">
<c:forEach var="c" items="${category}">
<option value="${c.getCategoryId()}" <c:if test="${c.getCategoryId() == chosenProduct.getCategoryId()}">selected</c:if>>${fn:escapeXml(c.getCategoryName())}</option>
</c:forEach>

</form:select><br>
<label>購入日</label><br>
<form:errors key="purchaseDate"/>
<form:input path="purchaseDate" type="date" value="${fn:escapeXml(chosenProduct.getPurchaseDate())}"/><br>

<label>使用開始日</label><br>
<form:errors key="startingDate"/>
<form:input path="startingDate" type="date" value="${fn:escapeXml(chosenProduct.getStartingDate())}"/><br>

<label>使用期限</label><br>
<form:errors key="expirationDate"/>
<form:input path="expirationDate" type="date" value="${fn:escapeXml(chosenProduct.getExpirationDate())}"/><br>

<label>お気に入り</label><br>
<form:select path ="favorite">
 <option value="true" <c:if test="${chosenProduct.isFavorite() == true}">selected</c:if>>○</option>
 <option value="false" <c:if test="${chosenProduct.isFavorite() == false}">selected</c:if>>×</option>
</form:select><br>

<label>使用済み</label><br>
<form:select path ="finished">
 <option value="true" <c:if test="${chosenProduct.isFinished() == true}">selected</c:if>>○</option>
 <option value="false" <c:if test="${chosenProduct.isFinished() == false}">selected</c:if>>×</option>
</form:select><br>

<form:button name="insert">更新</form:button>
</form:form>

<a href="back" class="blue_btn">戻る</a>
</div>
</body>
</html>