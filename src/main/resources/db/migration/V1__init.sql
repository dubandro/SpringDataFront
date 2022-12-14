create table categories
(
    id    bigserial primary key,
    title varchar(255)
);
insert into categories (title)
values
    ('Food1'),
    ('Food2');

create table products
(
    id          bigserial primary key,
    title       varchar(255),
    price       decimal(10, 2),
    category_id bigint references categories (id)
);
insert into products (title, price, category_id)
values
    ('milk', 80, 1),
    ('bread', 50, 1),
    ('cheese', 190, 1),
    ('meat', 250, 2),
    ('eggs', 90, 1),
    ('oil', 150, 2);