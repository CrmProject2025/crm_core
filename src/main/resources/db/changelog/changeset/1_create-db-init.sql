create TABLE client (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255),
    type VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone INT,
    date_register TIMESTAMP NOT NULL,
    active BOOLEAN NOT NULL
);

CREATE TABLE product (
    id SERIAL PRIMARY KEY NOT NULL,
    achievement_id BIGINT,
    student_id BIGINT,
);

CREATE TABLE sale (
    id SERIAL PRIMARY KEY NOT NULL,
    bonus INT NOT NULL,
    student_id BIGINT,
    constraint foreign_key_student_id FOREIGN KEY (student_id) REFERENCES student (id)
);