<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:remove var="aluno" scope="session"/>

<div id="mensagem">
	<h3>${msg}</h3>

</div>

<a href="add_curso.jsp">Criar Curso</a><br/>
<a href="listar_disciplinas.jsp">Listar Disciplinas por Cursos</a><br/>
<a href="gerenciar_cursos.jsp">Gerenciar Cursos</a><br/>
<br/>
<a href="add_aluno.jsp">Cadastrar Aluno</a><br/>
<a href="matricular.jsp">Matrícular Aluno</a><br/>
<a href="#">Gerenciar Alunos</a><br/>

</body>
</html>