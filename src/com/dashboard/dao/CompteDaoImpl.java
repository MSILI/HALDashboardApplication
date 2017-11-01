package com.dashboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dashboard.model.Compte;
import com.dashboard.util.DBUtil;

public class CompteDaoImpl implements CompteDao {

	private Connection connection;

	public CompteDaoImpl() {
		super();
		connection = DBUtil.getDBUtil().getConnection();
	}

	@Override
	public void add(Compte compte) {
		String query = "insert into compte(userName,password,typeCompte) values (?,?,?)";
		ResultSet valeursAutoGenerees = null;

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, compte.getUserName());
			preparedStatement.setString(2, compte.getPassword());
			preparedStatement.setInt(3, compte.getTypeCompte());
			preparedStatement.executeUpdate();

			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if (valeursAutoGenerees.next()) {
				compte.setId(valeursAutoGenerees.getInt(1));
			}
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void edit(Compte compte) {
		String query = "update compte set userName = ?,password = ?,typeCompte = ? where id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, compte.getUserName());
			preparedStatement.setString(2, compte.getPassword());
			preparedStatement.setInt(3, compte.getTypeCompte());
			preparedStatement.setInt(4, compte.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(int id) {
		String query = "delete from compte where id = ?";
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
	public Compte getCompte(int id) {
		String query = "select * from compte where id = ?";
		Compte compte = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				compte = new Compte(resultSet.getInt("id"), resultSet.getString("userName"),
						resultSet.getString("password"), resultSet.getInt("typeCompte"));
			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return compte;
	}

	@Override
	public List<Compte> listAll() {
		String query = "select * from compte";
		List<Compte> listCompte = new ArrayList<Compte>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				Compte compte = new Compte(resultSet.getInt("id"), resultSet.getString("userName"),
						resultSet.getString("password"), resultSet.getInt("typeCompte"));
				listCompte.add(compte);
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listCompte;
	}

	@Override
	public void updateIDPersonnel(Compte compte) {
		String query = "update compte set personnel = ? where id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, compte.getPersonnel().getId());
			preparedStatement.setInt(2, compte.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
