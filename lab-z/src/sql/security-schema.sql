CREATE TABLE users(
	user_name			VARCHAR(64)		NOT NULL,
	user_pass			VARCHAR(64)		NOT NULL,
	active				BOOLEAN			NOT NULL,
	PRIMARY KEY (user_name)
);

CREATE TABLE roles(
	role_name			VARCHAR(64)		NOT NULL,
	role_description	VARCHAR(256)	NOT NULL,
	PRIMARY KEY (role_name)
);

CREATE TABLE user_roles(
	user_name		VARCHAR(64)		NOT NULL,
	role_name		VARCHAR(64)		NOT NULL,
	PRIMARY KEY (user_name, role_name),
	FOREIGN KEY (role_name) REFERENCES roles,
	FOREIGN KEY (user_name) REFERENCES users
);

CREATE TABLE sections(
	id serial NOT NULL,
	name varchar(255) NOT NULL,
	url varchar(255),
	order_number int NOT NULL,
	parent_section int,
	PRIMARY KEY (id)
);

CREATE TABLE sections_roles(
	section integer NOT NULL,
	role VARCHAR(64) NOT NULL,
	FOREIGN KEY (section) REFERENCES sections,
	FOREIGN KEY (role) REFERENCES roles,
	UNIQUE (section, role)
);