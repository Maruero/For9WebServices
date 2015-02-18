<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>For9</title>
	</head>
	<body>
	
		<form action="logon" method="post" onsubmit="return validate(this);">
		
			Usuário: <input type="text" name="login.usuario" value="" requiredLength="1" requiredMessage="Por favor, preencha o login."/> <br>
			Senha: <input type="password" name="login.password" value="" requiredLength="1" requiredMessage="Por favor, preencha a senha."/> <br>
			<input type="submit" value="entrar" /> <br>
			${ message }
		
		</form>
	
		<script type="text/javascript" src="js/for9.js"></script>
		<script type="text/javascript" src="js/jquery-1.9.0.min.js"></script>
	
	</body>
</html>