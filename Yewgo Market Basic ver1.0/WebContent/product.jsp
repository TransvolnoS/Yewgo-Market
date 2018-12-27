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
<input type="hidden" value="add" name="method">
商品名称：<input type="text" name="pname"><br>
市场价格：<input type="text" name="market_price"><br>
店铺价格：<input type="text" name="shop_price"><br>
商品描述：<textarea rows="10" cols="30" name="pdesc"></textarea>

<input type="submit" value="添加商品" name="submit">
</form>
</body>
</html>