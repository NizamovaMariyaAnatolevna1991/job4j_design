
postgres=*# update products set count = 26 where name = 'product_1';
UPDATE 1
postgres=*# commit;
COMMIT
postgres=# begin transaction;
BEGIN
postgres=*# savepoint point1;
SAVEPOINT
postgres=*# release savepoint point1;
RELEASE
postgres=*# select * from products;
 id |   name    |  producer  | count | price
----+-----------+------------+-------+-------
  2 | product_2 | producer_2 |    15 |    32
  4 | product_4 |            |    11 |    64
  1 | product_1 | producer_1 |    26 |    75
(3 ёЄЁюъш)


postgres=*# delete from products where id = 2;
DELETE 1
postgres=*# select * from products;
 id |   name    |  producer  | count | price
----+-----------+------------+-------+-------
  4 | product_4 |            |    11 |    64
  1 | product_1 | producer_1 |    26 |    75
(2 ёЄЁюъш)


postgres=*# savepoint point2;
SAVEPOINT
postgres=*# update products set price = 85 where id = 1;
UPDATE 1
postgres=*# select * from products;
 id |   name    |  producer  | count | price
----+-----------+------------+-------+-------
  4 | product_4 |            |    11 |    64
  1 | product_1 | producer_1 |    26 |    85
(2 ёЄЁюъш)


postgres=*# rollback to savepoint point2;
ROLLBACK
postgres=*# select * from products;
 id |   name    |  producer  | count | price
----+-----------+------------+-------+-------
  4 | product_4 |            |    11 |    64
  1 | product_1 | producer_1 |    26 |    75
(2 ёЄЁюъш)


postgres=*# rollback to savepoint point 1;
ОШИБКА:  ошибка синтаксиса (примерное положение: "1")
СТРОКА 1: rollback to savepoint point 1;
                                      ^
postgres=!# rollback to savepoint point1;
ОШИБКА:  точка сохранения "point1" не существует
postgres=!# rollback to savepoint point1;
ОШИБКА:  точка сохранения "point1" не существует
postgres=!# rollback;
ROLLBACK
postgres=# select * from products;
 id |   name    |  producer  | count | price
----+-----------+------------+-------+-------
  2 | product_2 | producer_2 |    15 |    32
  4 | product_4 |            |    11 |    64
  1 | product_1 | producer_1 |    26 |    75
(3 ёЄЁюъш)


postgres=# delete from products where id = 2;
DELETE 1
postgres=# release savepoint point1;
ОШИБКА:  RELEASE SAVEPOINT может выполняться только внутри блоков транзакций
postgres=# begin transaction;
BEGIN
postgres=*# delete from products where id = 2;
DELETE 0
postgres=*# release savepoint point1;
ОШИБКА:  точка сохранения "point1" не существует
postgres=!# savepoint point1;
ОШИБКА:  текущая транзакция прервана, команды до конца блока транзакции игнорируются
postgres=!# commit;
ROLLBACK
postgres=# begin transaction;
BEGIN
postgres=*# delete from products where id = 2;
DELETE 0
postgres=*# savepoint point1;
SAVEPOINT
postgres=*# delete from products where id = 1;
DELETE 1
postgres=*# savepoint point2;
SAVEPOINT
postgres=*# delete from products where id = 4;
DELETE 1
postgres=*# select * from products;
 id | name | producer | count | price
----+------+----------+-------+-------
(0 ёЄЁюъ)


postgres=*# rollback to savepoint point2;
ROLLBACK
postgres=*# select * from products;
 id |   name    | producer | count | price
----+-----------+----------+-------+-------
  4 | product_4 |          |    11 |    64
(1 ёЄЁюър)


postgres=*# rollback to savepoint point1;
ROLLBACK
postgres=*# select * from products;
 id |   name    |  producer  | count | price
----+-----------+------------+-------+-------
  4 | product_4 |            |    11 |    64
  1 | product_1 | producer_1 |    26 |    75
(2 ёЄЁюъш)


postgres=*# rollback to savepoint point3;
ОШИБКА:  точка сохранения "point3" не существует
postgres=!# rollback;
ROLLBACK