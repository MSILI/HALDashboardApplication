package com.dashboard.model;

import java.io.Serializable;

public class Personnel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String nom;
	private String prenom;
	private String fonction;
	private int idHal;
	private Compte compte;

	public Personnel(int id, String nom, String prenom, String fonction) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.fonction = fonction;
	}

	public Personnel(String nom, String prenom, String fonction) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.fonction = fonction;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getFonction() {
		return fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public int getIdHal() {
		return idHal;
	}

	public void setIdHal(int idHal) {
		this.idHal = idHal;
	}

}
