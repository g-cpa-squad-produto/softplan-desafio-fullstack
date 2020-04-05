CREATE TABLE IF NOT EXISTS USERS 
(
    id         serial primary key,
    name       VARCHAR(50)        NOT NULL,
    password   VARCHAR(50)        NOT NULL,
    email      VARCHAR(50) UNIQUE NOT NULL,
    type       VARCHAR(20)        NOT NULL
);