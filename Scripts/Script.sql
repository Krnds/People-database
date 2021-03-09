DROP TRIGGER IF EXISTS check_birthdate on person;

CREATE TABLE if not exists person (
    id  NUMERIC unique NOT NULL,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    birthdate DATE NOT NULL,
    adress TEXT NOT NULL,
    postalcode INT NOT NULL,
    city VARCHAR(100) NOT NULL,
    phonenumber VARCHAR(12) NOT NULL
);

CREATE OR REPLACE FUNCTION check_person_birthdate() RETURNS TRIGGER AS $func$
	BEGIN
		IF (NEW.birthdate > date '2001-01-01') THEN
            RAISE EXCEPTION 'The person % must have at least 18 years old.', NEW.lastname;
		END IF;
        RETURN NULL;
    END;
$func$ LANGUAGE plpgsql;


CREATE TRIGGER check_birthdate
	BEFORE INSERT ON person
	FOR EACH ROW EXECUTE PROCEDURE check_person_birthdate();

insert into person(id, firstname, lastname, birthdate, adress, postalcode, city, phonenumber)
values (1, 'Florence', 'Cornebuse', '1963-12-03', '21 rue Parchemin', 64100, 'Bolquère', '0545282368'),
	   (2, 'David', 'Petuski', '1983-06-12', '15 avenue du Général de Gaulle', 78490, 'Grosrouvre', '0132537712'),
       (3, 'Pernille', 'Ludron', '1999-05-29', '114 boulevard de la République', 77100, 'Provins', '0144296384'),
       (4, 'Margot', 'La Rue', '2000-12-03', '5 bis rue Leduc', 31400, 'Montpellier', '0617662127'),
	   (5, 'Jean-Luc', 'Davant', '1953-01-31', '81 rue des Petits Champs', 44580, 'Les Moutiers-en-Retz', '0274638755'),
       (6, 'Arnaud', 'Frontbourg', '1989-07-22', '2 passage Giraud', 73500, 'Freney', '0464553317'),
       (7, 'Marie-Noëlle', 'Battistel', '1950-04-01', '4 rue du 11 novembre', 77100, 'Provins', '0144296384'),
       (8, 'David', 'Bordot', '1983-06-12', '15 avenue du Général de Gaulle', 78490, 'Grosrouvre', '0132537712'),
       (9, 'Alice', 'Bertin', '1999-02-03', '17 rue de la Cheminée Blanche', 91710, 'Vert-le-Petit', '0123259988'),
       (10, 'Thérèse', 'Porchet', '1947-08-30', '15 rue Virgile', 67200, 'Strasbourg', '0386156377');






