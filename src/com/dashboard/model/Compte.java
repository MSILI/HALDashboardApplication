package com.dashboard.model;

import java.io.Serializable;

public class Compte implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String userName;
	private String password;
	private int typeCompte;
	private Personnel personnel;

	public Compte() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Compte(int id, String userName, String password, int typeCompte) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.typeCompte = typeCompte;
	}

	public Compte(String userName, String password, int typeCompte) {
		super();
		this.userName = userName;
		this.password = password;
		this.typeCompte = typeCompte;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTypeCompte() {
		return typeCompte;
	}

	public void setTypeCompte(int typeCompte) {
		this.typeCompte = typeCompte;
	}

	public Personnel getPersonnel() {
		return personnel;
	}

	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}

}
