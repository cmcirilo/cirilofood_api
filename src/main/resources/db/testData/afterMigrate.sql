set foreign_key_checks=0;

DELETE FROM city;
DELETE FROM cuisine;
DELETE FROM state;
DELETE FROM form_payment;
DELETE FROM `group`;
DELETE FROM group_permission;
DELETE FROM permission;
DELETE FROM product;
DELETE FROM restaurant;
DELETE FROM restaurant_form_payment;
DELETE FROM `user`;
DELETE FROM user_group;

set foreign_key_checks=1;

ALTER TABLE city auto_increment=1;
ALTER TABLE cuisine auto_increment=1;
ALTER TABLE state auto_increment=1;
ALTER TABLE form_payment auto_increment=1;
ALTER TABLE `group` auto_increment=1;
ALTER TABLE permission auto_increment=1;
ALTER TABLE product auto_increment=1;
ALTER TABLE restaurant auto_increment=1;
ALTER TABLE `user` auto_increment=1;

insert into cuisine (id, name) values (1, 'Thay');
insert into cuisine (id, name) values (2, 'Indian');

insert into state (id, name) values (1, 'Minas Gerais');
insert into state (id, name) values (2, 'São Paulo');
insert into state (id, name) values (3, 'Ceará');

insert into city (id, name, state_id) values (1, 'Uberlândia', 1);
insert into city (id, name, state_id) values (2, 'Belo Horizonte', 1);
insert into city (id, name, state_id) values (3, 'São Paulo', 2);
insert into city (id, name, state_id) values (4, 'Campinas', 2);
insert into city (id, name, state_id) values (5, 'Fortaleza', 3);

insert into restaurant (id, name, shipping_fee, cuisine_id, created_date, updated_date, address_city_id, address_zipcode, address_street, address_number, address_district) values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp,3,  "05374-080", "Rua Ana Aurora Lisboa","66","Jardim Esther Yolanda");
insert into restaurant (id, name, shipping_fee, cuisine_id, created_date, updated_date) values (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp);
insert into restaurant (id, name, shipping_fee, cuisine_id, created_date, updated_date) values (3, 'Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp);

insert into form_payment (id, description) values (1, 'Cartão de crédito');
insert into form_payment (id, description) values (2, 'Cartão de débito');
insert into form_payment (id, description) values (3, 'Dinheiro');

insert into permission (id, name, description) values (1, 'CONSULTAR_CUISINES', 'Permite consultar cuisines');
insert into permission (id, name, description) values (2, 'EDITAR_CUISINES', 'Permite editar cuisines');

insert into restaurant_form_payment (restaurant_id, form_payment_id) values (1,1),(1,2),(1,3),(2,3),(3,2),(3,3);

insert into product (id, name, description, price, active , restaurant_id) values (1, "Arroz","Arroz description", 10.0, true, 1);
insert into product (id, name, description, price, active , restaurant_id) values (2, "Feijão","Feijão description", 15.0, true, 1);
insert into product (id, name, description, price, active , restaurant_id) values (3, "Carne","Carne description", 20.0, true,2);