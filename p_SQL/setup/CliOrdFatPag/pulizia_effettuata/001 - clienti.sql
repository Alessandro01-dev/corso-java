drop table if exists pagamenti;

drop table if exists fatture;

drop table if exists ordini;

drop table if exists clienti;


CREATE TABLE clienti
   (
    ID_CLIENTE int auto_increment,
	NOME VARCHAR(50),
	COGNOME VARCHAR(50),
	EMAIL VARCHAR(50),
	INDIRIZZO VARCHAR(100),
	CITTA VARCHAR(50),
	PROVINCIA VARCHAR(4),
	CAP varchar(5),
	primary key(id_cliente)
   );



CREATE TABLE ordini -- generare da 10 a 20 ordini per cliente
   (
    ID_ORDINE int auto_increment, -- null in java java.lang.Integer
	DATA DATETIME, --  '2020-MM-GG 00:00:00' MM random compreso tra 1 e 12, GG random compreso tra 5 e 25 in java tipo String
	VALORE decimal(10,2),  -- random compreso tra 10000 e 20000 in java double
	ID_CLIENTE int, -- da cliente in esame
	primary key(id_ordine)
   );



CREATE TABLE fatture
   (
    ID_FATTURA int auto_increment, -- progressivo
	DATA DATETIME, -- da ordini ma ....
	IMPORTO decimal(10,2), -- iva + imponibile
	IMPONIBILE decimal(10,2), -- da valore ordine ma....
	IVA decimal(10,2), -- calcolo sulla base 22%
	ID_ORDINE int, -- da tabella ordini
	primary key(id_fattura)
   );

   -- se id cliente è pari ==> 2 fatture a 30, 60 gg --> 50% importo
   -- se id cliente multiplo di 3 ==> 3 fatture a 30, 60, 90 gg
   -- in ogni altro caso ==> 1 fattura a 30 gg. per ogni ordine



CREATE TABLE pagamenti
   (
    ID_PAGAMENTO int auto_increment,
	DATA DATETIME,
	IMPORTO decimal(10,2),
	IMPONIBILE decimal(10,2),
	IVA decimal(10,2),
	ID_FATTURA int,
	primary key(id_pagamento)
   );
/*
alter table ordini add constraint fk_ordini_clienti
foreign key(ID_CLIENTE) references clienti(ID_CLIENTE);

alter table fatture add constraint fk_fatture_ordini
foreign key(ID_ORDINE) references ordini(ID_ORDINE);

alter table pagamenti add constraint fk_pagamenti_fatture
foreign key(ID_FATTURA) references fatture(ID_FATTURA);
*/
Insert into clienti (ID_CLIENTE,NOME,COGNOME,EMAIL,INDIRIZZO,CITTA,PROVINCIA,CAP) values ('1','Giuseppe','Verdi','gverdi@aol.com','Roncole Verdi','Busseto','PR','43011');
Insert into clienti (ID_CLIENTE,NOME,COGNOME,EMAIL,INDIRIZZO,CITTA,PROVINCIA,CAP) values ('2','Gioacchino','Rossini','gioacchino@libero.it','Via del Duomo','Pesaro','PU','61122');
Insert into clienti (ID_CLIENTE,NOME,COGNOME,EMAIL,INDIRIZZO,CITTA,PROVINCIA,CAP) values ('3','Giacomo','Puccini','gpuccini@gmail.com','Corte San Lorenzo, 9 ','Lucca','LU','55100');
Insert into clienti (ID_CLIENTE,NOME,COGNOME,EMAIL,INDIRIZZO,CITTA,PROVINCIA,CAP) values ('4','Gaetano','Donizetti','gaetano@walla.com','Via Don Luigi Palazzolo, 88','Bergamo','BG','24122');
Insert into clienti (ID_CLIENTE,NOME,COGNOME,EMAIL,INDIRIZZO,CITTA,PROVINCIA,CAP) values ('5','Vincenzo','Bellini','bellini@bellini.org','Piazza San Francesco d’Assisi, 3','Catania','CT','95100');
Insert into clienti (ID_CLIENTE,NOME,COGNOME,EMAIL,INDIRIZZO,CITTA,PROVINCIA,CAP) values ('6','Antonio','Vivaldi','antonio.vivaldi@vivaldi.com','Rion del boh, 33','Venezia','VE','30100');
Insert into clienti (ID_CLIENTE,NOME,COGNOME,EMAIL,INDIRIZZO,CITTA,PROVINCIA,CAP) values ('7','Domenico','Scarlatti','dscarlatti@mail.partenope.it','Rion del boh, 33','Napoli','NA','80100');
Insert into clienti (ID_CLIENTE,NOME,COGNOME,EMAIL,INDIRIZZO,CITTA,PROVINCIA,CAP) values ('8','Claudio','Monteverdi','34566@libero.it','Piazza del Duomo, 1','Cremona','CR','26100');
Insert into clienti (ID_CLIENTE,NOME,COGNOME,EMAIL,INDIRIZZO,CITTA,PROVINCIA,CAP) values ('9','Amilcare','Ponchielli','aponchielli@aol.com','Piazza Revellino, 3','Paderno Ponchielli','CR','26024');
Insert into clienti (ID_CLIENTE,NOME,COGNOME,EMAIL,INDIRIZZO,CITTA,PROVINCIA,CAP) values ('10','Arcangelo','Corelli','acor@spalfans.it','Piazza Roma, 18','Fusignano','RA','48032');
Insert into clienti (ID_CLIENTE,NOME,COGNOME,EMAIL,INDIRIZZO,CITTA,PROVINCIA,CAP) values ('11','Tommaso','Albinoni','albinoni@gmail.com','Via Roma','Castione della Presolana','BG','24020');
Insert into clienti (ID_CLIENTE,NOME,COGNOME,EMAIL,INDIRIZZO,CITTA,PROVINCIA,CAP) values ('12','Giovanni Battista','Pergolesi','gbattista.draghi@jesimusiva.org','Piazza Ghislieri, 4','Jesi','AN','60035');
Insert into clienti (ID_CLIENTE,NOME,COGNOME,EMAIL,INDIRIZZO,CITTA,PROVINCIA,CAP) values ('13','Vincenzo','Capecelatro','vin.cap@libero.it','Via Po, 22','Napoli','NA','80100');
Insert into clienti (ID_CLIENTE,NOME,COGNOME,EMAIL,INDIRIZZO,CITTA,PROVINCIA,CAP) values ('14','Francesco','Cilea','fcilea@cilea.it','Via Cilea, 1','Palmi','RC','89015');
Insert into clienti (ID_CLIENTE,NOME,COGNOME,EMAIL,INDIRIZZO,CITTA,PROVINCIA,CAP) values ('15','Arrigo','Boito','arrigob@aol.com','Via Cavour, 17','Padova','PD','35100');
Insert into clienti (ID_CLIENTE,NOME,COGNOME,EMAIL,INDIRIZZO,CITTA,PROVINCIA,CAP) values ('16','Luigi','Cherubini','lcherubini@gmail.com','Via Ricasoli, 10','Firenze','FI','50100');
Insert into clienti (ID_CLIENTE,NOME,COGNOME,EMAIL,INDIRIZZO,CITTA,PROVINCIA,CAP) values ('17','Giovanni Pierluigi','Da Palestrina','gpp@gmail.com','Via Cenreo, 2','Palestrina','RM','00036');
Insert into clienti (ID_CLIENTE,NOME,COGNOME,EMAIL,INDIRIZZO,CITTA,PROVINCIA,CAP) values ('18','Tarquinia','Molza','tarquinia@libero.it','Via Parma, 72','Modena','MO','41121');


