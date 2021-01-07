ALTER VIEW CRUDview1 AS
select  p.Proid, max(i.Procentaj) ProcentajMaxim
from Angajati a, Proiecte p, Implicari i
where p.Proid=i.Proid and i.Aid=a.Aid 
group by p.Proid
go

IF EXISTS (SELECT name FROM sys.indexes
			WHERE name='N_idx_Implicari_Procentaj')
	DROP INDEX N_idx_Implicari_Procentaj ON Implicari

CREATE NONCLUSTERED INDEX N_idx_Implicari_Procentaj
	ON Implicari(Procentaj)	

select * from CRUDview1
select * from Angajati
select * from Implicari
update Implicari set Procentaj=26.4 WHERE Proid between 270 and 399
select * from Proiecte
exec addAngajati 1000
exec addProiecte 1000
exec addImplicari 1000

exec CRUDAngajati 'n','p',-1200,100
