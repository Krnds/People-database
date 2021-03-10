package com.karinedias.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.postgresql.util.PSQLException;

import com.karinedias.exceptions.PersonNotFoundException;
import com.karinedias.model.Person;

public class DaoJDBC implements Dao {

	private static final String driver = "org.postgresql.Driver";
	private static final String url = "jdbc:postgresql://localhost:5432/people_database";
	private static final String user = "karine";
	private static final String password = "karine";

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection connection = null;
		Class.forName(driver);
		connection = DriverManager.getConnection(url, user, password);
		return connection;
	}

	public void closeConnection(Connection connection, PreparedStatement preparedStat, ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (preparedStat != null) {
			try {
				preparedStat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public Optional<Person> addPerson(Person person) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = this.getConnection();
			String sql = "insert into person(id, firstname, lastname, birthdate, adress, postalcode, city, phonenumber) values (?, ?, ?, ?, ?, ?, ?, ?)";
			ps = connection.prepareStatement(sql);
			int id = getNextIdAvailable() + 1;
			ps.setInt(1, id);
			ps.setString(2, person.getFirstname());
			ps.setString(3, person.getLastname());
			ps.setDate(4, Date.valueOf(person.getBirthdate()));
			ps.setString(5, person.getAdress());
			ps.setInt(6, Integer.valueOf(person.getPostalCode()));
			ps.setString(7, person.getCity());
			ps.setInt(8, Integer.valueOf(person.getPhoneNumber()));
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {

			this.closeConnection(connection, ps, rs);
		}
		return Optional.of(person);
	}

	public void deletePerson(int id) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = this.getConnection();
			String sql = "delete from person where id = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			if (ps.executeUpdate() == 0) {
				throw new PersonNotFoundException("The person you want to delete was not found on the database.");
			}
		} catch (ClassNotFoundException | SQLException w) {
			System.out.println("SQL exception : database access error. ");
		} finally {
			this.closeConnection(connection, ps, rs);
		}
	}

	public Optional<Person> updatePerson(Person newPerson) {
		Connection cnx = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			cnx = this.getConnection();
			String sql = "update person set firstname = ?, lastname = ?, adress = ?, postalcode = ?, city = ?, phonenumber = ? where id = ?";
			ps = cnx.prepareStatement(sql);
			ps.setString(1, newPerson.getFirstname());
			ps.setString(2, newPerson.getLastname());
			ps.setString(3, newPerson.getAdress());
			ps.setInt(4, Integer.valueOf(newPerson.getPostalCode()));
			ps.setString(5, newPerson.getCity());
			ps.setInt(6, Integer.valueOf(newPerson.getPhoneNumber()));
			ps.setInt(7, newPerson.getId());
			ps.executeUpdate();
			if (ps.executeUpdate() == 0) {
				throw new PersonNotFoundException(
						String.format("The person with ID %s you want to update doesn't exists", newPerson.getId()));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(cnx, ps, rs);
		}
		return Optional.of(newPerson);
	}

	public Optional<Person> getPerson(int personId) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Person person = null;
		try {
			cnx = this.getConnection();
			String sql = "select * from person where id = ?";
			pstmt = cnx.prepareStatement(sql);
			pstmt.setInt(1, personId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				do {
					person = new Person(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(),
							rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
				} while (rs.next());
			} else {
				throw new PersonNotFoundException(String.format("The person with ID %s doesn't exists", personId));
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

		} finally {
			this.closeConnection(cnx, pstmt, rs);
		}
		return Optional.of(person);
	}

	public List<Person> getAllPersons() {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Person> persons = new ArrayList<Person>();
		try {
			cnx = this.getConnection();
			String sql = "select * from person";
			pstmt = cnx.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Person person = new Person(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
				persons.add(person);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(cnx, pstmt, rs);
		}
		return persons;
	}

	private int getNextIdAvailable() { //TODO: delete
		int id = 0;
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			cnx = this.getConnection();
			String sql = "select id from person order by id desc";
			pstmt = cnx.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				id = rs.getInt("id");
				break;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(cnx, pstmt, rs);
		}

		return id;
	}
}
