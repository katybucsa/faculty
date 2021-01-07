create database DbMalluri
go 
use DbMalluri

create table Localitati(
idLocalitate int primary key identity,
denumire varchar(50),
populatie int)


create table Raioane(
idRaion int primary key identity,
tip varchar(50))

create table Malluri(
idMall int primary key identity,
denumire varchar(50),
stele int,
idLocalitate int foreign key references Localitati(idLocalitate))


create table Expozitii(
idExpozitie int primary key identity,
denumire varchar(50),
tip varchar(50),
datai date,
dataf date,
idMall int foreign key references Malluri(idMall))

create table MalluriRaioane(
idMall int foreign key references Malluri(idMall),
idRaion int foreign key references Raioane(idRaion),
nrmagazine int
constraint pk_MalluriRaioane primary key(idMall,idRaion))
go

--procedura care primeste un raion,un mall,un numar de magazine si adauga raionul mallului.
--Daca deja exista,se actualizeaza numarul de magazine cu 1.

create procedure adaugaRaion(@idRaion int,@idMall int,@nrmagazine int)
as
begin
	set nocount on
	declare @n int
	select @n=count(*) from MalluriRaioane where idMall=@idMall and idRaion=@idRaion
	if @n=0
	begin
		insert into MalluriRaioane values(@idMall,@idRaion,@nrmagazine)
		print 'Raionul a fost adaugat mallului'
	end
	else
	begin
		update MalluriRaioane set nrmagazine=nrmagazine+1 where idMall=@idMall and idRaion=@idRaion
		print 'Numarul de magazine din raionul si mallul specificat a fost actualizat'
	end
end
go

exec dbo.adaugaRaion 2,2,5
select * from Raioane
select * from Malluri
select * from MalluriRaioane
go

--functie care afiseaza numele mallurilor ce au cel putin 3 raionane si
-- o expozitie a carei denumire este data ca si parametru


create function uf_afisMalluri(@denumire varchar(50)) returns table
as 
return
	select m.denumire Mall
	from MalluriRaioane ml inner join Malluri m on ml.idMall=m.idMall
	inner join Expozitii e on m.idMall=e.idMall
	where e.denumire=@denumire
	group by m.idMall,m.denumire
	having count(ml.idRaion)>=3
go

select * from Expozitii
select * from Malluri
select * from MalluriRaioane
select * from dbo.uf_afisMalluri('expozitie1')

insert into Localitati values
('Cluj-Napoca',43452),
('Suceava',32424),
('Bucuresti',86543)
select * from Localitati

insert into Malluri values
('Iullius Mall CJ',3,1),
('Abc',4,1),
('Def',5,3),
('Iullius Mall SV',3,2)


insert into Raioane values
('electrocasnice'),
('lectura'),
('cosmetice'),
('imbracaminte')

insert into Expozitii values
('expozitie3','tablouri','2018-06-24','2018-06-30',3),
('expozitie1','masini','2019-07-4','2019-07-6',3),
('expozitie1','haine','2018-06-24','2018-06-30',1),
('expozitie2','carti','2018-08-16','2018-08-26',2)


insert into MalluriRaioane values
(3,3,1),
(3,4,6),
(3,1,2),
(1,1,5),
(1,3,2),
(1,2,4),
(3,2,5),
(2,1,3)
go
--creati un view care afiseaza mallurile unei localitati date

create view view_Malluri
as
	select m.denumire 
	from Malluri m inner join Localitati l on m.idLocalitate=l.idLocalitate
	where l.denumire='Cluj-Napoca' 
go
