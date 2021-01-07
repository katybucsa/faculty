/*dirty reads 1*/
begin tran
update Angajati set Nume='Alexandrescu' where Aid=1
insert into LogTable values('update','Angajati',CURRENT_TIMESTAMP)
waitfor delay '00:00:06'
rollback transaction

select * from Angajati

/*non-repetable reads 1*/
insert into Angajati values(100,'Petre','Mihaela',4564.65,2)
insert into LogTable values('insert','Angajati',CURRENT_TIMESTAMP)
begin tran
waitfor delay '00:00:10'
update Angajati set Nume='Petrescu' where Aid=100
insert into LogTable values('update','Angajati',CURRENT_TIMESTAMP)
commit tran

delete from Angajati where Aid=100
select * from Angajati


/*phantom reads 1*/
begin tran
waitfor delay '00:00:02'
insert into Angajati values(101,'Balaci','Dan',6786.35,1)
insert into LogTable values('insert','Angajati',CURRENT_TIMESTAMP)
commit tran

delete from Angajati where Aid=101
select * from Angajati


/*deadlock 1*/
set deadlock_priority low
begin tran 
update Angajati set Nume='Vasilescu1' where Aid =2
insert into LogTable values('update','Angajati',CURRENT_TIMESTAMP)
waitfor delay '00:00:10'

update Proiecte set Titlu='ABC1' where Proid=1
insert into LogTable values('update','Proiecte',CURRENT_TIMESTAMP)
commit tran