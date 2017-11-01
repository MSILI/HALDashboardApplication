package com.dashboard.model;

public class ChefEquipe extends Personnel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Equipe equipe;

	public ChefEquipe(int id, String nom, String prenom, String fonction, Equipe equipe) {
		super(id, nom, prenom, fonction);
		this.equipe = equipe;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

}
