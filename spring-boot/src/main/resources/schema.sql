CREATE TABLE to_do_item (
    id SERIAL PRIMARY KEY,
    description VARCHAR(110) NOT NULL,
    completed BOOLEAN NOT NULL
);