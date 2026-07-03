--
-- raggruppamenti
--

select count(*) from province;

-- quante sono  le province per regione
select p.cod_reg, r.den_reg, count(*) 
from province p
inner join regioni r
on p.COD_REG = r.COD_REG 
group by p.cod_reg;

-- seleziona il risultato in base al valore di un aggregazione
select p.cod_reg, r.den_reg, count(*) 
from province p
inner join regioni r
on p.COD_REG = r.COD_REG 
group by p.cod_reg
having count(*) >= 10;

-- ricerca quali sono i comuni con il medesimo nome
select comune, count(comune)
from comuni 
group by comune
having count(comune) > 1;

select count(*) 
from customers
where city not in (
	select comune
	from comuni 
	group by comune
	having count(comune) > 1
	
-- stampare regione, provincia, comune
-- dei comuni con nome duplicato
-- (AA)
select cod_reg, cod_uts, pro_com, comune from comuni
where comune in (
select comune -- , count(comune)
from comuni
group by comune
having count(comune) > 1);

select c.cod_reg, r.den_reg, c.cod_uts, c.pro_com, c.comune
from comuni c
inner join regioni r
on c.cod_reg = r.cod_reg
where c.comune in (
select comune -- , count(comune)
from comuni
group by comune
having count(comune) > 1);

select c.cod_reg, r.den_reg, c.cod_uts, p.den_uts, c.pro_com, c.comune
from comuni c
inner join regioni r
on c.cod_reg = r.cod_reg
inner join province p
on c.cod_uts = p.cod_uts
where c.comune in (
select comune -- , count(comune)
from comuni
group by comune
having count(comune) > 1);

-- select (AA)
select cod_reg, cod_uts, pro_com, comune from comuni
where comune in (
select comune -- , count(comune)
from comuni
group by comune
having count(comune) > 1);

-- revisione select (AA)
select c.cod_reg, r.den_reg, c.cod_uts, p.den_uts, c.pro_com, c.comune 
from comuni c
inner join (
	select comune, count(comune)
	from comuni
	group by comune
	having count(comune) > 1
) cd 
on c.comune = cd.comune
inner join regioni r
on c.cod_reg = r.cod_reg
inner join province p
on c.COD_UTS = p.COD_UTS 
;	

-- revisione select (AA) CTE
with cd as 
(
	select comune, count(comune)
	from comuni
	group by comune
	having count(comune) > 1
) -- ,(....), (....)
select c.cod_reg, r.den_reg, c.cod_uts, p.den_uts, c.pro_com, c.comune 
from comuni c
inner join cd
on c.comune = cd.comune
inner join regioni r
on c.cod_reg = r.cod_reg
inner join province p
on c.COD_UTS = p.COD_UTS 
;