<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
<table border="1px" align="center">
<tr>
<td colspan="8" align="center">
<form action="${pageContext.request.contextPath}/product" method="post">
<input type="hidden" name="method" value="search">
商品名称：<input type="text" name="name" value="${name}">
商品关键字：<input type="text" name="kw" value="${kw}">
<input type="submit" value="搜索商品">
</form>
</td>
<td colspan="1" align="center">
<input type="button" value="批量删除" id="patchBtn"></td>
</tr>
<tr>
<th><input type="checkbox" id="checkId"></th>
<th width=7%>商品图片</th>
<th width=6%>商品ID</th>
<th width=10%>商品名称</th>
<th width=8%>市场价格</th>
<th width=8%>店铺价格</th>
<th width=10%>起售日期</th>
<th>商品描述</th>
<th width=8%>操作</th>
</tr>


<c:if test="${empty list}">
<tr>
<td colspan="9">暂无此商品</td>
</tr>
</c:if>

<c:if test="${not empty list}">
<form action="${pageContext.request.contextPath }/product" method="post" id="formId">
<input type="hidden" name="method" value="delCheck">
<c:forEach items="${list}" var="pro">
<tr>
<td><input type="checkbox" name="id" value="${pro.pid}"></td>
<td><img src="${pageContext.request.contextPath}/${pro.pimage} "height="75px"></td>
<td>${pro.pid}</td>
<td>${pro.pname}</td>
<td>${pro.market_price}</td>
<td>${pro.shop_price}</td>
<td>${pro.pdate}</td>
<td>${pro.pdesc}</td>
<td>
<a href="${pageContext.request.contextPath}/product?method=edit&id=${pro.pid}">修改</a>
<a href="#" onclick="del('${pro.pid}')">删除</a>
</td>
</tr>
</c:forEach>
</form>
</c:if>

</table>
</body>
<script type="text/javascript">
function del(id){
	var flag=confirm("您确认要删除嘛？");
	if(flag){
		location.href="${pageContext.request.contextPath}/product?method=delete&pid="+id;
	}
}

$("#checkId").click(function(){
	$("[name=id]").prop("checked",$("#checkId").prop("checked"));
})

$("#patchBtn").click(function(){
	$("#formId").submit();
	
})
</script>
</html>