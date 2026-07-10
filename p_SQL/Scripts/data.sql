-- File: src/main/resources/data.sql

INSERT INTO customers (first_name, last_name, email, phone, address, city, is_active, creation_date, update_date)
VALUES
('Mario', 'Rossi', 'mario.rossi@example.com', '1234567890', 'Via Roma 1', 'Torino', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Luigi', 'Bianchi', 'luigi.bianchi@example.com', '0987654321', 'Via Milano 10', 'Milano', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO products (product_name, price, stock, created_at, updated_at)
VALUES
('Laptop', 1200.00, 10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Mouse', 25.00, 100, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Keyboard', 45.00, 50, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
