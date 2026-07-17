INSERT INTO students (first_name, last_name, email, phone, address, city, tax_id_code, date_of_birth) VALUES
('Mario', 'Rossi', 'mario.rossi@email.it', '+393331234567', 'Via Roma 10', 'Roma', 'RSSMRA95A01H501Z', '1995-01-01'),
('Laura', 'Bianchi', 'laura.bianchi@email.it', '+393342345678', 'Corso Milano 25', 'Milano', 'BNCHLR98B42F205A', '1998-02-02'),
('Giuseppe', 'Verdi', 'giuseppe.verdi@email.it', '+393353456789', 'Via Dante 5', 'Firenze', 'VRDGPP92C03D612Y', '1992-03-03'),
('Francesca', 'Ferrari', 'francesca.ferrari@email.it', '+393364567890', 'Via Torino 40', 'Torino', 'FRRFNC96D44L219X', '1996-04-04'),
('Alessandro', 'Russo', 'alessandro.russo@email.it', '+393375678901', 'Via Napoli 12', 'Napoli', 'RSSFBA94E05F839W', '1994-05-05'),
('Elena', 'Gallo', 'elena.gallo@email.it', '+393386789012', 'Piazza Bologna 3', 'Roma', 'GLLVNE99F46H501U', '1999-06-06'),
('Roberto', 'Esposito', 'roberto.esposito@email.it', '+393397890123', 'Via Garibaldi 8', 'Genova', 'SPSRRT93G07D969V', '1993-07-07'),
('Anna', 'Bruno', 'anna.bruno@email.it', '+393408901234', 'Via Veneto 15', 'Palermo', 'BRNNNA97H48G273T', '1997-08-08'),
('Stefano', 'Ricci', 'stefano.ricci@email.it', '+393419012345', 'Via Mazzini 22', 'Bologna', 'RCCSFN91I09A944S', '1991-09-09'),
('Chiara', 'Marini', 'chiara.marini@email.it', '+393420123456', 'Via Piave 7', 'Venezia', 'MRNCHR00A50L736R', '2000-01-10');

INSERT INTO teachers (first_name, last_name, email, phone, address, city, tax_id_code, date_of_birth) VALUES
('Giovanni', 'Pascoli', 'giovani.pascoli@scuola.it', '+39061111111', 'Via della Poesia 1', 'San Mauro', 'PSCGNN70A01H501A', '1970-01-01'),
('Maria', 'Montessori', 'maria.montessori@scuola.it', '+39022222222', 'Corso Scienza 4', 'Chiaravalle', 'MNTMRA75B42F205B', '1975-02-02'),
('Galileo', 'Galilei', 'galileo.galilei@scuola.it', '+39055333333', 'Via Astrologia 9', 'Pisa', 'GLLGLL65C03D612C', '1965-03-03'),
('Rita', 'Levi', 'rita.levi@scuola.it', '+39011444444', 'Via dei Neuroni 14', 'Torino', 'LVRRTI78D44L219D', '1978-04-04'),
('Enrico', 'Fermi', 'enrico.fermi@scuola.it', '+39081555555', 'Piazza Atomo 2', 'Roma', 'FRMNRC82E05F839E', '1982-05-05'),
('Giacomo', 'Leopardi', 'giacomo.leopardi@scuola.it', '+39071666666', 'Via del Colle 11', 'Recanati', 'LPRGCM80F06H501F', '1980-06-06'),
('Alessandro', 'Volta', 'alessandro.volta@scuola.it', '+39031777777', 'Via Pila 88', 'Como', 'VLTLSN73G07D969G', '1973-07-07'),
('Grazia', 'Deledda', 'grazia.deledda@scuola.it', '+39078888888', 'Via Nuoro 3', 'Nuoro', 'DLDGRZ85H48G273H', '1985-08-08'),
('Dante', 'Alighieri', 'dante.alighieri@scuola.it', '+39051999999', 'Via Inferno 100', 'Firenze', 'LGHDRN68I09A944I', '1968-09-09'),
('Margherita', 'Hack', 'margherita.hack@scuola.it', '+39040000000', 'Via delle Stelle 21', 'Trieste', 'HCKMGH77A50L736J', '1977-01-10');

INSERT INTO subjects (name) VALUES
('Matematica'),
('Fisica'),
('Chimica'),
('Storia'),
('Letteratura Italiana'),
('Inglese'),
('Informatica'),
('Biologia'),
('Filosofia'),
('Arte');

INSERT INTO courses (subject_id, teacher_id, is_available, max_student_number) VALUES
(1, 3, true, 25),   -- Matematica con Galileo Galilei
(2, 5, true, 20),   -- Fisica con Enrico Fermi
(3, 7, false, 15),  -- Chimica con Alessandro Volta
(4, 9, true, 30),   -- Storia con Dante Alighieri
(5, 1, true, 30),   -- Letteratura Italiana con Giovanni Pascoli
(6, 2, true, 25),   -- Inglese con Maria Montessori
(7, 4, true, 18),   -- Informatica con Rita Levi
(8, 10, false, 12), -- Biologia con Margherita Hack
(9, 6, true, 40),   -- Filosofia con Giacomo Leopardi
(10, 8, true, 22);  -- Arte con Grazia Deledda

INSERT INTO course_enrollments (student_id, course_id) VALUES
(1, 1),  -- Mario Rossi iscritto a Matematica
(1, 4),  -- Mario Rossi iscritto a Storia
(2, 1),  -- Laura Bianchi iscritta a Matematica
(2, 6),  -- Laura Bianchi iscritta a Inglese
(3, 2),  -- Giuseppe Verdi iscritto a Fisica
(4, 5),  -- Francesca Ferrari iscritta a Letteratura
(5, 7),  -- Alessandro Russo iscritto a Informatica
(6, 9),  -- Elena Gallo iscritta a Filosofia
(7, 2),  -- Roberto Esposito iscritto a Fisica
(8, 10); -- Anna Bruno iscritta a Arte
