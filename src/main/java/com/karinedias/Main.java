package com.karinedias;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.karinedias.dao.MemoryDao;
import com.karinedias.model.Person;
import com.karinedias.presentation.CommandLineInterface;
import com.karinedias.service.PersonService;

public class Main {

	public static void main(String[] args) {

		CommandLineInterface.main(args);

	}

	public static void database() {
		List<Person> database = new ArrayList<Person>(); // change to hashmap
		database.add(new Person(1, "Alana", "Travis", LocalDate.of(2007, 12, 03), "21 rue Parchemin", "64100",
				"Bolquère", "+336745282368"));
		database.add(new Person(2, "David", "Petuski", LocalDate.of(1983, 06, 12), "15 avenue du Général de Gaulle",
				"78490", "Grosrouvre", "+33132537712"));
		database.add(new Person(3, "Pernille", "Ludron", LocalDate.of(1999, 05, 29), "114 boulevard de la République",
				"77100", "Provins", "+33144296384"));
		database.add(new Person(4, "Margot", "La Rue", LocalDate.of(2007, 12, 03), "5 bis rue Leduc", "31400",
				"Montpellier", "+33617662127"));
		database.add(new Person(5, "Jean-Luc", "Davant", LocalDate.of(1953, 01, 31), "81 rue des Petits Champs",
				"44580", "Les Moutiers-en-Retz", "+33274638755"));
		database.add(new Person(6, "Arnaud", "Frontbourg", LocalDate.of(1989, 07, 22), "2 passage Giraud", "73500",
				"Fourneaux", "+33264553317"));
		database.add(new Person(7, "Marie-Noëlle", "Battistel", LocalDate.of(1950, 04, 01), "4 rue du 11 novembre",
				"77100", "Provins", "+33144296384"));

		Map<Integer, Person> map = database.stream().collect(Collectors.toMap(Person::getId, Function.identity()));

		PersonService personService = new PersonService();
		personService.create(map.get(1));
		personService.create(map.get(2));
		personService.create(map.get(3));
		personService.create(map.get(4));
		personService.create(map.get(5));
		personService.create(map.get(6));
		personService.create(map.get(7));
		personService.create(map.get(2));
		personService.deletePerson(map.get(2).getId());
		personService.updatePerson(new Person(6, "David", "Bordot", LocalDate.of(1983, 06, 12),
				"15 avenue du Général de Gaulle", "78490", "Grosrouvre", "+33132537712"));
		personService.create(new Person(9, "Alice", "Bertin", LocalDate.of(1999, 02, 03),
				"17 rue de la Cheminée Blanche", "91710", "Vert-le-Petit", "+33123259988"));

//        List<String> sortedList = personService.getAllPersons().stream().sorted().collect(Collectors.toList());

		List<Person> sorted = personService.getAllPersons().stream()
				.sorted(Comparator.comparingInt(person -> person.getId())).collect(Collectors.toList());

		sorted.forEach(System.out::println);

		// personService.getAllPersons().forEach(System.out::println);
	}

}
