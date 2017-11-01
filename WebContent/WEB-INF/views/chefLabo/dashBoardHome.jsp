<c:if test="${sessionScope.userSession.compte.typeCompte != 1}">
	<c:redirect url="/compte?action=conn" />
</c:if>
<!DOCTYPE html">
<html>
<head>
<meta charset="utf-8">
<title>Dashboard</title>
<link rel="shortcut icon" href="<c:url value="/images/logo.png"/>">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="<c:url value="/assets/css/style.css"/>">
</head>
<body>
	<div class="row">
		<c:import url="/WEB-INF/views/commun/header.jsp"></c:import>
	</div>

	<div class="container">
		<div class="row">
			<br /> <br /> <br /> <br />
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3>
						Laboratoire :
						<c:out value="${sessionScope.userSession.laboratoire.acronyme}" />
					</h3>
				</div>
				<div class="panel-body">
					<span class="col-md-3"><label>ID HAL : </label><i><c:out
								value="${sessionScope.userSession.idHal}" /></i></span> <span
						class="col-md-3"><label>Nom : </label><i><c:out
								value="${sessionScope.userSession.nom}" /></i></span> <span
						class="col-md-3"><label>Pr�nom : </label><i><c:out
								value="${sessionScope.userSession.prenom}" /></i></span> <span
						class="col-md-3"><label>Grade : </label><i><c:out
								value="${sessionScope.userSession.fonction}" /></i></span>

				</div>
			</div>
		</div>

		<div class="row">

			<div class="panel panel-default">
				<div class="panel-heading">
					<h3>
						Mon Tableau de bord :
						<c:out value="${cureYear}" />
					</h3>
				</div>
				<div class="panel-body">
					<p>
						<c:if test="${empty listEquipes}">
							<a href='<c:url value="/gestionEquipe?action=add"/>' class="btn btn-primary">Ajouter
								des equipe � votre laboratoire</a>
						</c:if>
					</p>
					<c:if test="${!empty listEquipes}">
						<div class="row">
							<p>
								<input type="hidden" id="AnneeDebut"
									value='<c:out value="${cureYear}"/>' disabled="disabled" /> <input
									type="hidden" id="MoisDebut" value='<c:out value="1"/>'
									disabled="disabled" /> <input type="hidden" id="AnneeFin"
									value='<c:out value="${cureYear}"/>' disabled="disabled" /> <input
									type="hidden" id="MoisFin"
									value='<c:out value="${cureMonth}"/>' disabled="disabled" /> <input
									type="hidden" id="lab"
									value="<c:out value="${sessionScope.userSession.laboratoire.idHal}"/>"
									disabled="disabled" /> <select id="listeEquipes"
									disabled="disabled" hidden="hidden">
									<c:forEach items="${listEquipes}" var="equipe"
										varStatus="boucle">
										<option value="${equipe.idHal}">${equipe.acronyme}</option>
									</c:forEach>
								</select>
							</p>
						</div>
						<div class="row" id="errorDiv">
							<div class="container" id="error"></div>
						</div>
						<div id="infoDiv">
							<div class="row">
								<div class="col-md-4">
									<div class="panel panel-info">
										<div class="panel-heading">
											<h4>Publications par type de documents</h4>
										</div>
										<div class="panel-body" id="CambChartContainer">
											<canvas id="CambChart"></canvas>
										</div>
									</div>

								</div>
								<div class="col-md-7">
									<div class="panel panel-info">
										<div class="panel-heading">
											<h4>Publications par equipes</h4>
										</div>
										<div class="panel-body" id="BatChartContainer">
											<canvas id="BatChart" class="col-md-6"></canvas>
										</div>
									</div>

								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="panel panel-info">
										<div class="panel-heading">
											<h4>Publications par mois</h4>
										</div>
										<div class="panel-body" id="LineChartContainer">
											<canvas id="LineChart"></canvas>
										</div>
									</div>

								</div>

								<div id="MaDiv" class="col-md-6">
									<h3>Liste des documents</h3>
									<hr>
									<div id="DivTableau">
										<table id="tableau"
											class="display table table-striped table-responsive">
										</table>
									</div>

								</div>
							</div>
						</div>
					</c:if>
				</div>
			</div>
		</div>

	</div>
	<c:import url="/WEB-INF/views/commun/footer.jsp"></c:import>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.js"></script>
	<script type="text/javascript"
		src="<c:url value="/assets/js/alertUtils.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/assets/js/scriptLabo.js"/>"></script>

	<script type="text/javascript">
		var and = document.getElementById("AnneeDebut").value;
		var anf = document.getElementById("AnneeFin").value;
		var md = document.getElementById("MoisDebut").value;
		var mf = document.getElementById("MoisFin").value;
		$(document).ready(function() {
			getData(and, anf, md, mf);
		});
	</script>
</body>
</html>