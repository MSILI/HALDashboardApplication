package com.dashboard.dao;

import java.util.List;

import com.dashboard.model.ChefEquipe;

public interface ChefEquipeDao {

	public void add(ChefEquipe chefEquipe);

	public void edit(ChefEquipe chefEquipe);

	public void delete(int id);

	public ChefEquipe getChefEquipe(int id);
	
	public ChefEquipe getChefEquipeByEquipe(int idEquipe);

	public List<ChefEquipe> listAll();

}
