<c:if test="${sessionScope.userSession.compte.typeCompte == 1}">
	<c:redirect url="/chef-labo-dashboard?action=cure" />
</c:if>
<c:if test="${sessionScope.userSession.compte.typeCompte == 2}">
	<c:redirect url="/chef-equipe-dashboard?action=cure" />
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="shortcut icon" href="<c:url value="/images/logo.png"/>">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="<c:url value="/assets/css/style.css"/>">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initialscale=1.0">
<title>Connectez-vous</title>
</head>
<body>

	<c:import url="/WEB-INF/views/commun/header.jsp"></c:import>
	<br />
	<br />
	<br />
	<div class="row">
		<div class="container well">
			<form method="post" class="form form-signin" role="form"
				action='<c:url value="/compte?action=conn"/>' id="connexion">
				<br> <br> <br>
				<h3 class="form-signin-heading">
					<span class="glyphicon glyphicon-globe"></span> Connexion
				</h3>
				<hr>
				<p class="errorlogin">
					<c:out value="${message}" />
				</p>
				<input type="text" id="login" name="login" class="form-control"
					placeholder="Nom Utilisateur" autofocus> <input
					type="password" id="password" name="password" class="form-control"
					placeholder="Mot de passe">
				<button class="btn btn-lg btn-default btn-block" id="submit"
					name="submit" type="submit">
					<span class="glyphicon glyphicon-ok-sign"></span> Connexion
				</button>
				<br>
				<p>
					<b>Remarque:</b> si vous n'avez pas de compte, vous pouvez vous
					inscrire <a href='<c:url value="/compte?action=insc"/>'>ici </a>
				</p>
				<br> <br> <br>
			</form>
		</div>
	</div>
	<c:import url="/WEB-INF/views/commun/footer.jsp"></c:import>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>