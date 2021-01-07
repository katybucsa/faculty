/*procedura ce contine o singura tranzactie pentru toate inserarile*/
create procedure adaugaAngajatiImplicariProiecte(@nume varchar(30),@prenume varchar(30),
				 @salariu float,@procentaj float, @titlu varchar(50),@descriere varchar(100))
as
begin
	set nocount on
	begin tran
	declare @Aid int
	declare @Did int
	declare @Pid int
		begin try
			/*validare angajat*/
			if(dbo.validareAngajat(@salariu)<>0)
				raiserror('Salariu invalid!',14,1)

			select @Aid=MAX(a.Aid) from Angajati a
			select @Did=MAX(Departamente.Did) from Departamente 
			set @Aid=@Aid+1
			/*introducere in tabela Angajati*/
			insert into Angajati values(@Aid,@nume,@prenume,@salariu,@Did)
			print 'S-a inserat in tabela Angajati'

			/*validare proiect*/
			if(dbo.validareProiect(@titlu)<>0)
				raiserror('Titlu proiect invalid!',15,1)

			select @Pid=MAX(p.Proid) from Proiecte p
			set @Pid=@Pid+1

			/*introducere in tabela Proiecte*/
			insert into Proiecte values(@Pid,@titlu,@descriere)
			print 'S-a inserat in tabela Proiecte'

			/*validare Implicare*/
			if(dbo.validareImplicare(@procentaj)<>0)
				raiserror('Procentaj invalid',15,1)

			/*introducere in tabela Implicari*/
			insert into Implicari values(@Aid,@Pid,@procentaj)
			print 'S-a inserat in tabela Implicari'
			commit tran
			print 'Transaction commited'
		end try

		begin catch
			rollback tran
			print 'Transaction rollbacked'
		end catch
end
go



/*procedura in care fiecare insert presupune o noua tranzactie*/
create procedure adaugaAngajatiImplicariProiecte2(@nume varchar(30),@prenume varchar(30),
				 @salariu float,@procentaj float, @titlu varchar(50),@descriere varchar(100))
as
begin
	set nocount on
	declare @Aid int
	declare @Did int
	declare @Pid int
		begin try
			begin tran
			/*validare angajat*/
			if(dbo.validareAngajat(@salariu)<>0)
				raiserror('Salariu invalid!',14,1)

			select @Aid=MAX(a.Aid) from Angajati a
			select @Did=MAX(Departamente.Did) from Departamente 
			set @Aid=@Aid+1
			/*introducere in tabela Angajati*/
			insert into Angajati values(@Aid,@nume,@prenume,@salariu,@Did)
			print 'S-a inserat in tabela Angajati'
			commit tran		/*prima tranzactie*/
		end try
		begin catch
			rollback tran
			print 'Rollback pentru tabela Angajati'
		end catch

		begin try
			begin tran
			/*validare proiect*/
			if(dbo.validareProiect(@titlu)<>0)
				raiserror('Titlu proiect invalid!',15,1)

			select @Pid=MAX(p.Proid) from Proiecte p
			set @Pid=@Pid+1

			/*introducere in tabela Proiecte*/
			insert into Proiecte values(@Pid,@titlu,@descriere)
			print 'S-a inserat in tabela Proiecte'
			commit tran		/*a 2-a tranzactie*/
		end try
		begin catch
			rollback tran
			print 'Rollback pentru tabela Proiecte'
		end catch

		begin try
			begin tran
			/*validare Implicare*/
			if(dbo.validareImplicare(@procentaj)<>0)
				raiserror('Procentaj invalid',15,1)

			/*introducere in tabela Implicari*/
			insert into Implicari values(@Aid,@Pid,@procentaj)
			print 'S-a inserat in tabela Implicari'
			commit tran /*a 3-a tranzactie*/
		end try
		begin catch
			rollback tran
			print 'Rollback pentru tabela Implicari'
		end catch
end
go




drop procedure adaugaAngajatiImplicariProiecte
go

create function dbo.validateAngajat(@salariu float)
returns int
as 
begin
	declare @k int
	if(@salariu<0)
		set @k=1
	else
		set @k=0
	return @k
end
go

create function dbo.validareProiect(@titlu varchar(30))
returns int
as 
begin
	declare @k int
	if(LEN(@titlu)>20)
		set @k=1
	else
		set @k=0
	return @k
end
go

create function dbo.validareImplicare(@procentaj float)
returns int
as 
begin
	declare @k int
	if(@procentaj<=0 or @procentaj>100)
		set @k=1
	else
		set @k=0
	return @k
end