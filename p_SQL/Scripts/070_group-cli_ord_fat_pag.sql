--
-- raggruppamenti
--

-- quanti ordini per ogni cliente
select c.id_cliente, c.nome, c.cognome, count(o.id_ordine)
from clienti c
inner join ordini o
on c.id_cliente = o.id_cliente
group by c.id_cliente
order by 4 desc
;

select now(), year(now()), month(now()), dayofmonth(now()) from dual;

select distinct concat('',year(data)) from ordini;

-- quanti ordini in totale sono stati fatti per anno
select concat('',year(o.data)) as 'Anno', count(o.id_ordine)
from ordini o
group by concat('',year(o.data));

-- per selezionare gli ordini solo di un anno specifico:
select concat('',year(o.data)) as 'Anno', count(o.id_ordine)
from ordini o
where year(o.data) = 2016
group by concat('',year(o.data));

-- quanti ordini in totale sono stati fatti per clienti/anno
select concat('',year(o.data)) as 'Anno', c.id_cliente as 'ID Cliente', c.cognome as 'Cognome Cliente', count(o.id_ordine) as 'Numero ordini'
from ordini o
inner join clienti c
on o.id_cliente = c.id_cliente
group by concat('',year(o.data)), c.id_cliente;

-- totale ordinato per anno significa:
-- 1) raggruppare gli ordini per anno
-- 2) sommare il valore dell'ordine anno per anno
select year(o.data), sum(o.valore)
from ordini o
group by year(o.data);

select year(o.data), sum(o.valore) as 'Valore', count(o.id_ordine) as '# ordine'
from ordini o
group by year(o.data);

select year(o.data), sum(o.valore) as 'Valore', count(o.id_ordine) as '# ordine',
sum(o.valore)/count(o.id_ordine) as 'media', avg(o.valore) as 'avg'
from ordini o
group by year(o.data);

select
	year(data),
	sum(o.valore) as 'valore' ,
	count(o.id_ordine) as '# ordini',
	sum(o.valore)/ count(o.id_ordine) as 'media',
	avg(o.valore) as 'avg',
	max(o.valore) as 'massimo valore',
	min(o.valore) as 'minimo valore'
from
	ordini o
group by
	year(data);

-- formattato con format:
select
	year(data),
	format(sum(o.valore), 2) as 'valore' ,
	count(o.id_ordine) as '# ordini',
	format(sum(o.valore)/ count(o.id_ordine), 2) as 'media',
	format(avg(o.valore), 2) as 'avg',
	format(max(o.valore), 2) as 'massimo valore',
	format(min(o.valore), 2) as 'minimo valore'
from
	ordini o
group by
	year(data);


-- totale ordinato per cliente
select c.id_cliente, c.nome, c.cognome, sum(o.valore)
from clienti c
inner join ordini o
on o.id_cliente = c.id_cliente
group by c.id_cliente;



-- calcolare per ogni cliente/anno
-- sum, avg, min, max valore ordini
select
	concat('',year(o.data)) as 'Anno ordine',
	c.id_cliente as 'Id cliente',
	c.nome as 'Nome cliente',
	c.cognome as 'Cognome cliente',
	format(sum(o.valore), 2) as 'Somma valore ordini',
	format(avg(o.valore), 2) as 'Media valore ordini',
	format(min(o.valore), 2) as 'Minimo valore ordini',
	format(max(o.valore), 2) as 'Massimo valore ordini'
from
	clienti c
inner join ordini o
on
	o.id_cliente = c.id_cliente
group by
	c.id_cliente, concat('',year(o.data))
order by
	concat('',year(o.data)) desc, c.id_cliente;


-- controllare che tutti i clienti abbiano fatturato i loro ordini
-- (tolleranza 100€ accettabile)
-- 1) somma(ordinato) = somma(fatturato) ==> somma(ordinato) - somma(fatturato) = 0;

select 
( select sum(o.valore) from ordini o) as ordinato,
( select sum(f.imponibile) from fatture f) as fatturato
from dual;

select round(sum(o.valore)) from ordini o;

select round(sum(f.imponibile)) from fatture f;

select 
( select sum(o.valore) from ordini o) as ordinato,
( select sum(f.imponibile) from fatture f) as fatturato,
(select round(sum(o.valore)) from ordini o) -
(select round(sum(f.imponibile)) from fatture f) as differenza
from dual;

--
-- tutti gli ordini hanno fattura?
-- esistono fatture senza ordini?
--
select count(*)
from fatture f
left join ordini o
on f.ID_ORDINE = o.ID_ORDINE 
where o.ID_ORDINE is null;

select count(*)
from fatture f
right join ordini o
on f.ID_ORDINE = o.ID_ORDINE 
where f.ID_FATTURA is null;

