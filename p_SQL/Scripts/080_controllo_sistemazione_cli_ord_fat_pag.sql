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
'DATABASE FATTURE CORROTTO Fatturato > Ordinato',
( select sum(o.valore) from ordini o) as ordinato,
( select sum(f.imponibile) from fatture f) as fatturato,
(select round(sum(o.valore)) from ordini o) -
(select round(sum(f.imponibile)) from fatture f) as differenza
from dual;

--
-- tutti gli ordini hanno fattura?
--
select count(*)
from fatture f
left join ordini o
on f.ID_ORDINE = o.ID_ORDINE 
where o.ID_ORDINE is null;

--
-- esistono fatture senza ordini?
--
select count(*)
from fatture f
right join ordini o
on f.ID_ORDINE = o.ID_ORDINE 
where f.ID_FATTURA is null;

-- controllare che tutti i clienti abbiano fatturato i loro ordini
-- elenco ordini ordinato=fatturato, già OK
select
c.id_cliente,
c.nome,
c.cognome,
o.id_ordine,
o.valore,
sum(f.imponibile) as fatturato
from ordini o
inner join fatture f
on o.id_ordine = f.id_ordine
inner join clienti c
on c.id_cliente = o.id_cliente
group by c.id_cliente, o.id_ordine
having o.valore = sum(f.imponibile);

-- elenco ordini ordinato != fatturato, da sistemare
select
c.id_cliente,
c.nome,
c.cognome,
o.id_ordine,
o.valore,
sum(f.imponibile) as fatturato
from ordini o
inner join fatture f
on o.id_ordine = f.id_ordine
inner join clienti c
on c.id_cliente = o.id_cliente
group by c.id_cliente, o.id_ordine
having o.valore != sum(f.imponibile);

-- vi sono ordini che sono stati fatturati in più
-- da investigare ....
select o.id_ordine, o.valore, count(f.id_fattura), sum(f.imponibile), min(id_fattura)
from ordini o
inner join fatture f
on f.ID_ORDINE  = o.ID_ORDINE
group by id_ordine
having count(id_fattura) > 1
order by 3 asc;


-- le uniche fatture corrette sono quelle fatturate a copertura totale di un ordine
select count(*) from (
select o.id_ordine, o.valore, count(f.id_fattura), sum(f.imponibile)
from ordini o
inner join fatture f
on f.ID_ORDINE  = o.ID_ORDINE
group by id_ordine
having COUNT(f.id_fattura) = 1 
) xx;

-- fatture che coprono 2 volte il valore di un ordine
select count(*) from (
select o.id_ordine, o.valore, count(f.id_fattura), sum(f.imponibile)
from ordini o
inner join fatture f
on f.ID_ORDINE  = o.ID_ORDINE
group by id_ordine
having COUNT(f.id_fattura) = 2 
) xx;

-- fatture che coprono 3 volte il valore di un ordine
select count(*) from (
select o.id_ordine, o.valore, count(f.id_fattura), sum(f.imponibile)
from ordini o
inner join fatture f
on f.ID_ORDINE  = o.ID_ORDINE
group by id_ordine
having COUNT(f.id_fattura) = 3 
) xx;

-- fatture che coprono 4 volte il valore di un ordine
select count(*) from (
select o.id_ordine, o.valore, count(f.id_fattura), sum(f.imponibile)
from ordini o
inner join fatture f
on f.ID_ORDINE  = o.ID_ORDINE
group by id_ordine
having COUNT(f.id_fattura) = 4 
) xx;

--  2016 fatture/ordini sono ok
--  1975 fatture/ordini coprono il doppio del valore ordine
--  4936 fatture/ordini coprono il triplo del valore ordine
--  5662 fatture/ordini coprono il quadruplo del valore ordine

select sum(imponibile) from fatture;
select sum(imponibile) from pagamenti;


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
 having
	count(id_fattura) > 1
order by
	3 asc;

-- 
-- VIEW sinottica per sistemazione fatture
-- ripetizioni = numero delle fatture che hanno causato un erroneo conteggio
-- min_id_fattura = numero della fattura corretta (da tenere in caso di storno delle altre)
-- la view comprende anche le fattura che coprono 1 <==> 1 gli ordini (ordini coperti da una singola fattura)
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
order by
	3 asc;

