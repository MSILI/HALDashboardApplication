package com.dashboard.dao;

import java.util.List;

import com.dashboard.model.Personnel;

public interface PersonnelDao {

	public void add(Personnel personnel);
	
	public void addMembreEquipe(Personnel personnel);

	public void edit(Personnel personnel);

	public void delete(int id);
	
	public Personnel getPersonnel(int id);
	
	public Personnel getPersonnelWithLoginAndPassword(String login,String password);

	public List<Personnel> listAll();

}