-- controllare che tutti i clienti abbiano fatturato i loro ordini
-- mia select:
select
c.id_cliente,
c.nome,
c.cognome,
o.id_ordine,
o.valore,
f.id_ordine,
f.id_fattura,
f.imponibile
from ordini o
inner join fatture f
on o.id_ordine = f.id_ordine
inner join clienti c
on c.id_cliente = o.id_cliente;
-- group by o.id_ordine, c.id_cliente
-- having sum(o.valore) = sum(f.imponibile);

-- vi sono ordini che sono stati fatturati in più
-- da investigare ....
select o.id_ordine, o.valore, count(f.id_fattura), sum(f.imponibile), min(id_fattura)
from ordini o
inner join fatture f
on f.ID_ORDINE  = o.ID_ORDINE
group by id_ordine
having count(id_fattura) > 1
order by 3 asc;

select count(*) from (
select o.id_ordine, o.valore, count(f.id_fattura), sum(f.imponibile)
from ordini o
inner join fatture f
on f.ID_ORDINE  = o.ID_ORDINE
group by id_ordine
having COUNT(f.id_fattura) = 1 
) xx;

-- per 2016 fatture/ordini 

select sum(imponibile) from fatture;
select sum(imponibile) from pagamenti;

-- delete delle fatture multiple da questa select
-- ok per 5682 fatture
select
o.id_ordine,
f.id_fattura
from ordini o
inner join fatture f
on o.id_ordine = f.id_ordine
where o.id_ordine != f.id_fattura;

select count(*) 
from fatture 
where id_ordine = id_fattura;

select count(*)
from fatture f
inner join ordini o
on o.id_ordine = f.id_ordine
where f.id_fattura > 5681
;



select
	o.id_ordine,
	o.valore,
	count(f.id_fattura) as ripetizioni,
	o.valore / count(f.id_fattura) as 'valore split',
	sum(f.imponibile),
	min(id_fattura)
from
	ordini o
inner join fatture f
on
	f.ID_ORDINE = o.ID_ORDINE
group by
	id_ordine
-- having
	-- count(id_fattura) > 1
order by
	3 asc;

-- 
-- VIEW
--   
create or replace view view_confronto_ordini_fatture_totale as
select
	o.id_ordine,
	o.valore,
	count(f.id_fattura) as ripetizioni,
	o.valore / count(f.id_fattura) as valore_splittato,
	sum(f.imponibile) as imponibile,
	min(id_fattura) as min_id_fattura
from
	ordini o
inner join fatture f
on
	f.ID_ORDINE = o.ID_ORDINE
group by
	id_ordine
-- having
	-- count(id_fattura) > 1
order by
	3 asc;



create or replace view view_confronto_ordini_fatture_totale_count_gt1 as
select
	o.id_ordine,
	o.valore,
	count(f.id_fattura) as ripetizioni,
	o.valore / count(f.id_fattura) as valore_splittato,
	sum(f.imponibile) as imponibile,
	min(id_fattura) as min_id_fattura
from
	ordini o
inner join fatture f
on
	f.ID_ORDINE = o.ID_ORDINE
group by
	id_ordine
having
	count(id_fattura) > 1
order by
	3 asc;


select count(*) from view_confronto_ordini_fatture_totale
where ripetizioni > 1
;


select c.id_cliente, c.nome, c.COGNOME, v.min_id_fattura 
from clienti c
inner join ordini o
on o.id_cliente = c.id_cliente
inner join view_confronto_ordini_fatture_totale v
on v.id_ordine = o.id_ordine
;
-- SISTEMAZIONE FATTURE
CREATE TABLE fatture_prova select * FROM fatture;
CREATE TABLE fatture_prova_new select * FROM fatture;

create or replace
view view_fattura_prova as
select
	o.id_ordine,
	o.valore,
	count(f.id_fattura) as ripetizioni,
	o.valore / count(f.id_fattura) as valore_splittato,
	sum(f.imponibile) as imponibile,
	min(id_fattura) as min_id_fattura
from
	ordini o
inner join fatture f
on
	f.ID_ORDINE = o.ID_ORDINE
group by
	id_ordine
order by
	3 asc;



-- 1) drop delle foreign key su pagamenti
-- 1) delete id_fattura from fatture per key duplicate
-- 1) delete from pagamenti where id_fattura not in fatture;
-- 1) restore FK su pagamenti
select count(*) from fatture_prova f
where f.ID_FATTURA not in(
select v.min_id_fattura from view_fattura_prova v);

delete f from fatture_prova f
where f.ID_FATTURA not in(
select v.min_id_fattura from view_fattura_prova v);


select
( select sum(o.valore) from ordini o) as ordinato,
( select sum(f.imponibile) from fatture_prova f) as fatturato,
(select round(sum(o.valore)) from ordini o) -
(select round(sum(f.imponibile)) from fatture_prova f) as differenza
from dual;


-- 2) update delle fatture con importi divisi
-- 2) arrotontamento su ultima/prima fattura
-- 2) update dei pagamenti where id_fattura match in pagamenti;
select * from view_fattura_prova vfp;
select * from fatture_prova_new fpn;

