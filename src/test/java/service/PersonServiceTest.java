package service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.karinedias.dao.MemoryDao;
import com.karinedias.model.Person;
import com.karinedias.service.PersonService;

class PersonServiceTest {

	private Person adultPerson = new Person(1, "Florence", "Cornebuse", LocalDate.of(1963, 12, 03), "21 rue Parchemin",
			"64100", "Bolquère", "+336745282368");
	private Person minorPerson = new Person(1, "Florence", "Cornebuse", LocalDate.of(2002, 12, 03), "21 rue Parchemin",
			"64100", "Bolquère", "+336745282368");

	@Mock
	MemoryDao memoryDao;

	private PersonService personService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		personService = new PersonService(memoryDao);
	}

	@Test
	void createAdultPerson_shouldReturnNonEmptyOptional() {
		when(memoryDao.addPerson(Mockito.any())).thenReturn(Optional.of(adultPerson));
		Optional<Person> person = personService.create(adultPerson);

		assertTrue(person.isPresent());
	}

	@Test
	void createMinorPerson_shouldReturnEmptyOptional() {
		Optional<Person> underagedPerson = personService.create(minorPerson);

		assertFalse(underagedPerson.isPresent());
	}

	@Test
	void deletePerson_shouldCallDao() {
		memoryDao.deletePerson(0);
		verify(memoryDao, times(1)).deletePerson(0);
	}

	@Test
	void updatePerson_shouldCallDao() {
		// Given
		when(memoryDao.updatePerson(Mockito.any())).thenReturn(Optional.of(adultPerson));

		// When
		Optional<Person> updatedPerson = memoryDao.updatePerson(adultPerson);

		// Then
		assertTrue(updatedPerson.isPresent());
		verify(memoryDao, times(1)).updatePerson(adultPerson);
	}

}
