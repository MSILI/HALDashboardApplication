package com.dashboard.dao;

import java.util.List;

import com.dashboard.model.Laboratoire;

public interface LaboratoireDao {

	public void add(Laboratoire laboratoire);

	public void edit(Laboratoire laboratoire);
	
	public void updateIDChefLabo(Laboratoire laboratoire);

	public void delete(int id);
	
	public Laboratoire getLaboratoire(int id);

	public List<Laboratoire> listAll();

}
