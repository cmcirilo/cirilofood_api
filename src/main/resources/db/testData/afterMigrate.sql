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
insert into cuisine (id, name) values (3, 'Chinese');
insert into cuisine (id, name) values (4, 'Brazilian');

insert into state (id, name) values (1, 'Minas Gerais');
insert into state (id, name) values (2, 'São Paulo');
insert into state (id, name) values (3, 'Ceará');

insert into city (id, name, state_id) values (1, 'Uberlândia', 1);
insert into city (id, name, state_id) values (2, 'Belo Horizonte', 1);
insert into city (id, name, state_id) values (3, 'São Paulo', 2);
insert into city (id, name, state_id) values (4, 'Campinas', 2);
insert into city (id, name, state_id) values (5, 'Fortaleza', 3);

insert into restaurant (id, name, shipping_fee, cuisine_id, created_date, updated_date, active, opened, address_city_id, address_zipcode, address_street, address_number, address_district) values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, true, false, 3,  "05374-080", "Rua Ana Aurora Lisboa","66","Jardim Esther Yolanda");
insert into restaurant (id, name, shipping_fee, cuisine_id, created_date, updated_date, active, opened) values (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp, true, false);
insert into restaurant (id, name, shipping_fee, cuisine_id, created_date, updated_date, active, opened) values (3, 'Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp, true, false);

insert into form_payment (id, description) values (1, 'Credit');
insert into form_payment (id, description) values (2, 'Debit');
insert into form_payment (id, description) values (3, 'Cash');

insert into permission (id, name, description) values (1, 'LIST_CUISINES', 'Allow list cuisines');
insert into permission (id, name, description) values (2, 'UPDATE_CUISINES', 'Allow update cuisines');

insert into restaurant_form_payment (restaurant_id, form_payment_id) values (1,1),(1,2),(1,3),(2,3),(3,2),(3,3);

insert into product (id, name, description, price, active , restaurant_id) values (1, "Rice","Rice description", 10.0, true, 1);
insert into product (id, name, description, price, active , restaurant_id) values (2, "Bean","Bean description", 15.0, true, 1);
insert into product (id, name, description, price, active , restaurant_id) values (3, "Meat","Meat description", 20.0, true,2);

insert into `group` (id, name) values (1,'Manager'),(2,'Salesman'), (3,'Secretary'), (4,'Delivery Man');

insert into group_permission (group_id, permission_id) values (1, 1), (1, 2), (2, 1), (2, 2), (3, 1);

insert into `user` (id, name, email, password, created_date) values
(1, 'Carlos Cirilo', 'carlos.cirilo@cirilofood.com', '123', utc_timestamp),
(2, 'Kathia Cirilo', 'kathia.cirilo@cirilofood.com', '123', utc_timestamp),
(3, 'Bernardo Cirilo', 'bernardo.cirilo@cirilofood.com', '123', utc_timestamp);