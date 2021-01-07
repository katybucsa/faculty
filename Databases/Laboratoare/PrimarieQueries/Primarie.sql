CREATE DATABASE Primarie 
USE Primarie

CREATE TABLE Manageri(
Mid INT PRIMARY KEY,
Nume VARCHAR(30),
Prenume VARCHAR(30))


CREATE TABLE Departamente(
Did INT FOREIGN KEY REFERENCES Manageri(Mid),
Nume VARCHAR(30) NOT NULL,
Descriere VARCHAR(50),
CONSTRAINT pk_ManagerDepartament PRIMARY KEY (Did))


CREATE TABLE Adrese(
Adid INT PRIMARY KEY,
Cod_Postal VARCHAR(20) NOT NULL,
Nume_Strada VARCHAR(30),
Numar_Strada INT CHECK(Numar_Strada>0),
Bloc VARCHAR(20),
Scara INT CHECK(Scara>0),
Numar INT NOT NULL Check(Numar>0))


CREATE TABLE Plati(
Pid INT PRIMARY KEY,
Suma FLOAT CHECK(Suma>0),
Tip VARCHAR(30))

CREATE TABLE Angajati(
Aid INT PRIMARY KEY,
Nume VARCHAR(30), 
Prenume VARCHAR(30),
Salariu FLOAT CHECK(Salariu>0),
Did INT FOREIGN KEY REFERENCES Departamente(Did))

CREATE TABLE Locuitori(
Lid INT PRIMARY KEY,
Nume VARCHAR(30) NOT NULL,
Prenume VARCHAR(30) NOT NULL,
Serviciu VARCHAR(30),
Adid INT FOREIGN KEY REFERENCES Adrese(Adid))


CREATE TABLE Interactiuni(
Aid INT FOREIGN KEY REFERENCES Angajati(Aid),
Lid INT FOREIGN KEY REFERENCES Locuitori(Lid),
Descriere VARCHAR(50),
CONSTRAINT pk_Interactiuni PRIMARY KEY(Aid,Lid)) 
 

CREATE TABLE Chitante(
Pid INT FOREIGN KEY REFERENCES Plati(Pid),
Lid INT FOREIGN KEY REFERENCES Locuitori(Lid),
Nr_chitanta INT NOT NULL CHECK(Nr_chitanta>0),
CONSTRAINT pk_PlataLocuitor PRIMARY KEY(Pid,Lid))


CREATE TABLE Proiecte(
Proid INT PRIMARY KEY,
Titlu VARCHAR(30) NOT NULL,
Descriere VARCHAR(50) DEFAULT 'Proiect')



CREATE TABLE Implicari(
Aid INT FOREIGN KEY REFERENCES Angajati(Aid),
Proid INT FOREIGN KEY REFERENCES Proiecte(Proid),
Procentaj FLOAT,
CONSTRAINT Pk_Implicare PRIMARY KEY(Aid,Proid))


select * from Manageri
insert into Manageri values
(5, 'Bogdan', 'Macovei'),
(6, 'Cristina', 'Dumitru'),
(7, 'Denis', 'Moldovan'),
(1, 'Andrei', 'Adam'),
(2, 'Alina', 'Popescu'),
(3, 'Gabriela', 'Filimon'),
(4, 'Valentin', 'Ionescu');

select * from Departamente
alter table Departamente	
alter column Descriere VARCHAR(500);	
alter column Nume VARCHAR(100);
insert into Departamente values
(6, 'Departament secretar', 'Asigura indeplinirea procedurilor de convocare a consiliului local'),
(1,'Departament achizitii publice', 'Elaboreaza programul anual al achizitiilor publice si stabileste gradele de prioritate in achizitionare.'),
(2, 'Departament asistenta sociala', 'Asigura solutionarea cererii de acordare a ajutorului social in termenul stabilit de legislatia in vigoare'),
(4, 'Departament registrul agricol', 'Completeaza, tine la zi si centralizeaza datele din registrul agricol.'),
(7, 'Departament cadastru funciar', 'Asigura si participa efectiv la aplicarea prevederilor legilor fondului funciar, ia masuri in conditiile legii pentru evidenta, apararea, conservarea si folosirea completa si rationala a terenurilor agricole'),
(5, 'Departament informare si relatii publice','Asigura corecta  inregistrare a  corespondentei in registrul de  intrare-iesire'),
(3, 'Departament stare civila', 'Inregistreaza actele si faptele de stare civila in registrul de nastere, casatorie si decese, inclusiv inscrierea filiatiei, divortului etc.');

select * from Adrese
insert into Adrese values
(4,	'400006', 'Clinicilor', 3, 'C4', 4, 5),
(1, '400001', 'Calea Motilor', 30, 'P1',3, 15),
(2, '400001', 'Calea Motilor', 12, 'A1' ,2, 9),
(3, '400015' , 'Bilascu Gheorghe', 24,'B3', 1, 2);




select * from Plati
insert into Plati values
(1, 123.04,'Impozit teren'),
(2, 234.5, 'Abonament parcare'),
(3, 45, 'Administratie');

