package com.dashboard.dao;

import java.util.List;

import com.dashboard.model.Equipe;

public interface EquipeDao {

	public void add(Equipe equipe);

	public void edit(Equipe equipe);

	public void updateIDChefEquipe(Equipe equipe);

	public void delete(int id);

	public Equipe getEquipe(int id);

	public List<Equipe> listAll();

	public List<Equipe> listByLaboratoire(int idLab);

}
