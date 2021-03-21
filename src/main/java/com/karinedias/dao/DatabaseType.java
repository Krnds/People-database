package com.karinedias.dao;

public enum DatabaseType {

	POSTGRESQL("org.postgresql.Driver", "jdbc:postgresql://localhost:5432/people_database"),
	H2("org.h2.Driver", "jdbc:h2:mem:/home/karine/src/java/people-db/src/test/java/dao/DaoJDBCTest.java;"
			+ "INIT=RUNSCRIPT FROM '/home/karine/src/java/people-db/Scripts/Script.sql';MODE=PostgreSQL");

	private final String driver;
	private final String url;

	DatabaseType(String driver, String url) {
		this.driver = driver;
		this.url = url;
	}

	public String getDriver() {
		return this.driver;
	}

	public String getURL() {
		return this.url;
	}

}
