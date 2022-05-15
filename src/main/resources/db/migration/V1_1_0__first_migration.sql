CREATE TABLE if not exists carsrental.users (
                                "id" serial NOT NULL,
                                "first_name" varchar(255) NOT NULL,
                                "last_name" varchar(255) NOT NULL,
                                "user_name" varchar(255) NOT NULL UNIQUE,
                                "email" varchar(255) NOT NULL,
                                "password" varchar(255) NOT NULL,
                                CONSTRAINT "users_pk" PRIMARY KEY ("id")
) ;



CREATE TABLE if not exists carsrental.cars (
                               "id" serial NOT NULL,
                               "brand" varchar(255) NOT NULL,
                               "vehicle_id" bigint NOT NULL,
                               "number" varchar(255) NOT NULL UNIQUE,
                               "is_rented" BOOLEAN NOT NULL,
                               CONSTRAINT "cars_pk" PRIMARY KEY ("id")
) ;



CREATE TABLE if not exists carsrental.roles (
                                "id" serial NOT NULL,
                                "name" varchar(255) NOT NULL UNIQUE,
                                CONSTRAINT "roles_pk" PRIMARY KEY ("id")
);



CREATE TABLE if not exists carsrental.rents (
                                "id" serial NOT NULL,
                                "user_id" bigint NOT NULL,
                                "car_id" bigint NOT NULL,
                                "start_date" TIMESTAMP NOT NULL,
                                "end_date" TIMESTAMP,
                                "is_finished" BOOLEAN NOT NULL,
                                CONSTRAINT "rents_pk" PRIMARY KEY ("id")
) ;



CREATE TABLE if not exists carsrental.vehicles (
                                   "id" serial NOT NULL,
                                   "name" varchar(255) NOT NULL UNIQUE,
                                   "price_id" bigint NOT NULL,
                                   "initial_period" int NOT NULL,
                                   CONSTRAINT "vehicles_pk" PRIMARY KEY ("id")
) ;



CREATE TABLE if not exists carsrental.prices (
                                 "id" serial NOT NULL,
                                 "price" float4 NOT NULL,
                                 "name" varchar(255) NOT NULL,
                                 CONSTRAINT "prices_pk" PRIMARY KEY ("id")
) ;



CREATE TABLE if not exists carsrental.user_roles (
                                     "user_id" bigint NOT NULL,
                                     "role_id" bigint NOT NULL
) ;




ALTER TABLE carsrental.cars ADD CONSTRAINT "cars_fk0" FOREIGN KEY ("vehicle_id") REFERENCES carsrental.vehicles("id");


ALTER TABLE carsrental.rents ADD CONSTRAINT "rents_fk0" FOREIGN KEY ("user_id") REFERENCES carsrental.users("id");
ALTER TABLE carsrental.rents ADD CONSTRAINT "rents_fk1" FOREIGN KEY ("car_id") REFERENCES carsrental.cars("id");

ALTER TABLE carsrental.vehicles ADD CONSTRAINT "vehicles_fk0" FOREIGN KEY ("price_id") REFERENCES carsrental.prices("id");


ALTER TABLE carsrental.user_roles ADD CONSTRAINT "user_roles_fk0" FOREIGN KEY ("user_id") REFERENCES carsrental.users("id");
ALTER TABLE carsrental.user_roles ADD CONSTRAINT "user_roles_fk1" FOREIGN KEY ("role_id") REFERENCES carsrental.roles("id");








