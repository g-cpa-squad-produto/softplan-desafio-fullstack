CREATE TABLE IF NOT EXISTS processes_process_users
(
    id           serial primary key,
    user_id      INT NOT NULL REFERENCES users(id) ON UPDATE CASCADE ON DELETE CASCADE,
    process_id   INT NOT NULL REFERENCES processes(id) ON UPDATE CASCADE ON DELETE CASCADE
);
