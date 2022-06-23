	CREATE TABLE user (
		id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
		nome VARCHAR(50) NOT NULL,
		cpf VARCHAR(11) NOT NULL UNIQUE,
		email VARCHAR(50) NOT NULL UNIQUE,
		senha VARCHAR(255) NOT NULL
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
	CREATE TABLE cat_filme (
		id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
		descricao VARCHAR(50) NOT NULL UNIQUE
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
	CREATE TABLE filme (
		id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
		nome VARCHAR(50) NOT NULL,
		sinopse text,
		cat_filme BIGINT(20) NOT NULL,
		FOREIGN KEY (cat_filme) REFERENCES cat_filme(id)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
	INSERT INTO cat_filme (descricao) values ("Ação");
	INSERT INTO cat_filme (descricao) values ("Aventura");
	INSERT INTO cat_filme (descricao) values ("Biográfico");
	INSERT INTO cat_filme (descricao) values ("Comédia");
	INSERT INTO cat_filme (descricao) values ("Dança");
	INSERT INTO cat_filme (descricao) values ("Desenho");
	INSERT INTO cat_filme (descricao) values ("Drama");
	INSERT INTO cat_filme (descricao) values ("Faroeste");
	INSERT INTO cat_filme (descricao) values ("Histórico");