-- 
-- VIEW sinottica per sistemazione fatture
-- ripetizioni = numero delle fatture che hanno causato un erroneo conteggio
-- min_id_fattura = numero della fattura corretta (da tenere in caso di storno delle altre)
-- la view esclude anche le fattura che coprono 1 <==> 1 gli ordini (ordini coperti da una singola fattura)
-- create or replace view view_confronto_ordini_fatture_totale_count_gt1 as
create or replace view view_confronto_ordini_fatture_totale_excl as
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


-- controllo coerenza views
select count(*) from view_confronto_ordini_fatture_totale where ripetizioni > 1;
select count(*) from view_confronto_ordini_fatture_totale_excl;


select c.id_cliente, c.nome, c.COGNOME, v.min_id_fattura 
from clienti c
inner join ordini o
on o.id_cliente = c.id_cliente
inner join view_confronto_ordini_fatture_totale v
on v.id_ordine = o.id_ordine
;



/*********** 
 * 	DELETE DELLE FATTURE 
 * Commentato perchè poco ortodosso
 * 
CREATE TABLE fatture_prova select * FROM fatture;
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

**************************/

-- 
--  UPDATE FATTURE
-- 
-- VIEW sinottica per sistemazione fatture
-- ripetizioni = numero delle fatture che hanno causato un erroneo conteggio
-- min_id_fattura = numero della fattura corretta (da tenere in caso di storno delle altre)
-- valore_splittato memorizzato senza decimali per facilitare le operazioni di sistemazione arrotondamenti
-- la view esclude anche le fattura che coprono 1 <==> 1 gli ordini (ordini coperti da una singola fattura)
-- create or replace view view_confronto_ordini_fatture_totale_count_gt1 as
create or replace view view_fattura_prova as
select
	o.id_ordine,
	o.valore,
	count(f.id_fattura) as ripetizioni,
	round(o.valore / count(f.id_fattura), 2) as valore_splittato,
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
	count(f.id_fattura) > 1
order by
	3 asc;


-- creazione tabelle temporanee per SISTEMAZIONE FATTURE
drop table if exists fatture_prova_new;
CREATE TABLE fatture_prova_new 
   (ID_FATTURA int, 
	DATA DATETIME, 
	IMPORTO decimal(10,2), 
	IMPONIBILE decimal(10,2), 
	IVA decimal(10,2), 
	ID_ORDINE int
   );
insert into fatture_prova_new select * FROM fatture;

-- 2) update delle fatture con importi divisi
-- 2) arrotontamento su ultima/prima fattura
-- 2) update dei pagamenti where id_fattura match in pagamenti;
select * from view_fattura_prova vfp;
select * from fatture_prova_new fpn;

-- conteggio update da fare per ripetizione
select ripetizioni, count(*) from fatture_prova_new fpn
inner join view_fattura_prova vfp
on fpn.ID_ORDINE = vfp.id_ordine --  and vfp.ripetizioni > 1
group by vfp.ripetizioni;


update
	fatture_prova_new fpn
inner join view_fattura_prova vfp
on
	fpn.ID_ORDINE = vfp.id_ordine -- and vfp.ripetizioni > 1
set
	fpn.imponibile = vfp.valore_splittato,
	fpn.iva = round(vfp.valore_splittato * 0.22, 2),
	fpn.importo = vfp.valore_splittato + round(vfp.valore_splittato * 0.22, 2);


-- query di controllo per verificare gli arrotondamenti
select
(select sum(o.valore) from ordini o) as ordinato,
(select sum(f.imponibile) from fatture_prova_new f) as fatturato,
(select round(sum(o.valore)) from ordini o) - (select round(sum(f.imponibile)) from fatture_prova_new f) as differenza
from dual;

-- ARROTONDAMENTI

-- View con i nuovi valori per determinare gli arrotondamenti necessari
create or replace
view view_fatture_after_update as
select
	o.id_ordine,
	o.valore,
	max(f.id_fattura) as maxfatt,
	sum(f.imponibile),
	o.valore - sum(f.imponibile) as delta
from
	fatture_prova_new f
inner join ordini o
on
	o.id_ordine = f.id_ordine
group by
	id_ordine
