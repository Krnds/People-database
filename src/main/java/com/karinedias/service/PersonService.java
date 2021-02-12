package com.karinedias.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
		return dao.addPerson(person);
	}

	private boolean isLegal(Person person) {
		return ChronoUnit.YEARS.between(person.getBirthdate(), LocalDate.now()) >= 18;
	}

	public void deletePerson(int id) {

		dao.deletePerson(id);

	}

	public Optional<Person> updatePerson(Person newPerson) {

		Optional<Person> updatedPerson = dao.updatePerson(newPerson);
		if (!isLegal(updatedPerson.get())) {
			return Optional.empty();
		}
		return updatedPerson;

	}

	public Optional<Person> getPerson(int id) {
		return dao.getPerson(id);

	}

	public List<Person> getAllPersons() {
		return dao.getAllPersons();

	}

}
