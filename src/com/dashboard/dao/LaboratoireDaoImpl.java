package com.dashboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dashboard.model.Laboratoire;
import com.dashboard.util.DBUtil;

public class LaboratoireDaoImpl implements LaboratoireDao {

	private Connection connection;

	public LaboratoireDaoImpl() {
		super();
		connection = DBUtil.getDBUtil().getConnection();
	}

	@Override
	public void add(Laboratoire laboratoire) {
		String query = "insert into laboratoire(acronyme,nom,idHal) values (?,?,?)";
		ResultSet valeursAutoGenerees = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, laboratoire.getAcronyme());
			preparedStatement.setString(2, laboratoire.getNom());
			preparedStatement.setInt(3, laboratoire.getIdHal());
			preparedStatement.executeUpdate();
			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if (valeursAutoGenerees.next()) {
				laboratoire.setId(valeursAutoGenerees.getInt(1));
			}
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void edit(Laboratoire laboratoire) {
		String query = "update laboratoire set acronyme = ?,nom = ?, idHal = ? where id= ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, laboratoire.getAcronyme());
			preparedStatement.setString(2, laboratoire.getNom());
			preparedStatement.setInt(3, laboratoire.getIdHal());
			preparedStatement.setInt(4, laboratoire.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(int id) {
		String query = "delete from laboratoire where id = ?";
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
	public Laboratoire getLaboratoire(int id) {
		String query = "select * from laboratoire where id = ?";
		Laboratoire laboratoire = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				laboratoire = new Laboratoire(resultSet.getInt("id"), resultSet.getString("acronyme"),
						resultSet.getString("nom"));
				laboratoire.setIdHal(resultSet.getInt("idHal"));
			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return laboratoire;
	}

	@Override
	public List<Laboratoire> listAll() {
		String query = "select * from laboratoire";
		List<Laboratoire> listLabo = new ArrayList<Laboratoire>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				Laboratoire laboratoire = new Laboratoire(resultSet.getInt("id"), resultSet.getString("acronyme"),
						resultSet.getString("nom"));
				laboratoire.setIdHal(resultSet.getInt("idHal"));
				listLabo.add(laboratoire);
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listLabo;
	}

	@Override
	public void updateIDChefLabo(Laboratoire laboratoire) {
		String query = "update laboratoire set chefLabo = ? where id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, laboratoire.getChefLabo().getId());
			preparedStatement.setInt(2, laboratoire.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
