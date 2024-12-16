CREATE TABLE ToDoItem (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    completed BOOLEAN NOT NULL
);

CREATE INDEX idx_username ON ToDoItem(username);