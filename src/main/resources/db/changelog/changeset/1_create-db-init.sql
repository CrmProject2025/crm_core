
CREATE TABLE product (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255),
    model VARCHAR(255),
    description VARCHAR(255),
    price INT,
    guarantee INT
);

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

CREATE TABLE sale (
    id SERIAL PRIMARY KEY NOT NULL,
    date_sale TIMESTAMP NOT NULL,
    count INT,
    sum INT,
    user_id BIGINT,
    product_id BIGINT,
    constraint foreign_key_user_id FOREIGN KEY (user_id) REFERENCES users (id),
    constraint foreign_key_product_id FOREIGN KEY (product_id) REFERENCES product (id)
);

CREATE TABLE users_role (
    id SERIAL PRIMARY KEY NOT NULL,
    roles VARCHAR(255),
    user_id BIGINT,
    constraint foreign_key_user_id FOREIGN KEY (user_id) REFERENCES users (id)
);

