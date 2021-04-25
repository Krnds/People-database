# People Database

## About

People database is a Java/Jakarta EE project made without Spring to explore JSP/Servlets, the connection between JDBC and a database, and setting up a Tomcat server.

The database can be accessed in CLI form or in a web application.

![Overview](/img/snapshot_database.png)
<img src="/img/snapshot_database_2.png" alt="overview" width="250"/>

## Usage

1. Install HTTP server Apache Tomcat 9+ [here](https://tomcat.apache.org/download-90.cgi)

2. Set up environnement variables for ```JAVA_HOME``` and ```CATALINA_HOME```

3. Make all Shell scripts in tomcat/bin executables whith ```chmod``` and execute ```startup.sh``` script.

4. Open http://localhost:8080/ in your browser to check if Tomcat is running

5. Drag and drop the people-db.war package in your tomcat/webapps and wait until you see this project folder created.

6. Run http://localhost:8080/people-db/ and that's it!


## TODO:

- [X] Add jsp page for getPerson

- [ ] Find a way to get values from the database entry and display them in the placeholders of updatePerson.jsp

- [X] Learn JQuery to :

    - [X] Sort database main table by columns
    - [ ] Prevent entry from deleting when trash button is called

- [X] Update CSS



Author :

@Karine Dias, 2021