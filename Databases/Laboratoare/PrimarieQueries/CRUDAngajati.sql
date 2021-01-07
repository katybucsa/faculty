CREATE PROCEDURE CRUDAngajati @nume VARCHAR(30),@prenume VARCHAR(30),@salariu FLOAT, @NrRows INT
AS
BEGIN
	SET NOCOUNT ON
	DECLARE @string VARCHAR(50)
	SELECT @string= dbo.validareAngajati(@salariu,@NrRows)
	IF @string<>NULL
		PRINT @string
	ELSE
		BEGIN
			--insert
			DECLARE @Aid INT, @Did INT,@id INT
			SELECT @id=MAX(Aid) FROM Angajati
			SET @Aid=@id+1
			SELECT TOP 1 @Did=Did FROM Departamente
			WHILE @NrRows >0
				BEGIN
					INSERT INTO Angajati VALUES(@Aid,@nume,@prenume,@salariu,@Did)
					SET @NrRows=@NrRows-1
					SET @Aid=@Aid+1
				END
			--select
			SELECT * FROM Angajati
			--update
			UPDATE Angajati SET Salariu=1880 WHERE Did=@Did
			--delete 
			DELETE FROM Angajati WHERE Aid>@id
			
			PRINT 'Au fost executate operatiile CRUD pe tabelul Angajati'
		END
END