var ListeIdHalLabo = [];
var ListeAcronymes = [];
var tabcouleurCam = [];
var tabcouleurBat = [];

function getSelectValue() {
	var listeEquipes = document.getElementById("listeEquipes");
	var i;
	for (i = 0; i < listeEquipes.length; i++) {
		ListeIdHalLabo.push(listeEquipes.options[i].value);
		ListeAcronymes.push(listeEquipes.options[i].text);
	}
}

function getData(AnneeDebut, AnneeFin, MoisDebut, MoisFin) {
	getSelectValue();
	if (MoisDebut < 10) {
		Debut = String(AnneeDebut) + String(MoisDebut);
	} else {
		Debut = String(AnneeDebut) + String(AnneeDebut);
	}

	if (MoisFin < 10) {
		Fin = String(AnneeFin) + String(MoisFin);
	} else {
		Fin = String(AnneeFin) + String(MoisFin);
	}

	var PeriodeAns = '[' + AnneeDebut + ' TO ' + AnneeFin + ']';
	var PeriodeMois;
	var Labos = document.getElementById("lab").value;
	var equipe = ListeIdHalLabo[0];
	for (i = 1; i < ListeIdHalLabo.length; i++) {
		equipe += ' OR ' + ListeIdHalLabo[i] + ' ';
	}
	urls = 'https://api.archives-ouvertes.fr/search/?wt=json&q= rteamStructId_i:('
			+ equipe
			+ ') AND labStructId_i: '
			+ Labos
			+ ' AND producedDateY_i: '
			+ PeriodeAns
			+ ' &fl=producedDateY_i%20AND%20docType_s AND producedDateY_i AND producedDateM_i AND rteamStructId_i AND rteamStructAcronym_s AND label_s AND docid AND uri_s &rows=1000';
	AnalyseJson(urls, AnneeDebut, AnneeFin, MoisDebut, MoisFin);
}

function AnalyseJson(url, AnneeDebut, AnneeFin, MoisDebut, MoisFin) {
	resetCharts();
	var requetes = [];
	var ourRequest = new XMLHttpRequest();
	var ourRequest = new XMLHttpRequest();
	ourRequest.open('GET', url);

	ourRequest.addEventListener("error", transferFailed, false);

	function transferFailed(evt) {
		showErrorAlert(" Erreur de connexion au serveur de HAL ! ");
		$("#infoDiv").hide();
	}

	ourRequest.onload = function() {
		var ourData = JSON.parse(ourRequest.responseText);
		if (typeof (ourData.error) == "undefined") {
			var ourinfo = ourData.response.docs;
			if (ourinfo.length > 0) {
				renderHTML(ourinfo, AnneeDebut, AnneeFin, MoisDebut, MoisFin);
			} else {
				showInfoAlert(" Aucun document pour cette periode ! ");
				$("#infoDiv").hide();

			}
		} else {
			showErrorAlert(" Erreur de connexion au serveur de HAL ! ");
			$("#infoDiv").hide();
		}
		// Fin
	};
	ourRequest.send();
}

