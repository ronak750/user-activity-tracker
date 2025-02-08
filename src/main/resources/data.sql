-- Insert sample users
--CREATE TABLE users (
--    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
--    user_name VARCHAR(255) NOT NULL,
--    status VARCHAR(50),
--    number VARCHAR(20),
--    user_image VARCHAR(255),
--    postal_code VARCHAR(20),
--    email VARCHAR(255) UNIQUE
--);
--
--CREATE TABLE user_details (
--    id BIGINT AUTO_INCREMENT PRIMARY KEY,
--    user_id BIGINT,
--    user_device VARCHAR(50),
--    log_in_time TIMESTAMP,
--    user_action VARCHAR(50),
--    FOREIGN KEY (user_id) REFERENCES users(user_id)
--);


INSERT INTO USERS (user_id, user_name, status, number, user_image, postal_code, email) VALUES
(1, 'John Doe', 'active', '9876543210', 'https://example.com/john.jpg', '123456', 'john.doe@example.com'),
(2, 'Alice Smith', 'inactive', '8765432109', 'https://example.com/alice.jpg', '654321', 'alice.smith@example.com'),
(3, 'Robert Brown', 'active', '7654321098', 'https://example.com/robert.jpg', '789456', 'robert.brown@example.com'),
(4, 'Emily Johnson', 'active', '6543210987', 'https://example.com/emily.jpg', '987654', 'emily.johnson@example.com');

INSERT INTO user_details (id, user_id, user_device, log_in_time, user_action) VALUES
(1, 1, 'Android', '2025-01-01T10:00:00', 'LOGIN'),
(11, 1, 'Android', '2025-02-02T10:00:00', 'LOGIN'),
(2, 1, 'Android', '2025-01-01T11:00:00', 'ORDER_CREATION'),
(3, 2, 'iOS', '2025-01-02T09:30:00', 'CANCEL'),
(4, 3, 'Windows', '2025-01-02T14:15:00', 'ADDRESS_CHANGE'),
(5, 3, 'MacOS', '2025-01-03T16:45:00', 'APP_UPDATE'),
(6, 4, 'Android', '2025-01-04T08:20:00', 'ORDER_FAIL'),
(7, 4, 'iOS', '2025-01-04T09:50:00', 'PENDING'),
(8, 1, 'Android', '2025-01-05T12:10:00', 'APP_CRASH'),
(9, 2, 'Windows', '2025-01-06T15:25:00', 'LOGIN'),
(10, 3, 'iOS', '2025-01-07T18:30:00', 'ORDER_CREATION');
