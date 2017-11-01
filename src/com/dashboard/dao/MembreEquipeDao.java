package com.dashboard.dao;

import java.util.List;

import com.dashboard.model.MembreEquipe;

public interface MembreEquipeDao {

	public void add(MembreEquipe membreEquipe);

	public void edit(MembreEquipe membreEquipe);

	public void delete(int id);
	
	public MembreEquipe getMembreEquipe(int idEquipe,int idMembre);

	public List<MembreEquipe> listAll();
	
	public List<MembreEquipe> listByTeam(int idEquipe);

}
