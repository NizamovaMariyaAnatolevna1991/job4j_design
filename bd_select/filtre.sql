create table type
(
    id  serial primary key,
    name  varchar(255)
);

create table product
(
    id    serial primary key,
    name  varchar(255),
    type_id int references type (id),
	expired_date date,
	price int
);

insert into type (name) values
('Молочный'),
('Хлебобулочный'),
('Фрукты'),
('Напитки'),
('Кондитерские изделия'),
('Мясные продукты'),
('Сыр')
;

insert into product (name, type_id, expired_date, price) values
('Молоко', 1, '2024-11-15', 85),
('Хлеб Бородинский', 2, '2024-10-20', 45),
('Яблоки Гала', 3, '2024-12-01', 95),
('Сок апельсиновый', 4, '2025-03-10', 75),
('Торт Шоколадный', 5, '2024-10-25', 450),
('Колбаса Докторская', 6, '2024-11-05', 210),
('Йогурт Клубничный', 1, '2024-11-10', 65),
('Батон "Нарезной"', 2, '2024-10-18', 40),
('Груши', 3, '2024-11-20', 110),
('Чай чёрный', 4, '2026-01-01', 120),
('Пирожное Эклер', 5, '2024-10-22', 80),
('Фарш говяжий', 6, '2024-11-02', 300),
('Cыр плавленый', 7, '2025-06-02', 300),
('Черинчное мороженое', 1, '2025-05-27', 450)
;

select * from product p 
join type t on p.type_id = t.id
where t.name = 'Сыр';

select * from product p 
join type t on p.type_id = t.id
where p.name like '%мороженое%';

select * from product p 
join type t on p.type_id = t.id
where expired_date < current_date ;

select p.name, p.price from product p 
where price = (select max(price) from product);

select t.name, count(*) from type t
join product p on p.type_id = t.id
group by t.name;

select * from product p 
join type t on p.type_id = t.id
where t.name in ('Сыр','Молочный');


select t.name, count(*) from type t
join product p on p.type_id = t.id
group by t.name 
having count(*) < 10;

select p.name, t.name from type t
join product p on p.type_id = t.id;





























