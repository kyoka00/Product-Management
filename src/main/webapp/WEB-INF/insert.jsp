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
<title>追加ページ</title>
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
<h3>追加したい値を選んでください</h3>

<form:form action ="insert" modelAttribute="product" method="post">

<form:errors path="productName" cssStyle="color: red"/><br>
<form:input path="productName" placeholder="商品名"/><br>


<form:input path="brandName" placeholder="ブランド名"/><br>


<form:select path="categoryId">
<c:forEach var="c" items="${category}">
<form:option value="${c.getCategoryId()}">${fn:escapeXml(c.getCategoryName())}</form:option>
</c:forEach>
</form:select><br>

<form:errors path="purchaseDate" cssStyle="color: red"/><br>
<form:input class="date_box" path="purchaseDate" type="text" onfocus="this.type='date'" onfocusout="this.type='text'" placeholder="購入日"/><br>

<form:input class="date_box" path="startingDate" type="text" onfocus="this.type='date'" onfocusout="this.type='text'" placeholder="使用開始日"/><br>

<form:errors path="expirationDate" cssStyle="color: red"/><br>
<form:input class= "date_box" path="expirationDate" type="text"  onfocus="this.type='date'" onfocusout="this.type='text'" placeholder="使用期限"/><br>

<label>お気に入り</label>
<form:checkbox path="favorite" value= "1"/><br>

<label>使用済み</label>
<form:checkbox path="finished" value= "1"/><br>

<form:button name="insert">追加</form:button>
</form:form>
<a href="back" class="blue_btn">戻る</a>
</div>
</body>
</html>