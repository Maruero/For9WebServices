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
		
		${ categoria.nome }
		
		<a href="adicionar-associado?categoriaId=${categoria.id}">Adicionar</a>
		
		<div style="width:500px;padding-top:50px;">
			<table id="table-associado" class="display" width="100%" cellspacing="0">
				<thead>
					<tr>
						<th>Nome</th>
						<th>Ações</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach items="${associados}" var="associado">
						<tr>
							<td>${associado.nome}</td>
							<td>
								<a href="editar-associado?associadoId=${associado.id}&categoriaId=${categoria.id}">Editar</a>
								<a href="remover-associado?associadoId=${associado.id}&categoriaId=${categoria.id}">Excluir</a>
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
			    $('#table-associado').DataTable(
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