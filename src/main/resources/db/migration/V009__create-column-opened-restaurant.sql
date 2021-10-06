alter table restaurant add opened tinyint(1) not null;
update restaurant set opened = false;