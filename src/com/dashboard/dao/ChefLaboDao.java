package com.dashboard.dao;

import java.util.List;

import com.dashboard.model.ChefLabo;

public interface ChefLaboDao {

	public void add(ChefLabo chefLabo);

	public void edit(ChefLabo chefLabo);

	public void delete(int id);
	
	public ChefLabo getChefLabo(int id);
	
	public List<ChefLabo> listAll();

}
