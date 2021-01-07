/*dirty reads 2*/
set transaction isolation level read uncommitted
 /*committed*/--bun
begin tran 
select * from Angajati
insert into LogTable values('select','Angajati',CURRENT_TIMESTAMP)
waitfor delay '00:00:10'
select * from Angajati
insert into LogTable values('select','Angajati',CURRENT_TIMESTAMP)
commit tran

select * from Angajati
select * from Proiecte

/*non-repetable reads 2*/
/*set transaction isolation level read committed*/
set transaction isolation level repeatable read--bun
begin tran 
select * from Angajati 
insert into LogTable values('select','Angajati',CURRENT_TIMESTAMP)
waitfor delay '00:00:05'
select * from Angajati
insert into LogTable values('select','Angajati',CURRENT_TIMESTAMP)
commit tran

/*phantom reads 2*/
/*set  transaction isolation level repeatable read*/
set  transaction isolation level serializable--bun
begin tran
select * from Angajati 
insert into LogTable values('select','Angajati',CURRENT_TIMESTAMP)
waitfor delay '00:00:05'
select * from Angajati
insert into LogTable values('select','Angajati',CURRENT_TIMESTAMP)
commit tran


/*deadlock 2*/
begin tran 
update Proiecte set Titlu='ABC2' where Proid=1
insert into LogTable values('update','Proiecte',CURRENT_TIMESTAMP)
waitfor delay '00:00:10'

update Angajati set Nume='Vasilescu' where Aid =2
insert into LogTable values('update','Angajati',CURRENT_TIMESTAMP)
commit tran





