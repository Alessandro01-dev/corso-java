--
-- raggruppamenti II
--
-- TABELLE: italia, clienti, ordini, fatture_prova_new
-- legami:
-- italia e clienti clienti.provincia <==> province.sigla_automobilistica
-- clienti e ordini link è id_cliente
-- ordini e fatture_prova_new è id_ordine
--

-- limit
select * from ordini o
limit 5;

-- calcolare il fatturato per ripartizione
-- fattura+ordine+cliente, provincia+regione+ripartizione 

select
	c.id_cliente,
	c.nome,
	c.cognome,
	sum(fpn.importo)
from fatture_prova_new fpn
inner join ordini o
on fpn.id_ordine = o.id_ordine
inner join clienti c
on o.id_cliente = c.id_cliente
group by c.id_cliente;

select
	ri.den_rip as Ripartizione,
	sum(fpn.imponibile) as 'Totale fatturato'
from fatture_prova_new fpn
inner join ordini o
on fpn.id_ordine = o.id_ordine
inner join clienti c
on o.id_cliente = c.id_cliente
inner join province p
on p.sigla_automobilistica = c.provincia
inner join regioni r
on p.cod_reg = r.cod_reg
inner join ripartizioni ri
on r.cod_rip = ri.cod_rip
group by ri.den_rip;


-- esercizio
-- stampare i 3 clienti (top3) con maggior fatturato nel 2018
--
-- con limit
select 
	concat('',year(fpn.data)) as 'Anno', 
	c.id_cliente,
	c.nome,
	c.cognome,
	sum(fpn.imponibile) as 'Totale fatturato'
from clienti c
inner join ordini o
on o.id_cliente = c.id_cliente 
inner join fatture_prova_new fpn
on fpn.id_ordine = o.id_ordine
where year(fpn.data) = 2018
group by c.id_cliente, concat('',year(fpn.data))
order by sum(fpn.imponibile) desc
limit 3;



select c.id_cliente,c.NOME , c.COGNOME , sum(f.imponibile)
from clienti c
inner join ordini o
on c.id_cliente = o.id_cliente
inner join fatture_prova_new f
on o.id_ordine = f.id_ordine and YEAR(f.data) = 2018
group by c.ID_CLIENTE
order by 4 desc
limit 3;

-- 
-- CTE Equivalente
--
with top3rows as (
		select
			c.id_cliente,
			c.NOME ,
			c.COGNOME ,
			sum(f.imponibile) as fatturato
		from
			clienti c
		inner join ordini o
		on c.id_cliente = o.id_cliente
		inner join fatture_prova_new f
		on o.id_ordine = f.id_ordine and YEAR(f.data) = 2018
		group by
			c.ID_CLIENTE
		order by
			4 desc
		limit 3
),
min_to_consider as (
	select min(fatturato) as min_fatturato from top3rows
)
select
	c.id_cliente,
	c.NOME ,
	c.COGNOME ,
	sum(f.imponibile) as fatturato
from
	clienti c
inner join ordini o
on c.id_cliente = o.id_cliente
inner join fatture_prova_new f
on o.id_ordine = f.id_ordine
	and YEAR(f.data) = 2018
group by
	c.ID_CLIENTE
having fatturato >= (select min_fatturato from min_to_consider)
order by
	4 desc
limit 3;