function renderHTML(data, AnneeDebut, AnneeFin, MoisDebut, MoisFin) {

	var htmlString = "<p></p>";
	var NombredeDocumentMois = [];
	var labels = [ "Janv ", "Fevr ", "Mars ", "Avr ", "Mai ", "Juin ", "Juil ",
			"Aout ", "Sept ", "Oct ", "Nov ", "Dec " ];
	var labMoiString = [];
	var labelMois = [];
	var labelType = [];
	var NombreparType = [];
	var tabAnnee = [];
	var Mois = [ "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" ];
	var nombreArticleSansMois = [];
	var periode;

	// Labes Mois

	// Debut
	console.log(AnneeDebut);
	for (i = AnneeDebut; i <= AnneeFin; i++) {
		tabAnnee.push(i);
		nombreArticleSansMois.push(0);

	}
	if (tabAnnee.length == 1) {
		for (k = (MoisDebut - 1); k < MoisFin; k++) {

			labelMois.push(String(tabAnnee[0]) + Mois[k]);
			labMoiString.push(labels[k] + String(tabAnnee[0]));
			NombredeDocumentMois.push(0);
		}

	} else {
		for (j = 0; j < tabAnnee.length; j++) {
			if (j == 0) {
				for (k = (MoisDebut - 1); k <= 11; k++) {

					labelMois.push(String(tabAnnee[j]) + Mois[k]);
					labMoiString.push(labels[k] + String(tabAnnee[j]));
					NombredeDocumentMois.push(0);
				}
			} else {
				if (j < tabAnnee.length - 1) {
					for (p = 0; p < Mois.length; p++) {

						labelMois.push(String(tabAnnee[j]) + Mois[p]);
						labMoiString.push(labels[p] + String(tabAnnee[j]));
						NombredeDocumentMois.push(0);
					}

				} else {
					for (l = 0; l <= (MoisFin - 1); l++) {

						labelMois.push(String(tabAnnee[j]) + Mois[l]);
						labMoiString.push(labels[l] + String(tabAnnee[j]));
						NombredeDocumentMois.push(0);
					}

				}
			}
			console.log(tabAnnee[j]);
		}
	}

	// FIN

	labelMois.sort();
	for (a = 0; a < tabAnnee.length; a++) {
		for (l = 0; l < data.length; l++) {

			periode = String(data[l].producedDateY_i)
					+ String(data[l].producedDateM_i);

			if (periode == String(data[l].producedDateY_i) + "undefined") {
				if (tabAnnee[a] == data[l].producedDateY_i) {
					nombreArticleSansMois[a] = nombreArticleSansMois[a] + 1;
				}

			}
		}
	}

	for (b = 0; b < nombreArticleSansMois.length; b++) {
		console.log(nombreArticleSansMois[b]);

	}

	for (ii = 0; ii < labelMois.length; ii++) {
		console.log(labelMois[ii]);

		for (l = 0; l < data.length; l++) {

			type = String(data[l].producedDateY_i)
					+ String(data[l].producedDateM_i);

			if (labelMois[ii] == type) {
				NombredeDocumentMois[ii] = NombredeDocumentMois[ii] + 1;

			}
		}

	}

	labelType.push(data[0].docType_s);
	NombreparType.push(0);

	// Debut

	for (i = 0; i < data.length; i++) {

		type = data[i].docType_s;
		periode = String(data[i].producedDateY_i)
				+ String(data[i].producedDateM_i);

		if (!(labelType.includes(type)) && (labelMois.includes(periode))) {

			labelType.push(data[i].docType_s);
			NombreparType.push(0);
		}
	}

	labelType.sort();

	for (ii = 0; ii < labelType.length; ii++) {
		console.log(labelType[ii]);

		for (l = 0; l < data.length; l++) {

			periode = String(data[l].producedDateY_i)
					+ String(data[l].producedDateM_i);

			if ((labelType[ii] == data[l].docType_s)
					&& (labelMois.includes(periode))) {
				NombreparType[ii] = NombreparType[ii] + 1;

			}

		}
		console.log(NombreparType[ii]);

	}

	// Fin

	// var ListeIdHalLabo = ["391223","391950","389915","390336"];

	var NomEquipe = [];
	var NombreParEquipe = [];
	for (k = 0; k < ListeIdHalLabo.length; k++) {
		console.log(ListeIdHalLabo[k]);
		NombreParEquipe.push(0);

	}

	for (i = 0; i < data.length; i++) {
		console.log(data[i].rteamStructId_i);
		for (j = 0; j < ListeIdHalLabo.length; j++) {
			var id = ListeIdHalLabo[j];
			periode = String(data[i].producedDateY_i)
					+ String(data[i].producedDateM_i);

			var tab = data[i].rteamStructId_i;
			for (var p = 0; p < tab.length; p++) {
				if ((tab[p] == ListeIdHalLabo[j])
						&& (labelMois.includes(periode))) {
					NombreParEquipe[j] = NombreParEquipe[j] + 1;
				}
			}

		}

	}
	Affichertableau(labelMois, data, "tous", "tous", labMoiString);
	AfficherDiagrammeCambaire(labelMois, data, NombreparType, labelType);
	AfficherDiagrammeBaton(NombreParEquipe, ListeAcronymes, labelMois, data);
	AfficherLineChart(labelMois, data, NombredeDocumentMois, labMoiString);

}

