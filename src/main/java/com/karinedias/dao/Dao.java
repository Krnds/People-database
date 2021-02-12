package com.karinedias.dao;

import java.util.List;
import java.util.Optional;

import com.karinedias.model.Person;

public interface Dao {

	Optional<Person> addPerson(Person per);

	void deletePerson(int id);

	Optional<Person> updatePerson(Person newPerson);

	Optional<Person> getPerson(int id);

	List<Person> getAllPersons();

}
