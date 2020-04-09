--CREATE TABLE user_role (
--	id BIGINT(20) AUTO_INCREMENT,
--	role VARCHAR(255),
--	PRIMARY KEY (id)
--);

CREATE TABLE user (
	id BIGINT(20) AUTO_INCREMENT,
	username VARCHAR(255),
	name VARCHAR(255),
	role INT(11),
	PRIMARY KEY (id),
	FOREIGN KEY (role) REFERENCES user_role (id)
);

