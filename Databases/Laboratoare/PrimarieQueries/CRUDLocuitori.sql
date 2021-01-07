USE [Primarie]
GO
/****** Object:  StoredProcedure [dbo].[CRUDLocuitori]    Script Date: 16/12/2018 19:15:53 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[CRUDLocuitori] @NrRows INT
AS
BEGIN
	SET NOCOUNT ON
	DECLARE @k BIT
	SELECT @k= dbo.validare(@NrRows)
	IF @k=1
		PRINT 'Parametru de intrare invalid!'
	ELSE
		BEGIN
			--insert
			DECLARE @Adid INT
			SELECT TOP 1 @Adid=Adid FROM Adrese
			DECLARE @Lid INT
			SELECT @Lid=MAX(Lid) FROM Locuitori
			SET @Lid=@Lid+1
			DECLARE @id INT=@Lid
			WHILE @NrRows >0
				BEGIN
					INSERT INTO Locuitori VALUES(@Lid,'Nume','Prenume','Serviciu',@Adid)
					SET @NrRows=@NrRows-1
					SET @Lid=@Lid+1
				END
			--select
			SELECT * FROM Locuitori
			--update
			UPDATE Locuitori SET Serviciu='Programator' WHERE Adid=@Adid
			--delete 
			DELETE FROM Locuitori WHERE Lid>=@id
			
			PRINT 'Au fost executate operatiile CRUD pe tabelul Locuitori'
		END
END