<c:if test="${sessionScope.userSession.compte.typeCompte != 2}">
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
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css">
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
					<div class="row">
						<h3>
							<span class="col-md-4"> Laboratoire : <c:out
									value="${sessionScope.userSession.equipe.laboratoire.acronyme}" />
							</span> <span class="col-md-4"> Equipe : <c:out
									value="${sessionScope.userSession.equipe.acronyme}" />
							</span>
						</h3>
					</div>
				</div>
				<div class="panel-body">
					<span class="col-md-3"><label>ID HAL : </label><i><c:out
								value="${sessionScope.userSession.idHal}" /></i></span> <span
						class="col-md-3"><label>Nom : </label><i><c:out
								value="${sessionScope.userSession.nom}" /></i></span> <span
						class="col-md-3"><label>Prénom : </label><i><c:out
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
						Evaluer un Membre :
						<c:out value="${cureYear}" />
					</h3>
				</div>
				<div class="panel-body">
					<p>
						<c:if test="${empty listMembres}">
							<a href='<c:url value=""/>' class="btn btn-primary">Ajouter
								des equipe à votre laboratoire</a>
						</c:if>
					</p>
					<div class="row">
						<p>
							<input type="hidden" id="equipe"
								value="<c:out value="${sessionScope.userSession.equipe.idHal}"/>"
								disabled="disabled" />
						</p>
					</div>
					<div class="row">
						<div class="col-md-3">
							<div class="form-group">
								<div class="col-md-3">
									<label>Membre: </label>
								</div>
								<div class="col-md-9">
									<select id="listMembres" class="form-control">
										<c:forEach items="${listMembres}" var="membreEq"
											varStatus="boucle">
											<option value="${membreEq.membre.idHal}">${membreEq.membre.nom}
												${membreEq.membre.prenom}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-3">
							<div class="form-group">
								<div class="col-md-3">
									<label>Date debut </label>
								</div>
								<div class="col-md-4">
									<select id="MoisDebutS" class="form-control">
										<c:forEach items="${mois}" var="mois" varStatus="boucle">
											<option value="${mois}">${mois}</option>
										</c:forEach>
									</select>
								</div>
								<div class="col-md-5">
									<select id="AnneeDebutS" class="form-control">
										<c:forEach items="${annees}" var="annee" varStatus="boucle">
											<option value="${annee}">${annee}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-3">
							<div class="form-group">
								<div class="col-md-3">
									<label>Date fin</label>
								</div>
								<div class="col-md-4">
									<select id="MoisFinS" class="form-control">
										<c:forEach items="${mois}" var="mois" varStatus="boucle">
											<option value="${mois}">${mois}</option>
										</c:forEach>
									</select>
								</div>

								<div class="col-md-5">
									<select id="AnneeFinS" class="form-control">
										<c:forEach items="${annees}" var="annee" varStatus="boucle">
											<option value="${annee}">${annee}</option>
										</c:forEach>
									</select>
								</div>

							</div>
						</div>
						<div class="col-md-3">
							<div class="form-group">
								<button id="btn" class="btn btn-primary">
									<span class="glyphicon glyphicon-search"></span> chercher
								</button>
							</div>
						</div>
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
							<div class="col-md-8">
								<div class="panel panel-info">
									<div class="panel-heading">
										<h4>Publications par mois</h4>
									</div>
									<div class="panel-body" id="LineChartContainer">
										<canvas id="LineChart"></canvas>
									</div>
								</div>
							</div>

						</div>

						<div class="row">
							<div id="MaDiv" class="col-md-12">
								<div class="row">
									<div class="container">
										<h3>Liste des documents</h3>
										<hr>
										<table id="tableau"
											class="display table table-striped table-responsive">
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
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
	<script
		src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="<c:url value="/assets/js/alertUtils.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/assets/js/periodeValide.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="/assets/js/scriptAuteur.js"/>"></script>

	<script type="text/javascript">
		btn.addEventListener("click", function() {
			var andS = document.getElementById("AnneeDebutS").value;
			var anfS = document.getElementById("AnneeFinS").value;
			var mdS = document.getElementById("MoisDebutS").value;
			var mfS = document.getElementById("MoisFinS").value;
			if (validForm(parseInt(andS), parseInt(anfS), parseInt(mdS),
					parseInt(mfS))) {
				$("#infoDiv").show();
				getData(andS, anfS, mdS, mfS);
				$("#error").remove();
			} else {
				$("#infoDiv").hide();
			}
		});

		$(function() {
			$("#infoDiv").hide();
		});

	</script>
</body>
</html>