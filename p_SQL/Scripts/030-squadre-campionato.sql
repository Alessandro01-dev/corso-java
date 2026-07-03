--
-- torneo di calcio
-- combinazioni
--

drop table if exists squadre;

CREATE TABLE squadre (
id INT NOT NULL AUTO_INCREMENT,
squadra VARCHAR(50) NOT NULL,
PRIMARY KEY (id),
UNIQUE KEY uq_squadra (squadra)
) ENGINE=InnoDB;

INSERT INTO squadre (squadra) VALUES
('Verona'),('Udinese'),('Torino'),('Sassuolo'),('Roma'),('Pisa'),
('Parma'),('Napoli'),('Milan'),('Lecce'),('Lazio'),('Juventus'),
('Inter'),('Genoa'),('Fiorentina'),('Cremonese'),('Como'),('Cagliari'),
('Bologna'),('Atalanta');

select * from squadre;

-- partite di torneo valide
select in_casa.squadra, fuori_casa.squadra
from squadre as in_casa, squadre as fuori_casa
where in_casa.squadra != fuori_casa.squadra
;

-- partite non valide
select in_casa.squadra, fuori_casa.squadra
from squadre in_casa, squadre fuori_casa
where in_casa.squadra = fuori_casa.squadra;

-- andata e ritorno su Atalanta e Lazio
select in_casa.squadra, fuori_casa.squadra
from squadre as in_casa, squadre as fuori_casa
where in_casa.squadra != fuori_casa.squadra AND
(
in_casa.squadra = 'Atalanta' and fuori_casa.squadra = 'Lazio'
or
in_casa.squadra = 'Lazio' and fuori_casa.squadra = 'Atalanta'
);

-- tutte le partite calcolate tramite id 
select in_casa.id, in_casa.squadra, fuori_casa.id, fuori_casa.squadra
from squadre as in_casa, squadre as fuori_casa
where in_casa.id != fuori_casa.id;

-- partite del girone di andata
select in_casa.id, in_casa.squadra, fuori_casa.id, fuori_casa.squadra
from squadre as in_casa, squadre as fuori_casa
where in_casa.id < fuori_casa.id;

-- partite del girone di ritorno
select in_casa.id, in_casa.squadra, fuori_casa.id, fuori_casa.squadra
from squadre as in_casa, squadre as fuori_casa
where in_casa.id > fuori_casa.id;














