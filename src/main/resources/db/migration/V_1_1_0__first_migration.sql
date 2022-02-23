CREATE TABLE if not exists "users" (
                                       "id" serial NOT NULL,
                                       "first_name" varchar(255) NOT NULL,
    "last_name" varchar(255) NOT NULL,
    "user_name" varchar(255) NOT NULL UNIQUE,
    "password" varchar(255) NOT NULL,
    "role_id" bigint NOT NULL,
    CONSTRAINT "users_pk" PRIMARY KEY ("id")
    ) WITH (
          OIDS=FALSE
          );



CREATE TABLE if not exists "cars" (
                                      "id" serial NOT NULL,
                                      "brand" varchar(255) NOT NULL,
    "vehicle_id" bigint NOT NULL,
    "number" varchar(255) NOT NULL UNIQUE,
    "is_rented" BOOLEAN NOT NULL,
    CONSTRAINT "cars_pk" PRIMARY KEY ("id")
    ) WITH (
          OIDS=FALSE
          );



CREATE TABLE if not exists "roles" (
                                       "id" serial NOT NULL,
                                       "name" varchar(255) NOT NULL UNIQUE,
    CONSTRAINT "roles_pk" PRIMARY KEY ("id")
    ) WITH (
          OIDS=FALSE
          );



CREATE TABLE if not exists "rents" (
                                       "id" serial NOT NULL,
                                       "car_id" bigint NOT NULL,
                                       "start_date" TIMESTAMP NOT NULL,
                                       "end_date" TIMESTAMP,
                                       "is_finished" BOOLEAN NOT NULL,
                                       CONSTRAINT "rents_pk" PRIMARY KEY ("id")
    ) WITH (
          OIDS=FALSE
          );



CREATE TABLE if not exists "vehicles" (
                                          "id" serial NOT NULL,
                                          "name" varchar(255) NOT NULL UNIQUE,
    "price_id" bigint NOT NULL,
    "initial_period" int NOT NULL,
    CONSTRAINT "vehicles_pk" PRIMARY KEY ("id")
    ) WITH (
          OIDS=FALSE
          );



CREATE TABLE if not exists "prices" (
                                        "id" serial NOT NULL,
                                        "name" varchar(255) NOT NULL,
    "price" float4 NOT NULL,
    CONSTRAINT "prices_pk" PRIMARY KEY ("id")
    ) WITH (
          OIDS=FALSE
          );



CREATE TABLE if not exists "user_rents" (
                                            "user_id" bigint NOT NULL,
                                            "rent_id" bigint NOT NULL
) WITH (
      OIDS=FALSE
      );



ALTER TABLE "users" ADD CONSTRAINT "users_fk0" FOREIGN KEY ("role_id") REFERENCES "roles"("id");

ALTER TABLE "cars" ADD CONSTRAINT "cars_fk0" FOREIGN KEY ("vehicle_id") REFERENCES "vehicles"("id");


ALTER TABLE "rents" ADD CONSTRAINT "rents_fk0" FOREIGN KEY ("car_id") REFERENCES "cars"("id");

ALTER TABLE "vehicles" ADD CONSTRAINT "vehicles_fk0" FOREIGN KEY ("price_id") REFERENCES "prices"("id");


ALTER TABLE "user_rents" ADD CONSTRAINT "user_rents_fk0" FOREIGN KEY ("user_id") REFERENCES "users"("id");
ALTER TABLE "user_rents" ADD CONSTRAINT "user_rents_fk1" FOREIGN KEY ("rent_id") REFERENCES "rents"("id");


