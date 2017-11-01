package com.dashboard.dao;

import java.util.List;

import com.dashboard.model.Membre;

public interface MembreDao {

	public void add(Membre membreEquipe);

	public void edit(Membre membreEquipe);

	public void delete(int id);
	
	public Membre getMembre(int id);

	public List<Membre> listAll();

}
