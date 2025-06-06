CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

insert into customers (first_name, last_name,age, country) values
('Мария', 'Низамова', 18, 'Уфа'),
('Ник', 'Старцев', 25, 'Уфа'),
('Тимур', 'Низамов', 18, 'Уфа'),
('Марскель', 'Юмагулов', 23, 'Кимры');


select * 
from customers
where age = (select min(age) from customers);

CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

insert into orders (amount, customer_id) values
(500, 1),
(500,1),
(600,2),
(300,2);

select * 
from customers c
where c.id not in (select customer_id from orders);

