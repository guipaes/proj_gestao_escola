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

	<h3>${cursoCadastro.nomeCurso}</h3>

	<form action="./disciplina.do" method="POST">
	
		<div id="disciplinas">
			<input type="text" name="nome_disciplina" placeholder="nome da disciplina"/>		
		</div>
		<input type="button" onclick="add_disciplina();" value="Adicionar Outra Disciplina" />
		
		<br/>
		<br/>
		<input type="submit" value="Criar Disciplina(s)" />
		
	</form>	
	

</body>
</html>