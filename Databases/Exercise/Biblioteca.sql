create database Biblioteca
go
use Biblioteca

create table Autori(
idAutor int primary key identity,
nume varchar(50),
prenume varchar(50))

create table Librarii(
idLibrarie int primary key identity,
nume varchar(50),
adresa varchar(50))

create table Domenii(
idDomeniu int primary key identity,
descriere varchar(50))

create table Carti(
idCarte int primary key identity,
idDomeniu int foreign key references Domenii(idDomeniu),
idLibrarie int foreign key references Librarii(idLibrarie),
datac date,
titlu varchar(50))

create table AutoriCarti(
idAutor int foreign key references Autori(idAutor),
idCarte int foreign key references Carti(idCarte),
constraint pk_AutoriCarti primary key(idAutor,idCarte))

alter table Autori add prenume varchar(50)
alter table Carti add datac date
go

--procedura care primeste un nume si un prenume de autor si o carte
--si asociaza autorul la carte. Daca autorul nu exista il adauga mai
--intai la autori.Afiseaza mesaj daca autorul este deja asociat
alter procedure adaugaAutor(@nume varchar(50),@prenume varchar(50),@idCarte int)
as
begin
	set nocount on
	declare @n int
	select @n=count(*) from Autori where nume=@nume and prenume=@prenume
	if @n=0
		insert into Autori values(@nume,@prenume)
	declare @idA int
	select @idA=idAutor from Autori where nume=@nume and prenume=@prenume
	declare @k int
	select @k=count(*) from AutoriCarti where idAutor=@idA and idCarte=@idCarte
	if @k<>0
		print 'Autor asociat deja cartii'
	else
	begin
		insert into AutoriCarti values(@idA,@idCarte)
		if @n=0
			print 'Autorul a fost adaugat in tabela Autori si a fost asociat cartii specificate'
		else
			print 'Autorul a fost asociat cartii specificate'
	end
end
go


exec dbo.adaugaAutor 'Rodica','Ojog-Brasoveanu',1
go

select * from Carti
go
--view care afiseaza nr cartilor cumparate dupa anul 2010 din fiecare
--librarie. se exclud librariile in care nr cartilor cumparate
--a fost < 5. datele se vor afisa in ordine invers alfabetica a numelui librariei
alter view cartiCumparate
as
	select l.nume, count(c.idCarte) numar_carti
	from Librarii l,Carti c
	where l.idLibrarie=c.idLibrarie and year(c.datac)>2010
	group by l.idLibrarie,l.nume
	having (select count(*) from Carti where idLibrarie=l.idLibrarie)>=1
	order by l.nume desc offset 0 rows
go
select * from dbo.cartiCumparate
insert into Carti values(1,1,getdate())
select * from Carti


alter table Carti add titlu varchar(50)
go

--functie care afiseaza o lista a cartilor ce au fost scrise de un numar dat de autori
--ordonate dupa titlul cartii.
alter function uf_listaCarti(@nrautori int) returns table as
return 
	select l.nume Libraria,l.adresa Adresa,c.titlu Titlul,count(ac.idAutor) NrAutori
	from Librarii l, Carti c,AutoriCarti ac
	where c.idCarte=ac.idCarte and c.idLibrarie=l.idLibrarie
	group by c.idCarte,l.nume,l.adresa ,c.titlu 
	having COUNT(ac.idAutor)=@nrautori
	order by c.titlu offset 0 rows
go


select * from dbo.uf_listaCarti(2)
select * from Carti
select * from Librarii
select * from AutoriCarti
select * from Autori





