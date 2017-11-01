function showErrorAlert(msg) {
	$("#error").remove();
	$("#errorDiv").append('<div class=\"container\" id=\"error\"></div>');
	$("#error")
			.append(
					'<div class=\"alert alert-danger alert-dismissable\">'
							+ '<a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a> <strong>Erreur : </strong>'
							+ msg + '</div>');
}

function showInfoAlert(msg) {
	$("#error").remove();
	$("#errorDiv").append('<div class=\"container\" id=\"error\"></div>');
	$("#error")
			.append(
					'<div class=\"alert alert-info alert-dismissable\">'
							+ '<a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a> <strong>Information : </strong>'
							+ msg + '</div>');
}

function showInfoTabAlert(msg) {
	$("#DivTableau").remove();
	$("#MaDiv").append('<div class=\"row\" id=\"DivTableau\"> </div>');
	$("#DivTableau")
			.append(
					'<div class=\"alert alert-info alert-dismissable\">'
							+ '<a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a> <strong>Information : </strong>'
							+ msg + '</div>');
}