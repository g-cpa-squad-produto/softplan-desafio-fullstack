CREATE TABLE IF NOT EXISTS processes_users
(
    id          serial primary key,
    userId      INT NOT NULL REFERENCES users(id) ON UPDATE CASCADE ON DELETE CASCADE,
    processId   INT NOT NULL REFERENCES processes(id) ON UPDATE CASCADE ON DELETE CASCADE
);
