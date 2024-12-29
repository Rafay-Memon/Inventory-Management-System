CREATE DATABASE IF NOT EXISTS productdb;

use productdb;

create table Product(
pid int primary  key auto_increment,
pname varchar(255),
pprice int,
pqty int
);