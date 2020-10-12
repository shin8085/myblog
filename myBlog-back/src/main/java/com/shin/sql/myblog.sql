create database myblog;

create table users(
    name varchar(24) not null primary key,
    password varchar(24) not null
);

create table blogs(
    id varchar(50) not null primary key,
    author_name varchar(24) not null,
    title varchar(100) not null,
    context varchar(255) not null,
    datetime datetime not null,
    CONSTRAINT u_b_name foreign key(author_name) references users(name)
);

insert into users (name, password) values ("shin","19981203");