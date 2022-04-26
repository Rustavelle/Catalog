create table category_products
(
    id bigint unsigned auto_increment,
    category_name varchar(100) not null,
    primary key (id)
);

create table products
(
    id bigint unsigned auto_increment,
    category_id bigint unsigned not null,
    foreign key (category_id) references category_products (id),
    product_name varchar(100) not null,
    price decimal not null,
    primary key (id)
);

create table characteristic
(
    id bigint unsigned auto_increment,
    category_id bigint unsigned not  null,
    foreign key (category_id) references category_products (id),
    name varchar(150) not null,
    primary key (id)
);

create table meaning
(
    id bigint unsigned auto_increment,
    product_id bigint unsigned not null,
    foreign key (product_id) references products (id),
    characteristic_id bigint unsigned not null,
    foreign key (characteristic_id) references characteristic (id),
    text text not null,
    primary key (id)
);