function AfficherLineChart(labelMois, data, info, labell) {
	const
	CHART = document.getElementById("LineChart");
	let
	lineChart = new Chart(CHART, {
		type : 'line',
		data : {
			labels : labell,
			datasets : [ {
				label : "Documents par mois",
				fill : false,
				lineTension : 0.1,
				backgroundColor : "rgba(75,192,192,0.4)",
				borderColor : "rgba(75,192,192,1)",
				borderCapStyle : 'butt',
				borderDash : [],
				borderDashOffset : 0.0,
				borderJoinStyle : 'miter',
				pointBorderColor : "rgba(75,192,192,1)",
				pointBackgroundColor : "#fff",
				pointBorderWidth : 1,
				pointHoverRadius : 1,
				pointHoverBackgroundColor : "rgba(75,192,192,1)",
				pointHoverBorderColor : "rgba(220,220,220,1)",
				pointHoverBorderWidth : 2,
				pointRadius : 1,
				pointHitRadius : 10,
				data : info,

			} ]
		},
		options : {
			scales : {
				yAxes : [ {
					ticks : {
						beginAtZero : true
					}
				} ]
			}
		}
	});

	document.getElementById("LineChart").onclick = function(evt) {
		var activePoints = lineChart.getElementsAtEvent(evt);

		if (activePoints.length > 0) {
			// get the internal index of slice in pie chart
			var clickedElementindex = activePoints[0]["_index"];
			// get specific label by index
			var label = lineChart.data.labels[clickedElementindex];
			// get value by index
			var value = lineChart.data.datasets[0].data[clickedElementindex];
			/* other stuff that requires slice's label and value */

			if (value == 0) {

				showInfoTabAlert(" Aucun document pour ce Mois !")
			} else {
				Affichertableauline(labelMois, data, label, value, labell);
			}

		}
	}

}

function AfficherDiagrammeBaton(info, labell, labelMois, data) {
	var ctx = document.getElementById("BatChart").getContext('2d');
	var myChart = new Chart(ctx, {
		type : 'bar',
		data : {
			labels : labell,
			datasets : [ {
				label : 'Documents',
				backgroundColor : 'rgb(255,69,0)',
				data : info,
			} ]
		},
		options : {
			scales : {
				yAxes : [ {
					ticks : {
						beginAtZero : true
					}
				} ]
			}
		}
	});

	document.getElementById("BatChart").onclick = function(evt) {
		var activePoints = myChart.getElementsAtEvent(evt);

		if (activePoints.length > 0) {
			// get the internal index of slice in pie chart
			var clickedElementindex = activePoints[0]["_index"];

			// get specific label by index
			var label = myChart.data.labels[clickedElementindex];

			// get value by index
			var value = myChart.data.datasets[0].data[clickedElementindex];

			if (value == 0) {

				showInfoTabAlert(" Aucun document pour cette equipe !")
			} else {
				Affichertableaulineequipe(labelMois, data, label, value, labell);
			}

		}
	}

}

function AfficherDiagrammeCambaire(labelMois, data, info, labell) {
	gencouleur(labell.length, tabcouleurCam);
	var ctx = document.getElementById("CambChart").getContext('2d');
	var myChart = new Chart(ctx, {
		type : 'pie',
		data : {
			labels : labell,
			datasets : [ {
				backgroundColor : tabcouleurCam,
				data : info
			} ]
		}
	});

	document.getElementById("CambChart").onclick = function(evt) {
		var activePoints = myChart.getElementsAtEvent(evt);

		if (activePoints.length > 0) {
			// get the internal index of slice in pie chart
			var clickedElementindex = activePoints[0]["_index"];

			// get specific label by index
			var label = myChart.data.labels[clickedElementindex];

			// get value by index
			var value = myChart.data.datasets[0].data[clickedElementindex];

			/* other stuff that requires slice's label and value */

			Affichertableau(labelMois, data, label, value, labell);
		}
	}

}

