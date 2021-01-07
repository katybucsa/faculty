create database CampionatTenis
go
use CampionatTenis

create table Turnee(
idTurneu int primary key identity,
loc varchar(50),
datas date,
dataf date)

create table Jucatori(
idJucator int primary key identity,
nume varchar(50),
puncte int,
venit float)

create table Arene(
idArena int primary key identity)

create table Partide(
idPartida int primary key identity,
idJucator1 int foreign key references Jucatori(idJucator),
idJucator2 int foreign key references Jucatori(idJucator),
idArena int foreign key references Arene(idArena),
idTurneu int foreign key references Turnee(idTurneu),
puncte1 int,
puncte2 int,
premiu1 float,
premiu2 float,
castigator int,
datad date,
ora time,
constraint constr1 check(idJucator1<>idJucator2),
constraint constr2 check(castigator=idJucator1 or castigator=idJucator2))
go

--primeste un turneu,doi jucatori, valorile punctelor si premiilor,castigatorul,
--arena,data si ora si adauga partida in baza de date
create procedure adaugaPartida(@idTurneu int,@idJ1 int,@idJ2 int,@puncte1 int,@puncte2 int,
@premiu1 float,@premiu2 float,@castigator int,@idArena int, @data date, @ora time)
as
begin
	set nocount on
	insert into Partide values(@idJ1,@idJ2,@idArena,@idTurneu,@puncte1,@puncte2,
	@premiu1,@premiu2,@castigator,@data,@ora)
	print 'Partida a fost adaugata'
end
go

select * from Partide

exec dbo.adaugaPartida 1,2,5,200,120,656.4,223,2,1,'2019-05-28','12:30'

--lista jucatorilor si numarul partidelor cartigate in ord. descr. dupa part. cast.

alter view partideCastigate
as
	select j.nume Castigator,count(p.castigator) NrPartideCastigate
	from Jucatori j inner join Partide p on j.idJucator=p.idJucator1 or j.idJucator=p.idJucator2
	where j.idJucator=p.castigator
	group by j.idJucator,j.nume
	order by count(p.castigator) desc offset 0 rows
go

select * from partideCastigate

delete from Partide where idJucator1=2 and idJucator2=2
go

--primeste un jucator si returneaza totalul punctelor si premiilor castigate pe durata campionatului
--cumulat cu valorile deja avute la inceputul campionatului.

alter function uf_totalCastig(@idJucator int)
returns varchar(500) as
begin 
	declare @puncte int =(select j.puncte from Jucatori j where j.idJucator=@idJucator)
	if (select sum(p.puncte1) from Partide p where p.idJucator1=@idJucator)<>null
		set @puncte= @puncte+(select sum(p.puncte1) from Partide p where p.idJucator1=@idJucator)
	if (select sum(p.puncte2) from Partide p where p.idJucator2=@idJucator)<>null
		set @puncte=@puncte + (select sum(p.puncte2) from Partide p where p.idJucator2=@idJucator)

	declare @premii float=(select j.venit from Jucatori j where j.idJucator=@idJucator)
	if (select sum(p.premiu1) from Partide p where p.idJucator1=@idJucator)<> null
		set @premii=@premii+(select sum(p.premiu1) from Partide p where p.idJucator1=@idJucator)
	if (select sum(p.premiu2) from Partide p where p.idJucator2=@idJucator)<>null
		set @premii=@premii +(select sum(p.premiu2) from Partide p where p.idJucator2=@idJucator)
	return 'Puncte: '+convert(varchar(50),@puncte)+'  , Premii: '+convert(varchar(50),@premii)
end
go

print dbo.uf_totalCastig(1)