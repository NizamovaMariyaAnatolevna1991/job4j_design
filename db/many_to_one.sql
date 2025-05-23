create table clients(
    id serial primary key,
    name varchar(255)
);

create table orders(
    id serial primary key,
    name varchar(255),
    clients_id int references clients(id)
);



