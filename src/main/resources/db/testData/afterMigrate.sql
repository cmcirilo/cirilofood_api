set foreign_key_checks=0;

DELETE FROM cidade;
DELETE FROM cozinha;
DELETE FROM estado;
DELETE FROM forma_pagamento;
DELETE FROM grupo;
DELETE FROM grupo_permissao;
DELETE FROM permissao;
DELETE FROM produto;
DELETE FROM restaurante;
DELETE FROM restaurante_forma_pagamento;
DELETE FROM usuario;
DELETE FROM usuario_grupo;

set foreign_key_checks=1;

ALTER TABLE cidade auto_increment=1;
ALTER TABLE cozinha auto_increment=1;
ALTER TABLE estado auto_increment=1;
ALTER TABLE forma_pagamento auto_increment=1;
ALTER TABLE grupo auto_increment=1;
ALTER TABLE permissao auto_increment=1;
ALTER TABLE produto auto_increment=1;
ALTER TABLE restaurante auto_increment=1;
ALTER TABLE usuario auto_increment=1;

insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');

insert into estado (id, nome) values (1, 'Minas Gerais');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Ceará');

insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3);

insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp,3,  "05374-080", "Rua Ana Aurora Lisboa","66","Jardim Esther Yolanda");
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (3, 'Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp);

insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1,1),(1,2),(1,3),(2,3),(3,2),(3,3);

insert into produto (id, nome, descricao, preco, ativo , restaurante_id) values (1, "Arroz","Arroz Descricao", 10.0, true, 1);
insert into produto (id, nome, descricao, preco, ativo , restaurante_id) values (2, "Feijão","Feijão Descricao", 15.0, true, 1);
insert into produto (id, nome, descricao, preco, ativo , restaurante_id) values (3, "Carne","Carne Descricao", 20.0, true,2);