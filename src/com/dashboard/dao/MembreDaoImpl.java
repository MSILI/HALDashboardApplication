package com.dashboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dashboard.model.Membre;
import com.dashboard.model.Personnel;
import com.dashboard.util.DBUtil;

public class MembreDaoImpl implements MembreDao {

	private Connection connection;

	public MembreDaoImpl() {
		super();
		connection = DBUtil.getDBUtil().getConnection();
	}

	@Override
	public void add(Membre membreEquipe) {
		String query = "insert into membre values (?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, membreEquipe.getId());
			preparedStatement.executeUpdate();

			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void edit(Membre membreEquipe) {

	}

	@Override
	public void delete(int id) {
		String query = "delete from membre where id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Membre getMembre(int id) {
		String query = "select * from membre where id = ?";
		PersonnelDao personnelDao = new PersonnelDaoImpl();
		Membre membre = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Personnel personnel = personnelDao.getPersonnel(id);
				membre = new Membre(id, personnel.getNom(), personnel.getPrenom(), personnel.getFonction());
				membre.setIdHal(personnel.getIdHal());
			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return membre;
	}

	@Override
	public List<Membre> listAll() {
		String query = "select * from membre";
		PersonnelDao personnelDao = new PersonnelDaoImpl();
		List<Membre> listMembreEquipe = new ArrayList<Membre>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				Personnel personnel = personnelDao.getPersonnel(resultSet.getInt("id"));
				Membre membre = new Membre(personnel.getId(), personnel.getNom(), personnel.getPrenom(), personnel.getFonction());
				membre.setIdHal(personnel.getIdHal());
				listMembreEquipe.add(membre);
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listMembreEquipe;
	}

}
