<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Adicionar Disciplinas</title>

<script type="text/javascript" >

	function add_disciplina(){
		input = document.createElement("input");
		input.setAttribute("type","text");
		input.setAttribute("name","nome_disciplina");
		input.setAttribute("placeholder","nome da disciplina");
		br = document.createElement("br");
		div = document.getElementById("disciplinas");
		div.appendChild(br);
		div.appendChild(br);
		div.appendChild(input);
	}
</script>

</head>
<body>

	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<h3>${cursoCadastro.nomeCurso}</h3>

	<c:if test="${gerenciar_curso eq 'alterar'}">
	
		<form action="./curso.do" method="GET">
		
			<p>Selecione as Disciplinas que Deseja Excluir:</p>
			<c:forEach var="disciplina" items="${cursoCadastro.disciplinas}">
				<input type="checkbox" name="excluir_disciplinas" value="${disciplina.id}"> ${disciplina.nomeDisciplina} <br/>		
			</c:forEach>
			<input type="submit" value="Excluir Disciplinas Selecionadas" />
		
		</form>
		
	</c:if>


	<form action="./disciplina.do" method="POST">
		
		<p>Incluir Disciplinas:</p>
		<div id="disciplinas">
			<input type="text" name="nome_disciplina" placeholder="nome da disciplina"/>		
		</div>
		<input type="button" onclick="add_disciplina();" value="Adicionar Outra Disciplina" />
		
		<br/>
		<br/>
		<input type="submit" value="Criar Disciplina(s)" />
		
	</form>	
	
	<h6><a href="<c:url value='/index.jsp'/>">Voltar à Página Principal</a></h6>

</body>
</html>