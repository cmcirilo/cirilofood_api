CREATE TABLE city (
    id bigint not null auto_increment,
    city_name varchar(80) not null,
    state_name varchar(80) not null,

    primary key (id)
) engine=InnoDB default charset=utf8;

insert into city values (1,'São Paulo', 'São Paulo');
insert into city values (2,'Santos', 'São Paulo');
insert into city values (3,'Rio de Janeiro', 'Rio de Janeiro');
insert into city values (4,'Uberlândia', 'Minas Gerais');
insert into city values (5,'Recife', 'Pernambuco');

