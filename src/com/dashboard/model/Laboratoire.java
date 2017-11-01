package com.dashboard.model;

import java.io.Serializable;
import java.util.List;

public class Laboratoire implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String acronyme;
	private String nom;
	private int idHal;
	private ChefLabo chefLabo;
	private List<Equipe> listEquipes;

	public Laboratoire(int id, String acronyme, String nom) {
		super();
		this.id = id;
		this.acronyme = acronyme;
		this.nom = nom;
	}

	public Laboratoire(String acronyme, String nom) {
		super();
		this.acronyme = acronyme;
		this.nom = nom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAcronyme() {
		return acronyme;
	}

	public void setAcronyme(String acronyme) {
		this.acronyme = acronyme;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getIdHal() {
		return idHal;
	}

	public void setIdHal(int idHal) {
		this.idHal = idHal;
	}

	public List<Equipe> getListEquipes() {
		return listEquipes;
	}

	public void setListEquipes(List<Equipe> listEquipes) {
		this.listEquipes = listEquipes;
	}

	public ChefLabo getChefLabo() {
		return chefLabo;
	}

	public void setChefLabo(ChefLabo chefLabo) {
		this.chefLabo = chefLabo;
	}

}
