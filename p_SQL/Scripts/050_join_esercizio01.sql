--
-- esercizi join
--

-- stampare l'elenco delle province con a fianco il nome della regione

select den_uts 'Provincia', den_reg 'Regione'
from province p 
join regioni r
on p.cod_reg = r.cod_reg;

--
-- elenco regioni, province, comuni del Lazio
--
select
	p.cod_reg as 'codice regione',
	r.den_reg as 'regione',
	p.cod_uts as 'codice provincia',
	p.den_uts as 'provincia',
	p.sigla_automobilistica as 'targa',
	c.pro_com as 'codice_comune',
	c.comune as 'comune'
from province p 
inner join regioni r
on p.cod_reg = r.cod_reg and r.den_reg = 'Lazio'
inner join comuni c 
on p.cod_uts = c.cod_uts and r.cod_reg = c.cod_reg;

-- si può fare anche così
select
	p.cod_reg as 'codice regione',
	r.den_reg as 'regione',
	p.cod_uts as 'codice provincia',
	p.den_uts as 'provincia',
	p.sigla_automobilistica as 'targa',
	c.pro_com as 'codice_comune',
	c.comune as 'comune'
from comuni c
inner join province p
on c.cod_uts = p.cod_uts
inner join regioni r
on p.cod_reg = r.cod_reg and r.den_reg = 'Lazio'


-- elencare i comuni del Centro Italia

-- senza join
select r.cod_rip
from ripartizioni r
where r.den_rip = 'Centro'

select count(*)
from comuni c 
where c.cod_rip = 3;

-- con join
select count(*) as 'Comuni del centro'
from ripartizioni r
inner join comuni c
on r.den_rip = 'Centro' and c.cod_rip = r.cod_rip;

select count(*)
from comuni c
inner join ripartizioni ri
where c.cod_rip = ri.cod_rip and den_rip = 'Centro';

select count(*)
from comuni c
where c.cod_rip = (
select ri.cod_rip 
from ripartizioni ri 
where den_rip= 'Centro'
);


-- quante sono le province del centro Italia
select count(*)
from province p
join regioni r
on p.cod_reg = r.cod_reg
join ripartizioni ri
on r.cod_rip = ri.cod_rip and den_rip = 'Centro';

select count(distinct p.den_uts)
from province p
join comuni c
on c.cod_uts = p.cod_uts
join ripartizioni r
on c.cod_rip = r.cod_rip and den_rip = 'Centro';


-- elencare
-- customer, ripartizione, regione, provincia, comune

select 
	cu.name 'Nome cliente', 
	cu.city 'Città cliente', 
	co.comune 'Comune', 
	pr.den_uts 'Provincia', 
	re.den_reg 'Regione', 
	ri.den_rip 'Ripartizione'
from customers cu
join comuni co
on cu.city = co.comune
join province pr
on co.cod_uts = pr.cod_uts
join regioni re
on pr.cod_reg = re.cod_reg
join ripartizioni ri
on re.cod_rip = ri.cod_rip;

alter table customers
add constraint fk_customers_comuni
foreign key (city)
references comuni(comune);





