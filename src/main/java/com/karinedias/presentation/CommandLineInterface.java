package com.karinedias.presentation;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.Scanner;

import com.karinedias.dao.Dao;
import com.karinedias.dao.DaoJDBC;
import com.karinedias.dao.DatabaseType;
import com.karinedias.dao.MemoryDao;
import com.karinedias.exceptions.PersonNotFoundException;
import com.karinedias.model.Person;
import com.karinedias.service.PersonService;

public class CommandLineInterface {

	private static final Scanner SCANNER = new Scanner(System.in);
	private static Dao dao;
	private static PersonService personService;

	public static void main(String[] args) {
		int choice;
		int strategy = chooseStrategy();
		if (strategy == 1) {
			dao = new MemoryDao();

		} else if (strategy == 2) {
			dao = new DaoJDBC(DatabaseType.POSTGRESQL);
		}
		personService = new PersonService(dao);

		do {
			choice = mainMenu();
			switch (choice) {
			case 1:
				personService.create(createUser()).ifPresent(System.out::println);
				break;
			case 2:
				System.out.println("Type in ID of the person to be deleted :\n");
				int id = SCANNER.nextInt();
				personService.deletePerson(id);
				break;
			case 3:
				personService.updatePerson(updateUser()).ifPresent(System.out::println);
				break;
			case 4:
				System.out.println("Type in ID of the person :\n");
				int id2 = SCANNER.nextInt();
				personService.getPerson(id2).ifPresent(System.out::println);
				break;
			case 5:
				System.out.println("Retrieving all users...\n");
				System.out.println("Counting " + personService.getAllPersons().size() + " persons :");
				personService.getAllPersons().forEach(p -> System.out.println(p.toString()));
				break;

			}
		} while (choice != 6);

		System.out.println("\n---------Quiting app !---------");
		System.exit(0);
	}

	private static int chooseStrategy() {
		int strategy = 0;
		do {
			System.out.println("Choose between :\n[1] Local database \n[2] JDBC :");
			strategy = SCANNER.nextInt();
			if (strategy == 1) {
				dao = (Dao) new MemoryDao();
			} else if (strategy == 2) {
				dao = (Dao) new DaoJDBC(DatabaseType.POSTGRESQL);
			} else {
				System.out.println("Wrong choice. Please type in again.\n");
			}
		} while (!(strategy == 1 || strategy == 2));

		return strategy;

	}

	public static int mainMenu() {
		int choice = 0;
		do {
			System.out.println("\nWelcome to the database system !\n-----------------------------");
			System.out.println("Please select a menu option :\n");
			System.out.println(
					"[1] Add user to database\n[2] Delete user in database\n[3] Update user in database\n[4] Retrieve user in database\n[5] Retrive ALL users in database\n[6] Quit app");

			choice = SCANNER.nextInt();

		} while (choice < 1 || choice > 6);
		return choice;
	}

	public static Person createUser() {
		System.out.println("Please type in user info\n[Firstname] :");
		String firstname = SCANNER.next();
		System.out.println("\n[Lastname] :");
		String lastname = SCANNER.next();

		boolean inputError = false;
		LocalDate birthLocalDate = null;
		do {

			System.out.println("\n[Birthdate in the form YEAR-MONTH-DAY] :");
			String birthday = SCANNER.next();

			try {
				birthLocalDate = LocalDate.parse(birthday);
				inputError = false;
			} catch (DateTimeParseException e) {
				inputError = true;
				System.err.println("Birthday incorrect. Type in again.");
			}
		} while (inputError);
		System.out.println("\n[Adress without postalcode and city name] :");
		SCANNER.nextLine();
		String adress = SCANNER.nextLine();
		System.out.println("\n[Postalcode] :");
		String postalCode = SCANNER.nextLine();
		System.out.println("\n[City] :");
		String city = SCANNER.nextLine();
		System.out.println("\n[Phone number] :");
		String phone = SCANNER.nextLine();
		return new Person(0, firstname, lastname, birthLocalDate, adress, postalCode, city, phone);

	}

	public static Person updateUser() {
		System.out.println("Type in the ID of the person to update :\n");
		int id = SCANNER.nextInt();
		Optional<Person> oldPerson = personService.getPerson(id);

		if (oldPerson.isPresent()) {
			System.out.println("Type in new firstname :\n");
			String firstname = SCANNER.next();
			System.out.println("Type in new lastname\n");
			String lastname = SCANNER.next();
			System.out.println("\n[Adress without postalcode and city name] :");
			SCANNER.nextLine();
			String adress = SCANNER.nextLine();
			System.out.println("\n[Postalcode] :");
			String postalCode = SCANNER.nextLine();
			System.out.println("\n[City] :");
			String city = SCANNER.nextLine();
			System.out.println("\n[Phone number] :");
			String phone = SCANNER.nextLine();
			return new Person(id, firstname, lastname, oldPerson.get().getBirthdate(), adress, postalCode, city, phone);
		} else {
			throw new PersonNotFoundException("Person not found", null);

		}

	}

}
