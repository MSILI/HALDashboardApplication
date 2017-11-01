package com.dashboard.dao;

import java.util.List;

import com.dashboard.model.Compte;

public interface CompteDao {

	public void add(Compte compte);

	public void edit(Compte compte);
	
	public void updateIDPersonnel(Compte compte);

	public void delete(int id);
	
	public Compte getCompte(int id);

	public List<Compte> listAll();

}
