	ALTER TABLE user
		ADD id_position BIGINT(20) NOT NULL,
		ADD FOREIGN KEY (id_position) REFERENCES position_user(id);
		
	INSERT INTO user (nome, cpf, email, senha, id_position) values ("admin", "0000000000", "admin@admin.com", "$2a$10$Zo9hXcAp.03XFrXPElpb1ODtZhbl7EjIGYyOfYASh/1Ub4sQ/44O2", 1);  
	-- @Admin123 
	INSERT INTO user (nome, cpf, email, senha, id_position) values ("atendente", "0000000001", "atend@atend.com", "$2a$10$C/xFYgp7My1KkXuKXU.Ueeyay/Gm4/.YAK/sf2BdXNJlNoqKziWfq", 2);  
	-- @Atend123  