package com.karinedias.dao;

import java.util.List;

import com.karinedias.model.Person;

public interface Dao {

	Person addPerson(Person per);

	void deletePerson(int id);

	Person updatePerson(Person newPerson);

	Person getPerson(int id);

	List<Person> getAllPersons();

}
