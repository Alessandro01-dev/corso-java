--
-- gestione transazioni
--

select *
from orders o
inner join order_items oi
on o.id = oi.order_id 
where oi.order_id = 22;

select count(*) from prova;
-- inizio transazione
start transaction;
delete from prova where 1=1;
select count(*) from prova;
-- rollback; -- ripristina la situazione precedente (indietro nel tempo)
-- commit; -- conferma definitiva delle operazioni
select count(*) from prova;
-- verifica



select count(*) from prova;
-- inizio transazione
start transaction;
truncate prova; -- in mariadb/mysql fuori dalla transazione
rollback;
select count(*) from prova;
-- verifica
