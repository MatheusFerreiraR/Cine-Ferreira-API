CREATE TABLE rules (
	id BIGINT(20) PRIMARY KEY,
	description VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO rules (id, description) values (1, "RULE_CADASTRAR_FILME");
INSERT INTO rules (id, description) values (2, "RULE_PESQUISAR_FILME");

INSERT INTO rules (id, description) values (3, "RULE_CADASTRAR_CAT_FILME");
INSERT INTO rules (id, description) values (4, "RULE_PESQUISAR_CAT_FILME");

INSERT INTO rules (id, description) values (5, "RULE_CADASTRAR_SESSAO");
INSERT INTO rules (id, description) values (6, "RULE_PESQUISAR_SESSAO");

INSERT INTO rules (id, description) values (7, "RULE_CADASTRAR_SALA");
INSERT INTO rules (id, description) values (8, "RULE_PESQUISAR_SALA");

INSERT INTO rules (id, description) values (9, "RULE_CADASTRAR_RESERVA");
INSERT INTO rules (id, description) values (10, "RULE_PESQUISAR_RESERVA");

INSERT INTO rules (id, description) values (11, "RULE_CADASTRAR_INGRESSO");
INSERT INTO rules (id, description) values (12, "RULE_PESQUISAR_INGRESSO");

INSERT INTO rules (id, description) values (13, "RULE_CADASTRAR_CAT_INGRESSO");
INSERT INTO rules (id, description) values (14, "RULE_PESQUISAR_CAT_INGRESSO");

INSERT INTO rules (id, description) values (15, "RULE_CADASTRAR_USUARIO");
INSERT INTO rules (id, description) values (16, "RULE_PESQUISAR_USUARIO");

CREATE TABLE position_user (
	id BIGINT(20) PRIMARY KEY,
	description VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO position_user (id, description) values (1, "Gerente");
INSERT INTO position_user (id, description) values (2, "Atendente");

CREATE TABLE position_user_rules (
	id_position BIGINT(20) NOT NULL,
	id_rules BIGINT(20) NOT NULL,
	PRIMARY KEY (id_position, id_rules),
	FOREIGN KEY (id_position) REFERENCES position_user(id),
	FOREIGN KEY (id_rules) REFERENCES rules(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Admin
INSERT INTO position_user_rules (id_position, id_rules) values (1, 1);
INSERT INTO position_user_rules (id_position, id_rules) values (1, 2);
INSERT INTO position_user_rules (id_position, id_rules) values (1, 3);
INSERT INTO position_user_rules (id_position, id_rules) values (1, 4);
INSERT INTO position_user_rules (id_position, id_rules) values (1, 5);
INSERT INTO position_user_rules (id_position, id_rules) values (1, 6);
INSERT INTO position_user_rules (id_position, id_rules) values (1, 7);
INSERT INTO position_user_rules (id_position, id_rules) values (1, 8);
INSERT INTO position_user_rules (id_position, id_rules) values (1, 9);
INSERT INTO position_user_rules (id_position, id_rules) values (1, 10);
INSERT INTO position_user_rules (id_position, id_rules) values (1, 11);
INSERT INTO position_user_rules (id_position, id_rules) values (1, 12);
INSERT INTO position_user_rules (id_position, id_rules) values (1, 13);
INSERT INTO position_user_rules (id_position, id_rules) values (1, 14);
INSERT INTO position_user_rules (id_position, id_rules) values (1, 15);
INSERT INTO position_user_rules (id_position, id_rules) values (1, 16);
-- Atendente
INSERT INTO position_user_rules (id_position, id_rules) values (2, 2);
INSERT INTO position_user_rules (id_position, id_rules) values (2, 4);
INSERT INTO position_user_rules (id_position, id_rules) values (2, 6);
INSERT INTO position_user_rules (id_position, id_rules) values (2, 8);
INSERT INTO position_user_rules (id_position, id_rules) values (2, 10);
INSERT INTO position_user_rules (id_position, id_rules) values (2, 12);
INSERT INTO position_user_rules (id_position, id_rules) values (2, 14);
INSERT INTO position_user_rules (id_position, id_rules) values (2, 16);