ALTER VIEW CRUDview2 
AS
	SELECT l.Serviciu, a.Nume,a.Salariu
	FROM Angajati a INNER JOIN Interactiuni i ON a.Aid=i.Aid
	INNER JOIN Locuitori l ON l.Lid=i.Lid
	WHERE a.Salariu<1800
go

select * from CRUDview2 
select * from Angajati

	
IF EXISTS (SELECT name FROM sys.indexes
			WHERE name='N_idx_Angajati_Salariu')
	DROP INDEX N_idx_Angajati_Salariu ON Angajati

CREATE NONCLUSTERED INDEX N_idx_Angajati_Salariu
	ON Angajati(Salariu)

select * from Interactiuni
select * from Locuitori