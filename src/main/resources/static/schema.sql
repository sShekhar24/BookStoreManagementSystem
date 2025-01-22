-- Create the 'role' table
CREATE TABLE role (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    UNIQUE(name)
);

-- Create the 'user' table
CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP
);

-- Create the 'book' table
CREATE TABLE book (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

-- Create the 'borrow_record' table
CREATE TABLE borrow_record (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    borrow_date TIMESTAMP NOT NULL,
    return_date TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (book_id) REFERENCES book(id)
);

-- Create the 'user_roles' table (mapping users to their roles)
CREATE TABLE user_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (role_id) REFERENCES role(id)
);

-- Create the 'rate_limits' table to manage rate limiting
CREATE TABLE rate_limits (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    request_count INT NOT NULL DEFAULT 0,
    last_request_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

-- Example queries to populate the roles table
INSERT INTO role (name) VALUES ('ROLE_USER');
INSERT INTO role (name) VALUES ('ROLE_ADMIN');

-- Example queries to populate the 'user' table
INSERT INTO user (email, password, created_at, updated_at) 
VALUES ('admin@example.com', 'adminpassword', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO user (email, password, created_at, updated_at) 
VALUES ('user@example.com', 'userpassword', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Example query to assign roles to users (mapping user to their roles)
INSERT INTO user_roles (user_id, role_id) 
VALUES ((SELECT id FROM user WHERE email = 'admin@example.com'), (SELECT id FROM role WHERE name = 'ROLE_ADMIN'));

INSERT INTO user_roles (user_id, role_id) 
VALUES ((SELECT id FROM user WHERE email = 'user@example.com'), (SELECT id FROM role WHERE name = 'ROLE_USER'));

-- Example query to insert books into the 'book' table
INSERT INTO book (title, author, price) 
VALUES ('The Great Gatsby', 'F. Scott Fitzgerald', 10.99);

INSERT INTO book (title, author, price) 
VALUES ('1984', 'George Orwell', 8.99);

-- Example query to create borrow records
INSERT INTO borrow_record (user_id, book_id, borrow_date) 
VALUES ((SELECT id FROM user WHERE email = 'user@example.com'), (SELECT id FROM book WHERE title = '1984'), CURRENT_TIMESTAMP);

