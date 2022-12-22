create table orders
(
    id          bigserial primary key,
    created_at  timestamp default current_timestamp,
    total_price decimal(10, 2)
--     user_id     bigint not null references users (id)
);
create table categories
(
    id    bigserial primary key,
    title varchar(255)
);
insert into categories (title)
values
    ('Food1'),
    ('Food2'),
    ('Food3'),
    ('Food4');

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
    ('meat', 350, 2),
    ('eggs', 90, 1),
    ('apple', 65, 3),
    ('pear', 95, 3),
    ('chicken', 200, 2),
    ('oil', 150, 2),
    ('grape', 120, 3),
    ('banana', 100, 3),
    ('orange', 120, 3),
    ('ketchup', 110, 1),
    ('tea', 200, 4),
    ('coffee', 500, 4),
    ('wine red', 400, 4),
    ('wine white', 400, 4),
    ('wine rose', 400, 4),
    ('strawberry', 180, 3),
    ('fish', 180, 2),
    ('turkey', 235, 2),
    ('sausages', 195, 2);