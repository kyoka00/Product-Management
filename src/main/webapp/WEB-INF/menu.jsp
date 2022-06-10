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
<title>メニュー画面</title>
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

	<h1>使用中</h1>
	<div class="right">
	<a href="insertMenu" class="white_btn">追加</a>
	</div>
	<p>${msg}</p>
	
	<form:form action="sort" modelAttribute="product" method= "get">
          <form:select class="base-text" path = "sortCase">
            <form:option value ="0">登録順</form:option>
            <form:option value = "1">カテゴリ</form:option>
            <form:option value = "2">購入日</form:option>
            <form:option value ="3">使用期限</form:option>
          </form:select>
          <form:button>並び替え</form:button>
        </form:form>
        
	<table>
		<tr>
			<th>商品名</th>
			<th>ブランド名</th>
			<th>カテゴリー名</th>
			<th>購入日</th>
			<th>使用開始日</th>
			<th>使用期限</th>
			<th>お気に入り</th>
			<th></th>
		</tr>

		<c:forEach var="p" items="${productList}" varStatus="status">
			<tr>
				<td>${fn:escapeXml(p.getProductName())}</td>
				<td>${fn:escapeXml(p.getBrandName())}</td>
				<td>${fn:escapeXml(p.getCategoryName())}</td>
				<td>${fn:escapeXml(p.getPurchaseDate())}</td>
				<td>${fn:escapeXml(p.getStartingDate())}</td>
				<td>${fn:escapeXml(p.getExpirationDate())}</td>
				<td><c:if test= "${p.isFavorite() == true}"><img src="img/star.png"></c:if></td>
				<td><a class="grey_btn" href="updateMenu?productId=${p.getProductId()}"> 更新</a></td>
			</tr>
		</c:forEach>

	</table>
</div>
</body>
</html>