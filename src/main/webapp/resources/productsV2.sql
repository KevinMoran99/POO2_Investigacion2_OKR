DROP DATABASE IF EXISTS productsV2;

CREATE DATABASE productsV2;

USE productsV2;

CREATE TABLE categories(
	id int PRIMARY KEY AUTO_INCREMENT,
    name varchar(50) not null,
    description varchar(500) not null,
    createdAt date not null,
    state boolean default true not null
);

CREATE TABLE products(
	id int PRIMARY KEY AUTO_INCREMENT,
    name varchar(50) not null,
    description varchar(500) not null,
    price decimal(5,2) not null,
    stock int not null,
    state boolean default true not null,
    categoryId int not null,
    FOREIGN KEY (categoryId) REFERENCES categories(id)
);