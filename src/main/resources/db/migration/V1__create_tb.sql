create table users(
    id serial not null primary key,
    email varchar (255) not null unique,
    first_name varchar (32) not null,
    last_name varchar (100) not null,
    password varchar (255) not null,
    role varchar (32) not null default 'USER',
    status varchar (32) not null default 'ACTIVE'

)