having
	delta <> 0
order by
	o.id_ordine asc
;

select count(*) from view_fatture_after_update;

select * from view_fatture_after_update;

-- Si procede agli update di sistemazione arrotondamenti 
-- sull'ultima fattura di ciascun ordine
update fatture_prova_new f
inner join view_fatture_after_update t
on f.id_fattura = t.maxfatt
set f.imponibile = f.imponibile + t.delta,
    f.iva = round(f.imponibile * 0.22, 2),
	f.importo = f.imponibile + f.iva;

--
-- FINE DI VENERDI' 26/06/2026
--

-- query di controllo per verificare i pagamenti
select
(select sum(o.valore) from ordini o) as ordinato,
(select sum(f.imponibile) from fatture_prova_new f) as fatturato,
(select round(sum(o.valore)) from ordini o) - (select round(sum(f.imponibile)) from fatture_prova_new f) as differenza,
(select sum(p.imponibile) from pagamenti p) as pagato,
(select sum(fe.imponibile) from fatture fe) as fatturato_errato
from dual;

-- 
-- sistemazione pagamenti
-- 
create or replace view view_sistemazione_pagamenti as
select 
	fe.id_fattura,
	fe.imponibile as fatturato_errato,
	fpn.imponibile as fatturato_corretto,
	fe.imponibile / fpn.imponibile,
	vfp.ripetizioni,
	p.id_pagamento,
	p.imponibile as imponibile_errato,
	round(p.imponibile / vfp.ripetizioni, 2) as imponibile_corretto
from fatture fe
inner join ordini o
on fe.id_ordine = o.id_ordine 
inner join view_fattura_prova vfp
on o.id_ordine = vfp.id_ordine 
inner join fatture_prova_new fpn 
on fe.id_fattura = fpn.id_fattura 
inner join pagamenti p
on fe.id_fattura = p.id_fattura
;

select count(*) from view_sistemazione_pagamenti;

drop table if exists pagamenti_sistemati;

CREATE TABLE pagamenti_sistemati
   (
    ID_PAGAMENTO int primary key,
	DATA DATETIME,
	IMPORTO decimal(10,2),
	IMPONIBILE decimal(10,2),
	IVA decimal(10,2),
	ID_FATTURA int
   );

insert into pagamenti_sistemati 
select * FROM pagamenti p
where p.id_pagamento in (select vsp.id_pagamento from view_sistemazione_pagamenti vsp)
;

select count(*) from pagamenti_sistemati;
select count(id_pagamento) from view_sistemazione_pagamenti;

-- 
-- QUI da SISTEMARE PAGAMENTI
-- 
-- qualcosa va ale nella logica di sistemazione pagamenti
-- non tutte le fatture dovrebbero essere pagate al 100%
-- ma non vi dovrebbero essere fatture sovra-pagate (vedi query riga 442)
--
update pagamenti_sistemati ps
inner join view_sistemazione_pagamenti vsp
on ps.id_pagamento = vsp.id_pagamento
set ps.imponibile = ps.imponibile / vsp.ripetizioni,
ps.iva = round(ps.imponibile * 0.22, 2),
ps.importo = ps.imponibile + ps.iva;

-- query di controllo per verificare i pagamenti
select
(select sum(o.valore) from ordini o) as ordinato,
(select sum(f.imponibile) from fatture_prova_new f) as fatturato,
(select round(sum(o.valore)) from ordini o) - (select round(sum(f.imponibile)) from fatture_prova_new f) as differenza,
(select sum(ps.imponibile) from pagamenti_sistemati ps) as pagato
from dual;

-- per velocizzare query inizio
alter table fatture_prova_new add primary key (id_fattura);
-- per velocizzare query fine

select 
o.id_ordine,
fpn.id_fattura,
fpn.imponibile,
count(*) as conteggio,
max(ps.id_pagamento),
sum(ps.imponibile),
fpn.imponibile - sum(ps.imponibile)
from 
ordini o
inner join fatture_prova_new fpn
on fpn.id_ordine = o.id_ordine 
inner join pagamenti_sistemati ps 
on ps.id_fattura = fpn.id_fattura
group by o.id_ordine, fpn.id_fattura
having (fpn.imponibile - sum(ps.imponibile)) <> 0;



