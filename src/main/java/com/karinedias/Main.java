package com.karinedias;

import java.time.LocalDate;

import com.karinedias.dao.MemoryDao;
import com.karinedias.model.Person;
import com.karinedias.presentation.CommandLineInterface;
import com.karinedias.service.PersonService;

public class Main {

	public static void main(String[] args) {

		// Put some people into local database
		MemoryDao memoryDao = new MemoryDao();
		PersonService personService = new PersonService(memoryDao);
		personService.create(new Person(1, "Florence", "Cornebuse", LocalDate.of(1963, 12, 03), "21 rue Parchemin",
				"64100", "Bolquère", "06745282368"));
		personService.create(new Person(2, "David", "Petuski", LocalDate.of(1983, 06, 12),
				"15 avenue du Général de Gaulle", "78490", "Grosrouvre", "0132537712"));
		personService.create(new Person(3, "Pernille", "Ludron", LocalDate.of(1999, 05, 29),
				"114 boulevard de la République", "77100", "Provins", "0144296384"));
		personService.create(new Person(4, "Margot", "La Rue", LocalDate.of(2000, 12, 03), "5 bis rue Leduc", "31400",
				"Montpellier", "0617662127"));
		personService.create(new Person(5, "Jean-Luc", "Davant", LocalDate.of(1953, 01, 31), "81 rue des Petits Champs",
				"44580", "Les Moutiers-en-Retz", "0274638755"));
		personService.create(new Person(6, "Arnaud", "Frontbourg", LocalDate.of(1989, 07, 22), "2 passage Giraud",
				"73500", "Fourneaux", "0264553317"));
		personService.create(new Person(7, "Marie-Noëlle", "Battistel", LocalDate.of(1950, 04, 01),
				"4 rue du 11 novembre", "77100", "Provins", "0144296384"));
		personService.create(new Person(8, "David", "Bordot", LocalDate.of(1983, 06, 12),
				"15 avenue du Général de Gaulle", "78490", "Grosrouvre", "0132537712"));
		personService.create(new Person(9, "Alice", "Bertin", LocalDate.of(1999, 02, 03),
				"17 rue de la Cheminée Blanche", "91710", "Vert-le-Petit", "0123259988"));
		personService.create(new Person(10, "Thérèse", "Porchet", LocalDate.of(1947, 8, 30), "15 rue Virgile", "67200",
				"Strasbourg", "0386156377"));

		CommandLineInterface.main(args);

	}

}
