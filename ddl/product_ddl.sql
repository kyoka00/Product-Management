create table users(
    user_no serial primary key
    , user_id varchar(50) not null unique
    ,user_name varchar(100) not null
    , password varchar(50) not null
);

create table products(
 user_no int not null,
 product_id serial primary key,
 product_name varchar(200)not null,
 brand_name varchar(100),
 category_id int not null,
 purchase_date date not null,
 starting_date date,
 expiration_date date not null,
 favorite boolean default FALSE,
 finished boolean default FALSE
);

create table category(
 category_id serial primary key,
 category_name varchar(60) not null
);
