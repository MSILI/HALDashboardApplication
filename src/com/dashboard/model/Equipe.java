package com.dashboard.model;

import java.io.Serializable;
import java.util.List;

public class Equipe implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String acronyme;
	private String nom;
	private int idHal;
	private Laboratoire laboratoire;
	private ChefEquipe chefEquipe;
	private List<Membre> listMembres;

	public Equipe(int id, String acronyme, String nom, Laboratoire laboratoire) {
		super();
		this.id = id;
		this.acronyme = acronyme;
		this.nom = nom;
		this.laboratoire = laboratoire;
	}

	public Equipe(String acronyme, String nom, Laboratoire laboratoire) {
		super();
		this.acronyme = acronyme;
		this.nom = nom;
		this.laboratoire = laboratoire;
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

	public List<Membre> getListMembres() {
		return listMembres;
	}

	public void setListMembres(List<Membre> listMembres) {
		this.listMembres = listMembres;
	}

	public int getIdHal() {
		return idHal;
	}

	public void setIdHal(int idHal) {
		this.idHal = idHal;
	}

	public Laboratoire getLaboratoire() {
		return laboratoire;
	}

	public void setLaboratoire(Laboratoire laboratoire) {
		this.laboratoire = laboratoire;
	}

	public ChefEquipe getChefEquipe() {
		return chefEquipe;
	}

	public void setChefEquipe(ChefEquipe chefEquipe) {
		this.chefEquipe = chefEquipe;
	}

}
