<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<div class="navbar-inner">
				<img alt="logo" class="logo-nav"
					src="<c:url value="/images/logo.png"/>"> <a
					href="<c:url value="#"/>" class="navbar-brand"> <b>MYDASHBOARD</b></a>
			</div>
			<div class="navbar-inner"></div>
		</div>
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<c:if test="${empty sessionScope.userSession}">
					<li class=""><a href="<c:url value="/"/>"><span
							class="glyphicon glyphicon-home"></span></a></li>
					<li class=""><a href="#"><span
							class="glyphicon glyphicon-th-large"></span> A propos</a></li>
					<li class=""><a href="#"><span
							class="glyphicon glyphicon-info-sign"></span> Aide</a></li>
				</c:if>
				<c:if test="${sessionScope.userSession.compte.typeCompte == 1}">
					<li class=""><a
						href='<c:url value="/chef-labo-dashboard?action=cure"/>'><span
							class="glyphicon glyphicon-home"></span></a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"><span
							class="glyphicon glyphicon-th-large"></span> Gestion equipes <b
							class="caret"></b> </a>
						<ul class="dropdown-menu">
							<li><a href='<c:url value="/gestionEquipe?action=add"/>'><span
									class="glyphicon glyphicon-plus"></span> Ajouter Equipe</a>
							<li><a href='<c:url value="/gestionEquipe?action=list"/>'>
									<span class="glyphicon glyphicon-list"></span> Lister Equipes
							</a>
						</ul></li>
					<li><a
						href='<c:url value="/chef-labo-dashboard?action=search" />'><span
							class="glyphicon glyphicon-search"></span> Recherche</a></li>

				</c:if>
				<c:if test="${sessionScope.userSession.compte.typeCompte == 2}">
					<li><a
						href='<c:url value="/chef-equipe-dashboard?action=cure"/>'><span
							class="glyphicon glyphicon-home"></span></a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"><span
							class="glyphicon glyphicon-th-large"></span> Gestion membres <b
							class="caret"></b> </a>
						<ul class="dropdown-menu">
							<li><a href='<c:url value="/gestionMembre?action=add"/>'><span
									class="glyphicon glyphicon-plus"></span> Ajouter membre</a>
							<li><a href='<c:url value="/gestionMembre?action=list"/>'><span
									class="glyphicon glyphicon-list"></span> Lister membre</a>
							<li><a
								href='<c:url value="/chef-equipe-dashboard?action=membre"/>'><span
									class="glyphicon glyphicon-user"></span> Evaluer membre</a>
						</ul></li>
					<li><a
						href='<c:url value="/chef-equipe-dashboard?action=search" />'><span
							class="glyphicon glyphicon-search"></span> Recherche</a></li>
				</c:if>
				<li class="dropdown"><c:if
						test="${!empty sessionScope.userSession}">
						<a href="#" class="dropdown-toggle " data-toggle="dropdown"><span
							class="glyphicon glyphicon-user"></span> <c:out
								value="${sessionScope.userSession.nom}" /> <c:out
								value="${sessionScope.userSession.prenom}" /> <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<c:if test="${sessionScope.userSession.compte.typeCompte == 1}">
								<li><a
									href='<c:url value="/chef-labo-dashboard?action=param"/>'><span
										class="glyphicon glyphicon-cog"></span> Paramétrage</a></li>
							</c:if>
							<c:if test="${sessionScope.userSession.compte.typeCompte == 2}">
								<li><a
									href='<c:url value="/chef-equipe-dashboard?action=param"/>'><span
										class="glyphicon glyphicon-cog"></span> Paramétrage</a></li>
							</c:if>
							<li><a href="<c:url value="/compte?action=deconn"/>"><span
									class="glyphicon glyphicon-off"></span> Déconnexion</a></li>
						</ul>
					</c:if> <c:if test="${empty sessionScope.userSession}">
						<a href="#" class="dropdown-toggle " data-toggle="dropdown"><span
							class="glyphicon glyphicon-user"></span> <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="<c:url value="/compte?action=insc"/>"><span
									class="glyphicon glyphicon-pencil"></span> Inscription</a></li>
							<li><a href="<c:url value="/compte?action=conn"/>"><span
									class="glyphicon glyphicon-globe"></span> Connexion</a></li>
						</ul>
					</c:if></li>
			</ul>
		</div>
	</div>
</nav>