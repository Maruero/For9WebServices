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
			
			<form action="salvar-agenda" id="form-add-agenda" method="post" onsubmit="return validate(this);">
				<input type="hidden" name="agenda.agendaId" value="${ agenda.agendaId }" />
				
				Nome <input type="text" name="agenda.nome" value="${agenda.nome}" requiredLength="1" requiredMessage="Por favor, informe o nome do evento." /> <br>
				Início do evento <input type="text" name="agenda.dateInicioEvento" value="${agenda.dateInicioEvento}" requiredLength="1" requiredMessage="Por favor, informe a data de início do evento." /> <br>
				Fim do evento <input type="text" name="agenda.dateFimEvento" value="${agenda.dateFimEvento}" requiredLength="1" requiredMessage="Por favor, informe a data de término do evento;" /> <br>
				Local <input type="text" name="agenda.local" value="${agenda.local}" requiredLength="1" requiredMessage="Por favor, informe o local do evento." /> <br>
				Site <input type="text" name="agenda.site" value="${agenda.site}" requiredLength="1" requiredMessage="Por favor, informe o site do evento." /> <br>				
				Descrição <textarea rows="3" cols="60" name="agenda.descricao">${agenda.descricao}</textarea><br>
				
				<c:forEach items="${agenda.listImagem }" var="imagem" varStatus="index">
					<input type="hidden" id="imagem-index" imagem-index="${index.index}" name="agenda.listImagem[${index.index}].id" value="${imagem.id}"/>
				</c:forEach>
				
				<input type="hidden" id="imagem-index" imagem-index="0" name="agenda.imagemPrincipal.id" value="${agenda.imagemPrincipal.id}" requiredLength="1" requiredMessage="Por favor, informe a imagem principal."/>
				<input type="submit" value="Salvar"/>
				
			</form>
			
			<br>
			<br>
			
			Imagem principal
			
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
				
				<img class="img-imagem" imagem-index="0" src="${agenda.imagemPrincipal.url}"/>
				<button onclick="return removeImage(this);" imagem-index="0" data-imagemId="${agenda.imagemPrincipal.id}" id='imagem-a-0'>Excluir</button>
				
			</div>
			
			<br>
			<br>
			
			Galeria de imagens
			<button onclick="addImagemElements();">Adicionar imagem</button>
			
			<div id="addImagemElement-div">
			
				<c:forEach items="${ agenda.listImagem}" var="imagem" varStatus="index">
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
			
		</div>
		
		<script type="text/javascript" src="js/jquery-1.9.0.min.js"></script>
		<script type="text/javascript" src="js/for9.js"></script>
		<script type="text/javascript">
		
		</script>
	
	</body>
</html>