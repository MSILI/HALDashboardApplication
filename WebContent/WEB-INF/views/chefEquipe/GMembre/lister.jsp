<c:if test="${sessionScope.userSession.compte.typeCompte != 2}">
	<c:redirect url="/compte?action=conn" />
</c:if>
<!DOCTYPE html">
<html>
<head>
<meta charset="utf-8">
<title>Lister Membre</title>
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
	<div class="row">
		<div class="container">
			<br /> <br />
			<div class="row">
				<div class="page-header">
					<h3>Liste des membre de l'equipe</h3>
				</div>
			</div>
			<div class="row">
				<table id="tableau"
					class="display table table-striped table-responsive">
					<thead>
						<tr>
							<th>ID HAL</th>
							<th>Nom</th>
							<th>Prenom</th>
							<th>Grade</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th>ID HAL</th>
							<th>Nom</th>
							<th>Prenom</th>
							<th>Grade</th>
							<th></th>
							<th></th>
						</tr>
					</tfoot>
					<tbody>
						<c:forEach items="${listMembres}" var="membreEq"
							varStatus="boucle">
							<tr>
								<td><c:out value="${membreEq.membre.idHal}" /></td>
								<td><c:out value="${membreEq.membre.nom}" /></td>
								<td><c:out value="${membreEq.membre.prenom}" /></td>
								<td><c:out value="${membreEq.membre.fonction}" /></td>
								<td><a
									href='<c:url value= "/gestionMembre?action=edit"><c:param name="id" value="${membreEq.membre.id}"/></c:url>'><span
										class="glyphicon glyphicon-pencil"></span></a></td>
								<td><a
									data-confirm="Etes-vous certain de vouloir supprimer?"
									href='<c:url value= "/gestionMembre?action=delete"><c:param name="id" value="${membreEq.membre.id}"/></c:url>'><span
										class="glyphicon glyphicon-trash"></span></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

		</div>
	</div>
	<c:import url="/WEB-INF/views/commun/footer.jsp"></c:import>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('#tableau')
									.DataTable(
											{
												language : {
													processing : "Traitement en cours...",
													search : "Rechercher&nbsp;:",
													lengthMenu : "Afficher _MENU_ &eacute;l&eacute;ments",
													info : "Affichage de l'&eacute;lement _START_ &agrave; _END_ sur _TOTAL_ &eacute;l&eacute;ments",
													infoEmpty : "Affichage de l'&eacute;lement 0 &agrave; 0 sur 0 &eacute;l&eacute;ments",
													infoFiltered : "(filtr&eacute; de _MAX_ &eacute;l&eacute;ments au total)",
													infoPostFix : "",
													loadingRecords : "Chargement en cours...",
													zeroRecords : "Aucun &eacute;l&eacute;ment &agrave; afficher",
													emptyTable : "Aucune donnée disponible dans le tableau",
													paginate : {
														first : "Premier",
														previous : "Pr&eacute;c&eacute;dent",
														next : "Suivant",
														last : "Dernier"
													},
													aria : {
														sortAscending : ": activer pour trier la colonne par ordre croissant",
														sortDescending : ": activer pour trier la colonne par ordre décroissant"
													}
												}

											});
						});

		$(function() {
			$('a[data-confirm]')
					.click(
							function(ev) {
								var href = $(this).attr('href');

								if (!$('#dataConfirmModal').length) {
									$('body')
											.append(
													'<div id="dataConfirmModal" class="modal" role="dialog" aria-labelledby="dataConfirmLabel" aria-hidden="true"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button><h3 id="dataConfirmLabel">Merci de confirmer</h3></div><div class="modal-body"></div><div class="modal-footer"><button class="btn" data-dismiss="modal" aria-hidden="true">Non</button><a class="btn btn-info" id="dataConfirmOK">Oui</a></div></div></div></div>');
								}
								$('#dataConfirmModal').find('.modal-body')
										.text($(this).attr('data-confirm'));
								$('#dataConfirmOK').attr('href', href);
								$('#dataConfirmModal').modal({
									show : true
								});

								return false;
							});
		});
	</script>
</body>
</html>