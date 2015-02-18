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
		
		<c:if test="${message != null }">
			${ message }
			
			<br>
			<br>
		</c:if>
		
		
		
		<div style="width:500px;padding-top:50px;">
			
			<form action="salvar-contatos"  method="post" onsubmit="return validate(this);">
				<input type="hidden" name="contato.id" value="${ contato.id }" />
				
				Horário de funcionamento <input type="text" name="contato.horarioFuncionamento" value="${contato.horarioFuncionamento}" requiredLength="1" requiredMessage="Por favor, informe o horário de funcionamento." /> <br>
				Endereço <input type="text" name="contato.endereco" value="${contato.endereco}" requiredLength="1" requiredMessage="Por favor, informe o endereço." /> <br>
				Estado <input type="text" name="contato.estado" value="${contato.estado}" requiredLength="1" requiredMessage="Por favor, informe o estado." /> <br>
				Cidade <input type="text" name="contato.cidade" value="${contato.cidade}" requiredLength="1" requiredMessage="Por favor, informe a cidade." /> <br>
				CEP <input type="text" name="contato.cep" value="${contato.cep}" requiredLength="1" requiredMessage="Por favor, informe o cep." /> <br>				
				Telefone 1 <input type="text" name="contato.telefone1" value="${contato.telefone1}" requiredLength="1" requiredMessage="Por favor, informe o telefone 1." /> <br>
				Telefone 2 <input type="text" name="contato.telefone2" value="${contato.telefone2}" requiredLength="1" requiredMessage="Por favor, informe o telefone 2." /> <br>
				Latitude <input type="text" name="contato.latitude" value="${contato.latitude}" requiredLength="1" requiredMessage="Por favor, informe a latitude." /> <br>
				Longitude <input type="text" name="contato.longitude" value="${contato.longitude}" requiredLength="1" requiredMessage="Por favor, informe a longitude" /> <br>
				
				<input type="submit" value="Salvar"/>
				
			</form>
			
		</div>
		
		<script type="text/javascript" src="js/jquery-1.9.0.min.js"></script>
		<script type="text/javascript" src="js/for9.js"></script>
		<script type="text/javascript">
		
		</script>
	
	</body>
</html>