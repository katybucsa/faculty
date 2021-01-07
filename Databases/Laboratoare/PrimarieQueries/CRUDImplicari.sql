CREATE PROCEDURE CRUDImplicari @NrRows INT 
AS
BEGIN
	SET NOCOUNT ON
	DECLARE @k INT
	SELECT @k= dbo.validare(@NrRows)
	IF @k=1
		PRINT 'Parametru de intrare invalid!'
	ELSE
		BEGIN
			--insert
			DECLARE @Aid INT, @Pid INT
			SELECT @Pid=MAX(Proid) FROM Proiecte
			SELECT @Aid=MAX(Aid) FROM Angajati
			WHILE @NrRows >0
				BEGIN
					INSERT INTO Implicari VALUES(@Aid,@Pid,5)
					SET @Pid=@Pid-1
					SET @Aid=@Aid-1
					SET @NrRows=@NrRows-1
				END
			--select
			SELECT * FROM Implicari
			--update
			UPDATE Implicari SET Procentaj=7 WHERE Procentaj=5
			--delete 
			DELETE FROM Implicari WHERE Aid>@Aid AND Proid>@Pid
			
			PRINT 'Au fost executate operatiile CRUD pe tabelul Implicari'
		END
END