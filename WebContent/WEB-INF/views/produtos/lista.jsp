<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista de Produtos</title>
</head>
<body>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		Este texto só será visto por ROLE_USER.<br/>
	</sec:authorize>
	<sec:authorize access="hasRole('ROLE_USER')">
		Este texto só será visto por ROLE_USER.<br/>
	</sec:authorize>
	<c:forEach items="${produtoList}" var="produto">
		${produto.descricao} - ${produto.quantidade}<br/>
	</c:forEach>
</body>
</html>