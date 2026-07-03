--
-- query generiche su db Italia dopo la creazione delle FK
--

select * from regioni;

-- impossibile eliminare perché c'è il constraint
/*
delete from regioni where den_reg = 'Liguria';
delete from province where den_uts = 'Roma';
*/

-- esercizi
select * from comuni where SIGLA_AUTOMOBILISTICA not in (
select SIGLA_AUTOMOBILISTICA from province);

select * from province where SIGLA_AUTOMOBILISTICA not in (
select distinct SIGLA_AUTOMOBILISTICA from comuni);

-- operatore 'like':
select * from regioni
where den_reg like '%e';

select * from regioni
where den_reg like 'e%';

select * from regioni
where den_reg like '%e%';