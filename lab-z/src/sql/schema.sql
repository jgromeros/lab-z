CREATE SEQUENCE sc_place_type;
CREATE TABLE place_type(
	id					INTEGER 		NOT NULL,
	description			VARCHAR(64)		NOT NULL,
	PRIMARY KEY (id)
);

CREATE SEQUENCE sc_place;
CREATE TABLE place(
	id					INTEGER			NOT NULL,
	name				VARCHAR(128)	NOT NULL,
	place_type			INTEGER			NOT NULL,
	national_code		VARCHAR(16),
	placed_on			INTEGER,
	PRIMARY KEY (id),
	FOREIGN KEY (place_type) REFERENCES place_type,
	FOREIGN KEY (placed_on) REFERENCES place
);

CREATE SEQUENCE sc_enterprise;
CREATE TABLE enterprise(
	id					INTEGER			NOT NULL,
	dni					NUMERIC(16)		NOT NULL,
	last_name			VARCHAR(64),--Realmente se va a utilizar?
	first_name			VARCHAR(128)		NOT NULL,
	address				VARCHAR(255),
	phone				VARCHAR(16),
	email				VARCHAR(64),
	numero_ica			VARCHAR(32),
	PRIMARY KEY (id),
	UNIQUE (dni)
);

CREATE SEQUENCE sc_specie;
CREATE TABLE specie (
	id					INTEGER			NOT NULL,
	name				VARCHAR(128)	NOT NULL,
	PRIMARY KEY (id)
);

CREATE SEQUENCE sc_race;
CREATE TABLE race (
	id					INTEGER			NOT NULL,
	name				VARCHAR(128)	NOT NULL,
	specie				INTEGER			NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (specie) REFERENCES specie
);

CREATE TABLE application_owner(
	id					SERIAL			NOT NULL,
	nit					VARCHAR(12)		NOT NULL,
	name				TEXT			NOT NULL,
	address				TEXT			NOT NULL,
	phone				TEXT			NOT NULL,
	fax					TEXT			NOT NULL,
	technical_dir		TEXT			NOT NULL,
	authorized_tests	TEXT,
	PRIMARY KEY(id),
	UNIQUE(nit)
);

CREATE TABLE lab_professional(
	id					SERIAL			NOT NULL,
	dni					VARCHAR(16)		NOT NULL,
	last_name			VARCHAR(64)		NOT NULL,
	first_name			VARCHAR(64)		NOT NULL,
	status				CHAR(1) NOT NULL CHECK (status IN ('A'/*Activo*/, 'I'/*Inactivo*/)),
	lab					INTEGER			NOT NULL,
	technical_director	BOOLEAN			NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (lab) REFERENCES application_owner,
	UNIQUE(dni),
);

CREATE SEQUENCE sc_labcase;
CREATE TABLE labcase(
	id					INTEGER			NOT NULL,
	reception_date		DATE			NOT NULL,
	code				VARCHAR(16)		NOT NULL,
	enterprise			INTEGER,
	sender				VARCHAR(128),
	city				INTEGER			NOT NULL,
	zone				VARCHAR(128),
	farm				VARCHAR(128),
	owner				VARCHAR(64),
	state				CHAR(1)			NOT NULL CHECK(state IN ('R'/*Registering*/, 'S'/*Saved*/,
																'W'/*With result*/, 'F'/*Finished*/, 'C'/*Cancelled*/)),
	analysis_purpose	VARCHAR(512),--No sera text?
	reproductive_problem TEXT,
	observations		TEXT,
	technical_director	INTEGER,
	PRIMARY KEY (id),
	FOREIGN KEY (enterprise) REFERENCES enterprise,
	FOREIGN KEY (city) REFERENCES place,
	FOREIGN KEY (technical_director) REFERENCES lab_professional,
	UNIQUE (code)
);

CREATE TABLE bank_place(
	id					SERIAL			NOT NULL,
	name				TEXT			NOT NULL,
	long_description	TEXT,
	placed_in			INTEGER,
	level				INTEGER			NOT NULL,
	animal				INTEGER,
	PRIMARY KEY (id),
	FOREIGN KEY (placed_in) REFERENCES bank_place,
	FOREIGN KEY (animal) REFERENCES animal,
	UNIQUE(animal)
);

CREATE SEQUENCE sc_animal;
CREATE TABLE animal (
	id					INTEGER			NOT NULL,
	name				VARCHAR(64)		NOT NULL,
	age					VARCHAR(16)		/*NOT NULL*/,
	race				INTEGER			/*NOT NULL*/,
	labcase				INTEGER			/*NOT NULL*/,
	gender				CHAR(1)			NOT NULL CHECK(gender IN ('M'/*Macho*/, 'H'/*Hembra*/)),
	observations		VARCHAR(255),--No sera text?
	position			INTEGER,
	PRIMARY KEY (id),
	FOREIGN KEY (race) REFERENCES race,
	FOREIGN KEY (labcase) REFERENCES labcase,
	UNIQUE (name, labcase)
);

