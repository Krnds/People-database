
# Notes on PostgreSQL and JDBC

## Setup PostgreSQL


If launching `psql` throws a
```bash
psql: error: FATAL:  database "<user>" does not exist
```
you have to :

 - `createdb`
 - then execute `psql -h localhost` and enter password
 psql will be launched!

## PSQL syntax

When a database is created you can specify a `schema` which is a namespace that contains named database objects such as tables, views, indexes, data types, functions, stored procedures and operators. If you don't specify this schema, the public one will be choosen.


in the psql prompt, for listing all existing databases, do : `\l`
It will return a table like this one :

```sql
                                   List of databases
    Name     |  Owner   | Encoding |   Collate   |    Ctype    |   Access privileges
-------------+----------+----------+-------------+-------------+-----------------------
 firstwebapp | karine   | UTF8     | fr_FR.UTF-8 | fr_FR.UTF-8 | =Tc/karine           +
             |          |          |             |             | karine=CTc/karine
 karine      | karine   | UTF8     | fr_FR.UTF-8 | fr_FR.UTF-8 |
 postgres    | postgres | UTF8     | fr_FR.UTF-8 | fr_FR.UTF-8 |
 template0   | postgres | UTF8     | fr_FR.UTF-8 | fr_FR.UTF-8 | =c/postgres          +
             |          |          |             |             | postgres=CTc/postgres
 template1   | postgres | UTF8     | fr_FR.UTF-8 | fr_FR.UTF-8 | =c/postgres          +
             |          |          |             |             | postgres=CTc/postgres
(5 rows)

```
List all tables with ```\dt```

```sql
        List of relations
 Schema |  Name  | Type  | Owner
--------+--------+-------+--------
 public | person | table | karine
(1 row)
```

List all info about a table with ```\d <tablename>```

```sql
                         Table "public.person"
   Column    |          Type          | Collation | Nullable | Default
-------------+------------------------+-----------+----------+---------
 id          | numeric                |           | not null |
 firstname   | character varying(50)  |           | not null |
 lastname    | character varying(100) |           | not null |
 birthdate   | date                   |           | not null |
 adress      | text                   |           | not null |
 postalcode  | integer                |           | not null |
 city        | character varying(100) |           | not null |
 phonenumber | numeric                |           | not null |
```


**WARNING!**
-   PostgreSQL uses **single quotes** for string constants (i.e. WHERE name = 'John').
-  **Double quotes** are used to quote system identifiers; field names, table or column names etc. (i.e. WHERE "last name" = 'Smith').
-   PostgreSQL is **case-sensitive for string comparisons**. The field "Smith" is not the same as the field "smith"

1. Create a database

```sql
CREATE DATABASE people_database WITH OWNER = 'karine';
```

## Firstly, install Java EE for Eclipse, JDBC and a plugin for managing database (DBeaver for example)

- Add Maven dependency for postgreSQL JDBC driver :

```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.2.19</version>
</dependency>
```

## Connect a PostgreSQL database with JDBC and DBeaver plugin in Eclipse

A **JDBC** driver is a collection of Java classes that enables you to connect to a certain database. For instance, PostgreSQL will have its own JDBC driver.

**DBeaver** is a database manager for all relational database and NoSQL databases too.  It can work with any database server which has JDBC or ODBC driver.

## Create a SQL script for creating a table and insert some values

If the table `person` already exists, do not duplicate.

```sql
CREATE TABLE if not exists person (
    id SERIAL PRIMARY KEY,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    birthdate DATE NOT NULL,
    adress TEXT NOT NULL,
    postalcode INT NOT NULL,
    city VARCHAR(100) NOT NULL,
    phonenumber VARCHAR(12) NOT NULL
);

insert into person(id, firstname, lastname, birthdate, adress, postalcode, city, phonenumber)
values (1, 'Florence', 'Cornebuse', '1963-12-03', '21 rue Parchemin', 64100, 'Bolquère', '0545282368');
ON CONFLICT DO NOTHING;

```

## Make connection with JDBC in your Java code

Once a JDBC driver is loaded and initialized, you need to connect to the database.
Create a method for opening the JDBC connection with the database :

