package com.dashboard.model;

import java.io.Serializable;

public class MembreEquipe implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Equipe equipe;
	private Membre membre;

	public MembreEquipe(Equipe equipe, Membre membre) {
		super();
		this.equipe = equipe;
		this.membre = membre;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public Membre getMembre() {
		return membre;
	}

	public void setMembre(Membre membre) {
		this.membre = membre;
	}

}
