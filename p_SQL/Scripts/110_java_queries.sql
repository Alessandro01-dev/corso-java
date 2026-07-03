--
-- Ripartizioni/Regioni/Province
--
select
	ri.cod_rip,
	ri.den_rip,
	re.cod_reg,
	re.den_reg,
	pr.cod_uts,
	pr.den_uts
from
	ripartizioni ri
inner join regioni re
on
	re.cod_rip = ri.cod_rip
inner join province pr
on
	pr.cod_reg = re.cod_reg
	;