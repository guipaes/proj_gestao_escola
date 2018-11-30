<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Adicionar Curso</title>
</head>
<body>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<c:set var="gerenciar_curso" scope="session" value="incluir"/>
	<form action="./curso.do" method="POST">
		<input type="text" name="nome_curso" />
		<input type="submit" value="Criar" />
	</form>	

</body>
</html>