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
		
		<br>
		<br>
		<br>
		
		<a href="adicionar-agenda">Adicionar</a>
		
		<div style="width:500px;padding-top:50px;">
			<table id="table-agenda" class="display" width="100%" cellspacing="0">
				<thead>
					<tr>
						<th>Foto</th>
						<th>Nome</th>
						<th>Início</th>
						<th>Ações</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach items="${agendas}" var="agenda">
						<tr>
							<td><img src="${agenda.imagemPrincipal.url}" width="50" height="50"/></td>
							<td>${agenda.nome}</td>
							<td>${agenda.dateInicioEvento}</td>
							<td>
								<a href="editar-agenda?agendaId=${agenda.agendaId}">Editar</a>
								<a href="remover-agenda?agendaId=${categoria.agendaId}">Excluir</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				
			</table>
		</div>
		
		<script type="text/javascript" src="js/jquery-1.9.0.min.js"></script>
		<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
		<script type="text/javascript">
			
			$(document).ready(function() {
			    $('#table-agenda').DataTable(
		    		{
		    			"language": {
		                "url": "js/portuguese.json"
		            	}
		    		}
			    );
			});
		
		</script>
	
	</body>
</html>