create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

create
or replace procedure delete_data(u_count integer, u_id integer)
language 'plpgsql'
as $$
    BEGIN
        if u_count = 0 THEN
            delete from products
            where id = u_id;
        end if;
    END;
$$;

call delete_data(4,1)
call delete_data(10,2)

create
or replace function f_delete_data(u_count integer, u_id integer)
returns integer
language 'plpgsql'
as
$$
    declare
        result integer;
    begin
        if u_count = 0 THEN
            delete from products
            where id = u_id;
            result := 1;
        else
        	result := -1;
        end if;
        return result;
    end;
$$;

select * from products;
select f_delete_data(1,1);
select f_delete_data(0,1);



