drop database if exists pizza_factory;
create database pizza_factory;

use pizza_factory;

create table orders (

	order_id char(8) not null,
	name varchar(64) not null,
	email varchar(64) not null,
	pizza_id varchar(8) not null,
	
	constraint pk_order_id primary key(order_id)

);

select * from orders;

select * from orders where email='boo@gmail.com';

SELECT order_id, order_date, total_amount FROM orders WHERE email='boo@gmail.com';

grant all privileges on pizza_factory.* to 'fred'@'%';
flush privileges;