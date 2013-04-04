<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastro de Produto</title>
</head>
<body>
	<sec:authorize access="hasRole('ROLE_USER')">
		Este texto só será visto por ROLE_USER.
	</sec:authorize>
	<form action="<c:url value='/produtos/salvar'/>" method="post">
		Descrição: <input type="text" name="descricao"/><form:errors path="produto.descricao"/><br/>
		Quantidade: <input type="text" name="quantidade"/><form:errors path="produto.quantidade"/><br/>
		<input type="submit" value="Cadastrar"/><br/>
	</form>
</body>
</html>