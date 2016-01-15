/*Added enabled property to profile*/
ALTER TABLE profile ADD COLUMN enabled BOOLEAN;
UPDATE profile SET enabled = true;
ALTER TABLE profile ALTER COLUMN enabled SET NOT NULL;

/*Updating profiles*/
UPDATE profile SET description = 'Perfil Electrolítico Básico' WHERE id = 1;
UPDATE profile SET description = 'Perfil Muscular' WHERE id = 13;
UPDATE profile SET description = 'Perfil Inmunológico Felino' WHERE id = 15;
UPDATE profile SET description = 'Perfil Geriátrico 1' WHERE id = 19;

UPDATE profile SET enabled = false WHERE id IN (7, 8, 10, 12, 18);

INSERT INTO profile VALUES (nextval('sc_profile'), 'PREQUIRURGICO 2', true);
INSERT INTO profile VALUES (nextval('sc_profile'), 'PREQUIRURGICO 3', true);
INSERT INTO profile VALUES (nextval('sc_profile'), 'PREQUIRURGICO 4', true);
INSERT INTO profile VALUES (nextval('sc_profile'), 'PREQUIRURGICO 5', true);
INSERT INTO profile VALUES (nextval('sc_profile'), 'PREQUIRURGICO 6', true);
INSERT INTO profile VALUES (nextval('sc_profile'), 'PERFIL GERIATRICO 2', true);
INSERT INTO profile VALUES (nextval('sc_profile'), 'PERFIL GERIATRICO 3', true);
INSERT INTO profile VALUES (nextval('sc_profile'), 'PERFIL CACHORRO 1', true);
INSERT INTO profile VALUES (nextval('sc_profile'), 'PERFIL CACHORRO 2', true);
INSERT INTO profile VALUES (nextval('sc_profile'), 'PERFIL CACHORRO 3', true);
INSERT INTO profile VALUES (nextval('sc_profile'), 'PERFIL CACHORRO 4', true);
INSERT INTO profile VALUES (nextval('sc_profile'), 'PERFIL CONVULSIVO 1', true);
INSERT INTO profile VALUES (nextval('sc_profile'), 'PERFIL CONVULSIVO 2', true);
INSERT INTO profile VALUES (nextval('sc_profile'), 'PERFIL CONVULSIVO 3', true);
INSERT INTO profile VALUES (nextval('sc_profile'), 'PERFIL CONVULSIVO 4', true);
INSERT INTO profile VALUES (nextval('sc_profile'), 'PERFIL RENAL 2', true);
INSERT INTO profile VALUES (nextval('sc_profile'), 'PERFIL RENAL 3', true);
INSERT INTO profile VALUES (nextval('sc_profile'), 'PERFIL HEPATICO BASICO', true);
INSERT INTO profile VALUES (nextval('sc_profile'), 'PERFIL HEPATICO COMPLETO', true);
INSERT INTO profile VALUES (nextval('sc_profile'), 'PERFIL ELECTROLITICO COMPLETO', true);
INSERT INTO profile VALUES (nextval('sc_profile'), 'PERFIL DIABETICO 1', true);
INSERT INTO profile VALUES (nextval('sc_profile'), 'PERFIL DIABETICO 2', true);
INSERT INTO profile VALUES (nextval('sc_profile'), 'EXCRECIÓN FRACCIONAL DE MAGNESIO', true);
INSERT INTO profile VALUES (nextval('sc_profile'), 'EXCRECIÓN FRACCIONAL DE CLORO', true);
INSERT INTO profile VALUES (nextval('sc_profile'), 'PERFIL CARDIACO', true);
INSERT INTO profile VALUES (nextval('sc_profile'), 'PERFIL LIPÍDICO', true);

