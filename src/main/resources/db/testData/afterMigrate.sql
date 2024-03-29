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
DELETE FROM product_photo;

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

insert into form_payment (id, description, updated_date) values (1, 'Credit', utc_timestamp);
insert into form_payment (id, description, updated_date) values (2, 'Debit', utc_timestamp);
insert into form_payment (id, description, updated_date) values (3, 'Cash', utc_timestamp);

insert into permission (id, name, description) values (1, 'LIST_CUISINES', 'Allow list cuisines');
insert into permission (id, name, description) values (2, 'UPDATE_CUISINES', 'Allow update cuisines');

insert into restaurant_form_payment (restaurant_id, form_payment_id) values (1,1),(1,2),(1,3),(2,3),(3,2),(3,3);

insert into product (id, name, description, price, active , restaurant_id) values (1, "Rice","Rice description", 10.0, true, 1);
insert into product (id, name, description, price, active , restaurant_id) values (2, "Bean","Bean description", 15.0, false, 1);
insert into product (id, name, description, price, active , restaurant_id) values (3, "Meat","Meat description", 20.0, true,2);

insert into `group` (id, name) values (1,'Manager'),(2,'Salesman'), (3,'Secretary'), (4,'Delivery Man');

insert into group_permission (group_id, permission_id) values (1, 1), (1, 2), (2, 1), (2, 2), (3, 1);

insert into `user` (id, name, email, password, created_date) values
(1, 'Carlos Cirilo', 'carlos.cirilo@cirilofood.com', '123', utc_timestamp),
(2, 'Kathia Cirilo', 'kathia.cirilo@cirilofood.com', '123', utc_timestamp),
(3, 'Bernardo Cirilo', 'bernardo.cirilo@cirilofood.com', '123', utc_timestamp),
(4, 'Cirila Cirilo', 'cmcirilo@gmail.com', '123', utc_timestamp);

insert into user_group (user_id, group_id) values (1, 1), (1, 2), (2, 2);

delete from restaurant_owner;

insert into `user` (id, name, email, password, created_date) values (5, 'Manoel Lima', 'manoel.loja@gmail.com', '123', utc_timestamp);

insert into restaurant_owner (restaurant_id, user_id) values (1, 5), (3, 5);

delete from order_item;
delete from `order`;

insert into `order` (id, code, restaurant_id, user_client_id, form_payment_id, address_city_id, address_zipcode, address_street, address_number, address_complement, address_district, status, created_date, subtotal, shipping_fee, total_value) values (1, '61297945-cf33-4c3c-8aef-f0d50a08d3c2', 1, 4, 1, 1, '38400-000', 'Rua Floriano Peixoto', '500', 'Apto 801', 'Brasil','CREATED', utc_timestamp, 298.90, 10, 308.90);
insert into order_item (id, order_id, product_id, quantity, unit_price, total_price, observation) values (1, 1, 1, 1, 78.9, 78.9, null);
insert into order_item (id, order_id, product_id, quantity, unit_price, total_price, observation) values (2, 1, 2, 2, 110, 220, 'Menos picante, por favor');

insert into `order` (id, code, restaurant_id, user_client_id, form_payment_id, address_city_id, address_zipcode, address_street, address_number, address_complement, address_district, status, created_date, subtotal, shipping_fee, total_value) values (2, '15449880-73cc-456e-b536-46e4c17170cc', 3, 4, 2, 1, '38400-111', 'Rua Acre', '300', 'Casa 2', 'Centro', 'CREATED', utc_timestamp, 79, 0, 79);
insert into order_item (id, order_id, product_id, quantity, unit_price, total_price, observation) values (3, 2, 3, 1, 79, 79, 'Ao ponto');

insert into `order` (id, code, restaurant_id, user_client_id, form_payment_id, address_city_id, address_zipcode, address_street, address_number, address_complement, address_district, status, created_date, subtotal, shipping_fee, total_value) values (3, 'b5741512-8fbc-47fa-9ac1-b530354fc0ff', 1, 4, 1, 1, '38400-222', 'Rua Natal', '200', null, 'Brasil','CREATED', '2019-10-30 21:10:00', 110, 10, 120);
insert into order_item (id, order_id, product_id, quantity, unit_price, total_price, observation) values (4, 3, 2, 1, 110, 110, null);

insert into `order` (id, code, restaurant_id, user_client_id, form_payment_id, address_city_id, address_zipcode, address_street, address_number, address_complement, address_district, status, created_date, subtotal, shipping_fee, total_value) values (4, '5c621c9a-ba61-4454-8631-8aabefe58dc2', 1, 2, 1, 1, '38400-800', 'Rua Fortaleza', '900', 'Apto 504', 'Centro','DELIVERED', '2019-11-02 20:34:04', 174.4, 5, 179.4);
insert into order_item (id, order_id, product_id, quantity, unit_price, total_price, observation) values (5, 4, 3, 2, 87.2, 174.4, null);

insert into `order` (id, code, restaurant_id, user_client_id, form_payment_id, address_city_id, address_zipcode, address_street, address_number, address_complement, address_district, status, created_date, subtotal, shipping_fee, total_value) values (5, '8d774bcf-b238-42f3-aef1-5fb388754d63', 1, 3, 2, 1, '38400-200', 'Rua 10', '930', 'Casa 20', 'Martins','DELIVERED', '2019-11-03 02:00:30', 87.2, 10, 97.2);
insert into order_item (id, order_id, product_id, quantity, unit_price, total_price, observation) values (6, 5, 3, 1, 87.2, 87.2, null);

alter table `order` auto_increment = 1;
alter table order_item auto_increment = 1;