CREATE SEQUENCE sc_sample_type;
CREATE TABLE sample_type(
	id					INTEGER			NOT NULL,
	description			VARCHAR(255)	NOT NULL,
	PRIMARY KEY (id)
);

CREATE SEQUENCE sc_lab_area;
CREATE TABLE lab_area(
	id					INTEGER			NOT NULL,
	description			VARCHAR(255)	NOT NULL,
	PRIMARY KEY (id)
);

CREATE SEQUENCE sc_assembly_type;
CREATE TABLE assembly_type(
	id					INTEGER			NOT NULL,
	name				TEXT			NOT NULL,
	PRIMARY KEY (id)
);

CREATE SEQUENCE sc_test_profile;
CREATE TABLE test_profile(
	id					INTEGER 		NOT NULL,
	description			VARCHAR(255)	NOT NULL,
	PRIMARY KEY (id),
	UNIQUE (description)
);

CREATE SEQUENCE sc_test_description;
CREATE TABLE test_description(
	id					INTEGER			NOT NULL,
	description			VARCHAR(255)	NOT NULL,--Tambien puede ser text
	lab_area			INTEGER 		NOT NULL,
	sample_type			INTEGER			NOT NULL,
	avg_result_time		INTERVAL,
	we_do_it			BOOLEAN			NOT NULL,
	assembly_type		INTEGER,
	invoice_code		VARCHAR(32),
	ica_acronym			VARCHAR(32),
	save_in_bank		BOOLEAN			NOT NULL DEFAULT false,
	PRIMARY KEY (id),
	FOREIGN KEY (sample_type) REFERENCES sample_type,
	FOREIGN KEY (lab_area) REFERENCES lab_area,
	FOREIGN KEY (assembly_type) REFERENCES assembly_type
);

CREATE TABLE test_description_profile(
	test_profile		INTEGER			NOT NULL,
	test_description	INTEGER			NOT NULL,
	PRIMARY KEY (test_profile, test_description),
	FOREIGN KEY (test_profile) REFERENCES test_profile,
	FOREIGN KEY (test_description) REFERENCES test_description
);

CREATE SEQUENCE sc_test;
CREATE TABLE test(
	id					INTEGER			NOT NULL,
	animal				INTEGER			/*NOT NULL*/,
	test_description	INTEGER			NOT NULL,
	observations		TEXT,
	status				CHAR(1)			NOT NULL CHECK(status IN ('R'/*Registered*/, 'C'/*Cancelled*/)),
	apply_discount		BOOLEAN			DEFAULT FALSE,
	counter_sample		BOOLEAN			NOT NULL DEFAULT FALSE,
	PRIMARY KEY (id),
	FOREIGN KEY (animal) REFERENCES animal,
	FOREIGN KEY (test_description) REFERENCES test_description
);

CREATE SEQUENCE sc_assembly_descriptor;
CREATE TABLE assembly_descriptor(
	id					INTEGER			NOT NULL,
	name				TEXT			NOT NULL,
	col					SMALLINT		NOT NULL,
	row					SMALLINT		NOT NULL,
	assembly_type		INTEGER			NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (assembly_type) REFERENCES assembly_type,
	UNIQUE (assembly_type, row, col)
);

CREATE SEQUENCE sc_assembly;
CREATE TABLE assembly(
	id					INTEGER			NOT NULL,
	assembly_date		DATE			NOT NULL,
	init_row			SMALLINT		NOT NULL DEFAULT 0,
	init_col			SMALLINT		NOT NULL DEFAULT 0,
	assembly_type		INTEGER			NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (assembly_type) REFERENCES assembly_type
);

CREATE SEQUENCE sc_result_factor;
CREATE TABLE result_factor(
	id					INTEGER			NOT NULL,
	unit				VARCHAR(32),
	test_description	INTEGER			NOT NULL,
	name				VARCHAR(255)	NOT NULL,
	_group				VARCHAR(255),
	calculated			BOOLEAN			NOT NULL,
	computed_value		BOOLEAN			NOT NULL,
	number_order		INTEGER			NOT NULL,
	value_type			CHAR(1) 		NOT NULL CHECK(value_type IN 'I'/*Integer*/, 'D'/*Decimal*/, 'T'/*Text*/),
	PRIMARY KEY (id),
	FOREIGN KEY (test_description) REFERENCES test_description
);

CREATE SEQUENCE sc_reference_value;
CREATE TABLE reference_value(
	id					INTEGER			NOT NULL,
	specie				INTEGER			NOT NULL,
	result_factor		INTEGER			NOT NULL,
	ref_value			VARCHAR(64)		NOT NULL,
	description			VARCHAR(128),
	PRIMARY KEY (id),
	FOREIGN KEY (specie) REFERENCES specie,
	FOREIGN KEY (result_factor) REFERENCES result_factor
);

