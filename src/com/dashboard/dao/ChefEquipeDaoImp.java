package com.dashboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dashboard.model.ChefEquipe;
import com.dashboard.model.Personnel;
import com.dashboard.util.DBUtil;

public class ChefEquipeDaoImp implements ChefEquipeDao {

	private Connection connection;

	public ChefEquipeDaoImp() {
		super();
		connection = DBUtil.getDBUtil().getConnection();
	}

	@Override
	public void add(ChefEquipe chefEquipe) {
		String query = "insert into chefEquipe values (?,?)";
		ResultSet valeursAutoGenerees = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, chefEquipe.getId());
			preparedStatement.setInt(2, chefEquipe.getEquipe().getId());
			preparedStatement.executeUpdate();

			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if (valeursAutoGenerees.next()) {
				chefEquipe.setId(valeursAutoGenerees.getInt(1));
			}
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void edit(ChefEquipe chefEquipe) {
		String query = "update chefLabo set equipe = ? where id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, chefEquipe.getNom());
			preparedStatement.setInt(2, chefEquipe.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String query = "delete from chefEquipe where id = ?";
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
	public ChefEquipe getChefEquipe(int id) {
		String query = "select * from chefEquipe where id = ?";
		PersonnelDao personnelDao = new PersonnelDaoImpl();
		EquipeDao equipeDao = new EquipeDaoImp();
		ChefEquipe chefEquipe = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Personnel personnel = personnelDao.getPersonnel(id);
				chefEquipe = new ChefEquipe(id, personnel.getNom(), personnel.getPrenom(), personnel.getFonction(),
						equipeDao.getEquipe(resultSet.getInt("equipe")));
				chefEquipe.setCompte(personnel.getCompte());
				chefEquipe.setIdHal(personnel.getIdHal());
			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chefEquipe;
	}

	@Override
	public List<ChefEquipe> listAll() {
		String query = "select * from chefEquipe";
		PersonnelDao personnelDao = new PersonnelDaoImpl();
		EquipeDao equipeDao = new EquipeDaoImp();
		List<ChefEquipe> listChefEquipe = new ArrayList<ChefEquipe>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				Personnel personnel = personnelDao.getPersonnel(resultSet.getInt("id"));
				ChefEquipe chefEquipe = new ChefEquipe(personnel.getId(), personnel.getNom(), personnel.getPrenom(),
						personnel.getFonction(), equipeDao.getEquipe(resultSet.getInt("equipe")));
				chefEquipe.setIdHal(personnel.getIdHal());
				chefEquipe.setCompte(personnel.getCompte());
				listChefEquipe.add(chefEquipe);
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listChefEquipe;
	}

	@Override
	public ChefEquipe getChefEquipeByEquipe(int idEquipe) {
		String query = "select * from chefEquipe where equipe = ?";
		PersonnelDao personnelDao = new PersonnelDaoImpl();
		EquipeDao equipeDao = new EquipeDaoImp();
		ChefEquipe chefEquipe = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, idEquipe);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Personnel personnel = personnelDao.getPersonnel(resultSet.getInt("id"));
				chefEquipe = new ChefEquipe(personnel.getId(), personnel.getNom(), personnel.getPrenom(), personnel.getFonction(),
						equipeDao.getEquipe(resultSet.getInt("equipe")));
				chefEquipe.setCompte(personnel.getCompte());
				chefEquipe.setIdHal(personnel.getIdHal());
			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chefEquipe;
	}

}
