<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Matrícula</title>
</head>
<body>

	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<%@ page import="java.util.Calendar" %>

	<div id="mensagem">
		
		<h3>${msg}</h3>
	</div>
	
	<br/>
	
	<!-- Lista apenas disciplinas do curso que o aluno está inscrito -->
	<form action="./matricula.do" method="GET">		
		<input type="text" name="nome_aluno" value="${aluno.nome}" placeholder="Nome do Aluno" />
		<c:choose>
			<c:when test="${aluno eq null}"> 
				<input type="date" name="data_nasc" placeholder="aaaa-mm-dd" />
			</c:when>
			<c:otherwise>
				<input type="text" name="data_nasc" value="<fmt:formatDate value='${aluno.dataNasc.time}' type="date" dateStyle="short" pattern='yyyy-MM-dd' />"/>			
			</c:otherwise>
		</c:choose>
		<input type="submit" value="Consultar Disciplinas para o Aluno" />
	</form>
	
	<p>Curso: ${aluno.curso.nomeCurso}</p>	
	<form  action ="./matricula.do" method="POST">

		<p> Selecione as Disciplinas:</p>
		 	
		<c:forEach var="disciplina" items="${aluno.curso.disciplinas}">
			<input type="checkbox" name="disciplina_matricula" value="${disciplina.nomeDisciplina}"/> ${disciplina.nomeDisciplina}
			</br>			
		</c:forEach>
		
		<input type="submit" value="Cadastrar" />
		
	</form>

	<h6><a href="<c:url value='/index.jsp'/>">Voltar à Página Principal</a></h6>


</body>
</html>