alter table form_payment add updated_date datetime null;
update form_payment set updated_date= utc_timestamp;
alter table form_payment modify updated_date datetime not null;
