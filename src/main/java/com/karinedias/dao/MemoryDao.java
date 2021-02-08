package com.karinedias.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.karinedias.model.Person;

public class MemoryDao implements Dao {

	private static final HashMap<Integer, Person> DB = new HashMap<Integer, Person>();
	private static int sequence = 1;

	@Override
	public Person addPerson(Person per) {
		int id = sequence++;
		per.setId(id);
		DB.put(id, per);
		
		return per;
	}

	@Override
	public void deletePerson(int id) {
		DB.remove(id); 
	}

	@Override
	public Person updatePerson(Person newPerson) {
		int id = newPerson.getId();
		Person oldPerson = DB.get(id);
		DB.replace(id, oldPerson, newPerson);
		return newPerson;
	}

	@Override
	public Person getPerson(int id) {
		return DB.get(id);

	}

	@Override
	public List<Person> getAllPersons() {
		List<Person> persons = new ArrayList<Person>(DB.values());
		return persons;
	}

}