select * from Angajati
insert into Angajati values
(12, 'Cosmin', 'Ilisoi', 2403.3,3),
(11,'Rares','Manghiuc',2132.3,7),
(1, 'Alexandru', 'Andronic', 2234.7, 3),
(2, 'Andreea', 'Croitor', 2041.23, 1),
(3, 'Diana', 'Muntean', 1856.54,1),
(4, 'Elena', 'Ungureanu', 2343.32,2),
(5, 'Florin', 'Manea', 1932.0,3),
(6, 'Iulian', 'Rotar', 1932.0,6),
(7, 'Vlad', 'Andrevici', 2163.0,4),
(8, 'Ionut', 'Morosan', 1923.2,3),
(9, 'Marian', 'Radulescu', 1776.9,4),
(10, 'Robert', 'Rusu', 2232.0,1);



select * from Locuitori
insert into Locuitori values
(8, 'Georgescu','Cristian', 'topolog',4),
(1, 'Minulescu', 'Andrei', 'avocat', 3),
(2, 'Cojocariu', 'Mariana',null,2),
(3,'Cojocariu', 'Mihai','profesor',2),
(4, 'Minulescu', 'Ioana', 'acovat',3),
(5,'Dranca','Alina', 'secretar',1),
(6,'Dranca', 'Liviu', 'student',1),
(7,'Dranca','Ciprian', 'pilot',1);

select * from Chitante
insert into Chitante values
(1,8,1128),
(2,3,1123),
(3,2,1124),
(2,6,1125),
(1,5,1126),
(2,7,1127);

select * from Proiecte
alter table Proiecte
alter column Descriere VARCHAR(200)
insert into Proiecte values
(1, 'Asfaltare strazi','Obtinere de fonduri pentru terminarea lucrarilor de asfaltare'),
(2, 'Sali de sport', 'Construire sali de sport unde tinerii, dar nu numai, pot practica activitati sportive'),
(3, 'Amenajare parc', 'Amenajarea unui nou parc central'),
(4, 'Revizuire tevi si canalizari', 'Cercetarea si daca este necesar, schimbarea tevilor de canalizare');


select * from Implicari
insert into Implicari values
(12,2,4.3),
(5, 2, 23.4),
(7,3,21.2),
(4,2,11.3),
(6,1,6.4),
(5,3,4.3);

--numarul angajatilor care au salariul >=2000 grupati dupa departament
select d.Did,count(a.Aid) as NumarAngajati
from Angajati a, Departamente d 
where a.Salariu>=2000.0 AND a.Did=d.Did
group by d.Did 

--toti locuitorii care locuiesc pe strada 'Calea Motilor' care au platit abonamentul de parcare
select distinct l.Nume, l.Prenume
from Locuitori l, Adrese a, Chitante c, Plati p
where l.Adid=a.Adid And a.Nume_Strada='Calea Motilor' and c.Lid=l.Lid and c.Pid=p.Pid and p.Tip='Abonament parcare'

--angajatii care s-au implicat in mai mult de un proiect
select a.Aid, count(a.Aid) NumarDeImplicari
from Angajati a, Proiecte p, Implicari i
where a.Aid=i.Aid and i.Proid=p.Proid
group by a.Aid
having count(a.Aid)>1

--managerii( si departamentul) care au subordonati mai multi angajat(>1) 
select m.Mid, count(a.Aid) 
from Manageri m, Angajati a, Departamente d
where m.Mid=d.Did and a.Did=d.Did
group by m.Mid
having count(a.Aid)>1

--toate departamentele pentru care media salariilor angajatilor sai este mai mare decat 2000
select d.Nume Departament, avg(a.Salariu) MedieSalarii
from Departamente d, Angajati a
where a.Did=d.Did
group by d.Nume
having avg(a.Salariu)>2000.0

--toate strazile pe care locuiesc persoane care nu au platit impozitul pe teren
select distinct a.Nume_Strada
from Locuitori l, Adrese a
where l.Adid=a.Adid and  not exists
(select l.Lid
 from Chitante c, Plati p
 where l.Lid=c.Lid and p.Pid=c.Pid and p.Tip like 'Impozit teren')




--angajatii care au cel mai mare procentaj de participare la realizarea unui proiect
select  p.Proid, max(i.Procentaj) 
from Angajati a, Proiecte p, Implicari i
where p.Proid=i.Proid and i.Aid=a.Aid 
group by p.Proid


--angajatii care au salariul maxim pe departamentul in care lucreaza
select d.Did, max(a.Salariu)
from Angajati a, Departamente d
where a.Did=d.Did
group by d.Did

--toti angajatii care lucreaza in departamentul 'Departament stare civila', sunt implicati in proiectul 'Sali de sport' si au numele de forma
select a.Aid, a.Nume 
from Departamente d, Angajati a, Proiecte p, Implicari i
where a.Did=d.Did and d.Nume='Departament stare civila' and a.Aid=i.Aid and i.Proid=p.Proid and p.Titlu='Sali de sport' and a.Nume like '%a'

--toti angajatii care nu sunt implicati in proiecte
select distinct a.Aid, a.Nume, a.Prenume
from Angajati a
where  not exists
(select a.Aid
 from  Implicari i, Proiecte p
 where a.Aid=i.Aid and i.Proid=p.Proid)


