create table restaurant_owner (
    restaurant_id bigint not null,
    user_id bigint not null,

    primary key (restaurant_id, user_id)
) engine=InnoDB default charset=utf8;

alter table restaurant_owner add constraint fk_restaurant_owner_restaurant
foreign key (restaurant_id) references restaurant (id);

alter table restaurant_owner add constraint fk_restaurant_owner_owner
foreign key (restaurant_id) references `user` (id);
