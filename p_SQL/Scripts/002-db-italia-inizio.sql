select 'Ripartizioni' as Tabella, count(*) as 'conteggio delle righe' from ripartizioni
union
select 'Regioni', count(*) from regioni
union
select 'Province', count(*) from province
union
select 'Comuni', count(*) from comuni c
union
select 'Comuni caratteristiche', count(*) from comuni_caratteristiche cc 
union
select 'Comuni dimensioni', count(*) from comuni_dimensione cd 
;

/*
select * from meta_colonna where id_sorgente='regioni' and colname='COD_REG';
select * from meta_colonna where id_sorgente='regioni' and colname='COD_RIP';
select * from meta_colonna where id_sorgente='regioni' and colname='DEN_REG';
select * from meta_colonna where id_sorgente='regioni' and colname='TIPO_REG';
select * from meta_colonna where id_sorgente='regioni' and colname='DESC_TIPO_REG';
select * from meta_colonna where id_sorgente='regioni' and colname='COD_REG_FISCALE';
select * from meta_colonna where id_sorgente='regioni' and colname='COD_NUTS2_2024';
*/

/*
select *
from meta_colonna
where id_sorgente='regioni'
and colname='COD_REG' or colname='COD_RIP' or colname='DEN_REG' or colname='TIPO_REG' or colname='DESC_TIPO_REG' or colname='COD_REG_FISCALE' or colname='COD_NUTS2_2024');
*/

select id_sorgente as 'sorgente',
	colname as 'nome colonna',
	label as 'etichetta',
	guida
from meta_colonna
where id_sorgente='regioni'
and colname in ('COD_REG', 'COD_RIP', 'DEN_REG', 'TIPO_REG', 'DESC_TIPO_REG', 'COD_REG_FISCALE', 'COD_NUTS2_2024');

select 
	COD_REG as 'Codice Regione',
	COD_RIP as 'Codice Ripartizione geografica',
	DEN_REG as 'Regione',
	TIPO_REG as 'Flag tipo regione',
	DESC_TIPO_REG as 'Tipo regione',
	COD_REG_FISCALE as 'Codice Fiscale',
	COD_NUTS2_2024 as 'Codice NUTS2 2024'
from regioni;

select 
	COD_REG as 'Codice Regione',
	COD_RIP as 'Codice Ripartizione geografica',
	DEN_REG as 'Regione',
	TIPO_REG as 'Flag tipo regione',
	DESC_TIPO_REG as 'Tipo regione',
	COD_REG_FISCALE as 'Codice Fiscale',
	COD_NUTS2_2024 as 'Codice NUTS2 2024'
from regioni
where DEN_REG='Calabria'
;

select 
	COD_REG as 'Codice Regione',
	COD_RIP as 'Codice Ripartizione geografica',
	DEN_REG as 'Regione',
	TIPO_REG as 'Flag tipo regione',
	DESC_TIPO_REG as 'Tipo regione',
	COD_REG_FISCALE as 'Codice Fiscale',
	COD_NUTS2_2024 as 'Codice NUTS2 2024'
from regioni
where DEN_REG='Veneto' or DEN_REG='Campania' or DEN_REG='Puglia'
;

select * from province where DEN_UTS = 'Napoli';

select * from meta_colonna where id_sorgente='province_uts' and colname='DEN_UTS';

select * from province where COD_REG in (
select COD_REG from regioni where DEN_REG in ('Lombardia', 'Lazio'));

select DEN_UTS from province where COD_REG in(
select COD_REG from regioni where DEN_REG in(
select 'Veneto' from dual
union
select 'Campania' from dual
union
select 'Puglia' from dual
));

-- query separate:
/*
select COD_PROV_STORICO from province where DEN_UTS = 'Modena'
select 'Comuni', count(*) from comuni where COD_PROV_STORICO = '036'; 
*/

-- variante con in:
select 'Conteggio comuni', count(*) from comuni where COD_PROV_STORICO in (select COD_PROV_STORICO from province where DEN_UTS = 'Modena'); 

-- query separate:
/*
select COD_REG from regioni where DEN_REG = 'Emilia-Romagna';
select 'Comuni', count(*) from comuni where COD_REG = '08';
*/

-- variante con in:
select 'Conteggio comuni', count(*) from comuni where COD_REG in (select COD_REG from regioni where DEN_REG = 'Emilia-Romagna');

-- esercizio: elencare le province con i comuni che contengono "sa" in ordine di nome discendente.
-- versione (con solo il nome dei capoluoghi di provincia il cui nome contiene 'sa')
/*
select den_uts as 'Elenco Comuni' from province
where den_uts 
like '%sa%'
order by den_uts desc
;
*/

select count(distinct cod_uts) as 'Numero Province' from comuni
where comune 
like '%sa%'
order by 1 desc
;

select den_uts, cod_uts from province where cod_uts in(
select distinct cod_uts from comuni
where comune 
like '%sa%'
) order by den_uts desc;

-- comuni con 'sa' della provincia di Viterbo
select comune as 'Elenco Comuni' from comuni
where comune
like '%sa%' and cod_uts=56
;










