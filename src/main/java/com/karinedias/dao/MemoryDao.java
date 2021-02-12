package com.karinedias.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.karinedias.exceptions.PersonNotFoundException;
import com.karinedias.model.Person;

public class MemoryDao implements Dao {

	private static final HashMap<Integer, Person> DB = new HashMap<Integer, Person>();
	private static int sequence = 1;

	@Override
	public Optional<Person> addPerson(Person person) {
		int id = sequence++;
		person.setId(id);
		DB.put(id, person);

		return Optional.of(person);
	}

	@Override
	public void deletePerson(int id) {
		DB.remove(id);
	}

	@Override
	public Optional<Person> updatePerson(Person newPerson) {
		if (DB.replace(newPerson.getId(), newPerson) == null) {
			throw new PersonNotFoundException(String.format("The person with ID %s doesn't exists", newPerson.getId()));
		}
		return Optional.of(newPerson);
	}

	@Override
	public Optional<Person> getPerson(int id) {
		Person personToGet = DB.get(id);
		if (personToGet == null) {
			throw new PersonNotFoundException(String.format("The person with ID %s doesn't exists", id));
		} else {
			return Optional.of(personToGet);
		}
	}

	@Override
	public List<Person> getAllPersons() {
		return new ArrayList<Person>(DB.values());
	}

}
