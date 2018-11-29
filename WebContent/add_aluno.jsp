<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Matricular Aluno</title>
</head>
<body>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ page import="java.util.List, br.ufrrj.web2.model.entities.CursoEntity" %>

	<form action="./aluno.do" method="POST">
		<h4> Dados do Novo Aluno</h4>
		<input type="text" name="nome_aluno" placeholder="Nome" /> 
		<!-- em alguns navegadores o type=date vai ser um campo de texto. por isso o placeholder -->
		<input type="date" name="data_nasc" placeholder="Nascimento aaaa-mm-dd"/> <br/>
		
		<p> Selecione um Curso</p>
		<c:forEach var="curso" items="${cursos}">
			<input type="radio" name="curso" value="${curso.nomeCurso}"> ${curso.nomeCurso} </input>
		</c:forEach>
				
		<br/>
		<input type="submit" value="Cadastrar" />
				
	</form>	
</body>
</html>