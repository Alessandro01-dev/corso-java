-- -----------------------------------------------------------------------------
-- 1. Schema (DDL)
-- -----------------------------------------------------------------------------
drop table if exists orders;
drop table if exists customers;


-- Tabella "padre": anagrafica clienti.
CREATE TABLE customers (
id INT NOT NULL AUTO_INCREMENT,
name VARCHAR(50) NOT NULL,
city VARCHAR(50) NOT NULL,
PRIMARY KEY (id)
) ENGINE=InnoDB;

-- Tabella "figlia": ordini. customer_id e' NULLABLE per ammettere
-- l'ordine privo di cliente, utile a dimostrare il RIGHT JOIN.
CREATE TABLE orders (
id INT NOT NULL AUTO_INCREMENT,
customer_id INT NULL,
amount DECIMAL(10,2) NOT NULL,
PRIMARY KEY (id),
CONSTRAINT fk_orders_customer
FOREIGN KEY (customer_id) REFERENCES customers(id)
) ENGINE=InnoDB;

-- -----------------------------------------------------------------------------
-- 2. Dati (DML)
-- -----------------------------------------------------------------------------

-- 5 clienti. Neri (4) e Gialli (5) NON avranno ordini.
INSERT INTO customers (id, name, city) VALUES
(1, 'Rossi', 'Milano'),
(2, 'Bianchi', 'Roma'),
(3, 'Verdi', 'Torino'),
(4, 'Neri', 'Napoli'), -- nessun ordine
(5, 'Gialli', 'Genova'); -- nessun ordine

-- 5 ordini. L'ordine 5 ha customer_id = NULL (cliente assente).
INSERT INTO orders (id, customer_id, amount) VALUES
(1, 1, 120.00),
(2, 1, 80.50), -- secondo ordine di Rossi (1:N)
(3, 2, 250.00),
(4, 3, 45.00),
(5, NULL, 99.99); -- ordine senza cliente

select * from customers;
select * from orders;


select * 
from customers -- left
inner join orders -- right
on customers.id = orders.customer_id

-- INNER JOIN
-- elenco degli ordini per cliente
select c.id as 'Cliente ID', c.name as 'Cliente', o.id as '# ordine', o.amount as 'valore' 
from customers c -- left
inner join orders o -- right
on c.id = o.customer_id


-- LEFT JOIN
-- elenco dei clienti con e senza ordini
select c.id as 'Cliente ID', c.name as 'Cliente', o.id as '# ordine', o.amount as 'valore' 
from customers c -- left
left join orders o -- right
on c.id = o.customer_id

-- LEFT JOIN + esclusione
-- elenco dei clienti "cattivi"
select c.id as 'Cliente ID', c.name as 'Cliente', o.id as '# ordine', o.amount as 'valore' 
from customers c -- left
left join orders o -- right
on c.id = o.customer_id
where o.id is null;

-- LEFT JOIN + inclusioni
-- elenco dei clienti "buoni"
select c.id as 'Cliente ID', c.name as 'Cliente', o.id as '# ordine', o.amount as 'valore' 
from customers c -- left
left join orders o -- right
on c.id = o.customer_id
where o.id is not null;

-- RIGHT JOIN
-- elenco degli ordini con e senza clienti
select c.id as 'Cliente ID', c.name as 'Cliente', o.id as '# ordine', o.amount as 'valore' 
from customers c -- left
right join orders o -- right
on c.id = o.customer_id

-- RIGHT JOIN
-- elenco degli ordini senza clienti (caso limite)
select c.id as 'Cliente ID', c.name as 'Cliente', o.id as '# ordine', o.amount as 'valore' 
from customers c -- left table
right join orders o -- right table
on c.id = o.customer_id
where c.id is null;

-- Full join in mysql
select c.id as 'Cliente ID', c.name as 'Cliente', o.id as '# ordine', o.amount as 'valore' 
from customers c -- left
left join orders o -- right
on c.id = o.customer_id
union
select c.id as 'Cliente ID', c.name as 'Cliente', o.id as '# ordine', o.amount as 'valore' 
from customers c -- left
right join orders o -- right
on c.id = o.customer_id;








