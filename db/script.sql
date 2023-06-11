create table cars(
id serial primary key,
	model varchar(200),
	year date,
	price numeric
);
insert into cars(model, year, price) values('audi A4', '10-11-2020', 2500000);
update cars set price = 3000000;
delete from cars;