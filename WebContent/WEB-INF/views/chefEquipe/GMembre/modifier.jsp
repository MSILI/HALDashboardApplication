<c:if test="${sessionScope.userSession.compte.typeCompte != 2}">
	<c:redirect url="/compte?action=conn" />
</c:if>
<!DOCTYPE html">
<html>
<head>
<meta charset="utf-8">
<title>Ajouter un Membre</title>
<link rel="shortcut icon" href="<c:url value="/images/logo.png"/>">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="<c:url value="/assets/css/style.css"/>">
<link rel="stylesheet"
	href='<c:url value = "/assets/css/validationEngine.jquery.css"/>'>
</head>
<body>
	<div class="row">
		<c:import url="/WEB-INF/views/commun/header.jsp"></c:import>
	</div>
	<div class="row">
		<div class="container">
			<br /> <br />
			<div class="page-header">
				<h3>Modifier un membre</h3>
			</div>
			<form id="form" action='<c:url value="/gestionMembre?action=edit"/>'
				method="post">
				<div class="col-md-6">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3>Informations membre</h3>
						</div>
						<div class="panel-body">
							<div class="form-group">
								<input type="hidden" name="id"
									value='<c:out value="${membre.id}"/>' class="form-control" />
							</div>
							<div class="form-group">
								<label>ID HAL : </label> <input type="text" name="idHal"
									value="<c:out value="${membre.idHal}"/>"
									class="form-control validate[required,minSize[4],custom[integer]] text-input" />
							</div>
							<div class="form-group">
								<label>Nom : </label> <input type="text" name="nom"
									value="<c:out value="${membre.nom}"/>"
									class="form-control validate[required,minSize[3],custom[onlyLetterNumber]] text-input" />
							</div>
							<div class="form-group">
								<label>Prénom : </label> <input type="text" name="prenom"
									value="<c:out value="${membre.prenom}"/>"
									class="form-control validate[required,minSize[3],custom[onlyLetterNumber]] text-input" />
							</div>
							<div class="form-group">
								<label>Grade : </label> <select name="grade"
									class="form-control">
									<option value="<c:out value="${membre.fonction}"/>"
										selected="selected"><c:out value="${membre.fonction}" /></option>
									<option value="Professeur">Professeur</option>
									<option value="M.C.A">M.C.A</option>
									<option value="M.C.B">M.C.B</option>
									<option value="M.A">M.A</option>
								</select>
							</div>
							<button type="submit" class="btn btn-primary btn-lg btn-block">modifier</button>
						</div>
					</div>
				</div>
			</form>

		</div>
	</div>
	<c:import url="/WEB-INF/views/commun/footer.jsp"></c:import>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jQuery-Validation-Engine/2.6.4/jquery.validationEngine.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jQuery-Validation-Engine/2.6.4/languages/jquery.validationEngine-fr.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#form").validationEngine();
		});
	</script>
</body>
</html>