package dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.karinedias.dao.DaoJDBC;
import com.karinedias.dao.DatabaseType;
import com.karinedias.exceptions.PersonNotFoundException;
import com.karinedias.model.Person;

public class DaoJDBCTest {

	DaoJDBC jdbcdao;
	private Connection conn;

	@BeforeEach
	void setUp() {
		jdbcdao = new DaoJDBC(DatabaseType.H2);
		try {
			conn = jdbcdao.getConnection();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@AfterEach
	void tearDown() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	Person personToTest = new Person(1, "Iseult", "Travers", LocalDate.of(1985, 10, 19), "117 Boulevard de Courcelles",
			"75017", "Paris", "01795282491");

	@Test
	void addPerson_shouldReturnExpectedPerson() {
		Optional<Person> result = jdbcdao.addPerson(personToTest);
		assertTrue(result.isPresent());
	}

	@Test
	void getDeletedPerson_shouldReturnPersonNotFoundException() {
		Optional<Person> addedPerson = jdbcdao.addPerson(personToTest);
		int id = addedPerson.get().getId();
		jdbcdao.deletePerson(id);

		Exception exception = assertThrows(PersonNotFoundException.class, () -> {
			jdbcdao.getPerson(addedPerson.get().getId());
		});
		String expectedMessage = "The person with ID " + id + " doesn't exists";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void deletePerson_shouldReturnOnePersonFewerFromDatabaseCount() {
		Optional<Person> addedPerson = jdbcdao.addPerson(personToTest);
		int personsInDatabaseBeforeDelete = jdbcdao.getAllPersons().size(); // should return 11
		jdbcdao.deletePerson(addedPerson.get().getId());

		int actualNumberOfPersons = jdbcdao.getAllPersons().size();
		int expectedNumberOfPersons = personsInDatabaseBeforeDelete - 1;

		assertTrue(actualNumberOfPersons == expectedNumberOfPersons);
	}

	@Test
	void updatedPerson_shouldBeEqualToExpectedPerson() {
		// Database will increment id to 11
		Person expectedUpdatedPerson = new Person(11, "Iseult", "Travers", LocalDate.of(1985, 10, 19),
				"12 rue de la Grange", "67000", "Strasbourg", "0379248060");
		jdbcdao.addPerson(personToTest);

		// Change personToTest adress, city, postalCode and phoneNumber
		personToTest.setAdress("12 rue de la Grange");
		personToTest.setCity("Strasbourg");
		personToTest.setPostalCode("67000");
		personToTest.setPhoneNumber("0379248060");
		Optional<Person> actualUpdatedPerson = jdbcdao.updatePerson(personToTest);

		assertThat(actualUpdatedPerson.get()).usingRecursiveComparison().isEqualTo(expectedUpdatedPerson);
	}

}
