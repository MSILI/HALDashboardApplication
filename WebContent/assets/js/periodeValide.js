function validForm(an1, an2, mois1, mois2) {
	if (an1 == an2) {
		if (mois1 <= mois2) {
			return true;
		} else {
			showErrorAlert(" Le mois de debut est superieur au mois de fin ! ");

			return false;
		}
	} else {
		if (an1 > an2) {
			showErrorAlert(" L'annee de debut est superieur a l'annee de fin ! ");
			return false;
		} else {
			return true;
		}
	}
}