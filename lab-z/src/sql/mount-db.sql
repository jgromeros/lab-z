\i /Users/juanromero/Documents/workspace/lab-z/src/sql/drop-schema.sql
\i /Users/juanromero/Documents/workspace/lab-z/src/sql/schema.sql
\i /Users/juanromero/Documents/workspace/lab-z/src/sql/security-schema.sql
\i /Users/juanromero/Documents/workspace/lab-z/src/sql/load_data.sql
SET CLIENT_ENCODING TO 'latin9';
COPY place (id, name, place_type) FROM '/Users/juanromero/Documents/workspace/lab-z/src/sql/departamentos.csv' WITH DELIMITER ';';
COPY place (id, name, place_type, placed_on, national_code) FROM '/Users/juanromero/Documents/workspace/lab-z/src/sql/municipios.csv' WITH DELIMITER ';';
SET CLIENT_ENCODING TO 'utf8';
SELECT setval('sc_place', 1153, false);
UPDATE test_description SET ica_acronym = 'RB' WHERE id = 29;
UPDATE test_description SET ica_acronym = 'ELISA-i' WHERE id IN (27, 28);
GRANT SELECT ON TABLE users TO utomcat;
GRANT SELECT ON TABLE roles TO utomcat;
GRANT SELECT ON TABLE user_roles TO utomcat;
GRANT SELECT ON TABLE sections TO utomcat;
GRANT SELECT ON TABLE sections_roles TO utomcat;
