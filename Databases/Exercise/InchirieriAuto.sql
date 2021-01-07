create database InchirieriAuto
go
use InchirieriAuto

create table Clienti(
idClient int primary key identity,
nume varchar(50),
prenume varchar(50))

create table Angajati(
idAngajat int primary key identity,
nume varchar(50),
prenume varchar(50))

create table Marci(
idMarca int primary key identity,
denumire varchar(50))

create table AutoVehicule(
nrInmatriculare varchar(50) primary key,
tipmotor varchar(50),
idMarca int foreign key references Marci(idMarca))

create table Inchirieri(
idInchiriere int primary key identity,
idAngajat int foreign key references Angajati(idAngajat),
idClient int foreign key references Clienti(idClient),
idAutovehicul varchar(50) foreign key references Autovehicule(nrInmatriculare),
datas datetime,
dataf datetime)
go

--primeste un angajat, un autovehicul, un client, data inchirierii, data returnarii si 
--un parametru tip operatie de tip bit.bit=True=> se insereaza in Inchirieri
--altfel se actualizeaza data inch. si data returnatii

create procedure adaugasaumodifica(@idAngajat int,@nrInmatriculare varchar(50),@idClient int,@data1 datetime,@data2 datetime, @param bit)
as
begin
	if @param=1
	begin
		insert into Inchirieri values(@idAngajat,@idClient,@nrInmatriculare,@data1,@data2)
		print 'A fost inregistrata o noua inchiriere'
	end
	else
	begin
		update Inchirieri set datas=@data1, dataf=@data2 where idAngajat=@idAngajat and idClient=@idClient and @nrInmatriculare=@nrInmatriculare
		print 'Inchirierea a fost actualizata'
	end	
end
go

--afiseaza o lista a angajatilor care au inchiriat in luna curenta cel putin un autovehicul 
--ce apartine unei marci date.Angajatii vor fi ordonati alfab. si va fi afisat nr total de
--inchirieri din luna curenta pentru fiecare angajat

create view view_listaAngajati
as
	select a.nume NumeAngajat,a.prenume PrenumeAngajat,count(i.idAngajat) NrInchirieri
	from Angajati a inner join Inchirieri i on a.idAngajat=i.idAngajat
	inner join AutoVehicule aut on i.idAutovehicul=aut.nrInmatriculare
	where aut.idMarca='Range Rover' and month(i.datas)=month(getdate())
	group by a.idAngajat,a.nume,a.prenume
	order by a.nume,a.prenume offset 0 rows
go
--returneaza o lista a autovehiculelor(numar,marca,tipmotor) libere
--(neinchiriate) pentru o anumita data si ora. coloane: NumarAutovehicul,
--Marca si TipMotorizare

create function uf_Neinchiriate(@data datetime) returns table
as 
return 
	select a.nrInmatriculare NumarAutovehicul,m.denumire Marca,a.tipmotor TipMotorizare
	from Marci m inner join AutoVehicule a on m.idMarca=a.idMarca 
	left join Inchirieri i on a.nrInmatriculare=i.idAutovehicul
	where i.datas>@data or i.dataf<@data




