CREATE TABLE IF NOT EXISTS users (
                                     id CHAR(36) PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
    );

INSERT IGNORE INTO users VALUES ('111e4567-e89b-12d3-a456-426614174001',
        'user@example.com',
        'user',
        'ADMIN');