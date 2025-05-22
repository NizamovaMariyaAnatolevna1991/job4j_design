create table family (
 id serial primary key,
 role varchar(20),
 name text,
 age integer
);

insert into family(role, name, age) values ('мама', 'Маша', 34);
insert into family(role, name, age) values ('папа', 'Тимур', 35);
insert into family(role, name, age) values ('сын', 'Гриша', 6);

update family set age = 7 where name = 'Гриша';
delete from family;