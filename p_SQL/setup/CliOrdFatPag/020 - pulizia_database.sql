--
-- pulizia database
--

-- delete pagamenti (cliente con provincia non in elenco) 
-- numero pagamenti: 3338
-- tempo di esecuzione: 51s
delete p
from pagamenti p
join fatture f on f.ID_FATTURA = p.ID_FATTURA
join ordini o on o.ID_ORDINE = f.ID_ORDINE
join clienti c on c.ID_CLIENTE = o.ID_CLIENTE
where c.Provincia not in (select SIGLA_AUTOMOBILISTICA from province);

-- delete fatture (cliente con provincia non in elenco)
-- numero fatture: 2317
-- tempo di esecuzione: 10s
delete f
from fatture f
join ordini o on o.ID_ORDINE = f.ID_ORDINE
join clienti c on c.ID_CLIENTE = o.ID_CLIENTE
where c.Provincia not in (select SIGLA_AUTOMOBILISTICA from province);

-- delete ordini (cliente con provincia non in elenco) 
-- numero ordini: 890
-- tempo di esecuzione: 0.026s
delete o
from ordini o
join clienti c on c.ID_CLIENTE = o.ID_CLIENTE
where c.Provincia not in (select SIGLA_AUTOMOBILISTICA from province);

-- delete cliente con provincia non in elenco
-- numero cliente:
-- tempo di esecuzione: 0.003s
delete c
from clienti c
where c.Provincia not in (select SIGLA_AUTOMOBILISTICA from province);
