<c:if test="${sessionScope.userSession.compte.typeCompte != 1}">
	<c:redirect url="/compte?action=conn" />
</c:if>
<!DOCTYPE html">
<html>
<head>
<meta charset="utf-8">
<title>Modifier Equipe</title>
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
				<h3>Modifier une equipe</h3>
			</div>
			<form id="form" action='<c:url value="/gestionEquipe?action=edit"/>'
				method="post">
				<div class="col-md-6">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3>Informations Chef Equipe</h3>
						</div>
						<div class="panel-body">
							<div class="form-group">
								<input type="hidden" name="id"
									value='<c:out value="${chefEquipe.id}"/>' class="form-control" />
							</div>
							<div class="form-group">
								<label>ID HAL : </label> <input type="text" name="idHal"
									value="<c:out value="${chefEquipe.idHal}"/>"
									class="form-control validate[required,minSize[4],custom[integer]] text-input" />
							</div>
							<div class="form-group">
								<label>Nom : </label> <input type="text" name="nom"
									value="<c:out value="${chefEquipe.nom}"/>"
									class="form-control validate[required,minSize[3],custom[onlyLetterNumber]] text-input" />
							</div>
							<div class="form-group">
								<label>Prénom : </label> <input type="text" name="prenom"
									value="<c:out value="${chefEquipe.prenom}"/>"
									class="form-control validate[required,minSize[3],custom[onlyLetterNumber]] text-input" />
							</div>
							<div class="form-group">
								<label>Grade : </label> <select name="grade"
									class="form-control">
									<option value="<c:out value="${chefEquipe.fonction}"/>"
										selected="selected"><c:out
											value="${chefEquipe.fonction}" /></option>
									<option value="Professeur">Professeur</option>
									<option value="M.C.A">M.C.A</option>
									<option value="M.C.B">M.C.B</option>
									<option value="M.A">M.A</option>
								</select>
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3>Informations compte Chef Equipe</h3>
						</div>
						<div class="panel-body">
							<div class="form-group">
								<input type="hidden" name="idCompte"
									value="<c:out value="${chefEquipe.compte.id}"/>"
									class="form-control" />
							</div>
							<div class="form-group">
								<label>Nom utilisateur : </label> <input type="text"
									name="userName"
									value="<c:out value="${chefEquipe.compte.userName}"/>"
									class="form-control validate[required,minSize[3],custom[onlyLetterNumber]] text-input" />
							</div>
							<div class="form-group">
								<label>Mot de passe : </label> <input type="password"
									name="password" value=""
									class="form-control  validate[minSize[3]] text-input" /> <input
									type="hidden" name="oldPassword"
									value="<c:out value="${chefEquipe.compte.password}"/>"
									class="form-control" />
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3>Informations Equipe</h3>
						</div>
						<div class="panel-body">
							<div class="form-group">
								<input type="hidden" name="idEq"
									value="<c:out value="${chefEquipe.equipe.id}"/>"
									class="form-control" />
							</div>
							<div class="form-group">
								<label>ID HAL : </label> <input type="text" name="idHalEq"
									value="<c:out value="${chefEquipe.equipe.idHal}"/>"
									class="form-control validate[required,minSize[4],custom[integer]] text-input" />
							</div>
							<div class="form-group">
								<label>Nom Equipe : </label> <input type="text" name="nomEq"
									value="<c:out value="${chefEquipe.equipe.nom}"/>"
									class="form-control validate[required,minSize[3]] text-input" />
							</div>
							<div class="form-group">
								<label>Acronyme Equipe : </label> <input type="text"
									name="acronymeEq"
									value="<c:out value="${chefEquipe.equipe.acronyme}"/>"
									class="form-control validate[required,minSize[3]] text-input" />
							</div>
						</div>
					</div>
					<button type="submit" class="btn btn-primary btn-lg btn-block">Modifier</button>
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