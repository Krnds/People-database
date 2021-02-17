# Notes about unit tests with JUnit5 and Mockito

> Mockito is a java based mocking framework, used in conjunction with
> other testing frameworks such as JUnit and TestNG

## Context

In a project with a DAO, Service and Presentation classes, you should unit test its data access layer (DAO).

![Project organisation](https://i2.wp.com/howtodoinjava.com/wp-content/uploads/2015/04/Unit-test-dao-layer.jpg?w=638&ssl=1)

Source : https://howtodoinjava.com/best-practices/how-you-should-unit-test-dao-layer/

## Mock the DAO

Use the annotation @Mock to create a dummy DAO instance

```java
@Mock
MemoryDao memoryDao;
```

## Prepare the Service

```java
private PersonService personService;
```

## Setup executed before each unit test

```java
@BeforeEach
void setUp() {
	MockitoAnnotations.openMocks(this);
	personService = new PersonService(memoryDao);
}
```

## Configure Mockito behavior

Mockito.when(Method call).then();

example :

```java
private Person adultPerson = new Person(...);

@Test
void createAdultPerson_shouldReturnNonEmptyOptional() {
	when(memoryDao.addPerson(Mockito.any())).thenReturn(Optional.of(adultPerson));
	Optional<Person> person = personService.create(adultPerson);
	assertTrue(person.isPresent());
}
```

## Test Exceptions

```java
@Test
void getDeletedPerson_shouldThrowPersonNotFoundException() {
	int id = personToTest.getId();
	memoryDao.deletePerson(id);
	Exception exception = assertThrows(PersonNotFoundException.class, () -> {
		memoryDao.getPerson(id);
	});

	String expectedMessage = "The person with ID " + id + " doesn't exists";
	String actualMessage = exception.getMessage();
	assertTrue(actualMessage.contains(expectedMessage));
}
```

## Author

Karine Dias
