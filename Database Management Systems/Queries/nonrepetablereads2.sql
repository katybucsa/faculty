/*non-repetable reads 2*/
set transaction isolation level read committed
/*set transaction isolation level repeatable read--bun*/
begin tran 
select * from Drumuri 
waitfor delay '00:00:09'
select * from Drumuri
commit tran