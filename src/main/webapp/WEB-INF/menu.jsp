<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー画面</title>
</head>
<body>
	<header>
		<a href="menu">ホーム</a> <a href="logout">ログアウト</a>
		<p>${fn:escapeXml(userName)}さん、こんにちは</p>
	</header>


	<h1>使用中</h1>
	<a href="insertMenu">追加</a>
	<p>${msg}</p>
	<table border=1>
		<tr>
			<th>商品名</th>
			<th>ブランド名</th>
			<th>カテゴリー名</th>
			<th>購入日</th>
			<th>使用開始日</th>
			<th>使用期限</th>
			<th>お気に入り</th>
		</tr>

		<c:forEach var="p" items="${productList}" varStatus="status">
			<tr>
				<td>${fn:escapeXml(p.getProductName())}</td>
				<td>${fn:escapeXml(p.getBrandName())}</td>
				<td>${fn:escapeXml(p.getCategoryName())}</td>
				<td>${fn:escapeXml(p.getPurchaseDate())}</td>
				<td>${fn:escapeXml(p.getStartingDate())}</td>
				<td>${fn:escapeXml(p.getExpirationDate())}</td>
				<td><c:if test= "${p.isFavorite() == TRUE}">a</c:if></td>
				<td><a class="detail_btn" href="detail?productId=${p.getProductId()}"> 更新</a></td>
			</tr>
		</c:forEach>

	</table>
	<a href="allMenu">全履歴表示</a>
</body>
</html>