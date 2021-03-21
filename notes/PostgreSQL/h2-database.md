# Notes on H2 database

H2 is a Java SQL database for unit testing.
It can be used in **embedded mode** or in **server mode**

- Embedded is faster but no other process can access the database.
- If your DB needs to be shared between servers or APIs, then the server mode is needed.

H2 database is generally used with Spring Boot, which is an open source Java-based framework used to create a comprehensive infrastructure  support for Java apps.

## Set up connection

To set up a H2 database, you need to pass `org.h2.Driver` as the driver name and  `jdbc:h2:{file};MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE;DB_CLOSE_DELAY=-1;INIT=RUNSCRIPT FROM '.../myScript.sql';` as the url.

This url enables :
- a database contained the `file`
- read PostgreSQL compatibility mode for reading and executing SQL files
- initializing automatically the SQL script when connecting to H2 database and perform some actions.

**WARNING!** if the H2 database is already connected, another connection made by some unit test will throw a `Database may be already in use: null. Possible solutions: close all other connection(s); use the server mode [90020-200]`

if H2 is in embedded mode, because only one connection is available.


#TODO

- [ ] Changer le schema par defaut car problème lors des TU avec H2.
- [ ] rajouter la clause ON CONFLICT DO NOTHING; non prise en charge par H2 postgresql mode, car ce n'est pas du SQL standard apparement.
Cf. ce topic sur le sujet : https://github.com/h2database/h2database/issues/2007

- [ ] Passer à Docker lors de la prochaine version.
- [ ] Quoi faire pour l'ID d'une personne qui est automatiquement mis dans la db en 10 + 1 ?