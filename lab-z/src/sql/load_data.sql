INSERT INTO place_type VALUES (nextval('sc_place_type'), 'Departamento');
INSERT INTO place_type VALUES (nextval('sc_place_type'), 'Municipio');
INSERT INTO place_type VALUES (nextval('sc_place_type'), 'Vereda');
INSERT INTO place_type VALUES (nextval('sc_place_type'), 'Finca');
INSERT INTO place_type VALUES (nextval('sc_place_type'), 'Banco de sueros');

--Entonces, con un superusuario cargo el archivo de departamentos con:
--anteponiendo primero el cambio de client_encoding con:
--SET CLIENT_ENCODING TO 'LATIN9';
--COPY place (id, description, place_type) FROM '/home/juango/workspace/labcop/src/sql/departamentos.csv' 
--WITH DELIMITER ';';
--Entonces, cargo el archivo de municipios con:
--Tambin se hace necesario anteponer el cambio del client_encoding
--COPY place (id, description, place_type, placed_on, national_code) FROM 
--'/home/juango/workspace/labcop/src/sql/municipios.csv' WITH DELIMITER ';';
--y actualizo el valor de la secuencia con:
--SELECT setval('sc_place', 1153, false);

INSERT INTO lab_area VALUES (nextval('sc_lab_area'), 'Qumica');
INSERT INTO lab_area VALUES (nextval('sc_lab_area'), 'Hematologa');
INSERT INTO lab_area VALUES (nextval('sc_lab_area'), 'Inmunologa');
INSERT INTO lab_area VALUES (nextval('sc_lab_area'), 'Microbiologa');
INSERT INTO lab_area VALUES (nextval('sc_lab_area'), 'Parasitologa');
INSERT INTO lab_area VALUES (nextval('sc_lab_area'), 'Patologa');
INSERT INTO lab_area VALUES (nextval('sc_lab_area'), 'Uroanlisis');

INSERT INTO sample_type VALUES (nextval('sc_sample_type'), 'Sangre');
INSERT INTO sample_type VALUES (nextval('sc_sample_type'), 'Materia Fecal');
INSERT INTO sample_type VALUES (nextval('sc_sample_type'), 'Orina');
INSERT INTO sample_type VALUES (nextval('sc_sample_type'), 'Secrecion');
INSERT INTO sample_type VALUES (nextval('sc_sample_type'), 'Leche');
INSERT INTO sample_type VALUES (nextval('sc_sample_type'), 'Tejido');
INSERT INTO sample_type VALUES (nextval('sc_sample_type'), 'Raspado de piel');
INSERT INTO sample_type VALUES (nextval('sc_sample_type'), 'Agua');
INSERT INTO sample_type VALUES (nextval('sc_sample_type'), 'Calculo');
INSERT INTO sample_type VALUES (nextval('sc_sample_type'), 'Suelo');
INSERT INTO sample_type VALUES (nextval('sc_sample_type'), 'Suero');
INSERT INTO sample_type VALUES (nextval('sc_sample_type'), 'Plasma');
INSERT INTO sample_type VALUES (nextval('sc_sample_type'), 'Otros Liquidos');
INSERT INTO sample_type VALUES (nextval('sc_sample_type'), 'Alimento');
INSERT INTO sample_type VALUES (nextval('sc_sample_type'), 'Citologia');
INSERT INTO sample_type VALUES (nextval('sc_sample_type'), 'Liquido cefalorraquideo');
INSERT INTO sample_type VALUES (nextval('sc_sample_type'), 'Semen');
INSERT INTO sample_type VALUES (nextval('sc_sample_type'), 'Pelo');
INSERT INTO sample_type VALUES (nextval('sc_sample_type'), 'Cadaver');
INSERT INTO sample_type VALUES (nextval('sc_sample_type'), 'Lavado prepucial');
INSERT INTO sample_type VALUES (nextval('sc_sample_type'), 'Sangre y Orina');
INSERT INTO sample_type VALUES (nextval('sc_sample_type'), 'Medula Osea');

