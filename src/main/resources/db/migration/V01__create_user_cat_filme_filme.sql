	CREATE TABLE user (
		id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
		nome VARCHAR(50) NOT NULL,
		cpf VARCHAR(11) NOT NULL UNIQUE,
		email VARCHAR(50) NOT NULL UNIQUE,
		senha VARCHAR(255) NOT NULL
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
	INSERT INTO user (nome, cpf, email, senha) values ("admin", "0000000000", "admin@admin.com", "$2a$10$Zo9hXcAp.03XFrXPElpb1ODtZhbl7EjIGYyOfYASh/1Ub4sQ/44O2");  
	-- @Admin123 
	INSERT INTO user (nome, cpf, email, senha) values ("atendente", "0000000001", "atend@atend.com", "$2a$10$C/xFYgp7My1KkXuKXU.Ueeyay/Gm4/.YAK/sf2BdXNJlNoqKziWfq");  
	-- @Atend123  
	
	CREATE TABLE cat_filme (
		id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
		descricao VARCHAR(50) NOT NULL UNIQUE
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
	CREATE TABLE filme (
		id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
		nome VARCHAR(50) NOT NULL,
		sinopse VARCHAR(255),
		link_capa VARCHAR(50),
		link_trailer VARCHAR(50),
		cat_filme BIGINT(20) NOT NULL,
		FOREIGN KEY (cat_filme) REFERENCES cat_filme(id)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;