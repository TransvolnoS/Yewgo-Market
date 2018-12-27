<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/product" method="post">
<input type="hidden" value="update" name="method">
<input type="hidden" value="${pro.pid }" name="pid">
商品名称：<input type="text" name="pname" value="${pro.pname}"><br>
市场价格：<input type="text" name="market_price" value="${pro.market_price}"><br>
店铺价格：<input type="text" name="shop_price" value="${pro.shop_price}"><br>
商品描述：<textarea rows="10" cols="30" name="pdesc">${pro.pdesc}</textarea>

<input type="submit" value="修改商品" name="submit">
</form>
</body>
</html>