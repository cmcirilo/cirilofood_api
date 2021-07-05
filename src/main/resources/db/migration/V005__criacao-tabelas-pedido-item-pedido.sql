create table pedido (
  id bigint not null auto_increment,
  subtotal decimal(10,2) not null,
  shipping_fee decimal(10,2) not null,
  valor_total decimal(10,2) not null,

  restaurant_id bigint not null,
  usuario_cliente_id bigint not null,
  form_payment_id bigint not null,

  address_city_id bigint(20) not null,
  address_zipcode varchar(9) not null,
  address_street varchar(100) not null,
  address_number varchar(20) not null,
  address_complement varchar(60) null,
  address_district varchar(60) not null,

  status varchar(10) not null,
  created_date datetime not null,
  confirmation_date datetime null,
  cancel_date datetime null,
  delivery_date datetime null,

  primary key (id),

  constraint fk_pedido_restaurant foreign key (restaurant_id) references restaurant (id),
  constraint fk_pedido_usuario_cliente foreign key (usuario_cliente_id) references usuario (id),
  constraint fk_pedido_form_payment foreign key (form_payment_id) references form_payment (id)
) engine=InnoDB default charset=utf8;

create table item_pedido (
  id bigint not null auto_increment,
  quantidade smallint(6) not null,
  preco_unitario decimal(10,2) not null,
  preco_total decimal(10,2) not null,
  observacao varchar(255) null,
  pedido_id bigint not null,
  produto_id bigint not null,

  primary key (id),
  unique key uk_item_pedido_produto (pedido_id, produto_id),

  constraint fk_item_pedido_pedido foreign key (pedido_id) references pedido (id),
  constraint fk_item_pedido_produto foreign key (produto_id) references produto (id)
) engine=InnoDB default charset=utf8;