INSERT INTO assembly_type VALUES (nextval('sc_assembly_type'), 'Brucella'),
	(nextval('sc_assembly_type'), 'Base'), (nextval('sc_assembly_type'), 'PRRS-TGEV/PRCV'),
	(nextval('sc_assembly_type'), 'Toxoplasma'), (nextval('sc_assembly_type'), 'T3 total, T4 total y T4 libre'),
	(nextval('sc_assembly_type'), 'TSH');

INSERT INTO specie VALUES (nextval('sc_specie'), 'Canina');
INSERT INTO specie VALUES (nextval('sc_specie'), 'Felina');
INSERT INTO specie VALUES (nextval('sc_specie'), 'Bovina');

INSERT INTO race VALUES (nextval('sc_race'), 'Samoyedo', 1);
INSERT INTO race VALUES (nextval('sc_race'), 'Mastin Napolitano', 1);
INSERT INTO race VALUES (nextval('sc_race'), 'Golden Retriever', 1);
INSERT INTO race VALUES (nextval('sc_race'), 'Maltes', 2);
INSERT INTO race VALUES (nextval('sc_race'), 'Persa', 2);
INSERT INTO race VALUES (nextval('sc_race'), 'Holstein', 3);
INSERT INTO race VALUES (nextval('sc_race'), 'Normando', 3);

--Descripci�n de los montajes
INSERT INTO assembly_descriptor VALUES (nextval('sc_assembly_descriptor'), 'Cc', 0, 0, 1);
INSERT INTO assembly_descriptor VALUES (nextval('sc_assembly_descriptor'), 'Cc', 1, 0, 1);
INSERT INTO assembly_descriptor VALUES (nextval('sc_assembly_descriptor'), 'Cc', 0, 1, 1);
INSERT INTO assembly_descriptor VALUES (nextval('sc_assembly_descriptor'), 'Cc', 1, 1, 1);
INSERT INTO assembly_descriptor VALUES (nextval('sc_assembly_descriptor'), 'C++', 0, 2, 1);
INSERT INTO assembly_descriptor VALUES (nextval('sc_assembly_descriptor'), 'C++', 1, 2, 1);
INSERT INTO assembly_descriptor VALUES (nextval('sc_assembly_descriptor'), 'C++', 0, 3, 1);
INSERT INTO assembly_descriptor VALUES (nextval('sc_assembly_descriptor'), 'C++', 1, 3, 1);
INSERT INTO assembly_descriptor VALUES (nextval('sc_assembly_descriptor'), 'C+', 0, 4, 1);
INSERT INTO assembly_descriptor VALUES (nextval('sc_assembly_descriptor'), 'C+', 1, 4, 1);
INSERT INTO assembly_descriptor VALUES (nextval('sc_assembly_descriptor'), 'C+', 0, 5, 1);
INSERT INTO assembly_descriptor VALUES (nextval('sc_assembly_descriptor'), 'C+', 1, 5, 1);
INSERT INTO assembly_descriptor VALUES (nextval('sc_assembly_descriptor'), 'C-', 0, 6, 1);
INSERT INTO assembly_descriptor VALUES (nextval('sc_assembly_descriptor'), 'C-', 1, 6, 1);
INSERT INTO assembly_descriptor VALUES (nextval('sc_assembly_descriptor'), 'C-', 0, 7, 1);
INSERT INTO assembly_descriptor VALUES (nextval('sc_assembly_descriptor'), 'C-', 1, 7, 1);
INSERT INTO assembly_descriptor VALUES (nextval('sc_assembly_descriptor'), 'C+', 0, 0, 2),
	(nextval('sc_assembly_descriptor'), 'C+', 0, 1, 2), (nextval('sc_assembly_descriptor'), 'C-', 0, 2, 2),
	(nextval('sc_assembly_descriptor'), 'C-', 0, 3, 2);
