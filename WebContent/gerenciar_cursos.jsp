<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gerenciar Cursos</title>
</head>
<body>

	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<select name="curso_disciplina">
		<option value="todos">Todos</option>
		<c:forEach var="curso" items="${cursos}">
			<option value="${curso.nomeCurso}"> ${curso.nomeCurso} </option>		
		</c:forEach>
	</select>
	
	<!-- Vocês podem criar mais servlets e usar somente os métodos POST e GET -->
	<form action="./disciplina.do" method="DELETE">
		<!-- Exclui o curso, alunos matriculados -->
		<input type="submit" value="Excluir Curso" />	
	</form>
	<form action="./disciplina.do" method="PUT">
		<!-- Exclui o curso, alunos matriculados -->
		<input type="submit" value="Atualizar Curso" />	
	</form>

</body>
</html>