create database Mersul_Trenurilor
go
use Mersul_Trenurilor

create table TipuriDeTren(
idTip int primary key identity,
descriere varchar(100))

create table Trenuri(
idTren int primary key identity,
nume varchar(100),
idTip int foreign key references TipuriDeTren(idTip))

create table Statii(
idStatie int primary key identity,
nume varchar(100))

create table Rute(
idRuta int primary key identity,
idTren int foreign key references Trenuri(idTren),
nume varchar(100))

create table RuteStatii(
idRuta int foreign key references Rute(idRuta),
idStatie int foreign key references Statii(idStatie),
oraS time,
oraP time,
constraint pk_RuteStatii primary key(idRuta,idStatie))

select * from rute
select * from RuteStatii
select * from Statii
select * from TipuriDeTren
select * from Trenuri
go

/*Creați o procedură stocată care primește o rută, o stație, ora sosirii, ora
plecării și adaugă noua stație rutei. Dacă stația există deja, se actualizează ora
sosirii și ora plecării. */

alter procedure adaugaStatie(@idRuta int,@idStatie int,@oraS time,@oraP time)
as
begin
	set nocount on
	declare @n int
	select @n=count(*) from RuteStatii where idRuta=@idRuta and  idStatie=@idStatie
	if @n=0
	begin
		insert into RuteStatii(idRuta,idStatie,oraS,oraP) values
		(@idRuta,@idStatie,@oraS,@oraP)
		print 'Statie adaugata cu succes'
	end
	else
	begin
		update RuteStatii set oraS=@oraS, oraP=@oraP 
		where idRuta=@idRuta and idStatie=@idStatie
		print 'Statie actualizata cu succes'
	end
end
go

select * from RuteStatii
exec adaugaStatie 1,1,'8:00','8:20'
go
-- Creați un view care afișează numele rutelor care conțin toate stațiile.

create view ruteComplete
as
	select r.nume
	from Rute r, RuteStatii rs
	where r.idRuta=rs.idRuta
	group by r.idRuta,r.nume
	having count(*)=(select count(*) from Statii);
go

select * from ruteComplete
select * from Statii
select * from RuteStatii
insert into RuteStatii values(2,1,'8:05','8:15')
go

-- Creați o funcție care afișează toate stațiile care au mai mult de un tren la un
-- anumit moment din zi.
create function uf_NrTrenuri() returns table as
return 
	select distinct Statii.idStatie,Statii.nume from Statii 
	inner join RuteStatii on RuteStatii.idStatie=Statii.idStatie
	inner join RuteStatii rs2 on RuteStatii.idStatie=rs2.idStatie and RuteStatii.idRuta<>rs2.idRuta
	where (RuteStatii.oraS>=rs2.oraS and RuteStatii.oraS<=rs2.oraP) or
			(RuteStatii.oraP>=rs2.oraS and RuteStatii.oraP<=rs2.oraP)

go
select * from dbo.uf_NrTrenuri();


select r1.idRuta,r2.idStatie
from RuteStatii r1,RuteStatii r2