INSERT INTO assembly_descriptor VALUES (nextval('sc_assembly_descriptor'), 'C+', 0, 0, 3),
	(nextval('sc_assembly_descriptor'), 'C+', 0, 1, 3), (nextval('sc_assembly_descriptor'), 'C-', 1, 0, 3),
	(nextval('sc_assembly_descriptor'), 'C-', 1, 1, 3);
INSERT INTO assembly_descriptor VALUES (nextval('sc_assembly_descriptor'), 'C+', 0, 0, 4),
	(nextval('sc_assembly_descriptor'), 'C-', 0, 1, 4), (nextval('sc_assembly_descriptor'), 'Cutoff', 0, 2, 4),
	(nextval('sc_assembly_descriptor'), 'Cutoff', 0, 3, 4);
INSERT INTO assembly_descriptor VALUES (nextval('sc_assembly_descriptor'), 'A', 0, 0, 5),
	(nextval('sc_assembly_descriptor'), 'B', 0, 1, 5), (nextval('sc_assembly_descriptor'), 'C', 0, 2, 5),
	(nextval('sc_assembly_descriptor'), 'D', 0, 3, 5), (nextval('sc_assembly_descriptor'), 'E', 0, 4, 5),
	(nextval('sc_assembly_descriptor'), 'F', 0, 5, 5);
INSERT INTO assembly_descriptor VALUES (nextval('sc_assembly_descriptor'), 'Ref1', 0, 0, 6),
	(nextval('sc_assembly_descriptor'), 'Ref2', 0, 1, 6), (nextval('sc_assembly_descriptor'), 'Ref3', 0, 2, 6),
	(nextval('sc_assembly_descriptor'), 'Ref4', 0, 3, 6), (nextval('sc_assembly_descriptor'), 'Ref5', 0, 4, 6),
	(nextval('sc_assembly_descriptor'), 'Ref6', 0, 5, 6), (nextval('sc_assembly_descriptor'), 'Ref7', 0, 6, 6);

--Pruebas tontas
INSERT INTO result_factor VALUES(nextval('sc_result_factor'), 'ppm', 51, 'Coprocitos', null);
INSERT INTO result_factor VALUES(nextval('sc_result_factor'), null, 28, 'Densidad Optica', null);

--Validas
INSERT INTO result_factor (id, test_description, name, _group)
VALUES (nextval('sc_result_factor'), 52, 'Color', 'Examen F�sico-qu�mico'),
		(nextval('sc_result_factor'), 52, 'Consistencia', 'Examen F�sico-qu�mico'),
		(nextval('sc_result_factor'), 52, 'pH', 'Examen F�sico-qu�mico'),
		(nextval('sc_result_factor'), 52, 'Az�cares reductores', 'Examen F�sico-qu�mico'),
		(nextval('sc_result_factor'), 52, 'Sangre oculta', 'Examen F�sico-qu�mico'),
		(nextval('sc_result_factor'), 52, 'Almidones', 'Examen Microsc�pico'),
		(nextval('sc_result_factor'), 52, 'Fibra vegetal', 'Examen Microsc�pico'),
		(nextval('sc_result_factor'), 52, 'Fibra muscular', 'Examen Microsc�pico'),
		(nextval('sc_result_factor'), 52, 'Levaduras', 'Examen Microsc�pico'),
		(nextval('sc_result_factor'), 52, 'Flora bacteriana', 'Examen Microsc�pico'),
		(nextval('sc_result_factor'), 52, 'Celulosa', 'Examen Microsc�pico'),
		(nextval('sc_result_factor'), 52, '�cidos grasos', 'Examen Microsc�pico'),
		(nextval('sc_result_factor'), 52, 'Grasas neutras', 'Examen Microsc�pico'),
		(nextval('sc_result_factor'), 52, 'Leucocitos', 'Examen Microsc�pico'),
		(nextval('sc_result_factor'), 52, 'Hemat�es', 'Examen Microsc�pico'),
		(nextval('sc_result_factor'), 52, 'Moco', 'Examen Microsc�pico'),
		(nextval('sc_result_factor'), 52, 'Otros', 'Examen Microsc�pico'),
		(nextval('sc_result_factor'), 52, 'Examen parasitol�gico', null);

