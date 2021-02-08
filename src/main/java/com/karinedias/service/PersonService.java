package com.karinedias.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.karinedias.dao.Dao;
import com.karinedias.dao.MemoryDao;
import com.karinedias.model.Person;

public class PersonService {

	private Dao dao = new MemoryDao();

	public Optional<Person> create(Person person) {
		if (!isLegal(person)) {
			return Optional.empty();
		}
		Person createdPerson = dao.addPerson(person);
		return Optional.of(createdPerson);
	}

	private boolean isLegal(Person person) {
		return ChronoUnit.YEARS.between(person.getBirthdate(), LocalDate.now()) >= 18;
	}
	
	
	private boolean isInDatabase(Person person) {
		
		boolean personExists = false;

		for (Person per : dao.getAllPersons()) {
			if (person.equals(per)) {
				personExists = true;
			}
		}
		return personExists;
				
				
				
	}

	public void deletePerson(int id) {

		dao.deletePerson(id);

	}

	public Optional<Person> updatePerson(Person newPerson) {

		Person updatedPerson = dao.updatePerson(newPerson);
		if (!isLegal(updatedPerson)) {
			return Optional.empty();
		}
		return Optional.of(updatedPerson);
	}

	public Optional<Person> getPerson(int id) {

		return Optional.of(dao.getPerson(id)); // or Optional.ofNullable ?
	}

	public List<Person> getAllPersons() {
		return dao.getAllPersons();

	}

}
