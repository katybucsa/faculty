CREATE PROCEDURE CRUDProiecte @titlu varchar(30),@descriere VARCHAR(30),@NrRows INT 
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
			DECLARE @Pid INT,@id INT
			SELECT @id=MAX(Proid) FROM Proiecte
			SET @Pid=@id+1
			WHILE @NrRows >0
				BEGIN
					INSERT INTO Proiecte VALUES(@Pid,@titlu,@descriere)
					SET @Pid=@Pid+1
					SET @NrRows=@NrRows-1
				END
			--select
			SELECT * FROM Proiecte
			--update
			UPDATE Proiecte SET Titlu='Titlu modificat' WHERE Titlu=@titlu
			--delete 
			DELETE FROM Proiecte WHERE Proid>@id 
			
			PRINT 'Au fost executate operatiile CRUD pe tabelul Proiecte'
		END
END