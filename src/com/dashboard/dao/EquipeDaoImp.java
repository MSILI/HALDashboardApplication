package com.dashboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dashboard.model.Equipe;
import com.dashboard.util.DBUtil;

public class EquipeDaoImp implements EquipeDao {

	private Connection connection;

	public EquipeDaoImp() {
		super();
		connection = DBUtil.getDBUtil().getConnection();
	}

	@Override
	public void add(Equipe equipe) {
		String query = "insert into equipe(acronyme,nom,idHal,laboratoire) values (?,?,?,?)";
		ResultSet valeursAutoGenerees = null;

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, equipe.getAcronyme());
			preparedStatement.setString(2, equipe.getNom());
			preparedStatement.setInt(3, equipe.getIdHal());
			preparedStatement.setInt(4, equipe.getLaboratoire().getId());
			preparedStatement.executeUpdate();
			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if (valeursAutoGenerees.next()) {
				equipe.setId(valeursAutoGenerees.getInt(1));
			}
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void edit(Equipe equipe) {
		String query = "update equipe set acronyme = ?,nom = ?, idHal = ? where id= ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, equipe.getAcronyme());
			preparedStatement.setString(2, equipe.getNom());
			preparedStatement.setInt(3, equipe.getIdHal());
			preparedStatement.setInt(4, equipe.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String query = "delete from equipe where id = ?";
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
	public Equipe getEquipe(int id) {
		String query = "select * from equipe where id = ?";
		LaboratoireDao laboratoireDao = new LaboratoireDaoImpl();
		Equipe equipe = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				equipe = new Equipe(resultSet.getInt("id"), resultSet.getString("acronyme"), resultSet.getString("nom"),
						laboratoireDao.getLaboratoire(resultSet.getInt("laboratoire")));
				equipe.setIdHal(resultSet.getInt("idHal"));
			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equipe;
	}

	@Override
	public List<Equipe> listAll() {
		String query = "select * from equipe";
		LaboratoireDao laboratoireDao = new LaboratoireDaoImpl();
		List<Equipe> listEquipe = new ArrayList<Equipe>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				Equipe equipe = new Equipe(resultSet.getInt("id"), resultSet.getString("acronyme"),
						resultSet.getString("nom"), laboratoireDao.getLaboratoire(resultSet.getInt("laboratoire")));
				equipe.setIdHal(resultSet.getInt("idHal"));
				listEquipe.add(equipe);
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listEquipe;
	}

	@Override
	public void updateIDChefEquipe(Equipe equipe) {
		String query = "update equipe set chefEquipe = ? where id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, equipe.getChefEquipe().getId());
			preparedStatement.setInt(2, equipe.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Equipe> listByLaboratoire(int idLab) {
		String query = "select * from equipe where laboratoire = ? order by idHal";
		LaboratoireDao laboratoireDao = new LaboratoireDaoImpl();
		List<Equipe> listEquipe = new ArrayList<Equipe>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, idLab);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Equipe equipe = new Equipe(resultSet.getInt("id"), resultSet.getString("acronyme"),
						resultSet.getString("nom"), laboratoireDao.getLaboratoire(resultSet.getInt("laboratoire")));
				equipe.setIdHal(resultSet.getInt("idHal"));
				listEquipe.add(equipe);
			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listEquipe;
	}

}
