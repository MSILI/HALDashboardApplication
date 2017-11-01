package com.dashboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dashboard.model.ChefLabo;
import com.dashboard.model.Personnel;
import com.dashboard.util.DBUtil;

public class ChefLaboDaoImp implements ChefLaboDao {

	private Connection connection;

	public ChefLaboDaoImp() {
		super();
		connection = DBUtil.getDBUtil().getConnection();
	}

	@Override
	public void add(ChefLabo chefLabo) {
		String query = "insert into chefLabo values (?,?)";
		ResultSet valeursAutoGenerees = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, chefLabo.getId());
			preparedStatement.setInt(2, chefLabo.getLaboratoire().getId());
			preparedStatement.executeUpdate();

			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if (valeursAutoGenerees.next()) {
				chefLabo.setId(valeursAutoGenerees.getInt(1));
			}
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void edit(ChefLabo chefLabo) {
		String query = "update chefLabo set laboratoire = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, chefLabo.getNom());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(int id) {
		String query = "delete from chefLabo where id = ?";
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
	public ChefLabo getChefLabo(int id) {
		String query = "select * from chefLabo where id = ?";
		PersonnelDao personnelDao = new PersonnelDaoImpl();
		LaboratoireDao laboratoireDao = new LaboratoireDaoImpl();
		ChefLabo chefLabo = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Personnel personnel = personnelDao.getPersonnel(id);
				chefLabo = new ChefLabo(id, personnel.getNom(), personnel.getPrenom(), personnel.getFonction(),
						laboratoireDao.getLaboratoire(resultSet.getInt("laboratoire")));
				chefLabo.setIdHal(personnel.getIdHal());
				chefLabo.setCompte(personnel.getCompte());
			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chefLabo;
	}

	@Override
	public List<ChefLabo> listAll() {
		String query = "select * from chefLabo";
		List<ChefLabo> lisChefsLabo = new ArrayList<ChefLabo>();
		PersonnelDao personnelDao = new PersonnelDaoImpl();
		LaboratoireDao laboratoireDao = new LaboratoireDaoImpl();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				Personnel personnel = personnelDao.getPersonnel(resultSet.getInt("id"));
				ChefLabo chefLabo = new ChefLabo(personnel.getId(), personnel.getNom(), personnel.getPrenom(),
						personnel.getFonction(), laboratoireDao.getLaboratoire(resultSet.getInt("laboratoire")));
				chefLabo.setIdHal(personnel.getIdHal());
				lisChefsLabo.add(chefLabo);
			}
			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lisChefsLabo;
	}
}
