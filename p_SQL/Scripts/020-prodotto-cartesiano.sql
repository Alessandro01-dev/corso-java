--
-- esempio di prodotto cartesiano
--
drop table if exists A;
drop table if exists B;

create table A (
campo char(1)
);

create table B (
campo char(1)
);

insert into A (campo) values ('A'), ('B'), ('C'), ('D'), ('E');
insert into B (campo) values ('U'), ('V'), ('X'), ('Y'), ('Z');

select campo from A;
select campo from B;

-- prodotto cartesiano
select A.campo, B.campo  
from A, B;

select ta.campo, tb.campo  
from A as ta, B as tb;

select ta.campo, tb.campo
from A ta, A tb;

select ta.campo, tb.campo
from A ta, A tb
where ta.campo = tb.campo;

select ta.campo, tb.campo
from A ta, A tb
where ta.campo != tb.campo;

-- esercizio creare il calendario del campionato di calcio
drop table if exists squadre;
create table squadre (
squadra varchar(50) -- squadre.squadra
);

insert into squadre (squadra) values
('Verona'),('Udinese'),('Torino'),('Sassuolo'),('Roma'),('Pisa'),
('Parma'),('Napoli'),('Milan'),('Lecce'),('Lazio'),('Juventus'),
('Inter'),('Genoa'),('Fiorentina'),('Cremonese'),('Como'),('Cagliari'),
('Bologna'),('Atalanta');

select squadreA.squadra as 'Squadre A', squadreB.squadra as 'Squadre B'
from squadre squadreA, squadre squadreB
where squadreA.squadra != squadreB.squadra;

select count(*) from squadre;
select count(*) * count(*) from squadre; -- con i doppioni
select count(*) * count(*) - count(*) from squadre; -- senza i doppioni

truncate B;
insert into B (campo) values ('C'), ('D'), ('E'), ('F'), ('G');

select campo from A
union -- union insiemistica
select campo from B;

select campo from A
union all -- qui i campi vengono messi anche se doppioni
select campo from B;

select campo from A
intersect -- questo prende l'intersezione
select campo from B;

select campo from A
except -- prende di A tutto quello che non è in B
select campo from B;

select campo from B
except -- prende di B tutto quello che non è in A
select campo from A;

