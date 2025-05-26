create table departments
(
    id  serial primary key,
    name  varchar(255)
);

create table employees
(
    id    serial primary key,
    name  varchar(255),
    department_id int references departments (id)
);

insert into departments (name) values
('IT-отдел'),
('Отдел продаж'),
('Бухгалтерия'),
('HR-отдел'),
('Склад');

insert into employees (name, department_id) values
('Иван Иванов', 1),
('Мария Петрова', 1),
('Алексей Смирнов', 2),
('Екатерина Новикова', 2),
('Дмитрий Кузнецов', 3),
('Ольга Сергеева', 4),
('Андрей Воробьев', 5),
('Татьяна Лебедева', 1),
('Николай Попов', 2),
('Наталья Соколова', 3);

delete from employees where name = 'Ольга Сергеева'

select d.name, e.name from departments d
left join employees e on d.id = e.department_id
where e.name is null;


select d.name, e.name from departments d
left join employees e on d.id = e.department_id;

select d.name, e.name from employees e
right join departments d on d.id = e.department_id;

select * from employees e
         cross join departments d;

create table teens
(
    name  varchar(255),
	gender varchar(255)
);	

insert into teens(name, gender)
values ('Вася','м');
insert into teens(name,gender)
values ('Маша', 'ж');
insert into teens(name,gender)
values ('Наташа', 'ж');

select t1.name as m, t2.name as w from teens t1
         cross join teens t2 
		 where t1.gender = 'м' and t2.gender = 'ж';



