```java
        private static final String driver = "org.postgresql.Driver";
        private static final String url = "jdbc:postgresql://localhost:5432/database";
        private static final String user = "user";
        private static final String password = "p4ssw0rd";

        public Connection getConnection() throws ClassNotFoundException, SQLException {
            Connection connection = null;
            Class.forName(driver);
            try {
                connection = DriverManager.getConnection(url, user, password);

                if (connection != null) {
                    System.out.println("Connected to the PostgreSQL server successfully.");
                } else {
                    System.out.println("Failed to make connection!");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            return connection;
        }
```

## Processing SQL Statements with JDBC (Core JDBC)

1. Establish a connection (done above).
2. Create a statement in SQL language with **PreparedStatement**

JDBC PreparedStatement is a special kind of Java JDBC Statement object with some useful additional features. Remember, you need a Statement in order to execute either a query or an update. You can use a Java JDBC PreparedStatement instead of a Statement and make easy to insert parameters into the SQL statement or reuse the PreparedStatement with new parameter values.

```java
String sql = "delete from person where id = ?";
connection.prepareStatement(sql);
```

3. Execute the query with JDBC.

`executeQuery()` was designed to execute query statements so it returns a ResultSet that contains the data returned by the query. The Statement class offers a second method that you should use to execute other types of commands (`UPDATE`, `INSERT`, `DELETE` etc.). Instead of returning a collection of rows, the `executeUpdate()` method returns the number of rows affected by the SQL command it executes.

4. Call the database and retrive the result of the query in a so-called ResultSet object.

A JDBC **ResultSet** contains records. Each records contains a set of columns. Each record contains the same amount of columns, although not all columns may have a value. A column can have a null value.

A ResultSet object maintains a cursor pointing to its current row of data. Initially the cursor is positioned before the first row. The next method moves the cursor to the next row, and because it returns false when there are no more rows in the ResultSet object, it can be used in a while loop to iterate through the result set.

5. Close the database connection.

This is a great overview :
![Overview](http://tutorials.jenkov.com/images/java-jdbc/overview.png)



## Difference between ExecuteUpdate and ExecuteQuery methods

**executeQuery** method execute statements that returns a result set by fetching some data from the database. It executes only select statements.

**executeUpdate** method execute sql statements that insert/update/delete data at the database. This method return int value representing number of records affected; Returns 0 if the query returns nothing. The method accepts only non-select statements.

If you want to delete a row that doesn't exists in the table, the statement below will return 0. Because databases return number of rows affected, they don't throw error, unless there is something wrong with the query.

```java
connection.prepareStatement(sql_query).executeUpdate();
```
Executes the SQL statement in this PreparedStatement object, which must be an SQL Data Manipulation Language (DML) statement, such as INSERT, UPDATE or DELETE; or an SQL statement that returns nothing, such as a DDL statement.

It returns either the row count for SQL Data Manipulation Language (DML) statements or  0 for SQL statements that return nothing.


## Add constraints on the database

A person should only be added to the database if her age is >= 18 yo.

To archieve this, we need to make a trigger. It's a database object which fires when an event occurs in a database. We can execute a SQL query that will "do something" in a database when a change occurs on a database table such as a record is inserted or updated or deleted. For example, a trigger can be set on a record insert in a database table.

#### Bugs encountered
As for now, DBeaver 6.2.3 (6.2.5 can't seem to be installed with my Eclipse 2020-12 version) has a bug [https://github.com/dbeaver/dbeaver/issues/7050] which throws a `Unterminated dollar quote` error when launching this PostgreSQL code because of the dollar sign.

```sql
CREATE OR REPLACE FUNCTION check_person_birthdate() RETURNS TRIGGER AS $f$
	BEGIN
		IF (NEW.birthdate > date '2001-01-01') THEN
            RAISE EXCEPTION 'The person % must have at least 18 years old.', NEW.lastname;
		END IF;
        RETURN NEW;
    END;
$f$ LANGUAGE PLPGSQL;


CREATE TRIGGER check_birthdate
	BEFORE INSERT ON person
	FOR EACH ROW EXECUTE PROCEDURE check_person_birthdate();
```