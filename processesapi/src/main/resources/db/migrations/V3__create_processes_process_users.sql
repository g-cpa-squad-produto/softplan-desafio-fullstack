CREATE TABLE IF NOT EXISTS processes_process_users
(
    id          serial primary key,
    userid      INT NOT NULL REFERENCES users(id) ON UPDATE CASCADE ON DELETE CASCADE,
    processid   INT NOT NULL REFERENCES processes(id) ON UPDATE CASCADE ON DELETE CASCADE
);