function gencouleur(n, tab) {
	for (i = 0; i < n; i++) {
		var couleur = '#'
				+ (function co(lor) {
					return (lor += [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'a', 'b',
							'c', 'd', 'e', 'f' ][Math.floor(Math.random() * 16)])
							&& (lor.length == 6) ? lor : co(lor);
				})('');
		tab.push(couleur);
	}
}

function resetCharts() {

	$('#CambChart').remove(); // this is my <canvas> element
	$('#CambChartContainer').append('<canvas id="CambChart"></canvas>');

	$('#BatChart').remove(); // this is my <canvas> element
	$('#BatChartContainer').append('<canvas id="BatChart"></canvas>');

	$('#LineChart').remove(); // this is my <canvas> element
	$('#LineChartContainer').append('<canvas id="LineChart"></canvas>');
}

function Affichertableau(labelMois, data, label, value, labell) {
	// debut Tableau
	var element;
	console.log("Tail");
	console.log(labell.length);
	for (var i = 0; i < labell.length; i++) {
		if (labell[i] == label) {
			console.log("Selected  ");
			console.log(labell[i]);
			element = label;

		}
	}

	// Affichertableau(labelMois, data, label, value );

	MaDiv = document.getElementById("MaDiv");
	$('#DivTableau').remove();
	MaDiv.insertAdjacentHTML("beforeend", '<div id=\"DivTableau\"> </div>');

	$('#DivTableau')
			.append(

					'<table  class=\"display table table-striped table-responsive\" id=\"tableau\">'
							+ '<thead>'
							+ '<tr>'
							+ '  <th>ID HAL</th>'
							+ '<th> Label</th>'
							+ '<th>URL</th> '
							+ ' </tr> '
							+ '</thead>' + '<tbody>' + '</tbody>' + '</table>');

	console.log("head tab");
	for (l = 0; l < data.length; l++) {

		periode = String(data[l].producedDateY_i)
				+ String(data[l].producedDateM_i);
		var Stingperiode = String(data[l].producedDateY_i) + " - "
				+ String(data[l].producedDateM_i);

		if (labelMois.includes(periode)) {

			if (label == "tous" && value == "tous") {
				if (l == 0) {
					console.log("Affichertableau complet");
				}

				$('#tableau tbody').append(
						'<tr><td>' + data[l].docid + '</td>' + '<td>'
								+ data[l].label_s + '</td>' + '<td>'
								+ '<a href=' + data[l].uri_s + '>'
								+ data[l].uri_s + '</a> ' + '</td>'

								+ '</tr>'

				);
			} else {

				if (l == 0) {
					console.log("Affichertableau click");
				}

				if (data[l].docType_s == element) {

					$('#tableau tbody').append(
							'<tr><td>' + data[l].docid + '</td>' + '<td>'
									+ data[l].label_s + '</td>' + '<td>'
									+ '<a href=' + data[l].uri_s + '>'
									+ data[l].uri_s + '</a> ' + '</td>'
									+ '</tr>');
				}

			}

		}

	}

	// fin Tableau
}

