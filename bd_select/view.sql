
-- только одна машина и стоит больше 2000000
create view show_people_car_cost_more_2mln 
as
select 
	p.id,
	p.name as people_name,
	MAX(c.name) as car_name,
	MAX(cst.cost) as car_cost
	from people p
	join cars_people cp on cp.people_id = p.id
	left join cars c on cp.car_id = c.id
	left join costs cst on cst.cars_id = c.id
	where 
	cst.cost > 2000000
	group by p.id, p.name
	having count(c.id)=1;

select * from show_people_car_cost_more_2mln;
	


