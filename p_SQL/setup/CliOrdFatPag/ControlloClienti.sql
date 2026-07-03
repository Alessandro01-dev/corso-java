
select
    (select count(*) from clienti) as 'Clienti', 
    (select count(*) from ordini) as 'Ordini', 
    (select count(*) from fatture) as 'Fatture',
    (select count(*) from pagamenti) as 'Pagamenti'
    ;


select c.id_cliente, c.provincia, p.den_uts
from clienti c
left join province p
on p.SIGLA_AUTOMOBILISTICA = c.PROVINCIA 
;