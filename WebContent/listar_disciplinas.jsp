<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}
</style>

<title>Disciplinas por Curso</title>
</head>
<body>

	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<!-- Lembrando que trouxe as disciplinas junto com os cursos (no mapeamento em EntityCurso, ver FetchType.EAGER) 
	e os coloquei (os cursos) no ServletContext. Se tivesse usado FetchType.LAZY eu economizaria a memória RAM, mas precisaria
	acessar o banco agora para fazer uma consulta pelas disciplinas associadas aos cursos.
	Como nossa aplicação é pequena, e por agilidade na programação, fui pragmático e deixei "eager".
	
	MAS PARA EXEMPLIFICAR, VOU FAZER A CONSULTA! COMO SE TIVESSE DEIXADO LAZY!!!
	 -->
	<form action="./disciplina.do" method="GET">
		<select name="curso_disciplina">
			<option value="todos">Todos</option>
			<c:forEach var="curso" items="${cursos}">
				<option value="${curso.nomeCurso}"> ${curso.nomeCurso} </option>		
			</c:forEach>
		</select>
		<input type="submit" value="Listar" />	
	</form>
	
	<br/>
	
	<c:if test="${listar_cursos ne null}">
		<table>
			<tr>
				<th> Curso </th>
				<th> Materias </th>
			</tr>
			
			<c:forEach var="curso_listar" items="${listar_cursos}"> 
				<tr>
					<td>${curso_listar.nomeCurso}</td>
					<td>
					<c:forEach var="disciplina" items="${curso_listar.disciplinas}">
							 ${disciplina.nomeDisciplina}<br/> 
					</c:forEach>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<h6><a href="<c:url value='/index.jsp'/>">Voltar à Página Principal</a></h6>
	
</body>
</html>