function Affichertableaulineequipe(labelMois, data, label, value, labell) {

	// debut Tableau
	for (var i = 0; i < labelMois.length; i++) {
		console.log(labelMois[i]);
	}

	// Affichertableau(labelMois, data, label, value );

	MaDiv = document.getElementById("MaDiv");
	$('#DivTableau').remove();
	MaDiv.insertAdjacentHTML("beforeend", '<div id=\"DivTableau\"> </div>');

	$('#DivTableau')
			.append(

					'<table  class=\"display table table-striped table-responsive\" id=\"tableau\">'
							+ '<thead>'
							+ '<tr>'
							+ '  <th>ID HAL</th>'
							+ '<th> Label</th>'
							+ '<th>URL</th> '
							+ ' </tr> '
							+ '</thead>' + '<tbody>' + '</tbody>' + '</table>');

	console.log("head tab");
	for (l = 0; l < data.length; l++) {

		periode = String(data[l].producedDateY_i)
				+ String(data[l].producedDateM_i);
		var Stingperiode = String(data[l].producedDateY_i) + " - "
				+ String(data[l].producedDateM_i);
		console.log(periode);

		if (labelMois.includes(periode)) {

			console.log(label);
			console.log(value);
			if (label == "tous" && value == "tous") {
				if (l == 0) {
					console.log("Affichertableau complet");
				}

				$('#tableau tbody').append(
						'<tr><td>' + data[l].docid + '</td>' + '<td>'
								+ data[l].label_s + '</td>' + '<td>'
								+ '<a href=' + data[l].uri_s + '>'
								+ data[l].uri_s + '</a> ' + '</td>'

								+ '</tr>'

				);
			} else {
				console.log("Tail");
				var element;

				console.log(labell.length);
				for (var i = 0; i < labell.length; i++) {
					console.log(labell[i]);
					if (labell[i] == label) {
						console.log("Selected  ");
						console.log(labell[i]);
						element = ListeIdHalLabo[i];
						console.log(element);

					}
				}

				if (l == 0) {
					console.log("Affichertableau click");
				}

				var tab = data[l].rteamStructId_i;
				console.log(tab);
				console.log(element);
				for (var m = 0; m < tab.length; m++) {
					console.log(tab[m]);

					if (tab[m] == element) {

						$('#tableau tbody').append(
								'<tr><td>' + data[l].docid + '</td>' + '<td>'
										+ data[l].label_s + '</td>' + '<td>'
										+ '<a href=' + data[l].uri_s + '>'
										+ data[l].uri_s + '</a> ' + '</td>'
										+ '</tr>');
					}
				}

			}

		}

	}

	// fin Tableau
}

function Affichertableauline(labelMois, data, label, value, labell) {
	// debut Tableau
	var element;
	console.log("Tail");
	console.log(labell.length);
	for (var i = 0; i < labell.length; i++) {
		if (labell[i] == label) {
			console.log("Selected  ");
			console.log(labell[i]);
			element = labelMois[i];

		}
	}

	// Affichertableau(labelMois, data, label, value );

	MaDiv = document.getElementById("MaDiv");
	$('#DivTableau').remove();
	MaDiv.insertAdjacentHTML("beforeend", '<div id=\"DivTableau\"> </div>');

	$('#DivTableau')
			.append(

					'<table  class=\"display table table-striped table-responsive\" id=\"tableau\">'
							+ '<thead>'
							+ '<tr>'
							+ '  <th>ID HAL</th>'
							+ '<th> Label</th>'
							+ '<th>URL</th> '
							+ ' </tr> '
							+ '</thead>' + '<tbody>' + '</tbody>' + '</table>');

	console.log("head tab");
	for (l = 0; l < data.length; l++) {

		periode = String(data[l].producedDateY_i)
				+ String(data[l].producedDateM_i);
		var Stingperiode = String(data[l].producedDateY_i) + " - "
				+ String(data[l].producedDateM_i);

		if (labelMois.includes(periode)) {

			if (label == "tous" && value == "tous") {
				if (l == 0) {
					console.log("Affichertableau complet");
				}

				$('#tableau tbody').append(
						'<tr><td>' + data[l].docid + '</td>' + '<td>'
								+ data[l].label_s + '</td>' + '<td>'
								+ '<a href=' + data[l].uri_s + '>'
								+ data[l].uri_s + '</a> ' + '</td>'

								+ '</tr>'

				);
			} else {

				if (l == 0) {
					console.log("Affichertableau click");
				}

				periode = String(data[l].producedDateY_i)
						+ String(data[l].producedDateM_i);
				console.log(periode);
				console.log(element);
				if (periode == element) {

					$('#tableau tbody').append(
							'<tr><td>' + data[l].docid + '</td>' + '<td>'
									+ data[l].label_s + '</td>' + '<td>'
									+ '<a href=' + data[l].uri_s + '>'
									+ data[l].uri_s + '</a> ' + '</td>'
									+ '</tr>');
				}

			}

		}

	}

	// fin Tableau
}
