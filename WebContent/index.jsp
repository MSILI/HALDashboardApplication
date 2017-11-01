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
<title>Bienvenu</title>
</head>
<body>
	<c:import url="/WEB-INF/views/commun/header.jsp"></c:import>
	<br>
	<div class="row">
		<br>
		<section class="col-sm-12 col-xs-12 col-lg-12 col-md-12">
			<div id="carousel" class="carousel slide">
				<ol class="carousel-indicators">
					<li data-target="#carousel" data-slide-to="0" class="active"></li>
					<li data-target="#carousel" data-slide-to="1"></li>
					<li data-target="#carousel" data-slide-to="2"></li>
					<li data-target="#carousel" data-slide-to="3"></li>
				</ol>
				<div class="carousel-inner">
					<div class="item active">
						<img alt="image1" src="<c:url value="/images/1.jpg"/>">
						<div class="container">
							<div class="carousel-caption " id="caption">
								<h1>Bienvenu à MYDASHBOARD</h1>
								<p class="">Inscrivrez-vous</p>
								<p>
									<a class="btn btn-lg btn-primary" href=""
										role="button"> <span class="glyphicon glyphicon-ok-sign"></span>
										Inscrivez-vous
									</a>
								</p>
							</div>
						</div>

					</div>
					<div class="item">
						<img alt="image1" src="<c:url value="/images/2.jpg"/>">
						<div class="container">
							<div class="carousel-caption " id="caption">
								<h1>Bienvenu à MYDASHBOARD</h1>
								<p class="">Inscrivrez-vous</p>
								<p>
									<a class="btn btn-lg btn-primary" href=""
										role="button"> <span class="glyphicon glyphicon-ok-sign"></span>
										Inscrivez-vous
									</a>
								</p>
							</div>
						</div>

					</div>
					<div class="item">
						<img alt="image1" src="<c:url value="/images/3.jpg"/>">
						<div class="container">
							<div class="carousel-caption " id="caption">
								<h1>Bienvenu à MYDASHBOARD</h1>
								<p class="">Inscrivrez-vous</p>
								<p>
									<a class="btn btn-lg btn-primary" href=""
										role="button"> <span class="glyphicon glyphicon-ok-sign"></span>
										Inscrivez-vous
									</a>
								</p>
							</div>
						</div>
					</div>
					<div class="item">
						<img alt="image1" src="<c:url value="/images/4.jpg"/>">
						<div class="container">
							<div class="carousel-caption " id="caption">
								<h1>Bienvenu à MYDASHBOARD</h1>
								<p class="">Inscrivrez-vous</p>
								<p>
									<a class="btn btn-lg btn-primary" href=""
										role="button"> <span class="glyphicon glyphicon-ok-sign"></span>
										Inscrivez-vous
									</a>
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>

	<c:import url="/WEB-INF/views/commun/footer.jsp"></c:import>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script>
		$(function() {
			$('.carousel').carousel();
		});
	</script>
</body>
</html>