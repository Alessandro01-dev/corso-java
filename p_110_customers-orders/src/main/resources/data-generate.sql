-- File: src/main/resources/data-generate.sql
-- Generazione casuale di ordini (orders) e dettagli (order_items)
-- a partire dalle tabelle gia' popolate customers e products.
--
-- COMPATIBILITA': scritto per il minimo comune denominatore tra
--   MariaDB 10/11, MySQL 8 e MySQL 5.7.
-- 
-- La moltiplicazione delle righe avviene tramite una tabella di appoggio
-- (tally table) popolata a mano con i numeri 1..N: costrutto valido ovunque.
--
-- PREREQUISITI: customers e products devono gia' contenere righe.
--   In particolare products NON deve essere vuota (si pesca un prodotto a caso).
-- 
-- ===========================================================================
-- Parametri modificabili.
-- Sono variabili di sessione: valgono per l'intera esecuzione dello script
-- sulla stessa connessione (Spring esegue gli statement in sequenza su una
-- sola connessione, quindi persistono da uno statement al successivo).
-- ===========================================================================
SET @max_orders_per_customer := 4;   -- tetto ordini per cliente (>=1 garantito)
SET @max_items_per_order     := 5;   -- tetto righe di dettaglio per ordine
SET @order_history_days      := 180; -- finestra temporale: fino a N giorni fa
SET @extra_order_prob        := 0.5; -- prob. di aggiungere un ordine oltre il 1o
SET @extra_item_prob         := 0.6; -- prob. di aggiungere una riga oltre la 1a


-- ===========================================================================
-- 1) Tally table: numeri 1..8. Serve solo a "moltiplicare" le righe via JOIN.
-- ===========================================================================
DROP TABLE IF EXISTS tmp_numbers;
CREATE TABLE tmp_numbers (n INT PRIMARY KEY);
INSERT INTO tmp_numbers (n) VALUES (1),(2),(3),(4),(5),(6),(7),(8);


-- ===========================================================================
-- 2) Ordini.
--    Ogni cliente incrociato con i numeri 1..@max_orders_per_customer.
--    La riga n=1 e' sempre tenuta (ogni cliente ha almeno un ordine);
--    le righe successive sono tenute con probabilita' @extra_order_prob,
--    cosi' il numero di ordini per cliente varia.
--    - order_date: istante casuale entro la finestra (granularita' al giorno)
--    - status:     scelto a caso tra 4 valori con ELT()
--    - total_amount: 0 provvisorio, ricalcolato al passo 5
-- ===========================================================================
INSERT INTO orders (customer_id, order_date, status, total_amount)
SELECT
    c.id,
    DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * @order_history_days) DAY),
    ELT(FLOOR(1 + RAND() * 4), 'NEW', 'PAID', 'SHIPPED', 'CANCELLED'),
    0
FROM customers c
JOIN tmp_numbers t ON t.n <= @max_orders_per_customer
WHERE t.n = 1 OR RAND() < @extra_order_prob;


-- ===========================================================================
-- 3) Dettagli ordine.
--    Ogni ordine incrociato con i numeri 1..@max_items_per_order, stessa
--    logica "n=1 sempre + extra con probabilita'".
--    - product_id: la subquery contiene RAND(), quindi e' NON deterministica
--                  e viene rivalutata riga per riga -> prodotto diverso ognuna
--    - quantity:   1..5 casuale
--    - unit_price: 0 provvisorio, allineato al prezzo reale al passo 4
-- ===========================================================================
INSERT INTO order_items (order_id, product_id, quantity, unit_price)
SELECT
    o.id,
    (SELECT p.id FROM products p ORDER BY RAND() LIMIT 1),
    FLOOR(1 + RAND() * 5),
    0
FROM orders o
JOIN tmp_numbers t ON t.n <= @max_items_per_order
WHERE t.n = 1 OR RAND() < @extra_item_prob;


-- ===========================================================================
-- 4) unit_price = prezzo reale del prodotto scelto (JOIN su products).
-- ===========================================================================
UPDATE order_items i
JOIN products p ON p.id = i.product_id
SET i.unit_price = p.price;


-- ===========================================================================
-- 5) total_amount dell'ordine = somma (quantity * unit_price) dei suoi dettagli.
--    Subquery correlata: legge order_items (tabella diversa da quella aggiornata),
--    quindi nessun conflitto "target table" su MySQL.
-- ===========================================================================
UPDATE orders o
SET o.total_amount = (
    SELECT COALESCE(SUM(i.quantity * i.unit_price), 0)
    FROM order_items i
    WHERE i.order_id = o.id
);


-- ===========================================================================
-- 6) Pulizia della tabella di appoggio.
-- ===========================================================================
DROP TABLE IF EXISTS tmp_numbers;
