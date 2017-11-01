package com.dashboard.model;

public class ChefLabo extends Personnel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Laboratoire laboratoire;

	public ChefLabo(int id, String nom, String prenom, String fonction,Laboratoire laboratoire) {
		super(id, nom, prenom, fonction);
		this.laboratoire = laboratoire;
	}
	
	public ChefLabo(String nom, String prenom, String fonction,Laboratoire laboratoire) {
		super(nom, prenom, fonction);
		this.laboratoire = laboratoire;
	}

	public Laboratoire getLaboratoire() {
		return laboratoire;
	}

	public void setLaboratoire(Laboratoire laboratoire) {
		this.laboratoire = laboratoire;
	}
	
}
