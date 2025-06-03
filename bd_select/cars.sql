create table car_bodies (
    id serial primary key,
    name text
);

create table car_engines (
    id serial primary key,
    name text
);

create table car_transmissions (
    id serial primary key,
    name text
);

create table cars (
    id serial primary key,
    name text,
	body_id int references car_bodies(id),
	engine_id int references car_engines(id),
	transmission_id int references car_transmissions(id)
);

INSERT INTO car_bodies (name) VALUES
('Седан'),
('Хэтчбек'),
('Универсал'),
('Купе'),
('Внедорожник'),
('Минивэн'),
('Пикап');

INSERT INTO car_engines (name) VALUES
('1.6L Бензиновый'),
('2.0L Турбо бензин'),
('1.5L Дизель'),
('3.0L V6 Дизель'),
('Электродвигатель 150кВт'),
('Гибридный 2.0L + электромотор'),
('2.5L Турбо дизель');

INSERT INTO car_transmissions (name) VALUES
('Механическая 5 ст.'),
('Автоматическая 6 ст.'),
('Роботизированная DSG'),
('Автоматическая 8 ст.'),
('Бесступенчатый вариатор (CVT)'),
('Автоматическая 7 ст.'),
('Электронная автоматическая');

INSERT INTO cars (name, body_id, engine_id, transmission_id) VALUES
('Toyota Corolla', 1, 1, 2),
('Ford Focus', 2, 2, 3),
('Volkswagen Passat', 1, 4, 2),
('BMW 3 Series', 1, 2, 6),
('Mazda CX-5', 5, 2, 5),
('Mercedes-Benz GLC', 5, 2, 6),
('Renault Logan', 1, 1, 1),
('Hyundai Tucson', 5, 2, 4),
('Kia Rio', 1, 1, 2),
('Volvo XC90', 5, 4, 4),
('Audi A4', 1, 2, 6),
('Skoda Octavia', 2, 2, 3),
('Lada Granta', 1, 1, 1);


select c.id as id, c.name as car_name, cb.name as body_name, ce.name as engine_name, ct.name as transmission_name 
from cars c
join car_bodies cb on c.body_id = cb.id
join car_engines ce on c.engine_id = ce.id
join car_transmissions ct on c.transmission_id = ct.id;

select cb.id, cb.name from car_bodies cb 
left join cars c  on c.body_id = cb.id 
where c.id is null; 

select ce.id, ce.name from car_engines ce 
left join cars c  on c.body_id = ce.id 
where c.id is null; 

select ct.id, ct.name from car_transmissions ct 
left join cars c  on c.body_id = ct.id 
where c.id is null; 




















