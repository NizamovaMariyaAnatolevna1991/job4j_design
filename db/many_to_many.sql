create table employees(
    id serial primary key,
    name varchar(255)
);

create table projects(
    id serial primary key,
    name varchar(255)
);

create table employees_projects(
    id serial primary key,
    employee_id int references employees(id),
    project_id int references projects(id)
);