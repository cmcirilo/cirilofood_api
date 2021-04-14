CREATE TABLE cidade (
    id bigint not null auto_increment,
    nome_cidade varchar(80) not null,
    nome_estado varchar(80) not null,

    primary key (id)
) engine=InnoDB default charset=utf8;

insert into cidade values (1,'São Paulo', 'São Paulo');
insert into cidade values (2,'Santos', 'São Paulo');
insert into cidade values (3,'Rio de Janeiro', 'Rio de Janeiro');
insert into cidade values (4,'Uberlândia', 'Minas Gerais');
insert into cidade values (5,'Recife', 'Pernambuco');