-- count= 41406
-- aggiungere 2016
-- totale = 43422
select count(*) from fatture_prova_new fpn
inner join view_fattura_prova vfp
on fpn.ID_ORDINE = vfp.id_ordine
and vfp.ripetizioni > 1

update
	fatture_prova_new fpn
inner join view_fattura_prova vfp
on
	fpn.ID_ORDINE = vfp.id_ordine
	and vfp.ripetizioni > 1
set
	fpn.imponibile = Round(vfp.valore_splittato, 2),
	fpn.iva = round(vfp.valore_splittato * 0.22, 2),
	fpn.importo = fpn.imponibile + fpn.iva;

select
( select sum(o.valore) from ordini o) as ordinato,
( select sum(f.imponibile) from fatture_prova_new f) as fatturato,
(select round(sum(o.valore)) from ordini o) -
(select round(sum(f.imponibile)) from fatture_prova_new f) as differenza
from dual;

-- ARROTONDAMENTI

-- View con i nuovi valori

create or replace view tabellaUpdated as
select o.ID_ORDINE ORDINE,
COUNT(o.ID_ORDINE ) CONTO,
ROUND(o.VALORE/COUNT(o.ID_ORDINE),2) IMPONIBILE,
ROUND(o.VALORE/COUNT(o.ID_ORDINE)*0.22,2) IVA,
ROUND(o.VALORE/COUNT(o.ID_ORDINE),2) + ROUND(o.VALORE/COUNT(o.ID_ORDINE)*0.22,2) IMPORTO
from fatture f
inner join ordini o
on o.ID_ORDINE =f.ID_ORDINE
group by o.ID_ORDINE
having
count(id_fattura) > 1
order by o.ID_ORDINE asc
;

-- Si procede agli update

update fatture f
inner join tabellaupdated t
on f.ID_ORDINE=t.ORDINE
set f.IMPONIBILE=t.IMPONIBILE , f.IVA=t.IVA , f.IMPORTO=t.IMPORTO
;

-- Controllo

select c.NOME, c.COGNOME, c.ID_CLIENTE ID, o.VALORE 'Ordinato', o.ID_ORDINE 'Ordine', SUM(f.IMPONIBILE) 'Fatturato', f.ID_FATTURA 'Fattura'
from fatture f
inner join ordini o
on o.ID_ORDINE=f.ID_ORDINE
inner join clienti c
on c.ID_CLIENTE=o.ID_CLIENTE
where o.ID_ORDINE in (
select t.ORDINE from tabellacount t
where t.CONTO=3
)
group by ID, ORDINE
order by 4 desc, COGNOME asc
;


-- Si procede agli update

update fatture f
inner join tabellaupdated t
on f.ID_ORDINE=t.ORDINE
set f.IMPONIBILE=t.IMPONIBILE , f.IVA=t.IVA , f.IMPORTO=t.IMPORTO
;

-- Controllo

select c.NOME, c.COGNOME, c.ID_CLIENTE ID, o.VALORE 'Ordinato', o.ID_ORDINE 'Ordine', SUM(f.IMPONIBILE) 'Fatturato', f.ID_FATTURA 'Fattura'
from fatture f
inner join ordini o
on o.ID_ORDINE=f.ID_ORDINE
inner join clienti c
on c.ID_CLIENTE=o.ID_CLIENTE
where o.ID_ORDINE in (
select t.ORDINE from tabellacount t
where t.CONTO=3
)
group by ID, ORDINE
order by 4 desc, COGNOME asc
;


-- Ordine e Fattura diversi

select c.NOME, c.COGNOME, c.ID_CLIENTE ID, o.VALORE 'Ordinato', o.ID_ORDINE 'Ordine', SUM(f.IMPONIBILE) 'Fatturato', f.ID_FATTURA 'Fattura'
-- COUNT(*)
from fatture f
inner join ordini o
on o.ID_ORDINE=f.ID_ORDINE
inner join clienti c
on c.ID_CLIENTE=o.ID_CLIENTE
group by ID, ORDINE
HAVING o.VALORE != SUM(f.IMPONIBILE)
order by 4 desc, COGNOME asc
;

-- Ordine e Fattura diversi 2

create or replace view ValoriDiversi as
select c.NOME, c.COGNOME, c.ID_CLIENTE ID, o.VALORE 'Ordinato', o.ID_ORDINE 'Ordine', SUM(f.IMPONIBILE) 'Fatturato', f.ID_FATTURA 'Fattura'
from fatture f
inner join ordini o
on o.ID_ORDINE=f.ID_ORDINE
inner join clienti c
on c.ID_CLIENTE=o.ID_CLIENTE
group by ID, ORDINE
HAVING o.VALORE != SUM(f.IMPONIBILE)
order by 4 desc, COGNOME asc
;

-- Ordine e Fattura diversi

select COUNT(*) from ValoriDiversi
;