INSERT INTO reference_value (id, specie, result_factor, ref_value, description)
	VALUES (nextval('sc_reference_value'), 1, 1, '<=200', 'Negativo');
INSERT INTO reference_value (id, specie, result_factor, ref_value, description)
	VALUES (nextval('sc_reference_value'), 1, 1, '>200', 'Positivo');

INSERT INTO users VALUES ('admin', md5('admin'), true);
INSERT INTO roles VALUES ('admin', 'Administrador');
INSERT INTO user_roles VALUES ('admin', 'admin');

INSERT INTO users VALUES ('auxiliar1', md5('auxiliar'), true);
INSERT INTO roles VALUES ('auxiliar', 'Auxiliar de laboratorio');
INSERT INTO user_roles VALUES ('auxiliar1', 'auxiliar');

INSERT INTO sections (name, url, order_number) VALUES ('Administrador', '/lab-zw/admin/', 1);
INSERT INTO sections (name, url, order_number) VALUES ('Nuevo caso', '/lab-zw/labcase/page1.htm', 2);
INSERT INTO sections (name, url, order_number) VALUES ('Buscar caso', '/lab-zw/labcase/search.htm', 3);
INSERT INTO sections (name, url, order_number) VALUES ('Realizar montaje', '/lab-zw/assembly/testdescriptions.htm', 4);
INSERT INTO sections (name, url, order_number) VALUES ('Registrar resultado', '/lab-zw/results/listcases.htm', 5);
INSERT INTO sections (name, url, order_number) VALUES ('Resultados montaje', '/lab-zw/results/listassemblies.htm', 6);
INSERT INTO sections (name, url, order_number) VALUES ('Banco de sueros', '/lab-zw/bank/labcases.htm', 7);
INSERT INTO sections (name, order_number) VALUES ('Par�metros', 8);
INSERT INTO sections (name, url, order_number, parent_section) VALUES ('Listar Bancos', '/lab-zw/bank/banklist.htm', 1, 8);
INSERT INTO sections_roles (section, role) VALUES (1, 'admin');
INSERT INTO sections_roles (section, role) VALUES (2, 'admin');
INSERT INTO sections_roles (section, role) VALUES (2, 'auxiliar');
INSERT INTO sections_roles (section, role) VALUES (3, 'admin');
INSERT INTO sections_roles (section, role) VALUES (4, 'admin');
INSERT INTO sections_roles (section, role) VALUES (5, 'admin');
INSERT INTO sections_roles (section, role) VALUES (6, 'admin');
INSERT INTO sections_roles (section, role) VALUES (7, 'admin');
INSERT INTO sections_roles (section, role) VALUES (8, 'admin');
INSERT INTO sections_roles (section, role) VALUES (9, 'admin');

INSERT INTO application_owner (nit, name, address, phone, fax, authorized_tests, webpage)
VALUES ('1', 'CEV', '', '', '',
		null, 'centroespecialidadesveterinarias@yahoo.com');
INSERT INTO authorization_codes (code, begin_date, end_date, owner) VALUES('142-2005', '01-01-2005', '12-31-2010', 1);
INSERT INTO lab_professional (dni, last_name, first_name, status, lab) VALUES('52474910', 'Acero Arias', 'Aura Luisa Esperanza', 'A', 1);
INSERT INTO lab_professional (dni, last_name, first_name, status, lab) VALUES('6985365', 'Castellanos', 'Alejandro', 'A', 1);
INSERT INTO lab_professional (dni, last_name, first_name, status, lab) VALUES('43875038', 'Chaparro', 'Yanira', 'A', 1);

INSERT INTO sequential_numbers (description, seq_number) VALUES ('N�meros de caso del ICA', 1);