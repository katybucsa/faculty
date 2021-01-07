create database OperatiuniBancare
go
use OperatiuniBancare

create table Clienti(
idClient int primary key identity,
nume varchar(50),
dataNasterii date)

create table Conturi(
iban varchar(100) primary key,
datac date,
sold float,
idClient int foreign key references Clienti(idClient))

create table Carduri(
numar int primary key identity,
dataelib date,
dataexp date,
codcvv int,
idCont varchar(100) foreign key references Conturi(iban))

create table Bancomate(
idBancomat int primary key identity,
adresa varchar(100),
stare bit)


create table Tranzactii(
idTranzactie int primary key identity,
tip varchar(50),
nrCard int foreign key references Carduri(numar),
suma float,
idBancomat int foreign key references Bancomate(idBancomat),
dataT datetime)
go

--primeste un cont bancar si sterge toate cardurile asociate contului. Daca exista tranzactii bancare
--cu carduri asociate contului respectiv, intai se sterg tranzactiile si apoi cardurile.

alter procedure stergeCarduri(@iban varchar(100))
as
begin
	set nocount on
	delete from Tranzactii 
	where nrCard in (select numar from Carduri where idCont=@iban)
	delete from Carduri 
	where idCont=@iban
	print 'Cardurile si tranzactiile asociate contului au fost sterse'
end
go

exec stergeCarduri 'ro3321bcr43133'
select * from Carduri
select * from Tranzactii
go

--view care afiseaza numerele cardurilor cu care s-au depus bani la 
--toate bancomatele bancii

create view view_toateCardurile
as
	select distinct c.numar  NumarCard
	from Carduri c inner join Tranzactii t on c.numar=t.nrCard 
	where t.tip='depunere'
go

select * from view_toateCardurile
go

--functie care afiseaza toti clientii(nume,data nasterii, valoare totala tranzactii) care 
--au avut o valoare totala a tranzactiilor de cel putin 20000 lei(depunere/retragere)


create function uf_valoareTotalaTranzactii() returns table
as
return 
	select c.nume NumeClient, c.dataNasterii DataNasterii,sum(t.suma) ValoareTotalaTranzactii
	from Clienti c inner join Conturi ct on c.idClient=ct.idClient 
	inner join Carduri cd on ct.iban=cd.idCont
	inner join Tranzactii t on cd.numar=t.nrCard 
	group by c.idClient,c.nume,c.dataNasterii
	having sum(t.suma)>20000
go


select * from Tranzactii
select * from Carduri
select * from Conturi
select *from Clienti