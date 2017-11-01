package com.dashboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dashboard.model.MembreEquipe;
import com.dashboard.util.DBUtil;

public class MembreEquipeDaoImp implements MembreEquipeDao {

	private Connection connection;

	public MembreEquipeDaoImp() {
		super();
		connection = DBUtil.getDBUtil().getConnection();
	}

	@Override
	public void add(MembreEquipe membreEquipe) {

		String query = "insert into membreEquipe values (?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, membreEquipe.getEquipe().getId());
			preparedStatement.setInt(2, membreEquipe.getMembre().getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void edit(MembreEquipe membreEquipe) {

	}

	@Override
	public void delete(int id) {
		String query = "delete from membreEquipe where id = ?";
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
	public MembreEquipe getMembreEquipe(int idEquipe, int idMembre) {
		String query = "select * from membreEquipe where membre = ? and equipe = ?";
		EquipeDao equipeDao = new EquipeDaoImp();
		MembreDao membreDao = new MembreDaoImpl();
		MembreEquipe membreEquipe = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, idEquipe);
			preparedStatement.setInt(2, idMembre);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				membreEquipe = new MembreEquipe(equipeDao.getEquipe(resultSet.getInt("equipe")),
						membreDao.getMembre(resultSet.getInt("membre")));
			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return membreEquipe;
	}

	@Override
	public List<MembreEquipe> listAll() {

		return null;
	}

	@Override
	public List<MembreEquipe> listByTeam(int idEquipe) {
		String query = "select * from membre m, membreEquipe me, equipe e where m.id = me.membre and  e.id = me.equipe and me.equipe = ?";
		EquipeDao equipeDao = new EquipeDaoImp();
		MembreDao membreDao = new MembreDaoImpl();
		List<MembreEquipe> listMembreEquipe = new ArrayList<MembreEquipe>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, idEquipe);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				MembreEquipe membreEquipe = new MembreEquipe(equipeDao.getEquipe(resultSet.getInt("me.equipe")),
						membreDao.getMembre(resultSet.getInt("me.membre")));
				listMembreEquipe.add(membreEquipe);
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listMembreEquipe;
	}

}
