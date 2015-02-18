<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>For9</title>
		<link href="css/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
	
		<a href="categorias">Associado</a>
		<a href="agendas">Agenda</a>
		<a href="contatos">Contato</a>
		
		<br>
		
		<div style="width:500px;padding-top:50px;">
			
			<form action="salvar-categoria" method="post" onsubmit="return validate(this);">
				<input type="hidden" name="categoria.id" value="${ categoria.id}" />
				<input type="hidden" id="imagem-index" imagem-index="0" name="categoria.imagem.id" value="${categoria.imagem.id}" requiredLength="1" requiredMessage="Por favor, carregue a imagem da categoria."/>
				Nome da categoria <input type="text" name="categoria.nome" value="${categoria.nome}" requiredLength="1" requiredMessage="Por favor, informe o nome da categoria." /> <br>
				<input type="submit" value="Salvar"/>
			</form>
			
			<br>
			<br>
			
			<form class="form-imagem" action="carregar-imagem" target="frame-imagem" method="post" enctype="multipart/form-data" onsubmit="return getCurrentImagemIndex(this);">
				<input type="file" name="image" />
				<input type="submit" value="carregar" />
				<input type="hidden" id="imagem-index" imagem-index="0"/>
			</form>
			
			<div style="display:none;"">
				<iframe name="frame-imagem">
				</iframe>
			</div>
			
			<div class="div-imagem" imagem-index="0">
				
				<img class="img-imagem" imagem-index="0" src="${categoria.imagem.url}"/>
				<button onclick="return removeImage(this);" imagem-index="0" data-imagemId="${categoria.imagem.id }" id='imagem-a-0'>Excluir</button>
			
				
			</div>
			
		</div>
		
		<script type="text/javascript" src="js/jquery-1.9.0.min.js"></script>
		<script type="text/javascript" src="js/for9.js"></script>
		<script type="text/javascript">
		
		</script>
	
	</body>
</html>