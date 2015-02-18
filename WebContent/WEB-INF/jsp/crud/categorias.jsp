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
		
		<a href="adicionar-categoria">Adicionar</a>
		
		<div style="width:500px;padding-top:50px;">
			<table id="table-categoria" class="display" width="100%" cellspacing="0">
				<thead>
					<tr>
						<th>Nome</th>
						<th>Ações</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach items="${categorias}" var="categoria">
						<tr>
							<td>${categoria.nome}</td>
							<td>
								<a href="editar-categoria?categoriaId=${categoria.id}">Editar</a>
								<a href="remover-categoria?categoriaId=${categoria.id}">Excluir</a>
								<a href="listar-associados?categoriaId=${categoria.id}">Associados</a>
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
			    $('#table-categoria').DataTable(
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