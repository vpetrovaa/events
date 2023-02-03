--liquibase formatted sql

--changeset eyakauleva:create_tables

create table users
(
    id bigserial,
	role varchar(45) not null,
	email varchar(45) not null unique,
	password varchar(60) not null,
	first_name varchar(45) not null,
	last_name varchar(45) not null,
	primary key (id)
);

create table events(
	id bigserial,
	name varchar(45) not null,
	description varchar(1000),
	event_time timestamp,
	price decimal(10, 2),
	type varchar(45),
	topic varchar(45),
	country varchar(45),
	coordinates point,
	status varchar(45),
	primary key (id)
);

create table tickets (
    id bigserial,
	user_id bigint not null,
	event_id bigint not null,
	amount integer not null,
	primary key (id),
    foreign key (user_id) references users (id) on update cascade on delete cascade,
    foreign key (event_id) references events (id) on update cascade on delete cascade
);

--  rollback drop table tickets;
--  drop table events;
--  drop table users;