INSERT INTO test_description_profile(profile, test_description) VALUES
(20, 57),(20, 84),(20, 56),
(21, 57),(21, 5),(21, 56),(21, 52),
(22, 57),(22, 84),(22, 56),(22, 52),
(23, 57),(23, 5),(23, 56),(23, 52),(23, 166),(23, 167),
(24, 57),(24, 84),(24, 56),(24, 52),(24, 166),(24, 167),
(25, 57),(25, 5),(25, 84),(25, 18),(25, 80),(25, 86),(25, 30),(25, 161),(25, 140),(25, 39),(25, 56),(25, 120),(25, 52),
(26, 57),(26, 5),(26, 84),(26, 18),(26, 80),(26, 86),(26, 40),(26, 170),(26, 30),(26, 56),(26, 52),
(27, 57),(27, 53),
(28, 57),(28, 53),(28, 126),
(29, 57),(29, 53),
(30, 57),(30, 53),(30, 68),
(31, 57),(31, 86),(31, 5),(31, 18),(31, 120),(31, 56),(31, 161),(31, 140),(31, 30),(31, 81),(31, 39),(31, 7),(31, 52),
(32, 57),(32, 86),(32, 5),(32, 18),(32, 80),(32, 120),(32, 56),(32, 161),(32, 140),(32, 30),(32, 81),(32, 52),
(33, 57),(33, 86),(33, 84),(33, 18),(33, 120),(33, 56),(33, 161),(33, 140),(33, 30),(33, 81),(33, 39),(33, 7),(33, 52),
(34, 57),(34, 86),(34, 84),(34, 18),(34, 80),(34, 120),(34, 56),(34, 161),(34, 140),(34, 30),(34, 81),(34, 52),
(35, 120),(35, 56),(35, 52),(35, 57),(35, 81),(35, 140),
(36, 120),(36, 56),(36, 52),(36, 81),(36, 30),(36, 174),
(37, 57),(37, 86),(37, 5),(37, 84),(37, 18),(37, 80),(37, 22),(37, 40),
(38, 57),(38, 86),(38, 5),(38, 84),(38, 18),(38, 80),(38, 22),(38, 40),(38, 203),(38, 7),(38, 52),
(39, 161),(39, 140),(39, 30),(39, 81),(39, 39),(39, 115),
(40, 86),(40, 233),(40, 236),(40, 5),(40, 80),(40, 52),
(41, 86),(41, 233),(41, 236),(41, 5),(41, 80),(41, 52),(41, 57),(41, 120),(41, 56),
(42, 56),(42, 187),(42, 193),(42, 115),
(43, 56),(43, 187),(43, 39),(43, 191),
(44, 249), (44, 248), (44, 247),
(45, 40), (45, 170), (45, 175),(45, 176);

INSERT INTO prices_by_test_desc (id, valid_from, valid_until, profile, price) VALUES
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',6,25000),
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',11,35000),
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',19,50000),
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',9,25000),
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',1,40000),
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',16,61000),
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',17,61000),
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',2,40000),
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',3,30000),
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',4,30000),
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',5,30000),
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',13,35000),
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',14,30000),
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',15,115000),
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',20,35000),
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',21,37000),
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',22,37000),
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',23,50000),
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',24,50000),
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',25,65000),
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',26,60000),
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',27,22500),
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',28,45000),
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',29,26000),
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',30,45000),
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',31,76000),
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',32,60000),
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',33,76000),
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',34,60000),
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',35,40000),
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',36,40000),
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',37,40000),
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',38,112000),
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',39,50000),
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',40,67000),
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',41,78000),
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',42,40000),
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',43,40000),
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',44,80000),
(nextval('sc_prices_by_test_desc'), '2016-01-01', '2017-12-31',45,30000);

UPDATE prices_by_test_desc SET tax = 16 WHERE valid_until = '2017-12-31';

ALTER TABLE enterprise ADD COLUMN city VARCHAR(64);

ALTER TABLE bill_detail ALTER COLUMN tax TYPE NUMERIC(12,2);

UPDATE sequential_numbers SET seq_number = 0 WHERE id = 2;
