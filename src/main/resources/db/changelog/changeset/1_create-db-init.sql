CREATE TABLE users (
    id SERIAL PRIMARY KEY NOT NULL,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255),
    name VARCHAR(255),
    surname VARCHAR(255),
    type VARCHAR(255),
    password VARCHAR(1500) NOT NULL,
    information VARCHAR(255),
    phone INT,
    date_joined TIMESTAMP NOT NULL,
    date_last_enter TIMESTAMP NOT NULL,
    active BOOLEAN NOT NULL
);

CREATE TABLE users_role (
    id SERIAL PRIMARY KEY NOT NULL,
    roles VARCHAR(255),
    user_id BIGINT,
    constraint foreign_key_user_id FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE warehouse (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255)
);

CREATE TABLE product (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL,
    model VARCHAR(255),
    description VARCHAR(1000),
    price NUMERIC(10, 2) NOT NULL,
    guarantee INT,
    deprecated BOOLEAN NOT NULL
);

CREATE TABLE stock (
    id SERIAL PRIMARY KEY NOT NULL,
    quantity INT,
    warehouse_id BIGINT,
    product_id BIGINT,
    constraint foreign_key_warehouse_id FOREIGN KEY (warehouse_id) REFERENCES warehouse (id),
    constraint foreign_key_product_id FOREIGN KEY (product_id) REFERENCES product (id)
);

CREATE TABLE orders (
    id SERIAL PRIMARY KEY NOT NULL,
    info VARCHAR(1000),
    address VARCHAR(255),
    date_create TIMESTAMP NOT NULL,
    status VARCHAR(255),
    user_id_client BIGINT,
    user_id_saler BIGINT,
    constraint foreign_key_user_id_client FOREIGN KEY (user_id_client) REFERENCES users (id),
    constraint foreign_key_user_id_saler FOREIGN KEY (user_id_saler) REFERENCES users (id)
);

CREATE TABLE order_product (
    id SERIAL PRIMARY KEY NOT NULL,
    count INT NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    order_id BIGINT,
    product_id BIGINT,
    constraint foreign_key_order_id FOREIGN KEY (order_id) REFERENCES orders (id),
    constraint foreign_key_product_id FOREIGN KEY (product_id) REFERENCES product (id)
);

CREATE TABLE meter (
    id SERIAL PRIMARY KEY NOT NULL,
    address VARCHAR(255),
    date_install TIMESTAMP NOT NULL,
    user_id_client BIGINT,
    user_id_installer BIGINT,
    product_id BIGINT,
    constraint foreign_key_user_id_client FOREIGN KEY (user_id_client) REFERENCES users (id),
    constraint foreign_key_user_id_installer FOREIGN KEY (user_id_installer) REFERENCES users (id),
    constraint foreign_key_product_id FOREIGN KEY (product_id) REFERENCES product (id)
);

CREATE TABLE measurement (
    id SERIAL PRIMARY KEY NOT NULL,
    measurement_date TIMESTAMP NOT NULL,
    value INT,
    meter_id BIGINT,
    constraint foreign_key_meter_id FOREIGN KEY (meter_id) REFERENCES meter (id)
);