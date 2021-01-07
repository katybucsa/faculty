--1

insert into Drumuri values('fewfqeg','tdg',1)
begin tran
waitfor delay '00:00:10'
update Drumuri set Denumire='Drum3' where Did=(select max(Did) from Drumuri)
commit tran

delete from Drumuri where Did=6
select * from Drumuri