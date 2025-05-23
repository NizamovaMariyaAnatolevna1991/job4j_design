create table contacts(
    phone int primary key
);

create table employees(
    id serial primary key,
    name varchar(255),
    phone int references contacts(phone) unique
);


