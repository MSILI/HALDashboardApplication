package com.dashboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dashboard.model.Personnel;
import com.dashboard.util.DBUtil;

public class PersonnelDaoImpl implements PersonnelDao {

	private Connection connection;

	public PersonnelDaoImpl() {
		super();
		connection = DBUtil.getDBUtil().getConnection();
	}

	@Override
	public void add(Personnel personnel) {
		String query = "insert into personnel (nom,prenom,fonction,idHal,compte) values (?,?,?,?,?)";
		ResultSet valeursAutoGenerees = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, personnel.getNom());
			preparedStatement.setString(2, personnel.getPrenom());
			preparedStatement.setString(3, personnel.getFonction());
			preparedStatement.setInt(4, personnel.getIdHal());
			preparedStatement.setInt(5, personnel.getCompte().getId());
			preparedStatement.executeUpdate();

			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if (valeursAutoGenerees.next()) {
				personnel.setId(valeursAutoGenerees.getInt(1));
			}
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void edit(Personnel personnel) {
		String query = "update personnel set nom = ?, prenom = ?, fonction = ?, idHal = ? where id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, personnel.getNom());
			preparedStatement.setString(2, personnel.getPrenom());
			preparedStatement.setString(3, personnel.getFonction());
			preparedStatement.setInt(4, personnel.getIdHal());
			preparedStatement.setInt(5, personnel.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(int id) {
		String query = "delete from personnel where id = ?";
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
	public Personnel getPersonnel(int id) {
		Personnel personnel = null;
		CompteDao compteDao = new CompteDaoImpl();
		String query = "select * from personnel where id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				personnel = new Personnel(resultSet.getInt("id"), resultSet.getString("nom"),
						resultSet.getString("prenom"), resultSet.getString("fonction"));
				personnel.setIdHal(resultSet.getInt("idHal"));
				personnel.setCompte(compteDao.getCompte(resultSet.getInt("compte")));
			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personnel;
	}

	@Override
	public List<Personnel> listAll() {
		List<Personnel> listPersonnel = new ArrayList<Personnel>();
		CompteDao compteDao = new CompteDaoImpl();
		String query = "select * from personnel";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				Personnel personnel = new Personnel(resultSet.getInt("id"), resultSet.getString("nom"),
						resultSet.getString("prenom"), resultSet.getString("fonction"));
				personnel.setCompte(compteDao.getCompte(resultSet.getInt("compte")));
				personnel.setIdHal(resultSet.getInt("idHal"));
				listPersonnel.add(personnel);
			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listPersonnel;
	}

	@Override
	public Personnel getPersonnelWithLoginAndPassword(String login, String password) {
		String query = "select * from personnel p, compte cpt where p.compte = cpt.id and cpt.personnel = p.id and cpt.userName = ? and cpt.password = ?";
		Personnel personnel = null;
		CompteDao compteDao = new CompteDaoImpl();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				personnel = new Personnel(resultSet.getInt("p.id"), resultSet.getString("p.nom"),
						resultSet.getString("p.prenom"), resultSet.getString("p.fonction"));
				personnel.setIdHal(resultSet.getInt("p.idHal"));
				personnel.setCompte(compteDao.getCompte(resultSet.getInt("p.compte")));
			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personnel;
	}

	@Override
	public void addMembreEquipe(Personnel personnel) {
		String query = "insert into personnel (nom,prenom,fonction,idHal) values (?,?,?,?)";
		ResultSet valeursAutoGenerees = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, personnel.getNom());
			preparedStatement.setString(2, personnel.getPrenom());
			preparedStatement.setString(3, personnel.getFonction());
			preparedStatement.setInt(4, personnel.getIdHal());
			preparedStatement.executeUpdate();

			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if (valeursAutoGenerees.next()) {
				personnel.setId(valeursAutoGenerees.getInt(1));
			}
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
