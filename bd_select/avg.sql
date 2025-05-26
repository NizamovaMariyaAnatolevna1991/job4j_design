create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people
(
    id   serial primary key,
    name varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

INSERT INTO people (name) VALUES
('Иван Иванов'),
('Мария Петрова'),
('Алексей Смирнов'),
('Екатерина Новикова'),
('Дмитрий Кузнецов');

INSERT INTO devices (name, price) VALUES
('iPhone 15 Pro', 999.99),
('Samsung Galaxy S24', 899.99),
('MacBook Air M2', 1199.00),
('Dell XPS 13', 1299.00),
('Apple Watch Ultra', 799.00),
('Sony WH-1000XM5', 399.00),
('iPad Pro', 799.00);

INSERT INTO devices_people (device_id, people_id) VALUES
(1, 1), -- iPhone у Ивана
(2, 1), -- Galaxy у Ивана
(3, 2), -- MacBook у Марии
(4, 3), -- Dell у Алексея
(5, 4), -- Apple Watch у Екатерины
(6, 5), -- Наушники у Дмитрия
(7, 2), -- iPad у Марии
(1, 3), -- iPhone у Алексея
(3, 4); -- MacBook у Екатерины


select d.name, avg(d.price) 
from devices_people as dp
join devices d
on d.id = dp.device_id
group by d.name;

select p.name, avg(d.price) 
from devices_people as dp
join devices d
on d.id = dp.device_id
join people p 
on d.id = dp.people_id
group by d.name, p.name
having avg(d.price) > 500;
;









