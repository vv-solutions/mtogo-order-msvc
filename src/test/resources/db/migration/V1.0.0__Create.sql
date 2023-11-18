
CREATE TABLE IF NOT EXISTS "order"
(
    id SERIAL PRIMARY KEY,
    address_id integer,
    comment varchar,
    create_stamp timestamp(6),
    customer_id integer,
    status_id integer,
    sub_total numeric(38,2),
    supplier_id integer,
    total numeric(38,2)
);

CREATE TABLE IF NOT EXISTS order_line
(
    id SERIAL PRIMARY KEY,
    product_id integer,
    quantity integer,
    sub_total numeric(38,2),
    total numeric(38,2),
    unit_gross_price numeric(38,2),
    unit_net_price numeric(38,2),
    order_id integer,
    FOREIGN KEY (order_id) REFERENCES "order"(id)
    );