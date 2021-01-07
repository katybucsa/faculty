create database GestiuneVanzari
go
use GestiuneVanzari

create table Clienti(
idClient int primary key identity,
denumire varchar(50),
codfiscal varchar(50))

create table AgentiDeVanzare(
idAgent int primary key identity,
nume varchar(50),
prenume varchar(50))

create table Produse(
idProdus int primary key identity,
denumire varchar(50),
umasura varchar(20))

create table Facturi(
idFactura int primary key identity,
numar int,
datae date,
idClient int foreign key references Clienti(idClient),
idAgent int foreign key references AgentiDeVanzare(idAgent))


create table ProduseFacturi(
idLeg int primary key identity,
idProdus int foreign key references Produse(idProdus),
idFactura int foreign key references Facturi(idFactura),
nrprodus int,
pret float,
cantitate int)
go


--primeste o factura,un produs,numarul de ordine,pretul,
--cantitatea si adauga noul produs facturii. daca produsul exista deja
--se adauga un nou rand cu cantitatea negativa
create procedure adaugaProdus(@idFactura int,@idProdus int,@nrProdus int,@pret float,@cantitate int)
as
begin
	set nocount on
	declare @n int
	select @n=count(*) from ProduseFacturi where idProdus=@idProdus and idFactura=@idFactura
	if @n=0
		begin
			insert into ProduseFacturi values(@idProdus,@idFactura,@nrProdus,@pret,@cantitate)
			print 'Produsul a fost adaugat pe factura'
		end
	else
		begin
			insert into ProduseFacturi values(@idProdus,@idFactura,@nrProdus,@pret,-@cantitate)
			print 'Produsul exista deja pe factura si s-a facut stornare'
		end
end
go
exec dbo.adaugaProdus 1,3,3,2,5
select * from ProduseFacturi
go

--afiseaza lista facturilor ca contin produsul shaorma a 
--caror valoare e >300 lei
create view ListaFacturi
as
	select c.denumire DenumireClient,f.numar NumarulFacturii,
	f.datae DataEmiterii,sum(pf.pret*pf.cantitate) ValoareFactura
	from Clienti c inner join Facturi f on c.idClient=f.idClient 
	inner join ProduseFacturi pf on f.idFactura=pf.idFactura
	inner join Produse p on pf.idProdus=p.idProdus
	where p.denumire='shaorma'
	group by f.idFactura,c.denumire,f.numar,f.datae
	having sum(pf.pret*pf.cantitate)>300
go

select * from dbo.ListaFacturi

select * from ProduseFacturi
go

--afiseaza valoarea totala a facturilor, grupate pe lunile anului si pe agentii de vanzare
--pentru un anumit an, ordonate crescator in ordinea lunilor si a numelor agentilor


create function uf_valoareTotalaFacturi(@an int) returns table
as
return
	select month(f.datae) Luna, a.nume NumeAgent, a.prenume PrenumeAgent,sum(pf.pret*pf.cantitate) ValoareTotala
	from AgentiDeVanzare a inner join Facturi f on a.idAgent=f.idAgent
	inner join ProduseFacturi pf on f.idFactura=pf.idFactura
	where year(f.datae)=@an 
	group by month(f.datae),a.idAgent,a.nume,a.prenume
	order by month(f.datae), a.nume offset 0 rows
go

select * from dbo.uf_valoareTotalaFacturi(2019)