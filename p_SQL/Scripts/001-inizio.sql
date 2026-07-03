-- 001-inizio

SELECT version();

drop table if exists prova;

create table prova (
	codice int,
	descrizione varchar(100)
);

insert into prova (codice, descrizione) values (10, 'articolo 10');
insert into prova (codice, descrizione) values (20, "articolo 20");

select codice, descrizione from prova where descrizione = 'articolo 20';
select codice, descrizione from prova where descrizione = 'Articolo 20';