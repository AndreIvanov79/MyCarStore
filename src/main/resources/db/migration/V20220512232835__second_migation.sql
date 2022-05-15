insert into carsrental.roles (id, name)
values(1,'ADMIN');

insert into carsrental.roles (id, name)
values(2,'USER');

insert into carsrental.roles (id, name)
values(3,'OPERATOR');


insert into carsrental.prices (id, name, price)
values(1,'basic',30);

insert into carsrental.prices (id, name, price)
values(2,'premium',40);


insert into carsrental.vehicles (id, name, price_id, initial_period)
values(2,'JEEP',1,3);

insert into carsrental.vehicles (id, name, price_id, initial_period)
values(3,'TRACK',1,5);

insert into carsrental.vehicles (id, name, price_id, initial_period)
values(1,'CAR',2,1);


insert into carsrental.users (id,first_name, last_name, user_name, email, password)
values(1,'John','Johnson','user1','user1@mail.com','user1');

insert into carsrental.users (id,first_name, last_name, user_name, email, password)
values(2,'Bill','Billson','user2','user2@mail.com','user2');

insert into carsrental.users (id,first_name, last_name, user_name, email, password)
values(3,'Chuck','Chuckson','user3','user3@mail.com','user3');

insert into carsrental.users (id,first_name, last_name, user_name, email, password)
values(4,'Dick','Dickson','user4','user4@mail.com','user4');


insert into carsrental.cars (id,brand, vehicle_id, number, is_rented)
values(1,'Mercedes',1,'AAA 100',true);

insert into carsrental.cars (id,brand, vehicle_id, number, is_rented)
values(2,'BMW',2,'BBB 100',true);

insert into carsrental.cars (id,brand, vehicle_id, number, is_rented)
values(3,'Volvo',3,'CCC 100',false);

insert into carsrental.cars (id,brand, vehicle_id, number, is_rented)
values(4,'Toyota',1,'DDD 100',false);


insert into carsrental.rents (id,user_id, car_id, start_date, end_date, is_finished)
values(1,1,1,'01.02.2020','10.02.2020',true);

insert into carsrental.rents (id,user_id, car_id, start_date, end_date, is_finished)
values(2,2,2,'01.02.2020','10.02.2020',false);





insert into carsrental.user_roles (user_id, role_id)
values(1,2);

insert into carsrental.user_roles (user_id, role_id)
values(2,2);

insert into carsrental.user_roles (user_id, role_id)
values(3,1);

insert into carsrental.user_roles (user_id, role_id)
values(4,1);