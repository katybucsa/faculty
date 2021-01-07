create table LogTable(
Lid int identity primary key,
OperationType varchar(50),
OperationTable varchar(50),
DateExecution datetime,
check (OperationType in('select','insert','update','delete'))
)