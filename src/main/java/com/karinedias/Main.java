package com.karinedias;

import java.time.LocalDate;

import com.karinedias.dao.MemoryDao;
import com.karinedias.model.Person;
import com.karinedias.presentation.CommandLineInterface;
import com.karinedias.service.PersonService;

public class Main {

	public static void main(String[] args) {

		// Put some people into database
		MemoryDao memoryDao = new MemoryDao();
		PersonService personService = new PersonService(memoryDao);
		personService.create(new Person(1, "Florence", "Cornebuse", LocalDate.of(1963, 12, 03), "21 rue Parchemin",
				"64100", "Bolquère", "+336745282368"));
		personService.create(new Person(2, "David", "Petuski", LocalDate.of(1983, 06, 12),
				"15 avenue du Général de Gaulle", "78490", "Grosrouvre", "+33132537712"));
		personService.create(new Person(3, "Pernille", "Ludron", LocalDate.of(1999, 05, 29),
				"114 boulevard de la République", "77100", "Provins", "+33144296384"));
		personService.create(new Person(4, "Margot", "La Rue", LocalDate.of(2007, 12, 03), "5 bis rue Leduc", "31400",
				"Montpellier", "+33617662127"));
		personService.create(new Person(5, "Jean-Luc", "Davant", LocalDate.of(1953, 01, 31), "81 rue des Petits Champs",
				"44580", "Les Moutiers-en-Retz", "+33274638755"));
		personService.create(new Person(6, "Arnaud", "Frontbourg", LocalDate.of(1989, 07, 22), "2 passage Giraud",
				"73500", "Fourneaux", "+33264553317"));
		personService.create(new Person(7, "Marie-Noëlle", "Battistel", LocalDate.of(1950, 04, 01),
				"4 rue du 11 novembre", "77100", "Provins", "+33144296384"));
		personService.deletePerson(2);
		personService.updatePerson(new Person(6, "David", "Bordot", LocalDate.of(1983, 06, 12),
				"15 avenue du Général de Gaulle", "78490", "Grosrouvre", "+33132537712"));
		personService.create(new Person(9, "Alice", "Bertin", LocalDate.of(1999, 02, 03),
				"17 rue de la Cheminée Blanche", "91710", "Vert-le-Petit", "+33123259988"));

		CommandLineInterface.main(args);

	}

}
