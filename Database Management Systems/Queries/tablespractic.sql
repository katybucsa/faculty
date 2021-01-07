-- TraficRutier
create database TraficRutier
go
use TraficRutier
go
-- drop database TraficRutier
go

if OBJECT_ID('Calatorii', 'U') IS NOT NULL
	DROP TABLE Calatorii
if OBJECT_ID('Drumuri', 'U') IS NOT NULL
	DROP TABLE Drumuri
if OBJECT_ID('Masini', 'U') IS NOT NULL
	DROP TABLE Masini
if OBJECT_ID('Soferi', 'U') IS NOT NULL
	DROP TABLE Soferi
if OBJECT_ID('EchipajePolitie', 'U') IS NOT NULL
	DROP TABLE EchipajePolitie

--
create table EchipajePolitie(
	Pid int primary key identity (1,1),
	DenumireP varchar(50), 
	TipControl varchar(50),
	NrMembrii int
)

create table Soferi(
	Sid int primary key identity(1,1),
	Nume varchar(50),
	Prenume varchar(50),
	DataNastere date,
	Gen varchar(10)
)

create table Masini(
	Mid int primary key identity(1,1), 
	NrInmatriculare varchar(50),
	Marca varchar(20),
	Model Varchar(50),
	DataInmatriculare date,
	ItpValid bit, 
	Pid int foreign key references EchipajePolitie(Pid),
	Sid int foreign key references Soferi(Sid) 
)

create table Drumuri(
	Did int primary key identity(1,1),
	Denumire VARCHAR(50), -- E20, ...
	Tip varchar(50),
	Calitate int -- 0-5  
)

create table Calatorii( -- DrumuriMasini
	Did int references Drumuri(Did),
	Mid int references Masini(Mid),
	GradAglomeratie int, -- 0-5
	ConditiiTrafic varchar(50), -- deschis traficului, lucrari, ...
	CONSTRAINT pk_Calatorii PRIMARY KEY(Did, Mid)
)

GO

INSERT INTO EchipajePolitie VALUES ('Echipaj 1', 'rovigneta', 3), ('Echipaj 2', 'limitare viteza', 4)
INSERT INTO Soferi VALUES ('Ana', 'Maria', '12/11/2019', 'f'), ('Fortz', 'Mihai', '2/2/2020', 'm')
INSERT INTO Masini VALUES ('CJ67REP', 'BMW', 'Seria 5', '4/4/2017', 1, 1, 1), ('AB89CHU', 'W', 'Polo', '2/5/2000', 1, 2, 2)
INSERT INTO Drumuri VALUES ('E80', 'european', 4), ('DN45', 'national', 3)
INSERT INTO Calatorii VALUES (1, 1, 2, 'ploaie puternica'), (1,2, 3, 'drum in lucru')
GO

select * from EchipajePolitie
select * from Soferi
select * from Masini
select * from Drumuri
select * from Calatorii

---