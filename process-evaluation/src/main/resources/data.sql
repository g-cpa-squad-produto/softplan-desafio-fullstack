INSERT INTO user (name, login, password, role) 
	VALUES ('Administrador', 'admin', '$2a$10$lSlx2fWgP0DZ1wNnEmbVfemYBWE.ds18MDoU68Do9o4K9IXe0xeou', 'ROLE_ADMIN');

INSERT INTO user (name, login, password, role) 
	VALUES ('Triador', 'triador', '$2a$10$P4PSl7AcoHQSm2b7diiZNeh2V0WusSVtPVmkIb2sD8oERWuUF.OKu', 'ROLE_SCREENING');

INSERT INTO user (name, login, password, role) 
	VALUES ('Finalizador', 'finalizador', '$2a$10$VEFJt1Da3x/3GxG1q2/efODD2NnMU9pQA9zZ7E/Eb9OegwxNZCTJq', 'ROLE_CLOSER');