CREATE SEQUENCE sc_result;
CREATE TABLE result(
	id					INTEGER			NOT NULL,
/*	numeric_value		NUMERIC(18,4),*/
	string_value		VARCHAR(255),
	result_factor		INTEGER			NOT NULL,
	test				INTEGER			/*NOT NULL*/,
	assembly_test		INTEGER,
	result_date			DATE			NOT NULL,
	lab_professional	INTEGER,
	PRIMARY KEY (id),
	FOREIGN KEY (result_factor) REFERENCES result_factor,
	FOREIGN KEY (test) REFERENCES test,
	FOREIGN KEY (assembly_test) REFERENCES assembly_test,
	FOREIGN KEY (lab_professional) REFERENCES lab_professional
/*	CHECK ((numeric_value IS NOT NULL AND
			string_value IS NULL) OR
			(numeric_value IS NULL AND
			string_value IS NOT NULL)),*/
);

CREATE SEQUENCE sc_assembly_control;
CREATE TABLE assembly_control(
	id					INTEGER			NOT NULL,
	test_description	INTEGER			NOT NULL,
	assembly_descriptor INTEGER			NOT NULL,
	result				INTEGER,
	PRIMARY KEY (id),
	FOREIGN KEY (test_description) REFERENCES test_description,
	FOREIGN KEY (assembly_descriptor) REFERENCES assembly_descriptor,
	FOREIGN KEY (result) REFERENCES result
);

CREATE SEQUENCE sc_assembly_test;
CREATE TABLE assembly_test(
	id					INTEGER			NOT NULL,
	assembly			INTEGER			/*NOT NULL*/,
	test				INTEGER,
	control				INTEGER,
	row					SMALLINT		NOT NULL,
	col					SMALLINT		NOT NULL,
	plaque				SMALLINT		NOT NULL,
	position			INTEGER,
	PRIMARY KEY (id),
	UNIQUE (assembly, plaque, row, col),
	UNIQUE (test),
	FOREIGN KEY (assembly) REFERENCES assembly,
	FOREIGN KEY (test) REFERENCES test,
	FOREIGN KEY (control) REFERENCES assembly_control,
	CHECK ((test IS NOT NULL AND
			control IS NULL) OR
			(test IS NULL AND
			control IS NOT NULL))
);

CREATE TABLE authorization_codes(
	id					SERIAL			NOT NULL,
	code				TEXT			NOT NULL,
	begin_date			DATE			NOT NULL,
	end_date			DATE			NOT NULL,
	owner				INTEGER			NOT NULL,
	PRIMARY KEY (id),
	UNIQUE(code),
	CHECK (begin_date < end_date),
	FOREIGN KEY (owner) REFERENCES application_owner
);

CREATE SEQUENCE sc_prices_by_test_desc;
CREATE TABLE prices_by_test_desc(
    id					INTEGER			NOT NULL,
    test_description	INTEGER,
    price				DECIMAL(10,2)	NOT NULL,
    tax					DECIMAL(6,2),
    valid_from			DATE			NOT NULL,
    valid_until			DATE			NOT NULL,
    test_profile		INTEGER,
    PRIMARY KEY (id),
    FOREIGN KEY (test_description) REFERENCES test_description,
    FOREIGN KEY (test_profile) REFERENCES test_profile,
    CHECK (valid_from < valid_until)
    CHECK ((test_description IS NOT NULL AND
			test_profile IS NULL) OR
			(test_description IS NULL AND
			test_profile IS NOT NULL))
);

CREATE SEQUENCE sc_bill;
CREATE TABLE bill(
	id					INTEGER			NOT NULL,
	bill_number			INTEGER			NOT NULL,
	bill_date			DATE			NOT NULL,
	client				INTEGER			NOT NULL,
	total_before_taxes	DECIMAL(12,2)	NOT NULL,
	total_after_taxes	DECIMAL(12,2)	NOT NULL,
	status				CHAR(1)			NOT NULL CHECK(status IN ('V'/*Valid*/, 'C'/*Cancelled*/)),
	PRIMARY KEY (id),
	UNIQUE (bill_number),
	FOREIGN KEY (client) REFERENCES enterprise
);

CREATE SEQUENCE sc_bill_detail;
CREATE TABLE bill_detail(
	id					INTEGER			NOT NULL,
	price				DECIMAL(12,2)	NOT NULL,
	tax					DECIMAL(6,2)	NOT NULL,
	test				INTEGER			NOT NULL,
	bill				INTEGER			NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (test) REFERENCES test,
	FOREIGN KEY (bill) REFERENCES bill
);
