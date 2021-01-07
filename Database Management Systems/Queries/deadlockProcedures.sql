create procedure deadlockScenario1(@name varchar(50),@title varchar(50))as
begin
	begin tran 
		update Angajati set Nume=@name where Aid = 2
		insert into LogTable values('update','Angajati',CURRENT_TIMESTAMP)
		waitfor delay '00:00:10'

		update Proiecte set Titlu=@title where Proid=1
		insert into LogTable values('update','Proiecte',CURRENT_TIMESTAMP)
	commit tran
end
go


create procedure deadlockScenario2(@title varchar(50),@name varchar(50))as
begin
	begin tran 
		update Proiecte set Titlu = @title where Proid = 1
		insert into LogTable values('update','Proiecte',CURRENT_TIMESTAMP)
		waitfor delay '00:00:10'

		update Angajati set Nume = @name where Aid = 2
		insert into LogTable values('update','Angajati',CURRENT_TIMESTAMP)
	commit tran
end

select * from Angajati
select * from Proiecte
select * from LogTable
delete from LogTable where Lid>0