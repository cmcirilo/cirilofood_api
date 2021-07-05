create table form_payment (
	id bigint not null auto_increment,
	description varchar(60) not null,
	primary key (id)
) engine=InnoDB default charset=utf8;

create table grupo (
	id bigint not null auto_increment,
	nome varchar(60) not null,

	primary key (id)
) engine=InnoDB default charset=utf8;

create table grupo_permissao (
	grupo_id bigint not null,
	permissao_id bigint not null,

	primary key (grupo_id, permissao_id)
) engine=InnoDB default charset=utf8;

create table permissao (
	id bigint not null auto_increment,
	descricao varchar(60) not null,
	nome varchar(100) not null,

	primary key (id)
) engine=InnoDB default charset=utf8;

create table produto (
	id bigint not null auto_increment,
	restaurant_id bigint not null,
	nome varchar(80) not null,
	descricao text not null,
	preco decimal(10,2) not null,
	ativo tinyint(1) not null,

	primary key (id)
) engine=InnoDB default charset=utf8;

create table restaurant (
	id bigint not null auto_increment,
	cuisine_id bigint not null,
	name varchar(80) not null,
	shipping_fee decimal(10,2) not null,
	created_date datetime not null,
	updated_date datetime not null,

	address_city_id bigint,
	address_zipcode varchar(9),
	address_street varchar(100),
	address_number varchar(20),
	address_complement varchar(60),
	address_district varchar(60),

	primary key (id)
) engine=InnoDB default charset=utf8;

create table restaurant_form_payment (
	restaurant_id bigint not null,
	form_payment_id bigint not null,

	primary key (restaurant_id, form_payment_id)
) engine=InnoDB default charset=utf8;

create table usuario (
	id bigint not null auto_increment,
	nome varchar(80) not null,
	email varchar(255) not null,
	senha varchar(255) not null,
	data_cadastro datetime not null,

	primary key (id)
) engine=InnoDB default charset=utf8;

create table usuario_grupo (
	usuario_id bigint not null,
	grupo_id bigint not null,

	primary key (usuario_id, grupo_id)
) engine=InnoDB default charset=utf8;




alter table grupo_permissao add constraint fk_grupo_permissao_permissao
foreign key (permissao_id) references permissao (id);

alter table grupo_permissao add constraint fk_grupo_permissao_grupo
foreign key (grupo_id) references grupo (id);

alter table produto add constraint fk_produto_restaurant
foreign key (restaurant_id) references restaurant (id);

alter table restaurant add constraint fk_restaurant_cuisine
foreign key (cuisine_id) references cuisine (id);

alter table restaurant add constraint fk_restaurant_city
foreign key (address_city_id) references city (id);

alter table restaurant_form_payment add constraint fk_rest_forma_pagto_forma_pagto
foreign key (form_payment_id) references form_payment (id);

alter table restaurant_form_payment add constraint fk_rest_forma_pagto_restaurant
foreign key (restaurant_id) references restaurant (id);

alter table usuario_grupo add constraint fk_usuario_grupo_grupo
foreign key (grupo_id) references grupo (id);

alter table usuario_grupo add constraint fk_usuario_grupo_usuario
foreign key (usuario_id) references usuario (id);