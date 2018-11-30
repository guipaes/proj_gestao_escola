<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gerenciar Cursos</title>

<script type="text/javascript">

	function alterarNomeCurso(){
		
		var select = document.getElementById("select_alterar");
		var i = select.selectedIndex;
		
		aux = select.options.item(i).text;
		document.getElementById("alterar_nome_curso").value = aux;
		
	}
	
</script>

</head>
<body>

	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
	<!-- só será útil se o propósito for alterar o curso -->
	<c:set var="gerenciar_curso" scope="session" value="alterar"/>
	
	<h3> ${msg} </h3>
	<form action="./gerenciar_curso.do" method="GET">
		
		<select name="curso_disciplina">
			<option value="todos">Todos</option>
			<c:forEach var="curso" items="${cursos}">
				<option value="${curso.nomeCurso}"> ${curso.nomeCurso} </option>		
			</c:forEach>
		</select>
		
		<!-- Exclui o curso, alunos matriculados -->
		<input type="submit" value="Excluir Curso" />	
	</form>
	<br/>
	<br/>
	
	<form action="./gerenciar_curso.do" method="POST">
		<select name="curso_disciplina" id="select_alterar" onchange="alterarNomeCurso();">			
			<option>Selecione uma Disciplina</option>
			<c:forEach var="curso" items="${cursos}">
				<option value="${curso.nomeCurso}"> ${curso.nomeCurso} </option>		
			</c:forEach>
		</select>
		Atualize o nome do curso: <input type="text" name="novo_nome_curso" id="alterar_nome_curso" />
		<input type="submit" value="Atualizar Curso" />	
	</form>

	<h6><a href="<c:url value='/index.jsp'/>">Voltar à Página Principal</a></h6>

</body>
</html>