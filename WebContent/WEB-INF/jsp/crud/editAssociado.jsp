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
			
			<form id="form-add-associado" action="salvar-associado" method="post" onsubmit="return validate(this);">
				<input type="hidden" name="categoriaId" value="${ categoriaId }" />
				<input type="hidden" name="associado.id" value="${ associado.id }" />
				Nome <input type="text" name="associado.nome" value="${associado.nome}" requiredLength="1" requiredMessage="Por favor, informe o nome do associado." /> <br>
				Site <input type="text" name="associado.site" value="${associado.site}" requiredLength="1" requiredMessage="Por favor, informe o site." /> <br>
				Telefone <input type="text" name="associado.telefone" value="${associado.telefone}" requiredLength="1" requiredMessage="Por favor, informe o telefone." /> <br>
				E-mail <input type="text" name="associado.email" value="${associado.email}" requiredLength="1" requiredMessage="Por favor, informe o e-mail." /> <br>
				Pais<select name="associado.pais.id">
					<option value="1">Brasil</option>
				</select>
				
				<c:forEach items="${ associado.listImagem }" var="imagem" varStatus="index">
					<input type="hidden" id="imagem-index" imagem-index="${index.index}" name="associado.listImagem[${index.index}].id" value="${imagem.id}"/>
				</c:forEach>
				
				<input type="submit" value="Salvar"/>
			</form>
			
			<br>
			<br>
			
			<button onclick="addImagemElements();">Adicionar imagem</button>
			
			<div id="addImagemElement-div">
			
				<c:forEach items="${ associado.listImagem}" var="imagem" varStatus="index">
					<div class="div-imagem" imagem-index="${index.index}">
						<form class="form-imagem" action="carregar-imagem" target="frame-imagem" method="post" enctype="multipart/form-data" onsubmit="return getCurrentImagemIndex(this);">
							<input type="file" name="image" />
							<input type="submit" value="carregar" />
							<input type="hidden" id="imagem-index" imagem-index="${index.index}"/>
						</form>
						<img class="img-imagem" imagem-index="${index.index}" src="${imagem.url}"/>
						<button onclick="return removeImageDiv(this);" imagem-index="${index.index}" data-imagemId="${imagem.id}">Excluir</button>
					</div>
				</c:forEach>
			
			</div>
			
			<div style="display:none;"">
				<iframe name="frame-imagem">
				</iframe>
			</div>
			
		</div>
		
		<script type="text/javascript" src="js/jquery-1.9.0.min.js"></script>
		<script type="text/javascript" src="js/for9.js"></script>
		<script type="text/javascript">
		
		</script>
	
	</body>
</html>