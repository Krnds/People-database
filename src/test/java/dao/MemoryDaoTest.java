package dao;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.karinedias.dao.MemoryDao;
import com.karinedias.exceptions.PersonNotFoundException;
import com.karinedias.model.Person;

class MemoryDaoTest {

	MemoryDao memoryDao;

	@BeforeEach
	void setUp() {
		memoryDao = new MemoryDao();
	}

	Person personToTest = new Person(1, "Iseult", "Travers", LocalDate.of(1985, 10, 19), "117 Boulevard de Courcelles",
			"75017", "Paris", "+331795282491");

	@Test
	void addPerson_shouldReturnExpectedPerson() {
		Optional<Person> result = memoryDao.addPerson(personToTest);
		assertTrue(result.isPresent());
	}

	@Test
	void getDeletedPerson_shouldThrowPersonNotFoundException() {
		memoryDao.addPerson(personToTest);
		int id = personToTest.getId();
		memoryDao.deletePerson(id);
		Exception exception = assertThrows(PersonNotFoundException.class, () -> {
			memoryDao.getPerson(id);
		});

		String expectedMessage = "The person with ID " + id + " doesn't exists";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void updatePerson_shouldReturnUpdatedPerson() {
		memoryDao.addPerson(personToTest);
		Person expectedPerson = new Person(1, "Iseult", "Travers", LocalDate.of(1985, 10, 19), "9 rue Buffon", "78000",
				"Versailles", "+331795282491");
		Optional<Person> actualPerson = memoryDao.updatePerson(expectedPerson);
		Assertions.assertThat(actualPerson.get()).usingRecursiveComparison().isEqualTo(expectedPerson);
	}

	@Test
	void updatePerson_shouldThrowException_ifPersonDoesntExist() {

		Exception exception = assertThrows(PersonNotFoundException.class, () -> {
			memoryDao.updatePerson(new Person(0, null, null, null, null, null, null, null));
		});

		String expectedMessage = "The person with ID " + 0 + " doesn't exists";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));

	}

}
