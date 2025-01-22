create TABLE client (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255),
    type VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone INT,
    date_register TIMESTAMP NOT NULL,
    dateLastEnter TIMESTAMP NOT NULL,
    active BOOLEAN NOT NULL
);

CREATE TABLE product (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255),
    model VARCHAR(255),
    description VARCHAR(255),
    price INT,
    guarantee INT
);

CREATE TABLE sale (
    id SERIAL PRIMARY KEY NOT NULL,
    date_sale TIMESTAMP NOT NULL,
    count INT,
    sum INT,
    client_id BIGINT,
    product_id BIGINT,
    constraint foreign_key_client_id FOREIGN KEY (client_id) REFERENCES client (id),
    constraint foreign_key_product_id FOREIGN KEY (product_id) REFERENCES product (id)
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY NOT NULL,
    username VARCHAR(255),
    name VARCHAR(255),
    surname VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(1500),
    role VARCHAR(255),
    information VARCHAR(255),
    phone INT,
    dateJoined TIMESTAMP NOT NULL,
    dateLastEnter TIMESTAMP NOT NULL,
    active BOOLEAN NOT NULL
);