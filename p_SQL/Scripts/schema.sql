-- File: src/main/resources/schema.sql
drop table if exists order_items;
drop table if exists orders;
drop table  if exists customers;
drop table  if exists products;

CREATE TABLE customers (
    id            INTEGER       PRIMARY KEY auto_increment,
    first_name    VARCHAR(100)  NOT NULL,
    last_name     VARCHAR(100)  NOT NULL,
    email         VARCHAR(255)  NOT NULL,
    phone         VARCHAR(50),
    address       VARCHAR(255),
    city          VARCHAR(100),
    is_active     BOOLEAN       NOT NULL,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_date   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE products (
    id           INTEGER       PRIMARY KEY auto_increment,
    product_name VARCHAR(100)  NOT NULL,
    price        DECIMAL(10,2) NOT NULL,
    stock        INTEGER       NOT NULL,
    created_at   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE orders (
    id           INTEGER       PRIMARY KEY auto_increment,
    customer_id  INTEGER       NOT NULL,
    order_date   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status       VARCHAR(20)   NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_orders_customer
        FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE order_items (
    id          INTEGER       PRIMARY KEY auto_increment,
    order_id    INTEGER       NOT NULL,
    product_id  INTEGER       NOT NULL,
    quantity    INTEGER       NOT NULL,
    unit_price  DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_order_items_order
        FOREIGN KEY (order_id) REFERENCES orders(id),
    CONSTRAINT fk_order_items_product
        FOREIGN KEY (product_id) REFERENCES products(id)
);
