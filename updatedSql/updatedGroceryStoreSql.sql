﻿create table customer(

id char(5),

balance numeric(20,2) not null,

fname varchar(10) not null,

lname varchar(10) not null,

dcity varchar(20) not null,

dstate varchar(20) not null,

dstreet varchar(50) not null,

dzipcode numeric(5,0) not null,

primary key (id));



create table creditcard(

customerid char(5),

pcity varchar(20) not null,

pstate varchar(20) not null,

pstreet varchar(50) not null,

pzipcode numeric(5,0) not null,

cnumber numeric(16,0) not null,

ceyear numeric(4,0) not null,

cemonth numeric(2,0) not null,

cvs numeric(3,0) not null,

primary key(customerid,cnumber),

foreign key(customerid) references customer(id));



create table staff(

id char(5),

fname varchar(20) not null,

lname varchar(20) not null,

salary numeric(10,2) not null,

scity varchar(20) not null,

sstate varchar(20) not null,

sstreet varchar(50) not null,

szipcode numeric(5,0) not null,

title varchar(20) not null,

primary key(id));



create table product(

pname varchar(20) not null,

size numeric(10,2) not null,

type varchar(10) not null,

primary key(pname));



create table supplier(

id char(5),

name varchar(50) not null,

city varchar(20) not null,

state varchar(20) not null,

street varchar(50) not null,

zipcode numeric(5,0) not null,

itemid varchar(5) not null,

type varchar(20) not null,

itemprice numeric(10,2) not null,

primary key(id));

create table type(
pname varchar(20) not null,
AdditionalInfo varchar (100) not null,
primary key (pname),
foreign key (pname) references product(pname));



create table productpricing(

productid char(5),

price numeric(10,2) not null,

state varchar(20) not null,

primary key(productid)

)

;



create table warehouse(

id char(5) not null,

city varchar(20) not null,

state varchar(20) not null,

street varchar(50) not null,

zipcode numeric(5,0) not null,

capacity numeric(10,2) not null,

primary key(id)

);



create table stock(

wid char(5) not null,

pid char(5) not null,

numofitem int not null,

primary key(wid,pid),

foreign key(pid) references warehouse(id),

foreign key(wid) references product(pname));





create table orders(

id char(20),

customerid char(5) not null,

pid char(5) not null,

num int not null,

cnumber numeric(16,0) not null,

status varchar(20) not null,

issuedate date not null,

primary key(id,pid,cnumber,customerid),

foreign key(pid) references product(pname),

foreign key(cnumber,customerid) references creditcard(cnumber,customerid)

);



create table cart(

id char(20),

customerid char(5) not null,

pid char(5) not null,

num int not null,

primary key(id,pid,customerid),

foreign key(pid) references product(pname),

foreign key(customerid) references customer(id)



);

